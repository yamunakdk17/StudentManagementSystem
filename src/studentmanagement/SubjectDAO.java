package studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

    // =========================================================
    // ADD SUBJECT
    // =========================================================

    public boolean addSubject(Subject subject) {

        String sql =
                "INSERT INTO subjects (subject_name, course_id) " +
                        "VALUES (?, ?)";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    subject.getSubjectName()
            );

            stmt.setInt(
                    2,
                    subject.getCourseId()
            );

            int rows =
                    stmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error adding subject: "
                            + e.getMessage()
            );

            return false;
        }
    }

    // =========================================================
    // GET ALL SUBJECTS
    // =========================================================

    public List<Subject> getAllSubjects() {

        List<Subject> subjects =
                new ArrayList<>();

        String sql =
                "SELECT subject_id, subject_name, course_id " +
                        "FROM subjects " +
                        "ORDER BY subject_id";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                Subject subject =
                        new Subject();

                subject.setSubjectId(
                        rs.getInt("subject_id")
                );

                subject.setSubjectName(
                        rs.getString("subject_name")
                );

                subject.setCourseId(
                        rs.getInt("course_id")
                );

                subjects.add(subject);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error loading subjects: "
                            + e.getMessage()
            );
        }

        return subjects;
    }

    // =========================================================
    // GET SUBJECT BY ID
    // =========================================================

    public Subject getSubjectById(int subjectId) {

        String sql =
                "SELECT subject_id, subject_name, course_id " +
                        "FROM subjects " +
                        "WHERE subject_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, subjectId);

            try (ResultSet rs =
                         stmt.executeQuery()) {

                if (rs.next()) {

                    Subject subject =
                            new Subject();

                    subject.setSubjectId(
                            rs.getInt("subject_id")
                    );

                    subject.setSubjectName(
                            rs.getString("subject_name")
                    );

                    subject.setCourseId(
                            rs.getInt("course_id")
                    );

                    return subject;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error finding subject: "
                            + e.getMessage()
            );
        }

        return null;
    }

    // =========================================================
    // UPDATE SUBJECT
    // =========================================================

    public boolean updateSubject(Subject subject) {

        String sql =
                "UPDATE subjects SET " +
                        "subject_name = ?, " +
                        "course_id = ? " +
                        "WHERE subject_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    subject.getSubjectName()
            );

            stmt.setInt(
                    2,
                    subject.getCourseId()
            );

            stmt.setInt(
                    3,
                    subject.getSubjectId()
            );

            int rows =
                    stmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error updating subject: "
                            + e.getMessage()
            );

            return false;
        }
    }

    // =========================================================
    // DELETE SUBJECT
    // =========================================================

    public boolean deleteSubject(int subjectId) {

        String sql =
                "DELETE FROM subjects " +
                        "WHERE subject_id = ?";

        try (
                Connection conn =
                        DatabaseConnection.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, subjectId);

            int rows =
                    stmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting subject: "
                            + e.getMessage()
            );

            return false;
        }
    }
}