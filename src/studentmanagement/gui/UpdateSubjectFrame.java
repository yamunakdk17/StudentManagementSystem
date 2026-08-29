package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateSubjectFrame extends JFrame {

    private JTextField subjectIdField;
    private JTextField subjectNameField;
    private JTextField courseIdField;

    public UpdateSubjectFrame() {

        setTitle("Update Subject");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(
                new GridLayout(5, 2, 10, 10)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 25, 30
                )
        );

        // =====================================================
        // SUBJECT ID
        // =====================================================

        panel.add(new JLabel("Subject ID:"));

        subjectIdField = new JTextField();
        panel.add(subjectIdField);

        // =====================================================
        // LOAD BUTTON
        // =====================================================

        JButton loadButton =
                new JButton("Load Subject");

        panel.add(loadButton);
        panel.add(new JLabel(""));

        // =====================================================
        // SUBJECT NAME
        // =====================================================

        panel.add(new JLabel("Subject Name:"));

        subjectNameField =
                new JTextField();

        panel.add(subjectNameField);

        // =====================================================
        // COURSE ID
        // =====================================================

        panel.add(new JLabel("Course ID:"));

        courseIdField =
                new JTextField();

        panel.add(courseIdField);

        // =====================================================
        // BUTTONS
        // =====================================================

        JButton updateButton =
                new JButton("Update Subject");

        JButton clearButton =
                new JButton("Clear");

        panel.add(updateButton);
        panel.add(clearButton);

        add(panel);

        // =====================================================
        // ACTIONS
        // =====================================================

        loadButton.addActionListener(
                e -> loadSubject()
        );

        updateButton.addActionListener(
                e -> updateSubject()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    // =========================================================
    // LOAD SUBJECT
    // =========================================================

    private void loadSubject() {

        try {

            int subjectId =
                    Integer.parseInt(
                            subjectIdField
                                    .getText()
                                    .trim()
                    );

            String sql =
                    "SELECT subject_name, course_id " +
                            "FROM subjects " +
                            "WHERE subject_id = ?";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement stmt =
                            conn.prepareStatement(sql)
            ) {

                stmt.setInt(1, subjectId);

                try (ResultSet rs =
                             stmt.executeQuery()) {

                    if (rs.next()) {

                        subjectNameField.setText(
                                rs.getString(
                                        "subject_name"
                                )
                        );

                        int courseId =
                                rs.getInt(
                                        "course_id"
                                );

                        courseIdField.setText(
                                String.valueOf(courseId)
                        );

                        JOptionPane.showMessageDialog(
                                this,
                                "Subject loaded successfully!"
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "No subject found with ID "
                                        + subjectId
                        );
                    }
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Subject ID."
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
    // UPDATE SUBJECT
    // =========================================================

    private void updateSubject() {

        String subjectName =
                subjectNameField
                        .getText()
                        .trim();

        String courseIdText =
                courseIdField
                        .getText()
                        .trim();

        if (subjectName.isEmpty() ||
                courseIdText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields."
            );

            return;
        }

        try {

            int subjectId =
                    Integer.parseInt(
                            subjectIdField
                                    .getText()
                                    .trim()
                    );

            int courseId =
                    Integer.parseInt(
                            courseIdText
                    );

            String sql =
                    "UPDATE subjects SET " +
                            "subject_name = ?, " +
                            "course_id = ? " +
                            "WHERE subject_id = ?";

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

                stmt.setInt(
                        3,
                        subjectId
                );

                int rows =
                        stmt.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Subject updated successfully!"
                    );

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No subject found with ID "
                                    + subjectId
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Subject ID and Course ID " +
                            "must be numbers."
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

        subjectIdField.setText("");
        subjectNameField.setText("");
        courseIdField.setText("");
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    new UpdateSubjectFrame()
                            .setVisible(true);
                }
        );
    }
}