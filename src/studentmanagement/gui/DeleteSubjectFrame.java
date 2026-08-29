package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteSubjectFrame extends JFrame {

    private JTextField subjectIdField;

    public DeleteSubjectFrame() {

        setTitle("Delete Subject");
        setSize(500, 300);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        JPanel panel =
                new JPanel(new GridBagLayout());

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 25, 30
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 8, 8, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // ==============================
        // TITLE
        // ==============================

        JLabel title =
                new JLabel("Delete Subject");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(title, gbc);

        // ==============================
        // SUBJECT ID LABEL
        // ==============================

        JLabel idLabel =
                new JLabel("Subject ID:");

        idLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        panel.add(idLabel, gbc);

        // ==============================
        // SUBJECT ID FIELD
        // ==============================

        subjectIdField =
                new JTextField();

        subjectIdField.setPreferredSize(
                new Dimension(200, 35)
        );

        gbc.gridx = 1;
        gbc.gridy = 1;

        panel.add(subjectIdField, gbc);

        // ==============================
        // DELETE BUTTON
        // ==============================

        JButton deleteButton =
                new JButton("Delete Subject");

        deleteButton.setPreferredSize(
                new Dimension(150, 40)
        );

        deleteButton.setFocusPainted(false);

        deleteButton.addActionListener(
                e -> deleteSubject()
        );

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        panel.add(deleteButton, gbc);

        // ==============================
        // CANCEL BUTTON
        // ==============================

        JButton cancelButton =
                new JButton("Cancel");

        cancelButton.setPreferredSize(
                new Dimension(150, 40)
        );

        cancelButton.setFocusPainted(false);

        cancelButton.addActionListener(
                e -> dispose()
        );

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        panel.add(cancelButton, gbc);

        add(panel);
    }

    // ==============================
    // DELETE SUBJECT
    // ==============================

    private void deleteSubject() {

        String idText =
                subjectIdField
                        .getText()
                        .trim();

        // Empty check

        if (idText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Subject ID.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int subjectId;

        try {

            subjectId =
                    Integer.parseInt(idText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Subject ID must be a number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Confirmation

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete Subject ID "
                                + subjectId
                                + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                confirm !=
                        JOptionPane.YES_OPTION
        ) {

            return;
        }

        // SQL

        String sql =
                "DELETE FROM subjects WHERE subject_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(
                    1,
                    subjectId
            );

            int rows =
                    stmt.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Subject deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                subjectIdField.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Subject ID not found.",
                        "Not Found",
                        JOptionPane.WARNING_MESSAGE
                );
            }

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

    // ==============================
    // MAIN
    // ==============================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    DeleteSubjectFrame frame =
                            new DeleteSubjectFrame();

                    frame.setVisible(true);
                }
        );
    }
}