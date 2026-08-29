package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewResultsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewResultsFrame() {

        setTitle("View Results");
        setSize(800, 500);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        // =====================================================
        // TABLE MODEL
        // =====================================================

        String[] columns = {
                "Result ID",
                "Student ID",
                "Total Marks",
                "Percentage",
                "Grade",
                "Status"
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

        // =====================================================
        // TABLE
        // =====================================================

        table =
                new JTable(model);

        table.setRowHeight(30);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        table.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(table);

        // =====================================================
        // REFRESH BUTTON
        // =====================================================

        JButton refreshButton =
                new JButton("Refresh");

        refreshButton.setFocusPainted(false);

        refreshButton.addActionListener(
                e -> loadResults()
        );

        // =====================================================
        // CLOSE BUTTON
        // =====================================================

        JButton closeButton =
                new JButton("Close");

        closeButton.setFocusPainted(false);

        closeButton.addActionListener(
                e -> dispose()
        );

        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);

        // =====================================================
        // FRAME LAYOUT
        // =====================================================

        setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );

        add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // LOAD DATA
        // =====================================================

        loadResults();
    }

    // =========================================================
    // LOAD RESULTS
    // =========================================================

    private void loadResults() {

        model.setRowCount(0);

        String sql =
                "SELECT result_id, student_id, total_marks, " +
                        "percentage, grade, result_status " +
                        "FROM results " +
                        "ORDER BY result_id DESC";

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
                                        "result_id"
                                ),

                                rs.getInt(
                                        "student_id"
                                ),

                                rs.getDouble(
                                        "total_marks"
                                ),

                                rs.getDouble(
                                        "percentage"
                                ),

                                rs.getString(
                                        "grade"
                                ),

                                rs.getString(
                                        "result_status"
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

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    ViewResultsFrame frame =
                            new ViewResultsFrame();

                    frame.setVisible(true);
                }
        );
    }
}