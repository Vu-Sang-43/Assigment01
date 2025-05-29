package com.HSF301.main;

import com.HSF301.pojo.Student;
import com.HSF301.pojo.Subject;
import com.HSF301.service.StudentService;
import com.HSF301.service.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final StudentService studentService = new StudentServiceImpl("persistence.xml");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Find Student by ID");
            System.out.println("5. List All Students");
            System.out.println("6. Add Subject to Student");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> findStudent();
                case 5 -> listStudents();
                case 6 -> addSubjectToStudent();
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addStudent() {
        Student student = new Student();
        List<Subject> subjects = new ArrayList<>();

        System.out.print("Enter email: ");
        student.setEmail(scanner.nextLine());

        System.out.print("Enter password: ");
        student.setPassword(scanner.nextLine());

        System.out.print("Enter first name: ");
        student.setFirstname(scanner.nextLine());

        System.out.print("Enter last name: ");
        student.setLastname(scanner.nextLine());

        System.out.print("Enter marks: ");
        student.setMarks(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        // Add subjects
        boolean addMoreSubjects = true;
        while (addMoreSubjects) {
            Subject subject = new Subject();
            System.out.print("Enter subject name: ");
            subject.setName_Subject(scanner.nextLine());
            subject.setStudentName(student.getFirstname() + " " + student.getLastname());
            subjects.add(subject);

            System.out.print("Add another subject? (yes/no): ");
            addMoreSubjects = scanner.nextLine().toLowerCase().startsWith("y");
        }

        student.setSubjects(subjects);
        studentService.save(student);
        System.out.println("Student and subjects added successfully!");
    }

    private static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = studentService.findById(id);
        if (student != null) {
            System.out.print("Update email? (yes/no): ");
            if (scanner.nextLine().toLowerCase().startsWith("y")) {
                System.out.print("Enter new email: ");
                student.setEmail(scanner.nextLine());
            }

            System.out.print("Update password? (yes/no): ");
            if (scanner.nextLine().toLowerCase().startsWith("y")) {
                System.out.print("Enter new password: ");
                student.setPassword(scanner.nextLine());
            }

            System.out.print("Update first name? (yes/no): ");
            if (scanner.nextLine().toLowerCase().startsWith("y")) {
                System.out.print("Enter new first name: ");
                student.setFirstname(scanner.nextLine());
            }

            System.out.print("Update last name? (yes/no): ");
            if (scanner.nextLine().toLowerCase().startsWith("y")) {
                System.out.print("Enter new last name: ");
                student.setLastname(scanner.nextLine());
            }

            System.out.print("Update marks? (yes/no): ");
            if (scanner.nextLine().toLowerCase().startsWith("y")) {
                System.out.print("Enter new marks: ");
                student.setMarks(scanner.nextInt());
                scanner.nextLine(); // Consume newline
            }

            System.out.print("Add new subjects? (yes/no): ");
            if (scanner.nextLine().toLowerCase().startsWith("y")) {
                boolean addMoreSubjects = true;
                while (addMoreSubjects) {
                    Subject subject = new Subject();
                    System.out.print("Enter subject name: ");
                    subject.setName_Subject(scanner.nextLine());
                    subject.setStudentName(student.getFirstname() + " " + student.getLastname());
                    subject.setStudent(student);

                    if (student.getSubjects() == null) {
                        student.setSubjects(new ArrayList<>());
                    }
                    student.getSubjects().add(subject);

                    System.out.print("Add another subject? (yes/no): ");
                    addMoreSubjects = scanner.nextLine().toLowerCase().startsWith("y");
                }
            }

            studentService.update(student);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        studentService.delete(id);
        System.out.println("Student deleted successfully!");
    }

    private static void findStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = studentService.findById(id);
        if (student != null) {
            // Display basic student info
            System.out.println("\nStudent Details:");
            System.out.println("ID: " + student.getId());
            System.out.println("Email: " + student.getEmail());
            System.out.println("First Name: " + student.getFirstname());
            System.out.println("Last Name: " + student.getLastname());
            System.out.println("Marks: " + student.getMarks());

            // Display subjects
            System.out.println("Enrolled Subjects:");
            List<Subject> subjects = student.getSubjects();
            if (subjects != null && !subjects.isEmpty()) {
                for (Subject subject : subjects) {
                    System.out.println("- Subject Name: " + subject.getName_Subject());
                }
            } else {
                System.out.println("No subjects enrolled");
            }
            System.out.println("---------------------");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void listStudents() {
        List<Student> students = studentService.findAll();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Students list:");
            students.forEach(Main::displayStudent);
        }
    }

    private static void displayStudent(Student student) {
        System.out.println("ID: " + student.getId());
        System.out.println("Email: " + student.getEmail());
        System.out.println("First Name: " + student.getFirstname());
        System.out.println("Last Name: " + student.getLastname());
        System.out.println("Marks: " + student.getMarks());
        System.out.println("Subjects:");
        if (student.getSubjects() != null && !student.getSubjects().isEmpty()) {
            student.getSubjects().forEach(subject ->
                    System.out.println("- " + subject.getName_Subject()));
        } else {
            System.out.println("No subjects enrolled");
        }
        System.out.println("---------------------");
    }
    private static void addSubjectToStudent() {
        System.out.println("\nAdd Subject to Existing Student");
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = studentService.findById(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        Subject subject = new Subject();
        System.out.print("Enter subject name: ");
        subject.setName_Subject(scanner.nextLine());
        subject.setStudentName(student.getFirstname() + " " + student.getLastname());
        subject.setStudent(student);

        List<Subject> subjects = student.getSubjects();
        if (subjects == null) {
            subjects = new ArrayList<>();
            student.setSubjects(subjects);
        }
        subjects.add(subject);

        try {
            studentService.update(student);
            System.out.println("Subject added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding subject: " + e.getMessage());
        }
    }
}