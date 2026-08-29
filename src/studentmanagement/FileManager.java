package studentmanagement;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {

    private static final String FILE_NAME = "students.txt";

    public static void saveStudent(Student student) {

        try {

            FileWriter fileWriter =
                    new FileWriter(FILE_NAME, true);

            PrintWriter writer =
                    new PrintWriter(fileWriter);

            writer.println("Student ID: " + student.getStudentId());
            writer.println("Name: " + student.getName());
            writer.println("Age: " + student.getAge());
            writer.println("Gender: " + student.getGender());
            writer.println("Address: " + student.getAddress());
            writer.println("Phone: " + student.getPhone());
            writer.println("Email: " + student.getEmail());
            writer.println("Course: " + student.getCourse());
            writer.println("Semester: " + student.getSemester());

            writer.println("------------------------------");

            writer.close();

            System.out.println("Student saved successfully!");

        } catch (IOException e) {

            System.out.println(
                    "Error while saving student data."
            );
        }
    }
}