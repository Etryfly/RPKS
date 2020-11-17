import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mark {
    private int subjectId;
    private int studentId;
    private int mark;
    private Date date;

    private SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");

    public Mark(String row) throws ParseException {
        String[] str = row.split(" ");
        if (str.length != 4) throw new IllegalArgumentException();
        subjectId = Integer.parseInt(str[0]);
        studentId = Integer.parseInt(str[1]);
        mark = Integer.parseInt(str[2]);
        date = parser.parse(str[3]);

    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
