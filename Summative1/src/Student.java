//import java.util.List;
//
//public class Student implements Serializable {
//    private int id;
//    private String firstName;
//    private String lastName;
//    private int year;
//    private String grade;
//    private List<String> subjects;
//
//    public Student(int id, String firstName, String lastName, int year, String grade, List<String> subjects) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.year = year;
//        this.grade = grade;
//        this.subjects = subjects;
//    }
//
//    @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", year=" + year +
//                ", grade=" + grade +
//                ", subjects=" + subjects +
//                '}';
//    }
//}

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable, Comparable<Student> {
    private int id;
    private String firstName;
    private String lastName;
    private int year;
    private String score;
    private List<String> subjects;

    public Student(int id, String firstName, String lastName, int year, String score, List<String> subjects) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.score = score;
        this.subjects = new ArrayList<>(subjects);
        Collections.sort(this.subjects);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Student student = (Student) obj;
        return id == student.id && new HashSet<>(subjects).equals(new HashSet<>(student.subjects));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, score);
    }

    @Override
    public String toString() {
        String result = "Student: {\n" +
                " id: " + id + ",\n" +
                " firstName: " + firstName + ",\n" +
                " lastName: " + lastName + ",\n" +
                " year: " + year + ",\n" +
                " avgScore: " + score + ",\n" +
                " subjects: {\n";
        for (String subject : subjects) {
            result += " " + subject + ",\n";
        }
        result += "}";
        return result;
    }

    @Override
    public int compareTo(Student other) {
        return this.score.compareTo(other.score);
    }

    public static void compareRandomStudents(List<Student> studentList) {
        Random random = new Random();
        int index1 = random.nextInt(studentList.size());
        int index2 = random.nextInt(studentList.size());

        while (index1 == index2) {
            index2 = random.nextInt(studentList.size());
        }

        Student s1 = studentList.get(index1);
        Student s2 = studentList.get(index2);

        System.out.println("Student 1 Id: " + s1.id);
        System.out.println("Student 2 Id: " + s2.id);
        System.out.println("Comparison Student 1 and Student 2");
        System.out.println("Grades are equal? " + s1.equals(s2));
        System.out.println("Result: " + s1.compareTo(s2));
    }

}
