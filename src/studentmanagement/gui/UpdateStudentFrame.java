
        package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateStudentFrame extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField courseField;
    private JTextField semesterField;

    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color CARD = Color.WHITE;
    private final Color TEXT = new Color(31, 41, 55);
    private final Color SECONDARY = new Color(107, 114, 128);
    private final Color PRIMARY = new Color(59, 130, 246);

    public UpdateStudentFrame() {

        setTitle("Update Student");
        setSize(650, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(25, 30, 25, 30));

        // ==============================
        // TITLE
        // ==============================

        JLabel title = new JLabel("Update Student");

        title.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        title.setForeground(TEXT);

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        // ==============================
        // FORM CARD
        // ==============================

        JPanel card = new JPanel(
                new GridBagLayout()
        );

        card.setBackground(CARD);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 228, 233)
                        ),
                        new EmptyBorder(
                                25, 30, 25, 30
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(7, 7, 7, 7);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        // ==============================
        // FIELDS
        // ==============================

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        genderField = new JTextField();
        addressField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        courseField = new JTextField();
        semesterField = new JTextField();

        addRow(card, gbc, 0, "Student ID:", idField);
        addRow(card, gbc, 1, "Name:", nameField);
        addRow(card, gbc, 2, "Age:", ageField);
        addRow(card, gbc, 3, "Gender:", genderField);
        addRow(card, gbc, 4, "Address:", addressField);
        addRow(card, gbc, 5, "Phone:", phoneField);
        addRow(card, gbc, 6, "Email:", emailField);
        addRow(card, gbc, 7, "Course:", courseField);
        addRow(card, gbc, 8, "Semester:", semesterField);

        // ==============================
        // BUTTON PANEL
        // ==============================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                10
                        )
                );

        buttonPanel.setBackground(CARD);

        JButton loadButton =
                createButton("Load Student");

        JButton updateButton =
                createButton("Update Student");

        JButton clearButton =
                createButton("Clear");

        buttonPanel.add(loadButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);

        // ==============================
        // BUTTON ACTIONS
        // ==============================

        loadButton.addActionListener(e ->
                loadStudent()
        );

        updateButton.addActionListener(e ->
                updateStudent()
        );

        clearButton.addActionListener(e ->
                clearFields()
        );

        // ==============================
        // ADD COMPONENTS
        // ==============================

        JPanel centerPanel =
                new JPanel(new BorderLayout());

        centerPanel.setBackground(
                BACKGROUND
        );

        centerPanel.add(
                card,
                BorderLayout.CENTER
        );

        centerPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    // =========================================================
    // ADD FORM ROW
    // =========================================================

    private void addRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            JTextField field
    ) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;

        JLabel label =
                new JLabel(labelText);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(TEXT);

        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        field.setPreferredSize(
                new Dimension(
                        350,
                        38
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(210, 214, 220)
                        ),
                        BorderFactory.createEmptyBorder(
                                5, 10, 5, 10
                        )
                )
        );

        panel.add(field, gbc);
    }

    // =========================================================
    // BUTTON DESIGN
    // =========================================================

    private JButton createButton(String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY);

        button.setFocusPainted(false);
        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 18, 10, 18
                )
        );

        return button;
    }

    // =========================================================
    // LOAD STUDENT
    // =========================================================

    private void loadStudent() {

        String idText =
                idField.getText().trim();

        if (idText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Student ID.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String sql =
                "SELECT * FROM students WHERE student_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(
                    1,
                    Integer.parseInt(idText)
            );

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                nameField.setText(
                        rs.getString("name")
                );

                ageField.setText(
                        String.valueOf(
                                rs.getInt("age")
                        )
                );

                genderField.setText(
                        rs.getString("gender")
                );

                addressField.setText(
                        rs.getString("address")
                );

                phoneField.setText(
                        rs.getString("phone")
                );

                emailField.setText(
                        rs.getString("email")
                );

                courseField.setText(
                        rs.getString("course")
                );

                semesterField.setText(
                        String.valueOf(
                                rs.getInt("semester")
                        )
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Student not found.",
                        "Not Found",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading student:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // UPDATE STUDENT
    // =========================================================

    private void updateStudent() {

        String sql =
                "UPDATE students SET "
                        + "name=?, age=?, gender=?, address=?, "
                        + "phone=?, email=?, course=?, semester=? "
                        + "WHERE student_id=?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    nameField.getText()
            );

            stmt.setInt(
                    2,
                    Integer.parseInt(
                            ageField.getText()
                    )
            );

            stmt.setString(
                    3,
                    genderField.getText()
            );

            stmt.setString(
                    4,
                    addressField.getText()
            );

            stmt.setString(
                    5,
                    phoneField.getText()
            );

            stmt.setString(
                    6,
                    emailField.getText()
            );

            stmt.setString(
                    7,
                    courseField.getText()
            );

            stmt.setInt(
                    8,
                    Integer.parseInt(
                            semesterField.getText()
                    )
            );

            stmt.setInt(
                    9,
                    Integer.parseInt(
                            idField.getText()
                    )
            );

            int rows =
                    stmt.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Student not found.",
                        "Update Failed",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error updating student:\n"
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

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        genderField.setText("");
        addressField.setText("");
        phoneField.setText("");
        emailField.setText("");
        courseField.setText("");
        semesterField.setText("");

        idField.requestFocus();
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new UpdateStudentFrame()
                    .setVisible(true);

        });
    }
}
