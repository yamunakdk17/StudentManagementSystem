package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateClassFrame extends JFrame {

    private JTextField classIdField;
    private JTextField classNameField;
    private JTextField courseIdField;

    public UpdateClassFrame() {

        setTitle("Update Class");
        setSize(500, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                5,
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

        panel.add(new JLabel("Class ID:"));

        classIdField = new JTextField();
        panel.add(classIdField);

        JButton loadButton =
                new JButton("Load Class");

        panel.add(loadButton);
        panel.add(new JLabel(""));

        panel.add(new JLabel("Class Name:"));

        classNameField =
                new JTextField();

        panel.add(classNameField);

        panel.add(new JLabel("Course ID:"));

        courseIdField =
                new JTextField();

        panel.add(courseIdField);

        JButton updateButton =
                new JButton("Update Class");

        JButton clearButton =
                new JButton("Clear");

        panel.add(updateButton);
        panel.add(clearButton);

        add(panel);

        loadButton.addActionListener(
                e -> loadClass()
        );

        updateButton.addActionListener(
                e -> updateClass()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    private void loadClass() {

        try {

            int classId =
                    Integer.parseInt(
                            classIdField
                                    .getText()
                                    .trim()
                    );

            String sql =
                    "SELECT class_name, course_id " +
                            "FROM classes " +
                            "WHERE class_id = ?";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement stmt =
                            conn.prepareStatement(sql)
            ) {

                stmt.setInt(1, classId);

                try (
                        ResultSet rs =
                                stmt.executeQuery()
                ) {

                    if (rs.next()) {

                        classNameField.setText(
                                rs.getString(
                                        "class_name"
                                )
                        );

                        courseIdField.setText(
                                String.valueOf(
                                        rs.getInt(
                                                "course_id"
                                        )
                                )
                        );

                        JOptionPane.showMessageDialog(
                                this,
                                "Class loaded successfully!"
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Class not found."
                        );
                    }
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Class ID."
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                            + e.getMessage()
            );
        }
    }

    private void updateClass() {

        try {

            int classId =
                    Integer.parseInt(
                            classIdField
                                    .getText()
                                    .trim()
                    );

            String className =
                    classNameField
                            .getText()
                            .trim();

            int courseId =
                    Integer.parseInt(
                            courseIdField
                                    .getText()
                                    .trim()
                    );

            if (className.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter Class Name."
                );

                return;
            }

            String sql =
                    "UPDATE classes SET " +
                            "class_name = ?, " +
                            "course_id = ? " +
                            "WHERE class_id = ?";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement stmt =
                            conn.prepareStatement(sql)
            ) {

                stmt.setString(1, className);
                stmt.setInt(2, courseId);
                stmt.setInt(3, classId);

                int rows =
                        stmt.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Class updated successfully!"
                    );

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Class not found."
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Class ID and Course ID must be numbers."
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

        classIdField.setText("");
        classNameField.setText("");
        courseIdField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new UpdateClassFrame()
                        .setVisible(true)
        );
    }
}