import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoginService loginService = new LoginService();
        String username = null;

        System.out.println("===== Welcome to Student Management System =====");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Choose an option: ");
        int opt = sc.nextInt();
        sc.nextLine();

        if (opt == 1) {
            System.out.print("Username: ");
            username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            if (!loginService.authenticate(username, password)) {
                System.out.println("Invalid login. Exiting...");
                return;
            }
            System.out.println("Login successful!");

        } else if (opt == 2) {
            System.out.print("Choose a username: ");
            username = sc.nextLine();
            System.out.print("Choose a password: ");
            String password = sc.nextLine();

            if (!loginService.register(username, password)) {
                System.out.println("Registration failed.");
                return;
            }

            System.out.print("Login now. Username: ");
            username = sc.nextLine();
            System.out.print("Password: ");
            password = sc.nextLine();

            if (!loginService.authenticate(username, password)) {
                System.out.println("Login failed after registration.");
                return;
            }
        } else {
            System.out.println("Invalid option.");
            return;
        }

        StudentDAO dao = new StudentDAOImpl();

        while (true) {
            System.out.println("\n===== Student Menu (User: " + username + ") =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    System.out.print("Enter grade: ");
                    String grade = sc.next();
                    dao.addStudent(new Student(0, name, age, grade, username));
                }
                case 2 -> {
                    List<Student> list = dao.getAllStudents(username);
                    if (list.isEmpty()) System.out.println("No records found.");
                    else list.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Enter student ID: ");
                    int id = sc.nextInt();
                    Student s = dao.getStudentById(id, username);
                    if (s != null) System.out.println(s);
                    else System.out.println("Student not found.");
                }
                case 4 -> {
                    System.out.print("Enter student ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("New name: ");
                    String name = sc.nextLine();
                    System.out.print("New age: ");
                    int age = sc.nextInt();
                    System.out.print("New grade: ");
                    String grade = sc.next();
                    dao.updateStudent(new Student(id, name, age, grade, username));
                }
                case 5 -> {
                    System.out.print("Enter student ID to delete: ");
                    int id = sc.nextInt();
                    dao.deleteStudent(id, username);
                }
                case 6 -> {
                    System.out.println("Logged out. Bye " + username + "!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
