package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddClassFrame extends JFrame {

    private JTextField classNameField;
    private JTextField courseIdField;

    public AddClassFrame() {

        setTitle("Add Class");
        setSize(450, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(
                new GridLayout(4, 2, 10, 10)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 30, 30, 30
                )
        );

        panel.add(new JLabel("Class Name:"));

        classNameField = new JTextField();
        panel.add(classNameField);

        panel.add(new JLabel("Course ID:"));

        courseIdField = new JTextField();
        panel.add(courseIdField);

        JButton addButton =
                new JButton("Add Class");

        JButton clearButton =
                new JButton("Clear");

        panel.add(addButton);
        panel.add(clearButton);

        add(panel);

        addButton.addActionListener(
                e -> addClass()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    private void addClass() {

        String className =
                classNameField.getText().trim();

        String courseIdText =
                courseIdField.getText().trim();

        if (className.isEmpty() ||
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
                    "INSERT INTO classes " +
                            "(class_name, course_id) " +
                            "VALUES (?, ?)";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement stmt =
                            conn.prepareStatement(sql)
            ) {

                stmt.setString(1, className);
                stmt.setInt(2, courseId);

                int rows =
                        stmt.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Class added successfully!"
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

    private void clearFields() {

        classNameField.setText("");
        courseIdField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new AddClassFrame().setVisible(true)
        );
    }
}