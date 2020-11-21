import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public  class CacheHelper {
    public static void updateWriteDate(String prefix) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Cache/" + prefix + "hash"))) {


           writer.write(calculateHashSum("FIO") + " ");
           writer.write(calculateHashSum("Groups") + " ");
           writer.write(calculateHashSum("Marks") + " ");
           writer.write(calculateHashSum("Subjects") + " ");
           writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String calculateHashSum(String fileName) throws IOException {
        Process proc=Runtime.getRuntime().exec("md5sum " + "/home/etryfly/IdeaProjects/Study/5/res/" + fileName);
        BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String responce = read.readLine();
        return responce.split(" ")[0];

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
        try (BufferedReader reader = new BufferedReader(new FileReader("Cache/" + prefix + "hash"))) {
            ArrayList<String> hashes = new ArrayList<>();
            String[] str = reader.readLine().split(" ");
            hashes.addAll(Arrays.asList(str));

            return calculateHashSum("FIO").equals(hashes.get(0)) &&
                    calculateHashSum("Groups").equals(hashes.get(1)) &&
                    calculateHashSum("Marks").equals(hashes.get(2)) &&
                    calculateHashSum("Subjects").equals(hashes.get(3));

        }
        catch (FileNotFoundException e) {
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


}
