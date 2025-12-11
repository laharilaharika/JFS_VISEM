package com.example;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/skillnext_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // change this

    // Add Student
    public void addStudent(Student s) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "INSERT INTO student (name, sem, dept) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, s.getName());
        stmt.setInt(2, s.getSem());
        stmt.setString(3, s.getDept());
        stmt.executeUpdate();
        conn.close();
    }

    // Fetch all students
    public List<Student> getAllStudents() throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM student");

        List<Student> list = new ArrayList<>();
        while (rs.next()) {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setSem(rs.getInt("sem"));
            s.setDept(rs.getString("dept"));
            list.add(s);
        }
        conn.close();
        return list;
    }

    // Delete student
    public void deleteStudent(int id) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "DELETE FROM student WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        conn.close();
    }

    // Update student
    public void updateStudent(Student s) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "UPDATE student SET name=?, sem=?, dept=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, s.getName());
        stmt.setInt(2, s.getSem());
        stmt.setString(3, s.getDept());
        stmt.setInt(4, s.getId());
        stmt.executeUpdate();
        conn.close();
    }

    // ================================
    // MAIN METHOD WITH SWITCH CASE MENU
    // ================================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int ch = sc.nextInt();

            try {
                switch (ch) {

                    case 1: // INSERT
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Sem: ");
                        int sem = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Dept: ");
                        String dept = sc.nextLine();

                        Student s = new Student(name, sem, dept);
                        dao.addStudent(s);
                        System.out.println("Student Added Successfully!");
                        break;

                    case 2: // DISPLAY
                        List<Student> list = dao.getAllStudents();
                        for (Student st : list) {
                            System.out.println(st);
                        }
                        break;

                    case 3: // UPDATE
                        System.out.print("Enter Student ID: ");
                        int uid = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Sem: ");
                        int newSem = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Dept: ");
                        String newDept = sc.nextLine();

                        Student us = new Student(newName, newSem, newDept);
                        us.setId(uid);

                        dao.updateStudent(us);
                        System.out.println("Student Updated Successfully!");
                        break;

                    case 4: // DELETE
                        System.out.print("Enter Student ID: ");
                        int did = sc.nextInt();

                        dao.deleteStudent(did);
                        System.out.println("Student Deleted Successfully!");
                        break;

                    case 5: // EXIT
                        System.out.println("Exiting...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}