package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateResultFrame extends JFrame {

    private JTextField resultIdField;
    private JTextField studentIdField;
    private JTextField totalMarksField;
    private JTextField percentageField;
    private JTextField gradeField;
    private JTextField statusField;

    public UpdateResultFrame() {

        setTitle("Update Result");
        setSize(550, 450);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                7,
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

        // =====================================================
        // RESULT ID
        // =====================================================

        panel.add(
                new JLabel("Result ID:")
        );

        resultIdField =
                new JTextField();

        panel.add(
                resultIdField
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
        // TOTAL MARKS
        // =====================================================

        panel.add(
                new JLabel("Total Marks:")
        );

        totalMarksField =
                new JTextField();

        panel.add(
                totalMarksField
        );

        // =====================================================
        // PERCENTAGE
        // =====================================================

        panel.add(
                new JLabel("Percentage:")
        );

        percentageField =
                new JTextField();

        panel.add(
                percentageField
        );

        // =====================================================
        // GRADE
        // =====================================================

        panel.add(
                new JLabel("Grade:")
        );

        gradeField =
                new JTextField();

        panel.add(
                gradeField
        );

        // =====================================================
        // STATUS
        // =====================================================

        panel.add(
                new JLabel("Result Status:")
        );

        statusField =
                new JTextField();

        panel.add(
                statusField
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JButton updateButton =
                new JButton("Update Result");

        JButton clearButton =
                new JButton("Clear");

        panel.add(
                updateButton
        );

        panel.add(
                clearButton
        );

        add(panel);

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        updateButton.addActionListener(
                e -> updateResult()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    // =========================================================
    // UPDATE RESULT
    // =========================================================

    private void updateResult() {

        String resultIdText =
                resultIdField
                        .getText()
                        .trim();

        String studentIdText =
                studentIdField
                        .getText()
                        .trim();

        String totalMarksText =
                totalMarksField
                        .getText()
                        .trim();

        String percentageText =
                percentageField
                        .getText()
                        .trim();

        String grade =
                gradeField
                        .getText()
                        .trim();

        String status =
                statusField
                        .getText()
                        .trim();

        // =====================================================
        // EMPTY CHECK
        // =====================================================

        if (
                resultIdText.isEmpty()
                        || studentIdText.isEmpty()
                        || totalMarksText.isEmpty()
                        || percentageText.isEmpty()
                        || grade.isEmpty()
                        || status.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields."
            );

            return;
        }

        try {

            int resultId =
                    Integer.parseInt(
                            resultIdText
                    );

            int studentId =
                    Integer.parseInt(
                            studentIdText
                    );

            double totalMarks =
                    Double.parseDouble(
                            totalMarksText
                    );

            double percentage =
                    Double.parseDouble(
                            percentageText
                    );

            // =================================================
            // VALIDATION
            // =================================================

            if (totalMarks < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Total marks cannot be negative."
                );

                return;
            }

            if (
                    percentage < 0
                            || percentage > 100
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Percentage must be between 0 and 100."
                );

                return;
            }

            // =================================================
            // SQL UPDATE
            // =================================================

            String sql =
                    "UPDATE results SET " +
                            "student_id = ?, " +
                            "total_marks = ?, " +
                            "percentage = ?, " +
                            "grade = ?, " +
                            "result_status = ? " +
                            "WHERE result_id = ?";

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

                stmt.setDouble(
                        2,
                        totalMarks
                );

                stmt.setDouble(
                        3,
                        percentage
                );

                stmt.setString(
                        4,
                        grade
                );

                stmt.setString(
                        5,
                        status
                );

                stmt.setInt(
                        6,
                        resultId
                );

                int rows =
                        stmt.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Result updated successfully!"
                    );

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Result ID not found."
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

        resultIdField.setText("");
        studentIdField.setText("");
        totalMarksField.setText("");
        percentageField.setText("");
        gradeField.setText("");
        statusField.setText("");
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    UpdateResultFrame frame =
                            new UpdateResultFrame();

                    frame.setVisible(true);
                }
        );
    }
}