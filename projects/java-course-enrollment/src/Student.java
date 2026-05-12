import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a student and tracks enrolled courses and grades.
 */
public class Student {
    private String studentName;
    private String studentId;
    private List<Course> enrolledCourses;
    private Map<String, Double> grades;

    public Student(String studentName, String studentId) {
        setStudentName(studentName);
        setStudentId(studentId);
        this.enrolledCourses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    public String getStudentName() { return studentName; }

    public void setStudentName(String studentName) {
        if (studentName == null || studentName.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        this.studentName = studentName.trim();
    }

    public String getStudentId() { return studentId; }

    public void setStudentId(String studentId) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        this.studentId = studentId.trim().toUpperCase();
    }

    public List<Course> getEnrolledCourses() { return enrolledCourses; }
    public Map<String, Double> getGrades() { return grades; }

    public void enrollCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        if (enrolledCourses.contains(course)) {
            throw new IllegalStateException("Student is already enrolled in this course.");
        }
        if (!course.hasAvailableSlot()) {
            throw new IllegalStateException("Cannot enroll. Course has reached maximum capacity.");
        }

        boolean enrolled = course.enrollStudent(this);
        if (!enrolled) {
            throw new IllegalStateException("Enrollment failed due to duplicate entry.");
        }
        enrolledCourses.add(course);
    }

    public void assignGrade(Course course, double grade) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        if (!enrolledCourses.contains(course)) {
            throw new IllegalStateException("Cannot assign grade. Student is not enrolled in this course.");
        }
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
        grades.put(course.getCourseCode(), grade);
    }

    public void displayStudentDetails() {
        System.out.println("\n--- Student Details ---");
        System.out.println("Name: " + studentName);
        System.out.println("ID: " + studentId);

        if (enrolledCourses.isEmpty()) {
            System.out.println("Enrolled Courses: None");
        } else {
            System.out.println("Enrolled Courses:");
            for (Course course : enrolledCourses) {
                Double grade = grades.get(course.getCourseCode());
                String gradeText = grade == null ? "Not Assigned" : String.format("%.2f", grade);
                System.out.printf("- %s (%s) | Grade: %s%n", course.getCourseName(), course.getCourseCode(), gradeText);
            }
        }
    }
}
