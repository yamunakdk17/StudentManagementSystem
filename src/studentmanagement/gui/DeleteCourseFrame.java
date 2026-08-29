package studentmanagement.gui;

import javax.swing.JFrame;

public class DeleteCourseFrame extends JFrame {

    public DeleteCourseFrame() {
        setTitle("Delete Course");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        DeleteCourseFrame frame = new DeleteCourseFrame();
        frame.setVisible(true);
    }
}