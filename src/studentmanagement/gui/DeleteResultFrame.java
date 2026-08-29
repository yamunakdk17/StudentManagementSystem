package studentmanagement.gui;

import studentmanagement.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteResultFrame extends JFrame {

    private JTextField resultIdField;

    public DeleteResultFrame() {

        setTitle("Delete Result");
        setSize(450, 250);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                10
                        )
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );

        // Result ID

        panel.add(
                new JLabel("Result ID:")
        );

        resultIdField =
                new JTextField();

        panel.add(
                resultIdField
        );

        // Delete Button

        JButton deleteButton =
                new JButton(
                        "Delete Result"
                );

        panel.add(
                deleteButton
        );

        // Cancel Button

        JButton cancelButton =
                new JButton(
                        "Cancel"
                );

        panel.add(
                cancelButton
        );

        add(panel);

        // Delete

        deleteButton.addActionListener(
                e -> deleteResult()
        );

        // Cancel

        cancelButton.addActionListener(
                e -> dispose()
        );
    }

    // =====================================================
    // DELETE RESULT
    // =====================================================

    private void deleteResult() {

        String idText =
                resultIdField
                        .getText()
                        .trim();

        if (idText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Result ID."
            );

            return;
        }

        int resultId;

        try {

            resultId =
                    Integer.parseInt(idText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Result ID must be a number."
            );

            return;
        }

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete Result ID "
                                + resultId
                                + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                confirm !=
                        JOptionPane.YES_OPTION
        ) {

            return;
        }

        String sql =
                "DELETE FROM results WHERE result_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(
                    1,
                    resultId
            );

            int rows =
                    stmt.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Result deleted successfully!"
                );

                resultIdField.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Result ID not found."
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

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    DeleteResultFrame frame =
                            new DeleteResultFrame();

                    frame.setVisible(true);
                }
        );
    }
}