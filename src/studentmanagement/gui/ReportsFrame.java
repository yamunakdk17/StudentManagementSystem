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

public class ReportsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ReportsFrame() {

        setTitle("Student Result Reports");
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        mainPanel.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
                new JLabel("Student Result Reports");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        title.setForeground(
                new Color(31, 41, 55)
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {

                "Student ID",
                "Name",
                "Organization",
                "Operating System",
                "OOP",
                "Networking",
                "Ethics",
                "Total",
                "Average",
                "Result"
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

        table =
                new JTable(model);

        table.setRowHeight(30);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        table.getTableHeader().setBackground(
                new Color(
                        240,
                        244,
                        248
                )
        );

        table.getTableHeader().setForeground(
                new Color(
                        31,
                        41,
                        55
                )
        );

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                225,
                                228,
                                233
                        )
                )
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM BUTTONS
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                5
                        )
                );

        bottomPanel.setBackground(
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

        refreshButton.setPreferredSize(
                new Dimension(100, 35)
        );

        closeButton.setPreferredSize(
                new Dimension(100, 35)
        );

        refreshButton.setFocusPainted(false);
        closeButton.setFocusPainted(false);

        bottomPanel.add(refreshButton);
        bottomPanel.add(closeButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        refreshButton.addActionListener(
                e -> loadReports()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        add(mainPanel);

        // Load reports
        loadReports();
    }

    // =========================================================
    // LOAD REPORTS
    // =========================================================

    private void loadReports() {

        model.setRowCount(0);

        String sql =
                "SELECT " +
                        "s.student_id, " +
                        "s.name, " +
                        "m.organization, " +
                        "m.operating_system, " +
                        "m.oop, " +
                        "m.networking, " +
                        "m.ethics " +
                        "FROM students s " +
                        "INNER JOIN marks m " +
                        "ON s.student_id = m.student_id " +
                        "ORDER BY s.student_id DESC";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                int studentId =
                        rs.getInt("student_id");

                String name =
                        rs.getString("name");

                double organization =
                        rs.getDouble("organization");

                double operatingSystem =
                        rs.getDouble("operating_system");

                double oop =
                        rs.getDouble("oop");

                double networking =
                        rs.getDouble("networking");

                double ethics =
                        rs.getDouble("ethics");

                // =================================================
                // CALCULATE TOTAL
                // =================================================

                double total =
                        organization
                                + operatingSystem
                                + oop
                                + networking
                                + ethics;

                // =================================================
                // CALCULATE AVERAGE
                // =================================================

                double average =
                        total / 5;

                // =================================================
                // RESULT
                // =================================================

                String result;

                if (average >= 40) {

                    result = "PASS";

                } else {

                    result = "FAIL";
                }

                // =================================================
                // ADD ROW
                // =================================================

                model.addRow(
                        new Object[]{

                                studentId,

                                name,

                                organization,

                                operatingSystem,

                                oop,

                                networking,

                                ethics,

                                String.format(
                                        "%.2f",
                                        total
                                ),

                                String.format(
                                        "%.2f",
                                        average
                                ),

                                result
                        }
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load reports:\n"
                            + e.getMessage(),
                    "Database Error",
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

                    ReportsFrame frame =
                            new ReportsFrame();

                    frame.setVisible(true);
                }
        );
    }
}