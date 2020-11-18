import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Date;

public  class CacheHelper {
    public static void updateWriteDate(String prefix) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Cache/" + prefix + "times"))) {


           writer.write(getModTime("FIO") + " ");
           writer.write(getModTime("Groups") + " ");
           writer.write(getModTime("Marks") + " ");
           writer.write(getModTime("Subjects") + " ");
           writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printCache(String prefix) {
        System.out.println("*******************************************");
        System.out.println("From cache");
        System.out.println("*******************************************");
        try (BufferedReader reader = new BufferedReader(new FileReader("Cache/" + prefix))) {
            while (true) {
                String str = reader.readLine();
                if (str == null) break;
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isCacheActual(String prefix) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Cache/" + prefix + "times"))) {
            ArrayList<Long> times = new ArrayList<>();
            String[] str = reader.readLine().split(" ");
            for (String s : str) {
                times.add(Long.parseLong(s));
            }

            return getModTime("FIO") == times.get(0) &&
                    getModTime("Groups") == times.get(1) &&
                    getModTime("Marks") == times.get(2) &&
                    getModTime("Subjects") == times.get(3);

        }
        catch (FileNotFoundException e) {
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static long getModTime(String fileName) throws IOException {
        File file = new File(fileName);
        return file.lastModified();
    }
}
