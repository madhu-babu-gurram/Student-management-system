import java.sql.*;
import java.util.*;

public class StudentDAOImpl implements StudentDAO {

    public void addStudent(Student s) {
        String sql = "INSERT INTO students (name, age, grade, username) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getName());
            stmt.setInt(2, s.getAge());
            stmt.setString(3, s.getGrade());
            stmt.setString(4, s.getUsername());
            stmt.executeUpdate();
            System.out.println("✅ Student added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents(String username) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("grade"),
                    rs.getString("username")
                );
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Student getStudentById(int id, String username) {
        String sql = "SELECT * FROM students WHERE id = ? AND username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("grade"),
                    rs.getString("username")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStudent(Student s) {
        String sql = "UPDATE students SET name=?, age=?, grade=? WHERE id=? AND username=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getName());
            stmt.setInt(2, s.getAge());
            stmt.setString(3, s.getGrade());
            stmt.setInt(4, s.getId());
            stmt.setString(5, s.getUsername());

            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("✅ Student updated successfully.");
            else System.out.println("❌ Update failed. Record not found or not yours.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id, String username) {
        String sql = "DELETE FROM students WHERE id = ? AND username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, username);

            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("✅ Student deleted successfully.");
            else System.out.println("❌ Delete failed. Record not found or not yours.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
