package studentmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // ==============================
    // CREATE - ADD STUDENT
    // ==============================
    public void addStudent(Student student) {

        String sql = "INSERT INTO students " +
                "(student_id, name, age, gender, address, phone, email, course, semester) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getAge());
            stmt.setString(4, student.getGender());
            stmt.setString(5, student.getAddress());
            stmt.setString(6, student.getPhone());
            stmt.setString(7, student.getEmail());
            stmt.setString(8, student.getCourse());
            stmt.setInt(9, student.getSemester());

            stmt.executeUpdate();

            System.out.println("Student added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ==============================
    // READ - GET ALL STUDENTS
    // ==============================
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Student student = new Student(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getInt("student_id"),
                        rs.getString("course"),
                        rs.getInt("semester")
                );

                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }


    // ==============================
    // UPDATE - UPDATE STUDENT
    // ==============================
    public void updateStudent(Student student) {

        String sql = "UPDATE students SET " +
                "name=?, age=?, gender=?, address=?, phone=?, email=?, course=?, semester=? " +
                "WHERE student_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setString(3, student.getGender());
            stmt.setString(4, student.getAddress());
            stmt.setString(5, student.getPhone());
            stmt.setString(6, student.getEmail());
            stmt.setString(7, student.getCourse());
            stmt.setInt(8, student.getSemester());
            stmt.setInt(9, student.getStudentId());

            stmt.executeUpdate();

            System.out.println("Student updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ==============================
    // DELETE - DELETE STUDENT
    // ==============================
    public void deleteStudent(int studentId) {

        String sql = "DELETE FROM students WHERE student_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            stmt.executeUpdate();

            System.out.println("Student deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}