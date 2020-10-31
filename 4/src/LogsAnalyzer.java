import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogsAnalyzer {



    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Invalid arguments");
            System.exit(1);
        }
        System.out.println("Enter delta gap time in seconds");
        Scanner scanner = new Scanner(System.in);
        int time = scanner.nextInt();

        try {
            long averageDeltaTime = countAverageTime(args[0]);
            if (averageDeltaTime == 0) averageDeltaTime = 3;
            System.out.println("Average time = " + averageDeltaTime);
            writeGapLogs(args[0], "4/res/output", averageDeltaTime, time);
        } catch (IOException e) {
            System.out.println("Can't read file");
        } catch (ParseException e) {
            System.out.println("Can't parse logs");
        }
    }

    private static void writeGapLogs(String inputFile, String outputFile, long averageTime, long d) throws IOException, ParseException {
        HashMap<Integer, LogRow> logs = new HashMap<>();
        long countOfLinesInFile = getLinesCount(inputFile);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        double n = 0;
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            LogRow log = parseStringToLogRow(line);
            if (logs.containsKey(log.ID)) {
                LogRow prevLog = logs.get(log.ID);
                long deltaTimeInSeconds = (log.DateTime.getTime() - prevLog.DateTime.getTime()) / 1000;
                if (Math.abs(deltaTimeInSeconds - averageTime) >= d) {
                    writer.write(prevLog.toString() + '\n');
                    writer.write(log.toString() + '\n');
                    writer.write("----\n");
                }
                logs.remove(log.ID);
            } else {
                logs.put(log.ID, log);
            }
            n++;
            System.out.println("Writing gaps in file  " + n + "/" +
                    countOfLinesInFile + "(" + (n / countOfLinesInFile) * 100 + "%)");
        }
        writer.flush();
        writer.close();
        reader.close();
    }

    public static final SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static LogRow parseStringToLogRow(String str) throws ParseException {
        String[] splittedStr = str.split(" ");
        LogRow logRow = new LogRow();
        if (splittedStr.length != 10 && splittedStr.length != 11) throw new ParseException("Invalid line", 0);
        logRow.DateTime = dateParser.parse(splittedStr[0] + " " + splittedStr[1]);
        logRow.LogLevel = splittedStr[3];
        if (splittedStr[5].equals("QUERY")) {
            logRow.logType = LogRow.Type.QUERY;
        } else if (splittedStr[5].equals("RESULT")) {
            logRow.logType = LogRow.Type.RESULT;
        }
        logRow.ID = Integer.parseInt(splittedStr[splittedStr.length - 1]);
        return logRow;
    }

    private static long getLinesCount(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        long count = 0;
        while (reader.readLine() != null) count++;
        reader.close();
        return count;
    }

    private static long countAverageTime(String fileName) throws IOException, ParseException {
        HashMap<Integer, LogRow> logs = new HashMap<>();
        long count = 0;
        long sumDeltaTime = 0;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        double n = 0;
        long countOfLinesInFile = getLinesCount(fileName);
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            LogRow log = parseStringToLogRow(line);
            if (logs.containsKey(log.ID)) {
                LogRow prevLog = logs.get(log.ID);
                long deltaTimeInSeconds = (log.DateTime.getTime() - prevLog.DateTime.getTime()) / 1000;
                count++;
                sumDeltaTime += deltaTimeInSeconds;
                logs.remove(log.ID);
            } else {
                logs.put(log.ID, log);
            }
            n++;
            System.out.println("Counting average response time " + n + "/" +
                    countOfLinesInFile + "(" + (n / countOfLinesInFile) * 100 + "%)");
        }

        reader.close();
        return sumDeltaTime / count;


    }
}
