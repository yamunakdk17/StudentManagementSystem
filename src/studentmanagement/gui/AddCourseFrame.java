package studentmanagement.gui;

import studentmanagement.Course;
import studentmanagement.CourseDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddCourseFrame extends JFrame {

    private JTextField courseNameField;
    private JTextField durationField;

    private CourseDAO courseDAO;

    public AddCourseFrame() {

        courseDAO = new CourseDAO();

        setTitle("Add New Course");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ==========================================
        // MAIN PANEL
        // ==========================================

        JPanel mainPanel = new JPanel(
                new BorderLayout()
        );

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        // ==========================================
        // HEADER
        // ==========================================

        JPanel headerPanel = new JPanel(
                new BorderLayout()
        );

        headerPanel.setBackground(Color.WHITE);

        headerPanel.setBorder(
                new EmptyBorder(
                        20, 25, 20, 25
                )
        );

        JLabel titleLabel = new JLabel(
                "Add New Course"
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        titleLabel.setForeground(
                new Color(31, 41, 55)
        );

        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // ==========================================
        // FORM PANEL
        // ==========================================

        JPanel formPanel = new JPanel(
                new GridBagLayout()
        );

        formPanel.setBackground(
                Color.WHITE
        );

        formPanel.setBorder(
                new EmptyBorder(
                        25, 35, 25, 35
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // ==========================================
        // COURSE NAME
        // ==========================================

        JLabel courseNameLabel =
                new JLabel("Course Name:");

        courseNameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        formPanel.add(
                courseNameLabel,
                gbc
        );

        courseNameField =
                new JTextField();

        courseNameField.setPreferredSize(
                new Dimension(250, 38)
        );

        courseNameField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        formPanel.add(
                courseNameField,
                gbc
        );

        // ==========================================
        // DURATION
        // ==========================================

        JLabel durationLabel =
                new JLabel("Duration (Years):");

        durationLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        formPanel.add(
                durationLabel,
                gbc
        );

        durationField =
                new JTextField();

        durationField.setPreferredSize(
                new Dimension(250, 38)
        );

        durationField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        formPanel.add(
                durationField,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // ==========================================
        // BUTTON PANEL
        // ==========================================

        JPanel buttonPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        15
                )
        );

        buttonPanel.setBackground(
                new Color(245, 247, 250)
        );

        JButton addButton =
                new JButton("Add Course");

        JButton clearButton =
                new JButton("Clear");

        styleButton(addButton);
        styleButton(clearButton);

        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ==========================================
        // BUTTON ACTIONS
        // ==========================================

        addButton.addActionListener(
                e -> addCourse()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    // ==========================================
    // ADD COURSE
    // ==========================================

    private void addCourse() {

        String courseName =
                courseNameField.getText().trim();

        String durationText =
                durationField.getText().trim();

        // ==========================================
        // VALIDATION
        // ==========================================

        if (courseName.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter course name.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            courseNameField.requestFocus();

            return;
        }

        if (durationText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter course duration.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            durationField.requestFocus();

            return;
        }

        int duration;

        try {

            duration =
                    Integer.parseInt(durationText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Duration must be a number.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            durationField.requestFocus();

            return;
        }

        if (duration <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Duration must be greater than 0.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ==========================================
        // CREATE COURSE OBJECT
        // ==========================================

        Course course =
                new Course(
                        courseName,
                        duration
                );

        // ==========================================
        // SAVE TO DATABASE
        // ==========================================

        boolean success =
                courseDAO.addCourse(course);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Course added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to add course.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================================
    // CLEAR
    // ==========================================

    private void clearFields() {

        courseNameField.setText("");
        durationField.setText("");

        courseNameField.requestFocus();
    }

    // ==========================================
    // BUTTON STYLE
    // ==========================================

    private void styleButton(
            JButton button
    ) {

        button.setPreferredSize(
                new Dimension(
                        140,
                        40
                )
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }

    // ==========================================
    // MAIN
    // ==========================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    new AddCourseFrame()
                            .setVisible(true);
                }
        );
    }
}