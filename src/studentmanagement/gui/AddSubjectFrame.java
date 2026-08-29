package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddSubjectFrame extends JFrame {

    private JTextField subjectNameField;
    private JTextField courseIdField;

    public AddSubjectFrame() {

        setTitle("Add Subject");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 30, 30, 30
                )
        );

        // Subject Name

        panel.add(new JLabel("Subject Name:"));

        subjectNameField = new JTextField();
        panel.add(subjectNameField);

        // Course ID

        panel.add(new JLabel("Course ID:"));

        courseIdField = new JTextField();
        panel.add(courseIdField);

        // Add Button

        JButton addButton =
                new JButton("Add Subject");

        // Clear Button

        JButton clearButton =
                new JButton("Clear");

        panel.add(addButton);
        panel.add(clearButton);

        add(panel);

        // Button actions

        addButton.addActionListener(
                e -> addSubject()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    // =====================================================
    // ADD SUBJECT
    // =====================================================

    private void addSubject() {

        String subjectName =
                subjectNameField.getText().trim();

        String courseIdText =
                courseIdField.getText().trim();

        if (subjectName.isEmpty() ||
                courseIdText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields."
            );

            return;
        }

        try {

            int courseId =
                    Integer.parseInt(courseIdText);

            String sql =
                    "INSERT INTO subjects " +
                            "(subject_name, course_id) " +
                            "VALUES (?, ?)";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement stmt =
                            conn.prepareStatement(sql)
            ) {

                stmt.setString(
                        1,
                        subjectName
                );

                stmt.setInt(
                        2,
                        courseId
                );

                int rows =
                        stmt.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Subject added successfully!"
                    );

                    clearFields();
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Course ID must be a number."
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                            + e.getMessage()
            );
        }
    }

    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        subjectNameField.setText("");
        courseIdField.setText("");
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    new AddSubjectFrame()
                            .setVisible(true);
                }
        );
    }
}