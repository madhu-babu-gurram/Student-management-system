import java.util.List;

public interface StudentDAO {
    void addStudent(Student student);
    List<Student> getAllStudents(String username);
    Student getStudentById(int id, String username);
    void updateStudent(Student student);
    void deleteStudent(int id, String username);
}
