package studentmanagement.gui;

import studentmanagement.AddMarksFrame;
import studentmanagement.UpdateMarksFrame;
import studentmanagement.ViewMarksFrame;



import studentmanagement.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MainFrame extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color SIDEBAR =
            new Color(31, 41, 55);

    private final Color SIDEBAR_DARK =
            new Color(24, 32, 45);

    private final Color BACKGROUND =
            new Color(245, 247, 250);

    private final Color CARD =
            Color.WHITE;

    private final Color TEXT =
            new Color(31, 41, 55);

    private final Color SECONDARY =
            new Color(107, 114, 128);

    private final Color PRIMARY =
            new Color(59, 130, 246);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MainFrame() {

        setTitle("Student Management System");

        setSize(
                1150,
                700
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        // Main container

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        // Sidebar

        JPanel sidebar =
                createSidebar();

        mainPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        // Right side

        JPanel rightPanel =
                new JPanel(
                        new BorderLayout()
                );

        rightPanel.setBackground(
                BACKGROUND
        );

        // Header

        JPanel header =
                createHeader();

        rightPanel.add(
                header,
                BorderLayout.NORTH
        );

        // Dashboard

        JPanel dashboard =
                createDashboard();


        JScrollPane scrollPane =
                new JScrollPane(
                        dashboard
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        rightPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                rightPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(
                        220,
                        700
                )
        );

        sidebar.setBackground(
                SIDEBAR
        );

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        sidebar.setBorder(
                new EmptyBorder(
                        25,
                        15,
                        20,
                        15
                )
        );

        // =====================================================
        // LOGO
        // =====================================================

        JLabel logo =
                new JLabel(
                        "STUDENT SYSTEM"
                );

        logo.setForeground(
                Color.WHITE
        );

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        logo.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebar.add(logo);

        // =====================================================
        // SUBTITLE
        // =====================================================

        JLabel version =
                new JLabel(
                        "Management Dashboard"
                );

        version.setForeground(
                new Color(
                        170,
                        180,
                        195
                )
        );

        version.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        version.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebar.add(version);

        sidebar.add(
                Box.createVerticalStrut(40)
        );

        // =====================================================
        // MENU TITLE
        // =====================================================

        JLabel menuTitle =
                new JLabel(
                        "MAIN MENU"
                );

        menuTitle.setForeground(
                new Color(
                        150,
                        160,
                        175
                )
        );

        menuTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );

        menuTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebar.add(menuTitle);

        sidebar.add(
                Box.createVerticalStrut(10)
        );

        // =====================================================
        // DASHBOARD
        // =====================================================

        JButton dashboardButton =
                createMenuButton(
                        "Dashboard"
                );

        sidebar.add(
                dashboardButton
        );

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        // =====================================================
        // STUDENTS
        // =====================================================

        JButton studentsButton =
                createMenuButton(
                        "Students"
                );

        studentsButton.addActionListener(
                e -> {

                    ViewStudentsFrame frame =
                            new ViewStudentsFrame();

                    frame.setVisible(true);
                }
        );

        sidebar.add(
                studentsButton
        );

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        // =====================================================
        // MARKS
        // =====================================================

        JButton marksButton =
                createMenuButton(
                        "Marks"
                );

        marksButton.addActionListener(
                e -> {

                    ViewMarksFrame frame =
                            new ViewMarksFrame();

                    frame.setVisible(true);
                }
        );

        sidebar.add(
                marksButton
        );

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        // =====================================================
        // REPORTS
        // =====================================================

        JButton reportsButton =
                createMenuButton(
                        "Reports"
                );
        reportsButton.addActionListener(e -> {

            ReportsFrame frame =
                    new ReportsFrame();

            frame.setVisible(true);
        });

        sidebar.add(
                reportsButton
        );

        sidebar.add(
                Box.createVerticalStrut(5)
        );

        // =====================================================
        // SETTINGS
        // =====================================================

        JButton settingsButton =
                createMenuButton(
                        "Settings"
                );

        sidebar.add(
                settingsButton
        );

        // Push Exit to bottom

        sidebar.add(
                Box.createVerticalGlue()
        );

        // =====================================================
        // EXIT
        // =====================================================

        JButton exitButton =
                createMenuButton(
                        "Exit"
                );

        exitButton.addActionListener(
                e -> System.exit(0)
        );

        sidebar.add(
                exitButton
        );

        return sidebar;
    }

    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private JButton createMenuButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                SIDEBAR
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        18,
                        10,
                        10
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setContentAreaFilled(true);

        button.setOpaque(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        // Hover effect

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                SIDEBAR_DARK
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                SIDEBAR
                        );
                    }
                }
        );

        return button;
    }

    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private JButton createActionButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setPreferredSize(
                new Dimension(
                        145,
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

        button.setForeground(
                TEXT
        );

        button.setBackground(
                Color.WHITE
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        214,
                                        220
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        // Hover

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                new Color(
                                        240,
                                        244,
                                        248
                                )
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                Color.WHITE
                        );
                    }
                }
        );

        return button;
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                Color.WHITE
        );

        header.setPreferredSize(
                new Dimension(
                        0,
                        75
                )
        );

        header.setBorder(
                new EmptyBorder(
                        15,
                        30,
                        15,
                        30
                )
        );

        JLabel title =
                new JLabel(
                        "Dashboard"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        title.setForeground(
                TEXT
        );

        JLabel admin =
                new JLabel(
                        "Administrator"
                );
        JButton refreshButton =
                new JButton("Refresh");

        refreshButton.setFocusPainted(false);
        refreshButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        refreshButton.addActionListener(e -> {

            dispose();

            SwingUtilities.invokeLater(() -> {

                MainFrame frame =
                        new MainFrame();

                frame.setVisible(true);
            });
        });

        admin.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        admin.setForeground(
                SECONDARY
        );

        header.add(
                title,
                BorderLayout.WEST
        );
        JPanel rightHeader =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        rightHeader.setBackground(Color.WHITE);

        rightHeader.add(refreshButton);
        rightHeader.add(admin);

        header.add(
                rightHeader,
                BorderLayout.EAST
        );

        return header;
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private JPanel createDashboard() {

        JPanel dashboard =
                new JPanel();

        dashboard.setBackground(
                BACKGROUND
        );

        dashboard.setLayout(
                new BoxLayout(
                        dashboard,
                        BoxLayout.Y_AXIS
                )
        );

        dashboard.setBorder(
                new EmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );

        // =====================================================
        // WELCOME
        // =====================================================

        JLabel welcome =
                new JLabel(
                        "Welcome back, Admin"
                );

        welcome.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        welcome.setForeground(
                TEXT
        );

        welcome.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        dashboard.add(
                welcome
        );

        dashboard.add(
                Box.createVerticalStrut(5)
        );

        JLabel description =
                new JLabel(
                        "Manage students and academic records from one place."
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        description.setForeground(
                SECONDARY
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        dashboard.add(
                description
        );

        dashboard.add(
                Box.createVerticalStrut(25)
        );

        // =====================================================
        // STAT CARDS
        // =====================================================

        JPanel stats =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        stats.setBackground(
                BACKGROUND
        );

        stats.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        110
                )
        );

        stats.add(
                createStatCard(
                        "STUDENTS",
                        String.valueOf(
                                getStudentCount()
                        ),
                        "Total Students"
                )
        );

        stats.add(
                createStatCard(
                        "MARKS",
                        String.valueOf(
                                getMarksCount()
                        ),
                        "Academic Records"
                )
        );

        stats.add(
                createStatCard(
                        "DATABASE",
                        getDatabaseStatus(),
                        "MySQL Connection"
                )
        );

        dashboard.add(
                stats
        );

        dashboard.add(
                Box.createVerticalStrut(25)
        );

        // =====================================================
        // STUDENT MANAGEMENT
        // =====================================================

        JLabel studentTitle =
                new JLabel(
                        "Student Management"
                );

        studentTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        studentTitle.setForeground(
                TEXT
        );

        studentTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        dashboard.add(
                studentTitle
        );

        dashboard.add(
                Box.createVerticalStrut(10)
        );
        dashboard.add(
                createStudentCard()
        );

        dashboard.add(
                Box.createVerticalStrut(25)
        );


// =========================================================



// =====================================================
// COURSE MANAGEMENT
// =====================================================
// =====================================================
// COURSE MANAGEMENT
// =====================================================

        JLabel courseManagementTitle =
                new JLabel("Course Management");

        courseManagementTitle.setFont(                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        courseManagementTitle.setForeground(TEXT);


        courseManagementTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

// ADD COURSE TITLE
        dashboard.add(courseManagementTitle);
        dashboard.add(
                Box.createVerticalStrut(10)
        );

// COURSE BUTTONS
        dashboard.add(
                createCourseCard()
        );

        dashboard.add(
                Box.createVerticalStrut(25)
        );

        // =====================================================

// SUBJECT MANAGEMENT
// =====================================================

        JLabel subjectTitle =
                new JLabel("Subject Management");

        subjectTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        subjectTitle.setForeground(TEXT);

        subjectTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        dashboard.add(subjectTitle);

        dashboard.add(
                Box.createVerticalStrut(10)
        );

        dashboard.add(
                createSubjectCard()
        );

        dashboard.add(
                Box.createVerticalStrut(25)
        );




        // =====================================================
        // MARKS MANAGEMENT
        // =====================================================

        JLabel marksTitle =
                new JLabel(
                        "Marks Management"
                );

        marksTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        marksTitle.setForeground(
                TEXT
        );

        marksTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        dashboard.add(
                marksTitle
        );

        dashboard.add(
                Box.createVerticalStrut(10)
        );

        dashboard.add(
                createMarksCard()
        );

        dashboard.add(
                Box.createVerticalStrut(25)
        );
        // =====================================================
// =====================================================
// ATTENDANCE MANAGEMENT
// =====================================================

        JLabel attendanceTitle =
                new JLabel("Attendance Management");

        attendanceTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        attendanceTitle.setForeground(TEXT);

        attendanceTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        dashboard.add(attendanceTitle);

        dashboard.add(
                Box.createVerticalStrut(10)
        );

        dashboard.add(
                createAttendanceCard()
        );

        dashboard.add(
                Box.createVerticalStrut(25)
        );


// =====================================================
// RESULTS MANAGEMENT
// =====================================================

        JLabel resultTitle =
                new JLabel("Results Management");

        resultTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        resultTitle.setForeground(TEXT);

        resultTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        dashboard.add(resultTitle);

        dashboard.add(
                Box.createVerticalStrut(10)
        );

        dashboard.add(
                createResultCard()
        );

        dashboard.add(
                Box.createVerticalStrut(25)
        );


// =====================================================
// RECENT STUDENTS
// =====================================================

        JLabel recentTitle =
                new JLabel("Recent Students");

        recentTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        recentTitle.setForeground(TEXT);

        recentTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        dashboard.add(recentTitle);

        dashboard.add(
                Box.createVerticalStrut(10)
        );

        dashboard.add(
                createRecentStudentsTable()
        );

        return dashboard;
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private JPanel createStatCard(
            String title,
            String value,
            String description
    ) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBackground(
                CARD
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        233
                                )
                        ),
                        new EmptyBorder(
                                15,
                                20,
                                15,
                                20
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        titleLabel.setForeground(
                PRIMARY
        );

        JLabel valueLabel =
                new JLabel(
                        value
                );

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        valueLabel.setForeground(
                TEXT
        );

        JLabel descriptionLabel =
                new JLabel(
                        description
                );

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        descriptionLabel.setForeground(
                SECONDARY
        );

        card.add(
                titleLabel
        );

        card.add(
                Box.createVerticalStrut(5)
        );

        card.add(
                valueLabel
        );

        card.add(
                Box.createVerticalStrut(3)
        );

        card.add(
                descriptionLabel
        );

        return card;
    }

    // =========================================================
    // STUDENT CARD
    // =========================================================

    private JPanel createStudentCard() {

        JPanel card =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                15
                        )
                );

        card.setBackground(
                CARD
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        233
                                )
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        75
                )
        );

        JButton add =
                createActionButton(
                        "+ Add Student"
                );

        JButton view =
                createActionButton(
                        "View Students"
                );

        JButton update =
                createActionButton(
                        "Update Student"
                );

        JButton delete =
                createActionButton(
                        "Delete Student"
                );

        // Add Student

        add.addActionListener(
                e -> {

                    AddStudentFrame frame =
                            new AddStudentFrame();

                    frame.setVisible(true);
                }
        );

        // View Students

        view.addActionListener(
                e -> {

                    ViewStudentsFrame frame =
                            new ViewStudentsFrame();

                    frame.setVisible(true);
                }
        );

        // Update Student

        update.addActionListener(
                e -> {

                    UpdateStudentFrame frame =
                            new UpdateStudentFrame();

                    frame.setVisible(true);
                }
        );

        // Delete Student

        delete.addActionListener(
                e -> {

                    DeleteStudentFrame frame =
                            new DeleteStudentFrame();

                    frame.setVisible(true);
                }
        );

        card.add(add);
        card.add(view);
        card.add(update);
        card.add(delete);

        return card;
    }
    // =========================================================
