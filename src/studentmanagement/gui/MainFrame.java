package studentmanagement.gui;

import studentmanagement.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainFrame extends JFrame {

    // =========================================================
    // MODERN COLOR PALETTE
    // =========================================================

    private final Color SIDEBAR =
            new Color(48, 36, 43);

    private final Color SIDEBAR_DARK =
            new Color(67, 45, 54);

    private final Color SIDEBAR_ACTIVE =
            new Color(139, 80, 93);

    private final Color BACKGROUND =
            new Color(248, 244, 246);

    private final Color CARD =
            new Color(255, 255, 255);

    private final Color TEXT =
            new Color(48, 38, 43);

    private final Color SECONDARY =
            new Color(120, 105, 112);

    private final Color PRIMARY =
            new Color(139, 80, 93);

    private final Color PRIMARY_DARK =
            new Color(112, 61, 74);

    private final Color BORDER =
            new Color(232, 222, 226);

    private final Color SUCCESS =
            new Color(62, 132, 94);

    private final Color WARNING =
            new Color(194, 139, 63);

    private final Color LIGHT_PRIMARY =
            new Color(246, 235, 239);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MainFrame() {

        setTitle("Student Management System");

        setSize(
                1200,
                750
        );

        setMinimumSize(
                new Dimension(
                        1050,
                        650
                )
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        // =====================================================
        // SIDEBAR
        // =====================================================

        JPanel sidebar =
                createSidebar();

        mainPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        // =====================================================
        // RIGHT SIDE
        // =====================================================

        JPanel rightPanel =
                new JPanel(
                        new BorderLayout()
                );

        rightPanel.setBackground(
                BACKGROUND
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel header =
                createHeader();

        rightPanel.add(
                header,
                BorderLayout.NORTH
        );

        // =====================================================
        // DASHBOARD
        // =====================================================

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

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
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
                        235,
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
                        30,
                        18,
                        25,
                        18
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
                        21
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
                        190,
                        170,
                        180
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
                Box.createVerticalStrut(45)
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
                        175,
                        155,
                        165
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
                Box.createVerticalStrut(12)
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
                Box.createVerticalStrut(7)
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
                Box.createVerticalStrut(7)
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
                Box.createVerticalStrut(7)
        );

        // =====================================================
        // REPORTS
        // =====================================================

        JButton reportsButton =
                createMenuButton(
                        "Reports"
                );

        reportsButton.addActionListener(
                e -> {

                    ReportsFrame frame =
                            new ReportsFrame();

                    frame.setVisible(true);
                }
        );

        sidebar.add(
                reportsButton
        );

        sidebar.add(
                Box.createVerticalStrut(7)
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

        // =====================================================
        // PUSH EXIT TO BOTTOM
        // =====================================================

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
                e -> {

                    int result =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Are you sure you want to exit?",
                                    "Exit System",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (result ==
                            JOptionPane.YES_OPTION) {

                        System.exit(0);
                    }
                }
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
                new JButton(text) {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        Graphics2D g2 =
                                (Graphics2D)
                                        g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        if (getModel().isRollover()) {

                            g2.setColor(
                                    SIDEBAR_ACTIVE
                            );

                        } else {

                            g2.setColor(
                                    SIDEBAR
                            );
                        }

                        g2.fillRoundRect(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                12,
                                12
                        );

                        g2.dispose();

                        super.paintComponent(g);
                    }
                };

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        46
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

        button.setContentAreaFilled(false);

        button.setOpaque(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
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
                        82
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

        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
                new JLabel(
                        "Dashboard"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        title.setForeground(
                TEXT
        );

        // =====================================================
        // REFRESH BUTTON
        // =====================================================

        JButton refreshButton =
                createActionButton(
                        "↻  Refresh"
                );

        refreshButton.setPreferredSize(
                new Dimension(
                        120,
                        40
                )
        );

        refreshButton.addActionListener(
                e -> {

                    dispose();

                    SwingUtilities.invokeLater(
                            () -> {

                                MainFrame frame =
                                        new MainFrame();

                                frame.setVisible(true);
                            }
                    );
                }
        );

        // =====================================================
        // ADMIN
        // =====================================================

        JLabel admin =
                new JLabel(
                        "Administrator"
                );

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

        // =====================================================
        // HEADER RIGHT
        // =====================================================

        JPanel rightHeader =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                0
                        )
                );

        rightHeader.setBackground(
                Color.WHITE
        );

        rightHeader.add(
                refreshButton
        );

        rightHeader.add(
                admin
        );

        header.add(
                title,
                BorderLayout.WEST
        );

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
                        40,
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
                        27
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
                Box.createVerticalStrut(6)
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

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
                Box.createVerticalStrut(28)
        );

        // =====================================================
        // STAT CARDS
        // =====================================================

        JPanel stats =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                18,
                                0
                        )
                );

        stats.setBackground(
                BACKGROUND
        );

        stats.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        125
                )
        );

        stats.add(
                createStatCard(
                        "STUDENTS",
                        String.valueOf(
                                getStudentCount()
                        ),
                        "Total Students",
                        PRIMARY
                )
        );

        stats.add(
                createStatCard(
                        "MARKS",
                        String.valueOf(
                                getMarksCount()
                        ),
                        "Academic Records",
                        WARNING
                )
        );

        stats.add(
                createStatCard(
                        "DATABASE",
                        getDatabaseStatus(),
                        "MySQL Connection",
                        SUCCESS
                )
        );

        dashboard.add(
                stats
        );

        dashboard.add(
                Box.createVerticalStrut(30)
        );

        // =====================================================
        // STUDENT MANAGEMENT
        // =====================================================

        addSection(
                dashboard,
                "Student Management",
                createStudentCard()
        );

        dashboard.add(
                Box.createVerticalStrut(28)
        );

        // =====================================================
        // COURSE MANAGEMENT
        // =====================================================

        addSection(
                dashboard,
                "Course Management",
                createCourseCard()
        );

        dashboard.add(
                Box.createVerticalStrut(28)
        );

        // =====================================================
        // SUBJECT MANAGEMENT
        // =====================================================

        addSection(
                dashboard,
                "Subject Management",
                createSubjectCard()
        );

        dashboard.add(
                Box.createVerticalStrut(28)
        );

        // =====================================================
        // MARKS MANAGEMENT
        // =====================================================

        addSection(
                dashboard,
                "Marks Management",
                createMarksCard()
        );

        dashboard.add(
                Box.createVerticalStrut(28)
        );

        // =====================================================
        // ATTENDANCE MANAGEMENT
        // =====================================================

        addSection(
                dashboard,
                "Attendance Management",
                createAttendanceCard()
        );

        dashboard.add(
                Box.createVerticalStrut(28)
        );

        // =====================================================
        // RESULTS MANAGEMENT
        // =====================================================

        addSection(
                dashboard,
                "Results Management",
                createResultCard()
        );

        dashboard.add(
                Box.createVerticalStrut(30)
        );

        // =====================================================
        // RECENT STUDENTS
        // =====================================================

        JLabel recentTitle =
                new JLabel(
                        "Recent Students"
                );

        recentTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        recentTitle.setForeground(
                TEXT
        );

        recentTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        dashboard.add(
                recentTitle
        );

        dashboard.add(
                Box.createVerticalStrut(12)
        );

        dashboard.add(
                createRecentStudentsTable()
        );

        return dashboard;
    }

    // =========================================================
    // ADD SECTION
    // =========================================================

    private void addSection(
            JPanel dashboard,
            String title,
            JPanel card
    ) {

        JLabel label =
                new JLabel(title);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        label.setForeground(
                TEXT
        );

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        dashboard.add(
                label
        );

        dashboard.add(
                Box.createVerticalStrut(12)
        );

        dashboard.add(
                card
        );
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private JPanel createStatCard(
            String title,
            String value,
            String description,
            Color accentColor
    ) {

        RoundedPanel card =
                new RoundedPanel(18);

        card.setLayout(
                new BorderLayout(
                        15,
                        0
                )
        );

        card.setBackground(
                CARD
        );

        card.setBorder(
                new EmptyBorder(
                        18,
                        18,
                        18,
                        20
                )
        );

        // =====================================================
        // ACCENT BAR
        // =====================================================

        JPanel accent =
                new JPanel();

        accent.setPreferredSize(
                new Dimension(
                        5,
                        70
                )
        );

        accent.setBackground(
                accentColor
        );

        // =====================================================
        // CONTENT
        // =====================================================

        JPanel content =
                new JPanel();

        content.setOpaque(false);

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
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
                        11
                )
        );

        titleLabel.setForeground(
                SECONDARY
        );

        JLabel valueLabel =
                new JLabel(
                        value
                );

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
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

        content.add(
                titleLabel
        );

        content.add(
                Box.createVerticalStrut(4)
        );

        content.add(
                valueLabel
        );

        content.add(
                Box.createVerticalStrut(3)
        );

        content.add(
                descriptionLabel
        );

        card.add(
                accent,
                BorderLayout.WEST
        );

        card.add(
                content,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private JButton createActionButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        Graphics2D g2 =
                                (Graphics2D)
                                        g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        if (getModel().isPressed()) {

                            g2.setColor(
                                    PRIMARY_DARK
                            );

                        } else if (
                                getModel().isRollover()
                        ) {

                            g2.setColor(
                                    PRIMARY
                            );

                        } else {

                            g2.setColor(
                                    LIGHT_PRIMARY
                            );
                        }

                        g2.fillRoundRect(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                12,
                                12
                        );

                        g2.dispose();

                        super.paintComponent(g);
                    }
                };

        button.setPreferredSize(
                new Dimension(
                        150,
                        44
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
                PRIMARY_DARK
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        14,
                        8,
                        14
                )
        );

        button.setContentAreaFilled(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setOpaque(
                false
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // STUDENT CARD
    // =========================================================

    private JPanel createStudentCard() {

        RoundedPanel card =
                createManagementCard();

        JButton add =
                createActionButton(
                        "+  Add Student"
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

        // Add

        add.addActionListener(
                e -> {

                    AddStudentFrame frame =
                            new AddStudentFrame();

                    frame.setVisible(true);
                }
        );

        // View

        view.addActionListener(
                e -> {

                    ViewStudentsFrame frame =
                            new ViewStudentsFrame();

                    frame.setVisible(true);
                }
        );

        // Update

        update.addActionListener(
                e -> {

                    UpdateStudentFrame frame =
                            new UpdateStudentFrame();

                    frame.setVisible(true);
                }
        );

        // Delete

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

        RoundedPanel card =
                createManagementCard();

        JButton add =
                createActionButton(
                        "+  Add Course"
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

        add.addActionListener(
                e -> {

                    AddCourseFrame frame =
                            new AddCourseFrame();

                    frame.setVisible(true);
                }
        );

        view.addActionListener(
                e -> {

                    ViewCoursesFrame frame =
                            new ViewCoursesFrame();

                    frame.setVisible(true);
                }
        );

        update.addActionListener(
                e -> {

                    UpdateCourseFrame frame =
                            new UpdateCourseFrame();

                    frame.setVisible(true);
                }
        );

        delete.addActionListener(
                e -> {

                    DeleteCourseFrame frame =
                            new DeleteCourseFrame();

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
    // SUBJECT CARD
    // =========================================================

    private JPanel createSubjectCard() {

        RoundedPanel card =
                createManagementCard();

        JButton add =
                createActionButton(
                        "+  Add Subject"
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

        add.addActionListener(
                e -> {

                    AddSubjectFrame frame =
                            new AddSubjectFrame();

                    frame.setVisible(true);
                }
        );

        view.addActionListener(
                e -> {

                    ViewSubjectsFrame frame =
                            new ViewSubjectsFrame();

                    frame.setVisible(true);
                }
        );

        update.addActionListener(
                e -> {

                    UpdateSubjectFrame frame =
                            new UpdateSubjectFrame();

                    frame.setVisible(true);
                }
        );

        delete.addActionListener(
                e -> {

                    DeleteSubjectFrame frame =
                            new DeleteSubjectFrame();

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
    // MARKS CARD
    // =========================================================

    private JPanel createMarksCard() {

        RoundedPanel card =
                createManagementCard();

        JButton add =
                createActionButton(
                        "+  Add Marks"
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

        add.addActionListener(
                e -> {

                    AddMarksFrame frame =
                            new AddMarksFrame();

                    frame.setVisible(true);
                }
        );

        view.addActionListener(
                e -> {

                    ViewMarksFrame frame =
                            new ViewMarksFrame();

                    frame.setVisible(true);
                }
        );

        update.addActionListener(
                e -> {

                    UpdateMarksFrame frame =
                            new UpdateMarksFrame();

                    frame.setVisible(true);
                }
        );

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

        RoundedPanel card =
                createManagementCard();

        JButton add =
                createActionButton(
                        "+  Add Attendance"
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

        add.addActionListener(
                e -> {

                    AddAttendanceFrame frame =
                            new AddAttendanceFrame();

                    frame.setVisible(true);
                }
        );

        view.addActionListener(
                e -> {

                    ViewAttendanceFrame frame =
                            new ViewAttendanceFrame();

                    frame.setVisible(true);
                }
        );

        update.addActionListener(
                e -> {

                    UpdateAttendanceFrame frame =
                            new UpdateAttendanceFrame();

                    frame.setVisible(true);
                }
        );

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

        RoundedPanel card =
                createManagementCard();

        JButton add =
                createActionButton(
                        "+  Add Result"
                );

        JButton view =
                createActionButton(
                        "View Results"
                );

        JButton update =
                createActionButton(
                        "Update Result"
                );

        JButton delete =
                createActionButton(
                        "Delete Result"
                );

        add.addActionListener(
                e -> {

                    AddResultFrame frame =
                            new AddResultFrame();

                    frame.setVisible(true);
                }
        );

        view.addActionListener(
                e -> {

                    ViewResultsFrame frame =
                            new ViewResultsFrame();

                    frame.setVisible(true);
                }
        );

        update.addActionListener(
                e -> {

                    UpdateResultFrame frame =
                            new UpdateResultFrame();

                    frame.setVisible(true);
                }
        );

        delete.addActionListener(
                e -> {

                    DeleteResultFrame frame =
                            new DeleteResultFrame();

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
    // COMMON MANAGEMENT CARD
    // =========================================================

    private RoundedPanel createManagementCard() {

        RoundedPanel card =
                new RoundedPanel(18);

        card.setLayout(
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
                new EmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        80
                )
        );

        return card;
    }

    // =========================================================
    // STUDENT COUNT
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
    // MARKS COUNT
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
                            &&
                            !conn.isClosed()
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

        RoundedPanel panel =
                new RoundedPanel(18);

        panel.setLayout(
                new BorderLayout()
        );

        panel.setBackground(
                CARD
        );

        panel.setBorder(
                new EmptyBorder(
                        12,
                        12,
                        12,
                        12
                )
        );

        panel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        270
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

        // =====================================================
        // TABLE DESIGN
        // =====================================================

        table.setRowHeight(
                34
        );

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

        table.setShowVerticalLines(
                false
        );

        table.setShowHorizontalLines(
                true
        );

        table.setGridColor(
                new Color(
                        238,
                        230,
                        234
                )
        );

        table.setIntercellSpacing(
                new Dimension(
                        0,
                        1
                )
        );

        table.setBackground(
                Color.WHITE
        );

        table.setSelectionBackground(
                LIGHT_PRIMARY
        );

        table.setSelectionForeground(
                TEXT
        );

        // =====================================================
        // HEADER
        // =====================================================

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
                        LIGHT_PRIMARY
                );

        table.getTableHeader()
                .setForeground(
                        TEXT
                );

        table.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );

        // =====================================================
        // LOAD STUDENTS
        // =====================================================

        String sql =
                "SELECT student_id, name, age, gender, course, semester "
                        +
                        "FROM students "
                        +
                        "ORDER BY student_id DESC "
                        +
                        "LIMIT 5";

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

        scrollPane.setBackground(
                Color.WHITE
        );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =========================================================
    // ROUNDED PANEL
    // =========================================================

    private static class RoundedPanel
            extends JPanel {

        private final int radius;

        public RoundedPanel(
                int radius
        ) {

            this.radius =
                    radius;

            setOpaque(
                    false
            );
        }

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D)
                            g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    getBackground()
            );

            g2.fill(
                    new RoundRectangle2D.Double(
                            0,
                            0,
                            getWidth() - 1,
                            getHeight() - 1,
                            radius,
                            radius
                    )
            );

            g2.dispose();

            super.paintComponent(g);
        }
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

                    frame.setVisible(
                            true
                    );
                }
        );
    }
}