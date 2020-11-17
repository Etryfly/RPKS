public class Subject {
    private int id;
    private String name;

    public Subject(String row) {
        String[] str = row.split(" ");
        if (str.length != 2) throw new IllegalArgumentException();
        id = Integer.parseInt(str[0]);
        name = str[1];
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