// COURSE CARD
// =========================================================



    private JPanel createCourseCard() {

        JPanel card =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                15
                        )
                );

        card.setBackground(CARD);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        233
                                )
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        75
                )
        );


        JButton add =
                createActionButton(
                        "+ Add Course"
                );

        JButton view =
                createActionButton(
                        "View Courses"
                );

        JButton update =
                createActionButton(
                        "Update Course"
                );

        JButton delete =
                createActionButton(
                        "Delete Course"
                );

        add.addActionListener(e -> {

            AddCourseFrame frame =
                    new AddCourseFrame();

            frame.setVisible(true);
        });

        view.addActionListener(e -> {

            ViewCoursesFrame frame =
                    new ViewCoursesFrame();

            frame.setVisible(true);
        });

        update.addActionListener(e -> {

            UpdateCourseFrame frame =
                    new UpdateCourseFrame();

            frame.setVisible(true);
        });

        delete.addActionListener(e -> {

            DeleteCourseFrame frame =
                    new DeleteCourseFrame();

            frame.setVisible(true);
        });

        card.add(add);
        card.add(view);
        card.add(update);
        card.add(delete);

        return card;
    }
    // =========================================================
// SUBJECT CARD
// =========================================================

    private JPanel createSubjectCard() {

        JPanel card =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                15
                        )
                );

        card.setBackground(CARD);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        233
                                )
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        75
                )
        );

        JButton add =
                createActionButton(
                        "+ Add Subject"
                );

        JButton view =
                createActionButton(
                        "View Subjects"
                );

        JButton update =
                createActionButton(
                        "Update Subject"
                );

        JButton delete =
                createActionButton(
                        "Delete Subject"
                );

        add.addActionListener(e -> {

            AddSubjectFrame frame =
                    new AddSubjectFrame();

            frame.setVisible(true);
        });

        view.addActionListener(e -> {

            ViewSubjectsFrame frame =
                    new ViewSubjectsFrame();

            frame.setVisible(true);
        });

        update.addActionListener(e -> {

            UpdateSubjectFrame frame =
                    new UpdateSubjectFrame();

            frame.setVisible(true);
        });

        delete.addActionListener(e -> {

            DeleteSubjectFrame frame =
                    new DeleteSubjectFrame();

            frame.setVisible(true);
        });

        card.add(add);
        card.add(view);
        card.add(update);
        card.add(delete);

        return card;
    }

    // =========================================================
    // MARKS CARD
    // =========================================================

    private JPanel createMarksCard() {

        JPanel card =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                15
                        )
                );

        card.setBackground(
                CARD
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        233
                                )
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        75
                )
        );

        JButton add =
                createActionButton(
                        "+ Add Marks"
                );

        JButton view =
                createActionButton(
                        "View Marks"
                );

        JButton update =
                createActionButton(
                        "Update Marks"
                );

        JButton delete =
                createActionButton(
                        "Delete Marks"
                );

        // Add Marks

        add.addActionListener(
                e -> {

                    AddMarksFrame frame =
                            new AddMarksFrame();

                    frame.setVisible(true);
                }
        );

        // View Marks

        view.addActionListener(
                e -> {

                    ViewMarksFrame frame =
                            new ViewMarksFrame();

                    frame.setVisible(true);
                }
        );

        // Update Marks

        update.addActionListener(
                e -> {

                    UpdateMarksFrame frame =
                            new UpdateMarksFrame();

                    frame.setVisible(true);
                }
        );

        // Delete Marks

        delete.addActionListener(
                e -> {

                    DeleteMarksFrame frame =
                            new DeleteMarksFrame();

                    frame.setVisible(true);
                }
        );

        card.add(add);
        card.add(view);
        card.add(update);
        card.add(delete);

        return card;
    }

    // =========================================================
