
        package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteMarksFrame extends JFrame {

    private JTextField studentIdField;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DeleteMarksFrame() {

        setTitle("Delete Student Marks");

        setSize(
                450,
                250
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        // =====================================================
        // PANEL
        // =====================================================

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

        // =====================================================
        // STUDENT ID
        // =====================================================

        panel.add(
                new JLabel("Student ID:")
        );

        studentIdField =
                new JTextField();

        panel.add(
                studentIdField
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JButton deleteButton =
                new JButton("Delete Marks");

        JButton clearButton =
                new JButton("Clear");

        panel.add(
                deleteButton
        );

        panel.add(
                clearButton
        );

        // Empty row
        panel.add(
                new JLabel("")
        );

        panel.add(
                new JLabel("")
        );

        // =====================================================
        // ADD PANEL
        // =====================================================

        add(panel);

        // =====================================================
        // DELETE BUTTON
        // =====================================================

        deleteButton.addActionListener(
                e -> deleteMarks()
        );

        // =====================================================
        // CLEAR BUTTON
        // =====================================================

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    // =========================================================
    // DELETE MARKS
    // =========================================================

    private void deleteMarks() {

        String idText =
                studentIdField.getText().trim();

        // -----------------------------------------------------
        // CHECK EMPTY ID
        // -----------------------------------------------------

        if (idText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Student ID.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            studentIdField.requestFocus();

            return;
        }

        try {

            int studentId =
                    Integer.parseInt(idText);

            // =================================================
            // CHECK WHETHER MARKS EXIST
            // =================================================

            String checkSql =
                    "SELECT COUNT(*) " +
                            "FROM marks " +
                            "WHERE student_id = ?";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement checkStmt =
                            conn.prepareStatement(checkSql)
            ) {

                checkStmt.setInt(
                        1,
                        studentId
                );

                try (
                        ResultSet rs =
                                checkStmt.executeQuery()
                ) {

                    if (rs.next()) {

                        int count =
                                rs.getInt(1);

                        if (count == 0) {

                            JOptionPane.showMessageDialog(
                                    this,
                                    "No marks found for Student ID: "
                                            + studentId,
                                    "Not Found",
                                    JOptionPane.WARNING_MESSAGE
                            );

                            return;
                        }
                    }
                }
            }

            // =================================================
            // CONFIRM DELETE
            // =================================================

            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete " +
                                    "marks for Student ID "
                                    + studentId
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

            // =================================================
            // DELETE FROM DATABASE
            // =================================================

            String sql =
                    "DELETE FROM marks " +
                            "WHERE student_id = ?";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement stmt =
                            conn.prepareStatement(sql)
            ) {

                stmt.setInt(
                        1,
                        studentId
                );

                int rows =
                        stmt.executeUpdate();

                // =================================================
                // SUCCESS
                // =================================================

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Marks deleted successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No marks were deleted.",
                            "Delete Failed",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student ID must be a valid number.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            studentIdField.requestFocus();

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

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearFields() {

        studentIdField.setText("");

        studentIdField.requestFocus();
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    DeleteMarksFrame frame =
                            new DeleteMarksFrame();

                    frame.setVisible(true);
                }
        );
    }
}
