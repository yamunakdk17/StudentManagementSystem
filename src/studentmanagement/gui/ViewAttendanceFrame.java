package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAttendanceFrame extends JFrame {

    private DefaultTableModel model;

    public ViewAttendanceFrame() {

        setTitle("View Attendance");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        mainPanel.setBackground(Color.WHITE);

        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
                new JLabel("Attendance Records");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {
                "Attendance ID",
                "Student ID",
                "Subject ID",
                "Total Classes",
                "Attended Classes",
                "Percentage"
        };

        model =
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

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JButton refreshButton =
                new JButton("Refresh");

        JButton closeButton =
                new JButton("Close");

        refreshButton.addActionListener(
                e -> loadAttendance()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // Load records

        loadAttendance();
    }

    // =========================================================
    // LOAD ATTENDANCE
    // =========================================================

    private void loadAttendance() {

        model.setRowCount(0);

        String sql =
                "SELECT attendance_id, student_id, " +
                        "subject_id, total_classes, " +
                        "attended_classes " +
                        "FROM attendance " +
                        "ORDER BY attendance_id DESC";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                int totalClasses =
                        rs.getInt(
                                "total_classes"
                        );

                int attendedClasses =
                        rs.getInt(
                                "attended_classes"
                        );

                double percentage = 0;

                if (totalClasses > 0) {

                    percentage =
                            (attendedClasses * 100.0)
                                    / totalClasses;
                }

                model.addRow(
                        new Object[]{

                                rs.getInt(
                                        "attendance_id"
                                ),

                                rs.getInt(
                                        "student_id"
                                ),

                                rs.getInt(
                                        "subject_id"
                                ),

                                totalClasses,

                                attendedClasses,

                                String.format(
                                        "%.2f%%",
                                        percentage
                                )
                        }
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    ViewAttendanceFrame frame =
                            new ViewAttendanceFrame();

                    frame.setVisible(true);
                }
        );
    }
}