// ATTENDANCE CARD
// =========================================================

    private JPanel createAttendanceCard() {

        JPanel card =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                15
                        )
                );

        card.setBackground(CARD);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        233
                                )
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        75
                )
        );

        JButton add =
                createActionButton(
                        "+ Add Attendance"
                );

        JButton view =
                createActionButton(
                        "View Attendance"
                );

        JButton update =
                createActionButton(
                        "Update Attendance"
                );

        JButton delete =
                createActionButton(
                        "Delete Attendance"
                );

        // Add Attendance

        add.addActionListener(
                e -> {

                    AddAttendanceFrame frame =
                            new AddAttendanceFrame();

                    frame.setVisible(true);
                }
        );

        // View Attendance

        view.addActionListener(
                e -> {

                    ViewAttendanceFrame frame =
                            new ViewAttendanceFrame();

                    frame.setVisible(true);
                }
        );

        // Update Attendance

        update.addActionListener(
                e -> {

                    UpdateAttendanceFrame frame =
                            new UpdateAttendanceFrame();

                    frame.setVisible(true);
                }
        );

        // Delete Attendance

        delete.addActionListener(
                e -> {

                    DeleteAttendanceFrame frame =
                            new DeleteAttendanceFrame();

                    frame.setVisible(true);
                }
        );

        card.add(add);
        card.add(view);
        card.add(update);
        card.add(delete);

        return card;
    }


