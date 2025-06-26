public class Student {
    private int id;
    private String name;
    private int age;
    private String grade;
    private String username;

    public Student(int id, String name, int age, String grade, String username) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.username = username;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGrade() { return grade; }
    public String getUsername() { return username; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setUsername(String username) { this.username = username; }

    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age + " | Grade: " + grade;
    }
}
