import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.*;

public class Aggregator {
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Group> groups = new ArrayList<>();
    private static ArrayList<Mark> marks = new ArrayList<>();
    private static ArrayList<Subject> subjects = new ArrayList<>();


    public static void main(String[] args) {
        readFromFiles();
        String str;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type A,B,C or smthng else");
        while (true) {
            switch (scanner.nextLine()) {
                case "A":
                    A();
                    break;




                default:
                    return;
            }
        }
    }

    private static void A() {
        for (String group : getAllGroupNames()) {
            System.out.println("Group name : " + group);
            int midMark = getMidGroupMark(group);
            ArrayList<Student> resultStudents = new ArrayList<>();
            for (Student student : getStudentsByGroupName(group)) {
                int studentMark = getSumOfMarksByStudentId(student.getId());
                if (studentMark > midMark) {
                    resultStudents.add(student);
                }
            }

            System.out.println("Mid mark : " + midMark);

            resultStudents.sort(Aggregator::CompareStudentsByMidMarks);
            for (int i = 0; i < resultStudents.size() && i < 5; i++) {
                System.out.println(resultStudents.get(i).getName() + " " +
                        getSumOfMarksByStudentId(resultStudents.get(i).getId()));

            }
        }
    }

    private static int CompareStudentsByMidMarks(Student a, Student b) {
        int aMarks = getSumOfMarksByStudentId(a.getId());
        int bMarks = getSumOfMarksByStudentId(b.getId());
        if (aMarks > bMarks) return -1;
        if (aMarks < bMarks) return 1;
        return 0;

    }

    private static ArrayList<String> getAllGroupNames() {
        ArrayList<String> result = new ArrayList<>();
        for (Group group : groups) {
            if (!result.contains(group.getName()))
                result.add(group.getName());
        }
        return result;
    }

    private static int getMidGroupMark(String groupName) {
        ArrayList<Student> studentsOfGroup = getStudentsByGroupName(groupName);
        int result = 0;
        for (Student student : studentsOfGroup) {
            result += getSumOfMarksByStudentId(student.getId());
        }
        return result / studentsOfGroup.size();
    }

    private static int getSumOfMarksByStudentId(int id) {
        int result = 0;
        for (Mark mark : marks) {
            if (mark.getStudentId() == id) {
                result += mark.getMark();
            }
        }
        return result;
    }

    private static ArrayList<Student> getStudentsByGroupName(String groupName) {
        ArrayList<Student> result = new ArrayList<>();
        for (Group g : groups) {
            if (g.getName().equals(groupName)) {
                result.add(getStudentById(g.getStudentId()));
            }
        }
        return result;
    }

    private static Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) return student;
        }
        return null;
    }

    public static void readFromFiles() {
        try (BufferedReader reader = new BufferedReader(new FileReader("FIO"))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                students.add(new Student(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("Groups"))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                groups.add(new Group(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("Marks"))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                marks.add(new Mark(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("Subjects"))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                subjects.add(new Subject(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
