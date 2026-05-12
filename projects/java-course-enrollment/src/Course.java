import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a university course with a fixed enrollment capacity.
 * Demonstrates encapsulation, static members, and object interaction.
 */
public class Course {
    private String courseCode;
    private String courseName;
    private int maximumCapacity;
    private List<Student> enrolledStudents;

    private static int totalEnrolledStudents = 0;

    public Course(String courseCode, String courseName, int maximumCapacity) {
        if (courseCode == null || courseCode.isBlank()) {
            throw new IllegalArgumentException("Course code cannot be empty.");
        }
        if (courseName == null || courseName.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }
        if (maximumCapacity <= 0) {
            throw new IllegalArgumentException("Maximum capacity must be greater than zero.");
        }
        this.courseCode = courseCode.trim().toUpperCase();
        this.courseName = courseName.trim();
        this.maximumCapacity = maximumCapacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getMaximumCapacity() { return maximumCapacity; }

    public List<Student> getEnrolledStudents() {
        return Collections.unmodifiableList(enrolledStudents);
    }

    public int getCurrentEnrollmentCount() {
        return enrolledStudents.size();
    }

    public boolean hasAvailableSlot() {
        return getCurrentEnrollmentCount() < maximumCapacity;
    }

    public boolean enrollStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (enrolledStudents.contains(student)) {
            return false;
        }
        if (!hasAvailableSlot()) {
            throw new IllegalStateException("Course is already at full capacity.");
        }
        enrolledStudents.add(student);
        totalEnrolledStudents++;
        return true;
    }

    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (Capacity: %d, Enrolled: %d)",
                courseCode, courseName, maximumCapacity, getCurrentEnrollmentCount());
    }
}
