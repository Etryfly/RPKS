import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class Finder {
    public static String getLineByPos(long pos, RandomAccessFile file) throws IOException {
        file.seek(pos);
        return file.readLine();
    }

    public static AtomicLong matches = new AtomicLong(0);
    public static AtomicLong checked = new AtomicLong(-2);
    public static String resultFile = "3/res/result";


    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Invalid arguments");
            return;
        }

        int cores = Runtime.getRuntime().availableProcessors();
        RandomAccessFile file;

        try {
            file = new RandomAccessFile(args[0], "r");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }
        System.out.println("Enter count of lines before found word: ");
        Scanner scanner = new Scanner(System.in);
        long stringsBefore = scanner.nextInt();
        System.out.println("Enter count of lines after found word: ");
        long stringsAfter = scanner.nextInt();
        String word = args[1];
        File outputFile = new File(resultFile);
        try {

            ExecutorService service = Executors.newFixedThreadPool(cores);
            long start = 0;
            long fragmentSize = file.length() / cores;
            ArrayList<Future<ArrayList<FinderResult>>> arr = new ArrayList<>();
            System.out.println("Creating threads");

            for (int i = 0; i < cores; i++) {
                long fragmentEnd = getNextNewLineSymbolPos(start + fragmentSize, file);
                 arr.add(service.submit(new SearchCallable(start, fragmentEnd , args[0], word, stringsBefore)));
                start = fragmentEnd;
            }

            long length = file.length() - 1;
            while (true) {
                for (Future<ArrayList<FinderResult>> future : arr) {
                    while (!future.isDone()) {
                        System.out.println(checked.toString() + "/" + length + " (" +  checked.doubleValue()/length * 100 +"%) : " + matches + " Matches");
                    }
                }
                System.out.println(checked.toString() + "/" + length + " (" +  checked.doubleValue()/length * 100 +"%) : " + matches + " Matches");

                break;
            }

            System.out.println("Writing into output file");
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                for (Future<ArrayList<FinderResult>> future :
                        arr) {
                    for (FinderResult fr:
                            future.get()) {
                        for (Long prevStrPos : fr.previousStringsPos) {
                            writer.write(getLineByPos(prevStrPos, file) + "\n");
                        }
                        writer.write(getLineByPos(fr.position, file) + "\n");
                        for (String line : getNStringsAfter(fr.position, stringsAfter, file)) {
                            writer.write(line + "\n");
                        }
                        writer.write("-------------------------\n");
                    }
                }
                writer.flush();
            }

            service.shutdown();
        } catch (IOException e) {
            System.out.println("Can't read file");
            System.out.println(e.getMessage());
            return;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }



    public static Long getNextNewLineSymbolPos(long currPos, RandomAccessFile file) throws IOException {
        file.seek(currPos);
        try {
            file.readLine();
            return file.getFilePointer();
        } catch (EOFException e) {
            return file.length();
        }

    }
    
    private static ArrayList<String> getNStringsAfter(Long pos, Long n, RandomAccessFile file) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        file.seek(pos);
        file.readLine();
        for (int i = 0; i < n; i++) {
            String str = file.readLine();
            if (str != null)
                result.add(str);
        }
        return result;
    }
}
