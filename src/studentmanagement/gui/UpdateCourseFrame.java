package studentmanagement.gui;

import studentmanagement.Course;
import studentmanagement.CourseDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UpdateCourseFrame extends JFrame {

    private JTextField courseIdField;
    private JTextField courseNameField;
    private JTextField durationField;

    private CourseDAO courseDAO;

    public UpdateCourseFrame() {

        courseDAO = new CourseDAO();

        setTitle("Update Course");
        setSize(520, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ==========================================
        // MAIN PANEL
        // ==========================================

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        // ==========================================
        // HEADER
        // ==========================================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(Color.WHITE);

        headerPanel.setBorder(
                new EmptyBorder(
                        20, 25, 20, 25
                )
        );

        JLabel titleLabel =
                new JLabel("Update Course");

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
        // FORM
        // ==========================================

        JPanel formPanel =
                new JPanel(new GridBagLayout());

        formPanel.setBackground(Color.WHITE);

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
        // COURSE ID
        // ==========================================

        JLabel idLabel =
                new JLabel("Course ID:");

        idLabel.setFont(
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
                idLabel,
                gbc
        );

        courseIdField =
                new JTextField();

        courseIdField.setPreferredSize(
                new Dimension(250, 38)
        );

        courseIdField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        formPanel.add(
                courseIdField,
                gbc
        );

        // ==========================================
        // LOAD BUTTON
        // ==========================================

        JButton loadButton =
                new JButton("Load Course");

        loadButton.setPreferredSize(
                new Dimension(
                        140,
                        38
                )
        );

        loadButton.setFocusPainted(false);

        gbc.gridx = 1;
        gbc.gridy = 1;

        formPanel.add(
                loadButton,
                gbc
        );

        // ==========================================
        // COURSE NAME
        // ==========================================

        JLabel nameLabel =
                new JLabel("Course Name:");

        nameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                nameLabel,
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
        gbc.gridy = 3;

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

        formPanel.add(
                durationField,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // ==========================================
        // BUTTONS
        // ==========================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                15
                        )
                );

        buttonPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        JButton updateButton =
                new JButton("Update Course");

        JButton clearButton =
                new JButton("Clear");

        styleButton(updateButton);
        styleButton(clearButton);

        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ==========================================
        // ACTIONS
        // ==========================================

        loadButton.addActionListener(
                e -> loadCourse()
        );

        updateButton.addActionListener(
                e -> updateCourse()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );
    }

    // ==========================================
    // LOAD COURSE
    // ==========================================

    private void loadCourse() {

        String idText =
                courseIdField.getText().trim();

        if (idText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Course ID.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int courseId;

        try {

            courseId =
                    Integer.parseInt(idText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Course ID must be a number.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Course course =
                courseDAO.getCourseById(courseId);

        if (course != null) {

            courseNameField.setText(
                    course.getCourseName()
            );

            durationField.setText(
                    String.valueOf(
                            course.getDuration()
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Course loaded successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Course not found.",
                    "Not Found",
                    JOptionPane.WARNING_MESSAGE
            );

            courseNameField.setText("");
            durationField.setText("");
        }
    }

    // ==========================================
    // UPDATE COURSE
    // ==========================================

    private void updateCourse() {

        String idText =
                courseIdField.getText().trim();

        String courseName =
                courseNameField.getText().trim();

        String durationText =
                durationField.getText().trim();

        if (idText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Course ID."
            );

            return;
        }

        if (courseName.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Course Name."
            );

            return;
        }

        if (durationText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Duration."
            );

            return;
        }

        int courseId;
        int duration;

        try {

            courseId =
                    Integer.parseInt(idText);

            duration =
                    Integer.parseInt(durationText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Course ID and Duration must be numbers.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (duration <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Duration must be greater than 0."
            );

            return;
        }

        Course course =
                new Course(
                        courseId,
                        courseName,
                        duration
                );

        boolean success =
                courseDAO.updateCourse(course);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Course updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Course not found or update failed.",
                    "Update Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================================
    // CLEAR
    // ==========================================

    private void clearFields() {

        courseIdField.setText("");
        courseNameField.setText("");
        durationField.setText("");

        courseIdField.requestFocus();
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

                    new UpdateCourseFrame()
                            .setVisible(true);
                }
        );
    }
}