public class Student {
    private int id;
    private String name;

    public Student(String row) {
        String[] str = row.split(" ");
        if (str.length != 4)
            throw new IllegalArgumentException();
        id = Integer.parseInt(str[0]);
        name = str[1] + " " + str[2] + " " + str[3];
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
}