// =========================================================
// RESULT CARD
// =========================================================

    private JPanel createResultCard() {

        JPanel card =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                15
                        )
                );

        card.setBackground(CARD);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        233
                                )
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        75
                )
        );

        JButton add =
                createActionButton("+ Add Result");

        JButton view =
                createActionButton("View Results");

        JButton update =
                createActionButton("Update Result");

        JButton delete =
                createActionButton("Delete Result");

        add.addActionListener(e -> {

            AddResultFrame frame =
                    new AddResultFrame();

            frame.setVisible(true);
        });

        view.addActionListener(e -> {

            ViewResultsFrame frame =
                    new ViewResultsFrame();

            frame.setVisible(true);
        });

        update.addActionListener(e -> {

            UpdateResultFrame frame =
                    new UpdateResultFrame();

            frame.setVisible(true);
        });

        delete.addActionListener(e -> {

            DeleteResultFrame frame =
                    new DeleteResultFrame();

            frame.setVisible(true);
        });

        card.add(add);
        card.add(view);
        card.add(update);
        card.add(delete);

        return card;
    }
    // =========================================================
    // GET STUDENT COUNT
    // =========================================================

    private int getStudentCount() {

        int count = 0;

        String sql =
                "SELECT COUNT(*) FROM students";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            if (rs.next()) {

                count =
                        rs.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to load student count: "
                            + e.getMessage()
            );
        }

        return count;
    }

    // =========================================================
    // GET MARKS COUNT
    // =========================================================

    private int getMarksCount() {

        int count = 0;

        String sql =
                "SELECT COUNT(*) FROM marks";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            if (rs.next()) {

                count =
                        rs.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to load marks count: "
                            + e.getMessage()
            );
        }

        return count;
    }

    // =========================================================
    // DATABASE STATUS
    // =========================================================

    private String getDatabaseStatus() {

        try (
                Connection conn =
                        DatabaseConnection.getConnection()
        ) {

            if (
                    conn != null
                            && !conn.isClosed()
            ) {

                return "ONLINE";
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database connection failed: "
                            + e.getMessage()
            );
        }

        return "OFFLINE";
    }

    // =========================================================
    // RECENT STUDENTS TABLE
    // =========================================================

    private JPanel createRecentStudentsTable() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                CARD
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        233
                                )
                        ),
                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10
                        )
                )
        );

        panel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        250
                )
        );

        // =====================================================
        // TABLE COLUMNS
        // =====================================================

        String[] columns = {

                "ID",
                "Name",
                "Age",
                "Gender",
                "Course",
                "Semester"
        };

        DefaultTableModel model =
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

        JTable table =
                new JTable(model);

        table.setRowHeight(30);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        table.getTableHeader()
                .setBackground(
                        new Color(
                                240,
                                244,
                                248
                        )
                );

        table.getTableHeader()
                .setForeground(
                        TEXT
                );

        // =====================================================
        // LOAD STUDENTS FROM MYSQL
        // =====================================================

        String sql =
                "SELECT student_id, name, age, gender, course, semester "
                        + "FROM students "
                        + "ORDER BY student_id DESC "
                        + "LIMIT 5";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                model.addRow(
                        new Object[]{

                                rs.getInt(
                                        "student_id"
                                ),

                                rs.getString(
                                        "name"
                                ),

                                rs.getInt(
                                        "age"
                                ),

                                rs.getString(
                                        "gender"
                                ),

                                rs.getString(
                                        "course"
                                ),

                                rs.getInt(
                                        "semester"
                                )
                        }
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to load recent students: "
                            + e.getMessage()
            );
        }

        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        table
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    MainFrame frame =
                            new MainFrame();

                    frame.setVisible(true);
                }
        );
    }
}