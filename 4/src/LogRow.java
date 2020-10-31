import java.util.Calendar;
import java.util.Date;

public class LogRow {
    public Date DateTime;
    public String LogLevel;
    public Type logType;
    public int ID;

    public enum Type {
        QUERY,
        RESULT
    }

    @Override
    public String toString() {
        String queryType = "";
        if (logType == Type.RESULT) queryType = "RESULT";


        return String.format("%s - %s - %s QUERY FOR ID = %d", LogsAnalyzer.dateParser.format(DateTime), LogLevel, queryType
                , ID);
    }
}