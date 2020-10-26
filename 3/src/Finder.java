import java.io.*;
import java.util.ArrayList;
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
        String word = args[1];
        File outputFile = new File(resultFile);
        try {

            ExecutorService service = Executors.newFixedThreadPool(cores);
            long start = 0;
            long fragmentSize = file.length() / cores;
            ArrayList<Future<ArrayList<FinderResult>>> arr = new ArrayList<>();
            System.out.println("Creating threads");
            long stringsBefore = 3;
            long stringsAfter = 5;
            for (int i = 0; i < cores; i++) {
                long fragmentEnd = getNextNewLineSymbolPos(start + fragmentSize, file);
                 arr.add(service.submit(new SearchCallable(start, fragmentEnd , args[0], word, stringsBefore, stringsAfter)));
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
//        if (currPos > file.length()) return file.length();
        try {
            file.readLine();
            return file.getFilePointer();
        } catch (EOFException e) {
            return file.length();
        }

    }
}
