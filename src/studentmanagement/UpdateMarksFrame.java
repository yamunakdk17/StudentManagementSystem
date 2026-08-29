package studentmanagement;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateMarksFrame extends JFrame {

    private JTextField studentIdField;
    private JTextField organizationField;
    private JTextField operatingSystemField;
    private JTextField oopField;
    private JTextField networkingField;
    private JTextField ethicsField;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public UpdateMarksFrame() {

        setTitle("Update Student Marks");
        setSize(520, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel panel = new JPanel(
                new GridLayout(8, 2, 10, 10)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 25, 25, 25
                )
        );

        // =====================================================
        // STUDENT ID
        // =====================================================

        panel.add(new JLabel("Student ID:"));

        studentIdField = new JTextField();
        panel.add(studentIdField);

        // =====================================================
        // LOAD BUTTON
        // =====================================================

        JButton loadButton =
                new JButton("Load Marks");

        panel.add(loadButton);
        panel.add(new JLabel(""));

        // =====================================================
        // ORGANIZATION
        // =====================================================

        panel.add(
                new JLabel("Organization:")
        );

        organizationField =
                new JTextField();

        panel.add(
                organizationField
        );

        // =====================================================
        // OPERATING SYSTEM
        // =====================================================

        panel.add(
                new JLabel("Operating System:")
        );

        operatingSystemField =
                new JTextField();

        panel.add(
                operatingSystemField
        );

        // =====================================================
        // OOP
        // =====================================================

        panel.add(
                new JLabel("OOP:")
        );

        oopField =
                new JTextField();

        panel.add(
                oopField
        );

        // =====================================================
        // NETWORKING
        // =====================================================

        panel.add(
                new JLabel("Networking:")
        );

        networkingField =
                new JTextField();

        panel.add(
                networkingField
        );

        // =====================================================
        // ETHICS
        // =====================================================

        panel.add(
                new JLabel("Ethics:")
        );

        ethicsField =
                new JTextField();

        panel.add(
                ethicsField
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JButton updateButton =
                new JButton("Update Marks");

        JButton clearButton =
                new JButton("Clear");

        panel.add(updateButton);
        panel.add(clearButton);

        // =====================================================
        // ADD PANEL TO FRAME
        // =====================================================

        add(panel);

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        loadButton.addActionListener(
                e -> loadMarks()
        );

        updateButton.addActionListener(
                e -> updateMarks()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    // =========================================================
    // LOAD MARKS
    // =========================================================

    private void loadMarks() {

        String idText =
                studentIdField.getText().trim();

        if (idText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Student ID.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int studentId =
                    Integer.parseInt(idText);

            String sql =
                    "SELECT organization, operating_system, " +
                            "oop, networking, ethics " +
                            "FROM marks " +
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

                try (
                        ResultSet rs =
                                stmt.executeQuery()
                ) {

                    if (rs.next()) {

                        organizationField.setText(
                                formatMark(
                                        rs.getDouble(
                                                "organization"
                                        )
                                )
                        );

                        operatingSystemField.setText(
                                formatMark(
                                        rs.getDouble(
                                                "operating_system"
                                        )
                                )
                        );

                        oopField.setText(
                                formatMark(
                                        rs.getDouble("oop")
                                )
                        );

                        networkingField.setText(
                                formatMark(
                                        rs.getDouble(
                                                "networking"
                                        )
                                )
                        );

                        ethicsField.setText(
                                formatMark(
                                        rs.getDouble("ethics")
                                )
                        );

                        JOptionPane.showMessageDialog(
                                this,
                                "Marks loaded successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "No marks found for Student ID: "
                                        + studentId,
                                "Not Found",
                                JOptionPane.WARNING_MESSAGE
                        );

                        clearMarkFields();
                    }
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student ID must be a valid number.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

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
    // UPDATE MARKS
    // =========================================================

    private void updateMarks() {

        try {

            // -------------------------------------------------
            // STUDENT ID
            // -------------------------------------------------

            String idText =
                    studentIdField.getText().trim();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter Student ID."
                );

                return;
            }

            int studentId =
                    Integer.parseInt(idText);

            // -------------------------------------------------
            // MARKS
            // -------------------------------------------------

            double organization =
                    Double.parseDouble(
                            organizationField
                                    .getText()
                                    .trim()
                    );

            double operatingSystem =
                    Double.parseDouble(
                            operatingSystemField
                                    .getText()
                                    .trim()
                    );

            double oop =
                    Double.parseDouble(
                            oopField
                                    .getText()
                                    .trim()
                    );

            double networking =
                    Double.parseDouble(
                            networkingField
                                    .getText()
                                    .trim()
                    );

            double ethics =
                    Double.parseDouble(
                            ethicsField
                                    .getText()
                                    .trim()
                    );

            // -------------------------------------------------
            // VALIDATE MARKS
            // -------------------------------------------------

            if (!isValidMark(organization) ||
                    !isValidMark(operatingSystem) ||
                    !isValidMark(oop) ||
                    !isValidMark(networking) ||
                    !isValidMark(ethics)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Each mark must be between 0 and 100.",
                        "Invalid Marks",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // -------------------------------------------------
            // CONFIRM UPDATE
            // -------------------------------------------------

            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to update " +
                                    "the marks for Student ID "
                                    + studentId
                                    + "?",
                            "Confirm Update",
                            JOptionPane.YES_NO_OPTION
                    );

            if (confirmation !=
                    JOptionPane.YES_OPTION) {

                return;
            }

            // -------------------------------------------------
            // SQL UPDATE
            // -------------------------------------------------

            String sql =
                    "UPDATE marks SET " +
                            "organization = ?, " +
                            "operating_system = ?, " +
                            "oop = ?, " +
                            "networking = ?, " +
                            "ethics = ? " +
                            "WHERE student_id = ?";

            try (
                    Connection conn =
                            DatabaseConnection.getConnection();

                    PreparedStatement stmt =
                            conn.prepareStatement(sql)
            ) {

                stmt.setDouble(
                        1,
                        organization
                );

                stmt.setDouble(
                        2,
                        operatingSystem
                );

                stmt.setDouble(
                        3,
                        oop
                );

                stmt.setDouble(
                        4,
                        networking
                );

                stmt.setDouble(
                        5,
                        ethics
                );

                stmt.setInt(
                        6,
                        studentId
                );

                int rows =
                        stmt.executeUpdate();

                // -------------------------------------------------
                // RESULT
                // -------------------------------------------------

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Marks updated successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No marks record found for Student ID: "
                                    + studentId,
                            "Update Failed",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers for all marks.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

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
    // VALIDATE MARK
    // =========================================================

    private boolean isValidMark(
            double mark
    ) {

        return mark >= 0 &&
                mark <= 100;
    }

    // =========================================================
    // FORMAT MARK
    // =========================================================

    private String formatMark(
            double mark
    ) {

        if (mark == (int) mark) {

            return String.valueOf(
                    (int) mark
            );
        }

        return String.valueOf(mark);
    }

    // =========================================================
    // CLEAR ALL FIELDS
    // =========================================================

    private void clearFields() {

        studentIdField.setText("");

        organizationField.setText("");

        operatingSystemField.setText("");

        oopField.setText("");

        networkingField.setText("");

        ethicsField.setText("");

        studentIdField.requestFocus();
    }

    // =========================================================
    // CLEAR ONLY MARK FIELDS
    // =========================================================

    private void clearMarkFields() {

        organizationField.setText("");

        operatingSystemField.setText("");

        oopField.setText("");

        networkingField.setText("");

        ethicsField.setText("");
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    UpdateMarksFrame frame =
                            new UpdateMarksFrame();

                    frame.setVisible(true);
                }
        );
    }
}
