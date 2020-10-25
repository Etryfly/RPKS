import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
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

        if (args.length != 2) {
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
            ArrayList<Future<ArrayList<Long>>> arr = new ArrayList<>();
            System.out.println("Creating threads");
            for (int i = 0; i < cores; i++) {
                long fragmentEnd = getNextNewLineSymbolPos(start + fragmentSize, file);
                 arr.add(service.submit(new SearchCallable(start, fragmentEnd , args[0], word )));
                start = fragmentEnd;
            }

            long length = file.length() - 1;
            while (true) {
                for (Future<ArrayList<Long>> future : arr) {
                    while (!future.isDone()) {
                        System.out.println(checked.toString() + "/" + length + " (" +  checked.doubleValue()/length * 100 +"%) : " + matches + " Matches");
                    }
                }
                System.out.println(checked.toString() + "/" + length + " (" +  checked.doubleValue()/length * 100 +"%) : " + matches + " Matches");

                break;
            }

            System.out.println("Writing into output file");
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                for (Future<ArrayList<Long>> future :
                        arr) {
                    for (Long pos:
                            future.get()) {
                        writer.write(getLineByPos(pos, file) + "\n");
                    }
                }
                writer.flush();
            }

            service.shutdown();
        } catch (IOException e) {
            System.out.println("Can't read file");
            System.out.println(e.getMessage());
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
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
