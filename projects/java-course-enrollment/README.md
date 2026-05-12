# Course Enrollment and Grade Management System

**University:** University of the People Nigeria

This Java console application demonstrates core Object-Oriented Programming (OOP) concepts:
- Encapsulation
- Static variables and static methods
- Instance methods
- Access modifiers
- Object interaction between classes
- Command line user interaction
- Exception handling
- Data management with collections

## Project Structure

```text
projects/java-course-enrollment/
├── README.md
└── src/
    ├── Course.java
    ├── Student.java
    ├── CourseManagement.java
    └── UniversityApp.java
```

## Class Documentation

### 1) `Student`
- **Purpose:** Stores student profile, enrolled courses, and grades.
- **Private variables:**
  - `studentName`
  - `studentId`
  - `enrolledCourses` (`ArrayList<Course>`)
  - `grades` (`HashMap<String, Double>`)
- **Key methods:**
  - `enrollCourse(Course course)`: checks duplicates and course capacity before enrolling.
  - `assignGrade(Course course, double grade)`: assigns/updates course grade.
  - `displayStudentDetails()`: prints student profile with course-grade details.

### 2) `Course`
- **Purpose:** Stores course metadata and manages enrolled student list.
- **Private variables:**
  - `courseCode`
  - `courseName`
  - `maximumCapacity`
  - `enrolledStudents`
- **Static variable:**
  - `totalEnrolledStudents`: tracks enrollment across all courses.
- **Static method:**
  - `getTotalEnrolledStudents()`
- **Key behavior:**
  - Prevents over-enrollment.
  - Prevents duplicate student enrollment in the same course.

### 3) `CourseManagement`
- **Purpose:** Central static service layer for course/student operations.
- **Private static collections:**
  - `allCourses`
  - `allStudents`
  - `overallStudentGrades`
- **Static methods:**
  - `addCourse()`
  - `enrollStudent()`
  - `assignGrade()`
  - `calculateOverallGrade()`
  - `displayAllCourses()`
- **Role in architecture:**
  - Delegates enrollment and grading to `Student` objects.
  - Coordinates interaction between `Student` and `Course` objects.

### 4) `UniversityApp`
- **Purpose:** Menu-driven CLI for administrators.
- **Menu options:**
  1. Add Course
  2. Enroll Student
  3. Assign Grade
  4. Calculate Overall Grade
  5. Display Student Information
  6. Display Course Information
  7. Exit
- **Input handling:**
  - Uses `Scanner` for command-line interaction.
  - Validates and sanitizes numeric and string inputs.
  - Uses try-catch to prevent crashes.

## How Static Variables Are Used

- `Course.totalEnrolledStudents` increases every time a student is successfully enrolled in any course.
- `Course.getTotalEnrolledStudents()` provides a global enrollment metric for reporting.

## How Enrollment Tracking Works

1. Admin creates courses using `addCourse()`.
2. Admin enrolls students using `enrollStudent()`.
3. `Student.enrollCourse()` validates duplicate enrollment and capacity.
4. `Course.enrollStudent()` adds student and increments global static count.

## How Grade Calculation Works

1. Admin assigns grades per student-course using `assignGrade()`.
2. Grades are stored in both student-level and management-level maps.
3. `calculateOverallGrade()` averages all available grades for a student.

## Program Execution Flow

1. Launch `UniversityApp`.
2. Select menu options.
3. Each operation calls `CourseManagement` static methods.
4. `CourseManagement` interacts with `Student` and `Course` objects.
5. Results and errors are displayed with clear messages.

## Compile and Run Instructions

From repository root:

```bash
cd projects/java-course-enrollment/src
javac *.java
java UniversityApp
```

## Example Output (Sample Execution)

```text
===============================================
Welcome to University of the People Nigeria
Course Enrollment and Grade Management System
===============================================

----------- Administrator Menu -----------
1. Add Course
2. Enroll Student
3. Assign Grade
4. Calculate Overall Grade
5. Display Student Information
6. Display Course Information
7. Exit
Enter your choice: 1
Enter course code: CSC101
Enter course name: Introduction to Programming
Enter maximum capacity: 2
Course added successfully.

Enter your choice: 2
Enter student ID: UOP001
Enter student name: Ada Eze
Enter course code to enroll: CSC101
Student enrolled successfully.

Enter your choice: 3
Enter student ID: UOP001
Enter course code: CSC101
Enter grade (0-100): 89.5
Grade assigned successfully.

Enter your choice: 4
Enter student ID: UOP001
Overall average grade for student UOP001: 89.50
```

## Screenshot Section

For submission, run the app locally and capture terminal screenshots showing:
- Course creation
- Student enrollment
- Grade assignment
- Overall grade calculation
- Course and student display output

(If required by your instructor, paste screenshots into a Word/PDF report.)
