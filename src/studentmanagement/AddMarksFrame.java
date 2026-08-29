package studentmanagement;

import javax.swing.*;
import java.awt.*;

public class AddMarksFrame extends JFrame {

    private JTextField studentIdField;
    private JTextField organizationField;
    private JTextField operatingSystemField;
    private JTextField oopField;
    private JTextField networkingField;
    private JTextField ethicsField;

    public AddMarksFrame() {

        setTitle("Add Student Marks");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        panel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        panel.add(studentIdField);

        panel.add(new JLabel("Organization:"));
        organizationField = new JTextField();
        panel.add(organizationField);

        panel.add(new JLabel("Operating System:"));
        operatingSystemField = new JTextField();
        panel.add(operatingSystemField);

        panel.add(new JLabel("OOP:"));
        oopField = new JTextField();
        panel.add(oopField);

        panel.add(new JLabel("Networking:"));
        networkingField = new JTextField();
        panel.add(networkingField);

        panel.add(new JLabel("Ethics:"));
        ethicsField = new JTextField();
        panel.add(ethicsField);

        JButton saveButton = new JButton("Save Marks");
        JButton clearButton = new JButton("Clear");

        panel.add(saveButton);
        panel.add(clearButton);

        add(panel);

        saveButton.addActionListener(e -> saveMarks());

        clearButton.addActionListener(e -> clearFields());
    }

    private void saveMarks() {

        try {

            int studentId = Integer.parseInt(
                    studentIdField.getText()
            );

            double organization = Double.parseDouble(
                    organizationField.getText()
            );

            double operatingSystem = Double.parseDouble(
                    operatingSystemField.getText()
            );

            double oop = Double.parseDouble(
                    oopField.getText()
            );

            double networking = Double.parseDouble(
                    networkingField.getText()
            );

            double ethics = Double.parseDouble(
                    ethicsField.getText()
            );

            if (organization < 0 || organization > 100 ||
                    operatingSystem < 0 || operatingSystem > 100 ||
                    oop < 0 || oop > 100 ||
                    networking < 0 || networking > 100 ||
                    ethics < 0 || ethics > 100) {

                JOptionPane.showMessageDialog(
                        this,
                        "Marks must be between 0 and 100."
                );

                return;
            }

            MarksDAO dao = new MarksDAO();

            if (dao.marksExist(studentId)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Marks already exist for this student."
                );

                return;
            }

            dao.addMarks(
                    studentId,
                    organization,
                    operatingSystem,
                    oop,
                    networking,
                    ethics
            );
            JOptionPane.showMessageDialog(
                    this,
                    "Marks added successfully!"
            );

            clearFields();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }

    private void clearFields() {

        studentIdField.setText("");
        organizationField.setText("");
        operatingSystemField.setText("");
        oopField.setText("");
        networkingField.setText("");
        ethicsField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new AddMarksFrame().setVisible(true);
        });
    }
}