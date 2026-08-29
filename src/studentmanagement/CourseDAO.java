package studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // ==========================================
    // ADD COURSE
    // ==========================================

    public boolean addCourse(Course course) {

        String sql =
                "INSERT INTO courses (course_name, duration) VALUES (?, ?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, course.getCourseName());
            stmt.setInt(2, course.getDuration());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error adding course: " + e.getMessage()
            );

            return false;
        }
    }

    // ==========================================
    // GET ALL COURSES
    // ==========================================

    public List<Course> getAllCourses() {

        List<Course> courses = new ArrayList<>();

        String sql =
                "SELECT course_id, course_name, duration " +
                        "FROM courses ORDER BY course_id";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Course course = new Course();

                course.setCourseId(
                        rs.getInt("course_id")
                );

                course.setCourseName(
                        rs.getString("course_name")
                );

                course.setDuration(
                        rs.getInt("duration")
                );

                courses.add(course);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error loading courses: " + e.getMessage()
            );
        }

        return courses;
    }

    // ==========================================
    // GET COURSE BY ID
    // ==========================================

    public Course getCourseById(int courseId) {

        String sql =
                "SELECT course_id, course_name, duration " +
                        "FROM courses WHERE course_id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, courseId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Course course = new Course();

                    course.setCourseId(
                            rs.getInt("course_id")
                    );

                    course.setCourseName(
                            rs.getString("course_name")
                    );

                    course.setDuration(
                            rs.getInt("duration")
                    );

                    return course;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error finding course: " + e.getMessage()
            );
        }

        return null;
    }

    // ==========================================
    // UPDATE COURSE
    // ==========================================

    public boolean updateCourse(Course course) {

        String sql =
                "UPDATE courses SET " +
                        "course_name = ?, " +
                        "duration = ? " +
                        "WHERE course_id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, course.getCourseName());
            stmt.setInt(2, course.getDuration());
            stmt.setInt(3, course.getCourseId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating course: " + e.getMessage()
            );

            return false;
        }
    }

    // ==========================================
    // DELETE COURSE
    // ==========================================

    public boolean deleteCourse(int courseId) {

        String sql =
                "DELETE FROM courses WHERE course_id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, courseId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting course: " + e.getMessage()
            );

            return false;
        }
    }
}