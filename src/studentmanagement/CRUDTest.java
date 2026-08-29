package studentmanagement;

import java.util.List;

public class CRUDTest {

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        // =========================
        // DELETE STUDENT 102
        // =========================

        dao.deleteStudent(102);

        // =========================
        // READ AFTER DELETE
        // =========================

        System.out.println("\n===== AFTER DELETE =====");

        List<Student> students = dao.getAllStudents();

        for (Student s : students) {
            s.displayStudent();
        }
    }
}