package studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarksDAO {

    // ==============================
    // ADD MARKS
    // ==============================
    public void addMarks(
            int studentId,
            double organization,
            double operatingSystem,
            double oop,
            double networking,
            double ethics) {

        String sql = "INSERT INTO marks " +
                "(student_id, organization, operating_system, oop, networking, ethics) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setDouble(2, organization);
            stmt.setDouble(3, operatingSystem);
            stmt.setDouble(4, oop);
            stmt.setDouble(5, networking);
            stmt.setDouble(6, ethics);

            stmt.executeUpdate();

            System.out.println("Marks added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ==============================
    // CHECK DUPLICATE MARKS
    // ==============================
    public boolean marksExist(int studentId) {

        String sql = "SELECT COUNT(*) FROM marks WHERE student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}