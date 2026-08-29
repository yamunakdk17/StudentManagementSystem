package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateAttendanceFrame extends JFrame {

    private JTextField attendanceIdField;
    private JTextField studentIdField;
    private JTextField subjectIdField;
    private JTextField totalClassesField;
    private JTextField attendedClassesField;

    public UpdateAttendanceFrame() {

        setTitle("Update Attendance");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                6,
                                2,
                                10,
                                10
                        )
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        // Attendance ID

        panel.add(
                new JLabel("Attendance ID:")
        );

        attendanceIdField =
                new JTextField();

        panel.add(attendanceIdField);

        // Load button

        JButton loadButton =
                new JButton("Load Attendance");

        panel.add(loadButton);
        panel.add(new JLabel(""));

        // Student ID

        panel.add(
                new JLabel("Student ID:")
        );

        studentIdField =
                new JTextField();

        panel.add(studentIdField);

        // Subject ID

        panel.add(
                new JLabel("Subject ID:")
        );

        subjectIdField =
                new JTextField();

        panel.add(subjectIdField);

        // Total Classes

        panel.add(
                new JLabel("Total Classes:")
        );

        totalClassesField =
                new JTextField();

        panel.add(totalClassesField);

        // Attended Classes

        panel.add(
                new JLabel("Attended Classes:")
        );

        attendedClassesField =
                new JTextField();

        panel.add(attendedClassesField);

        // Buttons

        JButton updateButton =
                new JButton("Update Attendance");

        JButton clearButton =
                new JButton("Clear");

        panel.add(updateButton);
        panel.add(clearButton);

        add(panel);

        loadButton.addActionListener(
                e -> loadAttendance()
        );

        updateButton.addActionListener(
                e -> updateAttendance()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    // =========================================================
    // LOAD ATTENDANCE
    // =========================================================

    private void loadAttendance() {

        try {

            int attendanceId =
                    Integer.parseInt(
                            attendanceIdField
                                    .getText()
                                    .trim()
                    );

            String sql =
                    "SELECT student_id, subject_id, " +
                            "total_classes, attended_classes " +
                            "FROM attendance " +
                            "WHERE attendance_id = ?";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement stmt =
                            conn.prepareStatement(sql)
            ) {

                stmt.setInt(1, attendanceId);

                try (
                        ResultSet rs =
                                stmt.executeQuery()
                ) {

                    if (rs.next()) {

                        studentIdField.setText(
                                String.valueOf(
                                        rs.getInt(
                                                "student_id"
                                        )
                                )
                        );

                        subjectIdField.setText(
                                String.valueOf(
                                        rs.getInt(
                                                "subject_id"
                                        )
                                )
                        );

                        totalClassesField.setText(
                                String.valueOf(
                                        rs.getInt(
                                                "total_classes"
                                        )
                                )
                        );

                        attendedClassesField.setText(
                                String.valueOf(
                                        rs.getInt(
                                                "attended_classes"
                                        )
                                )
                        );

                        JOptionPane.showMessageDialog(
                                this,
                                "Attendance loaded successfully!"
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Attendance record not found."
                        );
                    }
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Attendance ID."
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                            + e.getMessage()
            );
        }
    }

    // =========================================================
    // UPDATE ATTENDANCE
    // =========================================================

    private void updateAttendance() {

        try {

            int attendanceId =
                    Integer.parseInt(
                            attendanceIdField
                                    .getText()
                                    .trim()
                    );

            int studentId =
                    Integer.parseInt(
                            studentIdField
                                    .getText()
                                    .trim()
                    );

            int subjectId =
                    Integer.parseInt(
                            subjectIdField
                                    .getText()
                                    .trim()
                    );

            int totalClasses =
                    Integer.parseInt(
                            totalClassesField
                                    .getText()
                                    .trim()
                    );

            int attendedClasses =
                    Integer.parseInt(
                            attendedClassesField
                                    .getText()
                                    .trim()
                    );

            // Validation

            if (totalClasses <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Total classes must be greater than 0."
                );

                return;
            }

            if (attendedClasses < 0 ||
                    attendedClasses > totalClasses) {

                JOptionPane.showMessageDialog(
                        this,
                        "Attended classes must be between 0 and total classes."
                );

                return;
            }

            String sql =
                    "UPDATE attendance SET " +
                            "student_id = ?, " +
                            "subject_id = ?, " +
                            "total_classes = ?, " +
                            "attended_classes = ? " +
                            "WHERE attendance_id = ?";

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
                stmt.setInt(5, attendanceId);

                int rows =
                        stmt.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Attendance updated successfully!"
                    );

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Attendance record not found."
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers."
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                            + e.getMessage()
            );
        }
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearFields() {

        attendanceIdField.setText("");
        studentIdField.setText("");
        subjectIdField.setText("");
        totalClassesField.setText("");
        attendedClassesField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    UpdateAttendanceFrame frame =
                            new UpdateAttendanceFrame();

                    frame.setVisible(true);
                }
        );
    }
}