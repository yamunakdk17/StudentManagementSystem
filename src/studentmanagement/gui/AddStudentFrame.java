package studentmanagement.gui;

import studentmanagement.Student;
import studentmanagement.StudentDAO;

import javax.swing.*;
import java.awt.*;

public class AddStudentFrame extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField courseField;
    private JTextField semesterField;

    public AddStudentFrame() {

        setTitle("Add Student");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        panel.add(new JLabel("Gender:"));
        genderField = new JTextField();
        panel.add(genderField);

        panel.add(new JLabel("Address:"));
        addressField = new JTextField();
        panel.add(addressField);

        panel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Course:"));
        courseField = new JTextField();
        panel.add(courseField);

        panel.add(new JLabel("Semester:"));
        semesterField = new JTextField();
        panel.add(semesterField);

        JButton saveButton = new JButton("Save Student");
        JButton clearButton = new JButton("Clear");

        panel.add(saveButton);
        panel.add(clearButton);

        add(panel);

        // SAVE BUTTON
        saveButton.addActionListener(e -> saveStudent());

        // CLEAR BUTTON
        clearButton.addActionListener(e -> clearFields());
    }

    private void saveStudent() {

        try {

            int studentId = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String course = courseField.getText();
            int semester = Integer.parseInt(semesterField.getText());

            Student student = new Student(
                    name,
                    age,
                    gender,
                    address,
                    phone,
                    email,
                    studentId,
                    course,
                    semester
            );

            StudentDAO dao = new StudentDAO();
            dao.addStudent(student);

            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully!"
            );

            clearFields();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers for ID, Age and Semester."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        genderField.setText("");
        addressField.setText("");
        phoneField.setText("");
        emailField.setText("");
        courseField.setText("");
        semesterField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new AddStudentFrame().setVisible(true);
        });
    }
}