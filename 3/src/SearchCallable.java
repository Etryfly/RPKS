import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class SearchCallable implements Callable<ArrayList<Long>> {

    private long startPos;
    private long endPos;
    public ArrayList<Long> foundWordsPos;
    private RandomAccessFile file;
    private String wordForSearch;


    public SearchCallable(long startPos, long endPos, String fileName, String wordForSearch) {
        this.startPos = startPos;
        this.endPos = endPos;
        try {
            this.file = new RandomAccessFile(fileName, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.wordForSearch = wordForSearch;
        foundWordsPos = new ArrayList<>();
    }

    @Override
    public ArrayList<Long> call() {
//        long pos = startPos;
        try {
            file.seek(startPos);
            String separator = System.lineSeparator();
            int sepLen = separator.length();
            while (file.getFilePointer() < endPos) {
                long prevPos = file.getFilePointer();
                String line = file.readLine();
                if (line == null) break;
                Finder.checked.addAndGet(line.length() + sepLen);
                if (line.contains(wordForSearch)) {
                    foundWordsPos.add(prevPos);
                    Finder.matches.incrementAndGet();
                }
//                pos += line.length();/

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return foundWordsPos;
    }
}
