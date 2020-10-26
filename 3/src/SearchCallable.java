import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.Callable;


public class SearchCallable implements Callable<ArrayList<FinderResult>> {

    private final long startPos;
    private final long endPos;

    private RandomAccessFile file;
    private final String wordForSearch;
    private final long stringsBefore;
    private final long stringsAfter;


    public SearchCallable(long startPos, long endPos, String fileName, String wordForSearch, long stringsBefore, long stringsAfter) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.stringsBefore = stringsBefore;
        this.stringsAfter = stringsAfter;
        try {
            this.file = new RandomAccessFile(fileName, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.wordForSearch = wordForSearch;

    }

    @Override
    public ArrayList<FinderResult> call() {
//        long pos = startPos;
        ArrayList<FinderResult> result = new ArrayList<>();
        ArrayDeque<Long> strBuffer = new ArrayDeque<>();
        try {
            file.seek(startPos);
            String separator = System.lineSeparator();
            int sepLen = separator.length();

            while (file.getFilePointer() < endPos) {


                long prevPos = file.getFilePointer();
                String line = file.readLine();
                if (line == null) break;
                if (stringsBefore != 0) {
                    if (strBuffer.size() >= stringsBefore) {
                        strBuffer.pop();
                    }
                    strBuffer.add(prevPos);
                }
                Finder.checked.addAndGet(line.length() + sepLen);
                if (line.contains(wordForSearch)) {
                    FinderResult fr = new FinderResult();
                    for (int i = 0; strBuffer.size() > 1; i++) {

                        fr.previousStringsPos.add(strBuffer.pop());
                    }

                    fr.position = prevPos;
                    //TODO add prev strings to fr
                    result.add(fr);
                    Finder.matches.incrementAndGet();
                }
//                pos += line.length();/

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
