package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAttendanceFrame extends JFrame {

    private JTextField attendanceIdField;

    public DeleteAttendanceFrame() {

        setTitle("Delete Attendance");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                10
                        )
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );

        panel.add(
                new JLabel("Attendance ID:")
        );

        attendanceIdField =
                new JTextField();

        panel.add(attendanceIdField);

        JButton deleteButton =
                new JButton("Delete Attendance");

        JButton clearButton =
                new JButton("Clear");

        panel.add(deleteButton);
        panel.add(clearButton);

        add(panel);

        deleteButton.addActionListener(
                e -> deleteAttendance()
        );

        clearButton.addActionListener(
                e -> attendanceIdField.setText("")
        );
    }

    // =========================================================
    // DELETE ATTENDANCE
    // =========================================================

    private void deleteAttendance() {

        try {

            int attendanceId =
                    Integer.parseInt(
                            attendanceIdField
                                    .getText()
                                    .trim()
                    );

            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete Attendance ID "
                                    + attendanceId
                                    + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (
                    confirmation !=
                            JOptionPane.YES_OPTION
            ) {

                return;
            }

            String sql =
                    "DELETE FROM attendance " +
                            "WHERE attendance_id = ?";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement stmt =
                            conn.prepareStatement(sql)
            ) {

                stmt.setInt(
                        1,
                        attendanceId
                );

                int rows =
                        stmt.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Attendance deleted successfully!"
                    );

                    attendanceIdField.setText("");

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
}