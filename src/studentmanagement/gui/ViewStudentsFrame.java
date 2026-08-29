package studentmanagement.gui;

import studentmanagement.Student;
import studentmanagement.StudentDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewStudentsFrame extends JFrame {

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public ViewStudentsFrame() {

        setTitle("View Students");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "Student ID",
                "Name",
                "Age",
                "Gender",
                "Address",
                "Phone",
                "Email",
                "Course",
                "Semester"
        };

        tableModel = new DefaultTableModel(columns, 0);

        studentTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);

        add(scrollPane, BorderLayout.CENTER);

        loadStudents();
    }

    private void loadStudents() {

        StudentDAO dao = new StudentDAO();

        List<Student> students = dao.getAllStudents();

        for (Student student : students) {

            Object[] row = {
                    student.getStudentId(),
                    student.getName(),
                    student.getAge(),
                    student.getGender(),
                    student.getAddress(),
                    student.getPhone(),
                    student.getEmail(),
                    student.getCourse(),
                    student.getSemester()
            };

            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new ViewStudentsFrame().setVisible(true);
        });
    }
}