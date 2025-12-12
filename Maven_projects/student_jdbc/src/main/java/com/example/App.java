package com.example;

import java.util.*;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {

            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1. Insert Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            try {

                switch (choice) {

                    // Insert
                    case 1:
                        sc.nextLine(); // clear buffer
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Semester: ");
                        int sem = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Department: ");
                        String dept = sc.nextLine();

                        Student s = new Student(name, sem, dept);
                        dao.addStudent(s);
                        System.out.println("Student Added Successfully!");
                        break;

                    // View
                    case 2:
                        List<Student> list = dao.getAllStudents();
                        for (Student st : list) {
                            System.out.println(st);
                        }
                        break;

                    // Update
                    case 3:
                        System.out.print("Enter Student ID to Update: ");
                        int uid = sc.nextInt();
                        sc.nextLine();

                        System.out.print("New Name: ");
                        String newName = sc.nextLine();

                        System.out.print("New Semester: ");
                        int newSem = sc.nextInt();
                        sc.nextLine();

                        System.out.print("New Department: ");
                        String newDept = sc.nextLine();

                        Student updated = new Student(newName, newSem, newDept);
                        updated.setId(uid);

                        dao.updateStudent(updated);
                        System.out.println("Student Updated Successfully!");
                        break;

                    // Delete
                    case 4:
                        System.out.print("Enter Student ID to Delete: ");
                        int id = sc.nextInt();

                        dao.deleteStudent(id);
                        System.out.println("Student Deleted Successfully!");
                        break;

                    // Exit
                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice! Try again.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}