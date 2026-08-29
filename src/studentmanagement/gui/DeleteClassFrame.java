package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteClassFrame extends JFrame {

    private JTextField classIdField;

    public DeleteClassFrame() {

        setTitle("Delete Class");
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
                new JLabel("Class ID:")
        );

        classIdField =
                new JTextField();

        panel.add(classIdField);

        JButton deleteButton =
                new JButton("Delete Class");

        JButton clearButton =
                new JButton("Clear");

        panel.add(deleteButton);
        panel.add(clearButton);

        add(panel);

        deleteButton.addActionListener(
                e -> deleteClass()
        );

        clearButton.addActionListener(
                e -> classIdField.setText("")
        );
    }

    private void deleteClass() {

        try {

            int classId =
                    Integer.parseInt(
                            classIdField
                                    .getText()
                                    .trim()
                    );

            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete Class ID "
                                    + classId
                                    + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (
                    confirmation !=
                            JOptionPane.YES_OPTION
            ) {
                return;
            }

            String sql =
                    "DELETE FROM classes " +
                            "WHERE class_id = ?";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement stmt =
                            conn.prepareStatement(sql)
            ) {

                stmt.setInt(1, classId);

                int rows =
                        stmt.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Class deleted successfully!"
                    );

                    classIdField.setText("");

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
}