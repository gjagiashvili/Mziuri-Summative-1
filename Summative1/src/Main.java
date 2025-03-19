import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputFile = "students.txt";
        String outputFile = "newstudents.txt";
        List<Student> studentList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");

                if (split.length == 6) {
                    int id = Integer.parseInt(split[0].trim());
                    String firstName = split[1].trim();
                    String lastName = split[2].trim();
                    int year = Integer.parseInt(split[3].trim());
                    String grade = split[4].trim();
                    List<String> subjects = Arrays.asList(split[5].trim().split(" "));

                    Student student = new Student(id, firstName, lastName, year, grade, subjects);
                    studentList.add(student);
                }
            }

        } catch (IOException | NumberFormatException ioe) {
            System.err.println(ioe.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            oos.writeObject(studentList);
            System.out.println("წარმატებულია");
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(outputFile))) {
            List<Student> deserializedList = (List<Student>) ois.readObject();
            System.out.println("უკვე დასერიალიზებული მონაცემები:");
            for (Student student : deserializedList) {
                System.out.println(student);
            }
            Student.compareRandomStudents(deserializedList);
        } catch (IOException | ClassNotFoundException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
