import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
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
                    
                case "B":
                    B();
                    break;

                case "C":
                    C();
                    break;

                default:
                    return;
            }
        }
    }

    private static void C() {
        if (CacheHelper.isCacheActual("C")) {
            CacheHelper.printCache("C");
        } else {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Cache/C"))) {

                for (String groupName : getAllGroupNames()) {

                    subjects.sort((Subject a, Subject b) -> {
                        int failA = getFailPercent(groupName, a.getId());
                        int failB = getFailPercent(groupName, b.getId());

                        if (failA > failB) return -1;
                        if (failA < failB) return 1;

                        return 0;
                    });
                    writer.write(groupName + " group\n");
                    System.out.println(groupName + " group");
                    for (int i = 0; i < subjects.size() && i < 5; i++) {
                        writer.write(subjects.get(i).getName() + " " +
                                getFailPercent(groupName, subjects.get(i).getId()) + "\n");
                        System.out.println(subjects.get(i).getName() + " " +
                                getFailPercent(groupName, subjects.get(i).getId()));
                    }
                    System.out.println();
                    writer.newLine();
                }

                writer.flush();
                CacheHelper.updateWriteDate("C");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Subject getSubjectByName(String name) {
        for (Subject s : subjects) {
            if (s.getName().equals(name)) return s;

        }
        return null;
    }

    private static int getFailPercent(String groupName, int subjectId) {
        double countOfStudents = 0;
        double countOfFail = 0;
        for (Student student : getStudentsByGroupName(groupName)) {
            countOfStudents ++;
            for (Mark mark : marks) {
                if (mark.getStudentId() == student.getId()) {
                    if (mark.getSubjectId() == subjectId) {
                        if (mark.getMark() == 2) {
                            countOfFail ++;
                        }
                    }
                }
            }
        }

        return (int) ((countOfFail / countOfStudents) * 100);
    }

    private static void B() {
        if (CacheHelper.isCacheActual("B")) {
            CacheHelper.printCache("B");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Cache/B"))) {

                ArrayList<String> groupNames = getAllGroupNames();
                groupNames.sort((String a, String b) -> {
                    int aPercentage = getLowestPercentageByGroupName(a);
                    int bPercentage = getLowestPercentageByGroupName(b);
                    if (aPercentage > bPercentage) return -1;
                    if (aPercentage < bPercentage) return 1;
                    return 0;
                });

                for (int i = 0; i < groupNames.size() && i < 5; i++) {
                    writer.write(groupNames.get(i) + " " + getCountOfLowestMarksInGroup(groupNames.get(i)) +
                            " students (" + getLowestPercentageByGroupName(groupNames.get(i)) + "%)\n");
                    System.out.println(groupNames.get(i) + " " + getCountOfLowestMarksInGroup(groupNames.get(i)) +
                            " students (" + getLowestPercentageByGroupName(groupNames.get(i)) + "%)");
                }

                writer.flush();
                CacheHelper.updateWriteDate("B");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getStudentsCount(String groupName) {
        int count = 0;
        for (Group group : groups) {
            if (group.getName().equals(groupName)) count ++;
        }
        return count;
    }

    private static int getLowestPercentageByGroupName(String groupName) {
        double countOfLowest = getCountOfLowestMarksInGroup(groupName);
        double countOfStudents = getStudentsCount(groupName);

        return (int) ((countOfLowest / countOfStudents) * 100);
    }



    private static int getCountOfLowestMarksInGroup(String groupName) {
        int count = 0;
        for (Student student : getStudentsByGroupName(groupName)) {
            for (Mark mark : marks) {
                if (mark.getStudentId() == student.getId()) {
                    if (mark.getMark() == 2) {
                        count ++;
                        break;
                    }
                }
            }

        }
        return count;
    }

    private static void A() {

        if (CacheHelper.isCacheActual("A")) {
            CacheHelper.printCache("A");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Cache/A"))) {

                for (String group : getAllGroupNames()) {
                    System.out.println("Group name : " + group);
                    writer.write("Group name : " + group + '\n');
                    int midMark = getMidGroupMark(group);
                    ArrayList<Student> resultStudents = new ArrayList<>();
                    for (Student student : getStudentsByGroupName(group)) {
                        int studentMark = getSumOfMarksByStudentId(student.getId());
                        if (studentMark > midMark) {
                            resultStudents.add(student);
                        }
                    }

                    System.out.println("Mid mark : " + midMark);
                    writer.write("Mid mark : " + midMark + '\n');

                    resultStudents.sort(Aggregator::CompareStudentsByMidMarks);
                    for (int i = 0; i < resultStudents.size() && i < 5; i++) {
                        System.out.println(resultStudents.get(i).getName() + " " +
                                getSumOfMarksByStudentId(resultStudents.get(i).getId()));
                        writer.write(resultStudents.get(i).getName() + " " +
                                getSumOfMarksByStudentId(resultStudents.get(i).getId()) + '\n');

                    }

                    System.out.println();
                    writer.newLine();
                    writer.flush();
                }
                CacheHelper.updateWriteDate("A");
            } catch (IOException e) {
                e.printStackTrace();
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
