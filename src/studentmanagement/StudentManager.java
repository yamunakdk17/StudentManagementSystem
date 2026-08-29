package studentmanagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {

    private ArrayList<Student> students;

    // =========================================
    // CONSTRUCTOR
    // =========================================

    public StudentManager() {

        students = new ArrayList<>();
    }

    // =========================================
    // 1. ADD STUDENT
    // =========================================

    public void addStudent(Student student) {

        for (Student existingStudent : students) {

            if (existingStudent.getStudentId()
                    == student.getStudentId()) {

                System.out.println(
                        "\nStudent ID already exists!"
                );

                return;
            }
        }

        students.add(student);

        System.out.println(
                "\nStudent added successfully!"
        );
    }

    // =========================================
    // 2. VIEW ALL STUDENTS
    // =========================================

    public void viewStudents() {

        if (students.isEmpty()) {

            System.out.println(
                    "\nNo students found."
            );

            return;
        }

        System.out.println(
                "\n========== ALL STUDENTS =========="
        );

        for (Student student : students) {

            student.displayStudent();
        }
    }

    // =========================================
    // 3. SEARCH STUDENT
    // =========================================

    public void searchStudent(int studentId) {

        for (Student student : students) {

            if (student.getStudentId() == studentId) {

                System.out.println(
                        "\n========== STUDENT FOUND =========="
                );

                student.displayStudent();

                return;
            }
        }

        System.out.println(
                "\nStudent not found."
        );
    }

    // =========================================
    // 4. UPDATE STUDENT
    // =========================================

    public void updateStudent(
            int studentId,
            String name,
            int age,
            String address,
            String phone,
            String email,
            String course,
            int semester) {

        for (Student student : students) {

            if (student.getStudentId() == studentId) {

                student.setName(name);
                student.setAge(age);
                student.setAddress(address);
                student.setPhone(phone);
                student.setEmail(email);
                student.setCourse(course);
                student.setSemester(semester);

                System.out.println(
                        "\nStudent updated successfully!"
                );

                return;
            }
        }

        System.out.println(
                "\nStudent not found."
        );
    }

    // =========================================
    // 5. DELETE STUDENT
    // =========================================

    public void deleteStudent(int studentId) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getStudentId()
                    == studentId) {

                students.remove(i);

                System.out.println(
                        "\nStudent deleted successfully!"
                );

                return;
            }
        }

        System.out.println(
                "\nStudent not found."
        );
    }

    // =========================================
    // 6. ADD / UPDATE MARKS
    // =========================================

    public void updateMarks(
            int studentId,
            double organization,
            double operatingSystem,
            double oop,
            double networking,
            double ethics) {

        for (Student student : students) {

            if (student.getStudentId() == studentId) {

                student.setMarks(
                        organization,
                        operatingSystem,
                        oop,
                        networking,
                        ethics
                );

                System.out.println(
                        "\nMarks updated successfully!"
                );

                return;
            }
        }

        System.out.println(
                "\nStudent not found."
        );
    }

    // =========================================
    // 7. SAVE ALL STUDENTS
    // =========================================

    public void saveAllStudents() {

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter("students.txt")
                    );

            for (Student student : students) {

                writer.println(
                        "Student ID: "
                                + student.getStudentId()
                );

                writer.println(
                        "Name: "
                                + student.getName()
                );

                writer.println(
                        "Age: "
                                + student.getAge()
                );

                writer.println(
                        "Gender: "
                                + student.getGender()
                );

                writer.println(
                        "Address: "
                                + student.getAddress()
                );

                writer.println(
                        "Phone: "
                                + student.getPhone()
                );

                writer.println(
                        "Email: "
                                + student.getEmail()
                );

                writer.println(
                        "Course: "
                                + student.getCourse()
                );

                writer.println(
                        "Semester: "
                                + student.getSemester()
                );

                writer.println(
                        "Organization: "
                                + student.getOrganization()
                );

                writer.println(
                        "Operating System: "
                                + student.getOperatingSystem()
                );

                writer.println(
                        "OOP: "
                                + student.getOop()
                );

                writer.println(
                        "Networking: "
                                + student.getNetworking()
                );

                writer.println(
                        "Ethics: "
                                + student.getEthics()
                );

                writer.println(
                        "Marks Added: "
                                + student.isMarksAdded()
                );

                writer.println(
                        "------------------------------"
                );
            }

            writer.close();

            System.out.println(
                    "\nAll student data saved successfully!"
            );

        } catch (IOException e) {

            System.out.println(
                    "\nError saving student data."
            );
        }
    }

    // =========================================
    // 8. LOAD STUDENTS FROM FILE
    // =========================================

    public void loadStudents() {

        File file = new File("students.txt");

        if (!file.exists()) {

            System.out.println(
                    "\nNo saved student data found."
            );

            return;
        }

        try {

            Scanner fileInput = new Scanner(file);

            while (fileInput.hasNextLine()) {

                String line =
                        fileInput.nextLine();

                if (line.startsWith("Student ID: ")) {

                    int studentId =
                            Integer.parseInt(
                                    line.substring(12)
                            );

                    String name =
                            fileInput.nextLine()
                                    .substring(6);

                    int age =
                            Integer.parseInt(
                                    fileInput.nextLine()
                                            .substring(5)
                            );

                    String gender =
                            fileInput.nextLine()
                                    .substring(8);

                    String address =
                            fileInput.nextLine()
                                    .substring(9);

                    String phone =
                            fileInput.nextLine()
                                    .substring(7);

                    String email =
                            fileInput.nextLine()
                                    .substring(7);

                    String course =
                            fileInput.nextLine()
                                    .substring(8);

                    int semester =
                            Integer.parseInt(
                                    fileInput.nextLine()
                                            .substring(10)
                            );

                    double organization =
                            Double.parseDouble(
                                    fileInput.nextLine()
                                            .substring(14)
                            );

                    double operatingSystem =
                            Double.parseDouble(
                                    fileInput.nextLine()
                                            .substring(19)
                            );

                    double oop =
                            Double.parseDouble(
                                    fileInput.nextLine()
                                            .substring(5)
                            );

                    double networking =
                            Double.parseDouble(
                                    fileInput.nextLine()
                                            .substring(12)
                            );

                    double ethics =
                            Double.parseDouble(
                                    fileInput.nextLine()
                                            .substring(8)
                            );

                    boolean marksAdded =
                            Boolean.parseBoolean(
                                    fileInput.nextLine()
                                            .substring(13)
                            );

                    Student student =
                            new Student(
                                    name,
                                    age,
                                    gender,
                                    address,
                                    phone,
                                    email,
                                    studentId,
                                    course,
                                    semester
                            );

                    if (marksAdded) {

                        student.setMarks(
                                organization,
                                operatingSystem,
                                oop,
                                networking,
                                ethics
                        );
                    }

                    students.add(student);

                    // Skip separator
                    if (fileInput.hasNextLine()) {
                        fileInput.nextLine();
                    }
                }
            }

            fileInput.close();

            System.out.println(
                    "\nStudent data loaded successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "\nError loading student data."
            );
        }
    }
}