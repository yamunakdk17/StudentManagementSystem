package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddAttendanceFrame extends JFrame {

    private JTextField studentIdField;
    private JTextField subjectIdField;
    private JTextField totalClassesField;
    private JTextField attendedClassesField;

    public AddAttendanceFrame() {

        setTitle("Add Attendance");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 30, 30, 30
                )
        );

        // Student ID
        panel.add(new JLabel("Student ID:"));

        studentIdField = new JTextField();
        panel.add(studentIdField);

        // Subject ID
        panel.add(new JLabel("Subject ID:"));

        subjectIdField = new JTextField();
        panel.add(subjectIdField);

        // Total Classes
        panel.add(new JLabel("Total Classes:"));

        totalClassesField = new JTextField();
        panel.add(totalClassesField);

        // Attended Classes
        panel.add(new JLabel("Attended Classes:"));

        attendedClassesField = new JTextField();
        panel.add(attendedClassesField);

        // Buttons
        JButton addButton = new JButton("Add Attendance");
        JButton clearButton = new JButton("Clear");

        panel.add(addButton);
        panel.add(clearButton);

        add(panel);

        // Button actions

        addButton.addActionListener(
                e -> addAttendance()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    // =====================================================
    // ADD ATTENDANCE
    // =====================================================

    private void addAttendance() {

        String studentText =
                studentIdField.getText().trim();

        String subjectText =
                subjectIdField.getText().trim();

        String totalText =
                totalClassesField.getText().trim();

        String attendedText =
                attendedClassesField.getText().trim();

        // Check empty fields

        if (studentText.isEmpty()
                || subjectText.isEmpty()
                || totalText.isEmpty()
                || attendedText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int studentId =
                    Integer.parseInt(studentText);

            int subjectId =
                    Integer.parseInt(subjectText);

            int totalClasses =
                    Integer.parseInt(totalText);

            int attendedClasses =
                    Integer.parseInt(attendedText);

            // Validation

            if (totalClasses < 0
                    || attendedClasses < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Classes cannot be negative.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (attendedClasses > totalClasses) {

                JOptionPane.showMessageDialog(
                        this,
                        "Attended classes cannot be greater than total classes.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String sql =
                    "INSERT INTO attendance " +
                            "(student_id, subject_id, total_classes, attended_classes) " +
                            "VALUES (?, ?, ?, ?)";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement stmt =
                            conn.prepareStatement(sql)
            ) {

                stmt.setInt(1, studentId);
                stmt.setInt(2, subjectId);
                stmt.setInt(3, totalClasses);
                stmt.setInt(4, attendedClasses);

                int rows =
                        stmt.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Attendance added successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    clearFields();
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student ID, Subject ID and class values must be numbers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        studentIdField.setText("");
        subjectIdField.setText("");
        totalClassesField.setText("");
        attendedClassesField.setText("");
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    AddAttendanceFrame frame =
                            new AddAttendanceFrame();

                    frame.setVisible(true);
                }
        );
    }
}