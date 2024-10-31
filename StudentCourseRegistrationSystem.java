package package1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private ArrayList<Student> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.size() < capacity && !registeredStudents.contains(student)) {
            registeredStudents.add(student);
            student.addCourse(this);
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        if (registeredStudents.remove(student)) {
            student.removeCourse(this);
            return true;
        }
        return false;
    }
}

class Student {
    private String studentID;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void addCourse(Course course) {
        registeredCourses.add(course);
    }

    public void removeCourse(Course course) {
        registeredCourses.remove(course);
    }

    public void displayRegisteredCourses() {
        System.out.println("\n📜 Courses registered by " + name + " (" + studentID + "):");
        if (registeredCourses.isEmpty()) {
            System.out.println("🔹 No courses registered.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println("✅ " + course.getCourseCode() + ": " + course.getTitle());
            }
        }
    }
}

class CourseRegistrationSystem {
    private HashMap<String, Course> courseCatalog;
    private HashMap<String, Student> studentDatabase;

    public CourseRegistrationSystem() {
        courseCatalog = new HashMap<>();
        studentDatabase = new HashMap<>();
    }

    public void addCourse(Course course) {
        courseCatalog.put(course.getCourseCode(), course);
    }

    public void addStudent(Student student) {
        studentDatabase.put(student.getStudentID(), student);
    }

    public Student getStudentById(String studentID) {
        return studentDatabase.get(studentID);
    }

    public void displayAvailableCourses() {
        System.out.println("\n📚 Available Courses:");
        for (Course course : courseCatalog.values()) {
            System.out.println("📘 Course Code: " + course.getCourseCode());
            System.out.println("📖 Title: " + course.getTitle());
            System.out.println("📈 Available Slots: " + course.getAvailableSlots());
            System.out.println("------------------------");
        }
    }

    public void registerStudentToCourse(String studentID, String courseCode) {
        Student student = getStudentById(studentID);
        Course course = courseCatalog.get(courseCode);
        if (student != null && course != null) {
            if (course.registerStudent(student)) {
                System.out.println("🎉 Registration successful! " + student.getName() + " has been registered for " + course.getTitle());
            } else {
                System.out.println("⚠️ Registration failed: Course may be full or student already registered.");
            }
        } else {
            System.out.println("❌ Invalid student ID or course code.");
        }
    }

    public void dropStudentFromCourse(String studentID, String courseCode) {
        Student student = getStudentById(studentID);
        Course course = courseCatalog.get(courseCode);
        if (student != null && course != null) {
            if (course.removeStudent(student)) {
                System.out.println("🚫 Course dropped successfully! " + student.getName() + " has been removed from " + course.getTitle());
            } else {
                System.out.println("⚠️ Drop failed: Student not registered in this course.");
            }
        } else {
            System.out.println("❌ Invalid student ID or course code.");
        }
    }
}

public class StudentCourseRegistrationSystem {
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Sample data
        system.addCourse(new Course("CS101", "Introduction to Computer Science", "Basics of programming and algorithms", 3, "MWF 10-11 AM"));
        system.addCourse(new Course("IT201", "Introduction to Cloud Computing", "Basics of Cloud Computing", 5, "TTh 2-3:30 PM"));
        
        system.addStudent(new Student("S123", "Homi"));
        system.addStudent(new Student("S124", "Harsh"));

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- 📘 Course Registration System ---");
            System.out.println("1. 📚 View Available Courses");
            System.out.println("2. 📝 Register for a Course");
            System.out.println("3. 🚫 Drop a Course");
            System.out.println("4. 📜 View Registered Courses");
            System.out.println("5. ❌ Exit");
            System.out.print("👉 Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.displayAvailableCourses();
                    break;
                case 2:
                    System.out.print("👤 Enter Student ID: ");
                    String studentID = scanner.next();
                    System.out.print("📘 Enter Course Code: ");
                    String courseCode = scanner.next();
                    system.registerStudentToCourse(studentID, courseCode);
                    break;
                case 3:
                    System.out.print("👤 Enter Student ID: ");
                    studentID = scanner.next();
                    System.out.print("📘 Enter Course Code: ");
                    courseCode = scanner.next();
                    system.dropStudentFromCourse(studentID, courseCode);
                    break;
                case 4:
                    System.out.print("👤 Enter Student ID: ");
                    studentID = scanner.next();
                    Student student = system.getStudentById(studentID);
                    if (student != null) {
                        student.displayRegisteredCourses();
                    } else {
                        System.out.println("❌ Invalid student ID.");
                    }
                    break;
                case 5:
                    System.out.println("👋 Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("⚠️ Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
