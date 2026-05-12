import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * University of the People Nigeria
 * Course Enrollment and Grade Management System
 */
public class UniversityApp {
    private static final String UNIVERSITY_NAME = "University of the People Nigeria";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("===============================================");
        System.out.println("Welcome to " + UNIVERSITY_NAME);
        System.out.println("Course Enrollment and Grade Management System");
        System.out.println("===============================================");

        while (running) {
            printMenu();
            int choice = readIntInput(scanner, "Enter your choice: ");

            try {
                switch (choice) {
                    case 1 -> addCourseFlow(scanner);
                    case 2 -> enrollStudentFlow(scanner);
                    case 3 -> assignGradeFlow(scanner);
                    case 4 -> calculateOverallGradeFlow(scanner);
                    case 5 -> displayStudentInfoFlow(scanner);
                    case 6 -> CourseManagement.displayAllCourses();
                    case 7 -> {
                        running = false;
                        System.out.println("Exiting system. Goodbye!");
                    }
                    default -> System.out.println("Invalid menu choice. Please select between 1 and 7.");
                }
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.out.println("Operation failed: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Unexpected error occurred: " + ex.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------- Administrator Menu -----------");
        System.out.println("1. Add Course");
        System.out.println("2. Enroll Student");
        System.out.println("3. Assign Grade");
        System.out.println("4. Calculate Overall Grade");
        System.out.println("5. Display Student Information");
        System.out.println("6. Display Course Information");
        System.out.println("7. Exit");
    }

    private static void addCourseFlow(Scanner scanner) {
        String code = readStringInput(scanner, "Enter course code: ");
        String name = readStringInput(scanner, "Enter course name: ");
        int capacity = readIntInput(scanner, "Enter maximum capacity: ");

        CourseManagement.addCourse(code, name, capacity);
        System.out.println("Course added successfully.");
    }

    private static void enrollStudentFlow(Scanner scanner) {
        String studentId = readStringInput(scanner, "Enter student ID: ");
        String studentName = readStringInput(scanner, "Enter student name: ");
        String courseCode = readStringInput(scanner, "Enter course code to enroll: ");

        CourseManagement.enrollStudent(studentId, studentName, courseCode);
        System.out.println("Student enrolled successfully.");
    }

    private static void assignGradeFlow(Scanner scanner) {
        String studentId = readStringInput(scanner, "Enter student ID: ");
        String courseCode = readStringInput(scanner, "Enter course code: ");
        double grade = readDoubleInput(scanner, "Enter grade (0-100): ");

        CourseManagement.assignGrade(studentId, courseCode, grade);
        System.out.println("Grade assigned successfully.");
    }

    private static void calculateOverallGradeFlow(Scanner scanner) {
        String studentId = readStringInput(scanner, "Enter student ID: ");
        double average = CourseManagement.calculateOverallGrade(studentId);
        System.out.printf("Overall average grade for student %s: %.2f%n", studentId.toUpperCase(), average);
    }

    private static void displayStudentInfoFlow(Scanner scanner) {
        String studentId = readStringInput(scanner, "Enter student ID: ");
        CourseManagement.getStudent(studentId).displayStudentDetails();
    }

    private static String readStringInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            if (value != null && !value.isBlank()) {
                return value.trim();
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static int readIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid integer input. Please try again.");
            }
        }
    }

    private static double readDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid numeric input. Please try again.");
            }
        }
    }
}
