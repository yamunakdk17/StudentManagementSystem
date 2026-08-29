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

public class ViewMarksFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewMarksFrame() {

        setTitle("View Student Marks");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBackground(new Color(245, 247, 250));

        mainPanel.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel title = new JLabel("Student Marks");

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
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
                "Mark ID",
                "Student ID",
                "Organization",
                "Operating System",
                "OOP",
                "Networking",
                "Ethics"
        };

        model = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };

        table = new JTable(model);

        table.setRowHeight(30);

        table.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        table.getTableHeader().setBackground(
                new Color(240, 244, 248)
        );

        table.getTableHeader().setForeground(
                new Color(31, 41, 55)
        );

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(225, 228, 233)
                )
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM PANEL
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
                new Color(245, 247, 250)
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
                e -> loadMarks()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        add(mainPanel);

        // Load data when window opens
        loadMarks();
    }

    // =========================================================
    // LOAD MARKS
    // =========================================================

    private void loadMarks() {

        model.setRowCount(0);

        String sql =
                "SELECT mark_id, student_id, organization, " +
                        "operating_system, oop, networking, ethics " +
                        "FROM marks " +
                        "ORDER BY mark_id DESC";

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

                                rs.getInt("mark_id"),

                                rs.getInt("student_id"),

                                rs.getDouble("organization"),

                                rs.getDouble("operating_system"),

                                rs.getDouble("oop"),

                                rs.getDouble("networking"),

                                rs.getDouble("ethics")
                        }
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load marks:\n"
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

                    ViewMarksFrame frame =
                            new ViewMarksFrame();

                    frame.setVisible(true);
                }
        );
    }
}