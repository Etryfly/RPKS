public class Group {

    private int id;
    private String name;
    private int studentId;

    public Group(String row) {


        String[] str = row.split(" ");
        if (str.length != 3) throw new IllegalArgumentException();
        id = Integer.parseInt(str[0]);
        name = str[1];
        studentId = Integer.parseInt(str[2]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
