import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Central manager for course and student operations using static collections.
 */
public class CourseManagement {
    private static Map<String, Course> allCourses = new HashMap<>();
    private static Map<String, Student> allStudents = new HashMap<>();
    private static Map<String, Map<String, Double>> overallStudentGrades = new HashMap<>();

    private CourseManagement() {}

    public static void addCourse(String code, String name, int capacity) {
        String normalizedCode = code.trim().toUpperCase();
        if (allCourses.containsKey(normalizedCode)) {
            throw new IllegalStateException("Course with this code already exists.");
        }
        Course course = new Course(normalizedCode, name, capacity);
        allCourses.put(normalizedCode, course);
    }

    public static Student getOrCreateStudent(String studentId, String studentName) {
        String normalizedStudentId = studentId.trim().toUpperCase();
        if (!allStudents.containsKey(normalizedStudentId)) {
            allStudents.put(normalizedStudentId, new Student(studentName, normalizedStudentId));
        }
        return allStudents.get(normalizedStudentId);
    }

    public static void enrollStudent(String studentId, String studentName, String courseCode) {
        Course course = getCourse(courseCode);
        Student student = getOrCreateStudent(studentId, studentName);
        student.enrollCourse(course);
    }

    public static void assignGrade(String studentId, String courseCode, double grade) {
        Student student = getStudent(studentId);
        Course course = getCourse(courseCode);
        student.assignGrade(course, grade);

        overallStudentGrades.putIfAbsent(student.getStudentId(), new HashMap<>());
        overallStudentGrades.get(student.getStudentId()).put(course.getCourseCode(), grade);
    }

    public static double calculateOverallGrade(String studentId) {
        Student student = getStudent(studentId);
        Map<String, Double> grades = overallStudentGrades.get(student.getStudentId());

        if (grades == null || grades.isEmpty()) {
            throw new IllegalStateException("No grades available for this student.");
        }

        double total = 0;
        for (double grade : grades.values()) {
            total += grade;
        }
        return total / grades.size();
    }

    public static void displayAllCourses() {
        if (allCourses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        System.out.println("\n--- Available Courses ---");
        for (Course course : allCourses.values()) {
            System.out.println(course);
        }
        System.out.println("Total enrolled students across all courses: " + Course.getTotalEnrolledStudents());
    }

    public static Student getStudent(String studentId) {
        String normalizedStudentId = studentId.trim().toUpperCase();
        Student student = allStudents.get(normalizedStudentId);
        if (student == null) {
            throw new IllegalStateException("Student not found.");
        }
        return student;
    }

    public static Course getCourse(String code) {
        String normalizedCode = code.trim().toUpperCase();
        Course course = allCourses.get(normalizedCode);
        if (course == null) {
            throw new IllegalStateException("Course not found.");
        }
        return course;
    }

    public static Collection<Student> getAllStudents() {
        return allStudents.values();
    }
}
