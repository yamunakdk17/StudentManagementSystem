package studentmanagement.gui ;

import studentmanagement.StudentDAO;

import javax.swing.*;
import java.awt.*;

public class DeleteStudentFrame extends JFrame {

    private JTextField idField;

    public DeleteStudentFrame() {

        setTitle("Delete Student");
        setSize(400, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        );

        panel.add(new JLabel("Student ID:"));

        idField = new JTextField();
        panel.add(idField);

        JButton deleteButton = new JButton("Delete Student");
        JButton cancelButton = new JButton("Cancel");

        panel.add(deleteButton);
        panel.add(cancelButton);

        add(panel);

        // DELETE BUTTON
        deleteButton.addActionListener(e -> deleteStudent());

        // CANCEL BUTTON
        cancelButton.addActionListener(e -> dispose());
    }

    private void deleteStudent() {

        try {

            int studentId = Integer.parseInt(idField.getText());

            int confirmation = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete student ID "
                            + studentId + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmation == JOptionPane.YES_OPTION) {

                StudentDAO dao = new StudentDAO();

                dao.deleteStudent(studentId);

                JOptionPane.showMessageDialog(
                        this,
                        "Student deleted successfully!"
                );

                idField.setText("");
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Student ID."
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new DeleteStudentFrame().setVisible(true);
        });
    }
}