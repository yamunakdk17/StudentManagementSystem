package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddResultFrame extends JFrame {

    private JTextField studentIdField;
    private JTextField totalMarksField;
    private JTextField percentageField;
    private JTextField gradeField;
    private JTextField statusField;

    public AddResultFrame() {

        setTitle("Add Result");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(
                new GridLayout(6, 2, 10, 10)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 25, 30
                )
        );

        panel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        panel.add(studentIdField);

        panel.add(new JLabel("Total Marks:"));
        totalMarksField = new JTextField();
        panel.add(totalMarksField);

        panel.add(new JLabel("Percentage:"));
        percentageField = new JTextField();
        panel.add(percentageField);

        panel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        panel.add(gradeField);

        panel.add(new JLabel("Result Status:"));
        statusField = new JTextField();
        panel.add(statusField);

        JButton addButton =
                new JButton("Add Result");

        JButton clearButton =
                new JButton("Clear");

        panel.add(addButton);
        panel.add(clearButton);

        add(panel);

        addButton.addActionListener(
                e -> addResult()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    private void addResult() {

        try {

            int studentId =
                    Integer.parseInt(
                            studentIdField.getText().trim()
                    );

            double totalMarks =
                    Double.parseDouble(
                            totalMarksField.getText().trim()
                    );

            double percentage =
                    Double.parseDouble(
                            percentageField.getText().trim()
                    );

            String grade =
                    gradeField.getText().trim();

            String status =
                    statusField.getText().trim();

            if (totalMarks < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Total marks cannot be negative."
                );

                return;
            }

            if (percentage < 0 ||
                    percentage > 100) {

                JOptionPane.showMessageDialog(
                        this,
                        "Percentage must be between 0 and 100."
                );

                return;
            }

            if (grade.isEmpty() ||
                    status.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields."
                );

                return;
            }

            String sql =
                    "INSERT INTO results " +
                            "(student_id, total_marks, percentage, grade, result_status) " +
                            "VALUES (?, ?, ?, ?, ?)";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement stmt =
                            conn.prepareStatement(sql)
            ) {

                stmt.setInt(1, studentId);
                stmt.setDouble(2, totalMarks);
                stmt.setDouble(3, percentage);
                stmt.setString(4, grade);
                stmt.setString(5, status);

                int rows =
                        stmt.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Result added successfully!"
                    );

                    clearFields();
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

    private void clearFields() {

        studentIdField.setText("");
        totalMarksField.setText("");
        percentageField.setText("");
        gradeField.setText("");
        statusField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new AddResultFrame()
                        .setVisible(true)
        );
    }
}