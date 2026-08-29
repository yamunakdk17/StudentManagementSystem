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

public class ViewSubjectsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewSubjectsFrame() {

        setTitle("View Subjects");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        panel.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
                new JLabel("Subject List");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {
                "Subject ID",
                "Subject Name",
                "Course ID",
                "Course Name"
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
                        14
                )
        );

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM BUTTON
        // =====================================================

        JButton refreshButton =
                new JButton("Refresh");

        refreshButton.addActionListener(
                e -> loadSubjects()
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        bottomPanel.add(refreshButton);

        panel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(panel);

        // Load data when window opens

        loadSubjects();
    }

    // =========================================================
    // LOAD SUBJECTS
    // =========================================================

    private void loadSubjects() {

        model.setRowCount(0);

        String sql =
                "SELECT s.subject_id, " +
                        "s.subject_name, " +
                        "s.course_id, " +
                        "c.course_name " +
                        "FROM subjects s " +
                        "LEFT JOIN courses c " +
                        "ON s.course_id = c.course_id " +
                        "ORDER BY s.subject_id";

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
                                        "subject_id"
                                ),

                                rs.getString(
                                        "subject_name"
                                ),

                                rs.getInt(
                                        "course_id"
                                ),

                                rs.getString(
                                        "course_name"
                                )
                        }
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
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

                    new ViewSubjectsFrame()
                            .setVisible(true);
                }
        );
    }
}