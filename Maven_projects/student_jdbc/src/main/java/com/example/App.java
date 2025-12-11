package com.example;

import java.util.*;

public class App {
    public static void main(String[] args) {
        try {
            StudentDAO dao = new StudentDAO();

            // Add Student
            Student s1 = new Student("Raju", 3, "CSE");
            dao.addStudent(s1);
            System.out.println("Student Added Successfully!");

            // Fetch students
            List<Student> students = dao.getAllStudents();
            for (Student s : students) {
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
