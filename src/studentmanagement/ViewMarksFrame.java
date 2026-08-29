package studentmanagement;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewMarksFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewMarksFrame() {

        setTitle("View Marks");
        setSize(950, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );

        // ==============================
        // TITLE
        // ==============================

        JLabel title = new JLabel("Academic Records");

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

        // ==============================
        // TABLE
        // ==============================

        model = new DefaultTableModel();

        model.setColumnIdentifiers(
                new String[]{
                        "Mark ID",
                        "Student ID",
                        "Organization",
                        "Operating System",
                        "OOP",
                        "Networking",
                        "Ethics"
                }
        );

        table = new JTable(model);

        table.setRowHeight(30);

        table.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13)
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

        // ==============================
        // REFRESH BUTTON
        // ==============================

        JButton refreshButton =
                new JButton("Refresh");

        refreshButton.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        refreshButton.setFocusPainted(false);

        refreshButton.addActionListener(e ->
                loadMarks()
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        bottomPanel.setBackground(
                new Color(245, 247, 250)
        );

        bottomPanel.add(refreshButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // Load data when window opens
        loadMarks();
    }

    // ==============================
    // LOAD MARKS
    // ==============================

    private void loadMarks() {

        model.setRowCount(0);

        String sql =
                "SELECT mark_id, student_id, " +
                        "organization, operating_system, " +
                        "oop, networking, ethics " +
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

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load marks:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}