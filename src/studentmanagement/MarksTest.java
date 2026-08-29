package studentmanagement;

public class MarksTest {

    public static void main(String[] args) {

        MarksDAO dao = new MarksDAO();

        // Student ID 101 already exists
        dao.addMarks(
                101,
                80,  // Organization
                75,  // Operating System
                85,  // OOP
                78,  // Networking
                82   // Ethics
        );
    }
}