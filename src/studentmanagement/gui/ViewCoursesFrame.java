package studentmanagement.gui;

import studentmanagement.Course;
import studentmanagement.CourseDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewCoursesFrame extends JFrame {

    private JTable courseTable;
    private DefaultTableModel tableModel;

    private CourseDAO courseDAO;

    public ViewCoursesFrame() {

        courseDAO = new CourseDAO();

        setTitle("View Courses");
        setSize(750, 500);
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
                new JLabel("Course Management");

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
        // TABLE
        // ==========================================

        String[] columns = {
                "Course ID",
                "Course Name",
                "Duration (Years)"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        courseTable =
                new JTable(tableModel);

        courseTable.setRowHeight(32);

        courseTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        courseTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        courseTable.getTableHeader()
                .setBackground(
                        new Color(
                                240,
                                244,
                                248
                        )
                );

        courseTable.getTableHeader()
                .setForeground(
                        new Color(
                                31,
                                41,
                                55
                        )
                );

        courseTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(courseTable);

        scrollPane.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // ==========================================
        // BUTTON PANEL
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

        JButton refreshButton =
                new JButton("Refresh");

        JButton closeButton =
                new JButton("Close");

        styleButton(refreshButton);
        styleButton(closeButton);

        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // ==========================================
        // BUTTON ACTIONS
        // ==========================================

        refreshButton.addActionListener(
                e -> loadCourses()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        // ==========================================
        // LOAD COURSES
        // ==========================================

        loadCourses();
    }

    // ==========================================
    // LOAD COURSES
    // ==========================================

    private void loadCourses() {

        tableModel.setRowCount(0);

        List<Course> courses =
                courseDAO.getAllCourses();

        for (Course course : courses) {

            tableModel.addRow(
                    new Object[]{

                            course.getCourseId(),

                            course.getCourseName(),

                            course.getDuration()
                    }
            );
        }
    }

    // ==========================================
    // BUTTON STYLE
    // ==========================================

    private void styleButton(
            JButton button
    ) {

        button.setPreferredSize(
                new Dimension(
                        120,
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

                    new ViewCoursesFrame()
                            .setVisible(true);
                }
        );
    }
}