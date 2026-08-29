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

public class ViewClassesFrame extends JFrame {

    private DefaultTableModel model;

    public ViewClassesFrame() {

        setTitle("View Classes");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        panel.setBorder(
                new EmptyBorder(20, 20, 20, 20)
        );

        JLabel title = new JLabel("Class List");

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        panel.add(title, BorderLayout.NORTH);

        String[] columns = {
                "Class ID",
                "Class Name",
                "Course ID",
                "Course Name"
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

        JTable table = new JTable(model);

        table.setRowHeight(30);

        table.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JButton refreshButton =
                new JButton("Refresh");

        refreshButton.addActionListener(
                e -> loadClasses()
        );

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        bottom.add(refreshButton);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        add(panel);

        loadClasses();
    }

    private void loadClasses() {

        model.setRowCount(0);

        String sql =
                "SELECT cl.class_id, " +
                        "cl.class_name, " +
                        "cl.course_id, " +
                        "c.course_name " +
                        "FROM classes cl " +
                        "LEFT JOIN courses c " +
                        "ON cl.course_id = c.course_id " +
                        "ORDER BY cl.class_id";

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

                                rs.getInt("class_id"),

                                rs.getString("class_name"),

                                rs.getInt("course_id"),

                                rs.getString("course_name")
                        }
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error:\n"
                            + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new ViewClassesFrame()
                        .setVisible(true)
        );
    }
}