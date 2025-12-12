package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/skillnext_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Get Connection
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Insert Student
    public int addStudent(Student s) throws Exception {
        Connection conn = getConnection();
        String sql = "INSERT INTO student (name, sem, dept) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, s.getName());
        stmt.setInt(2, s.getSem());
        stmt.setString(3, s.getDept());

        int rows = stmt.executeUpdate();
        conn.close();
        return rows;
    }

    // Get All Students
    public List<Student> getAllStudents() throws Exception {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM student");

        List<Student> list = new ArrayList<>();

        while (rs.next()) {
            Student st = new Student();
            st.setId(rs.getInt("id"));
            st.setName(rs.getString("name"));
            st.setSem(rs.getInt("sem"));
            st.setDept(rs.getString("dept"));
            list.add(st);
        }

        conn.close();
        return list;
    }

    // Delete Student
    public int deleteStudent(int id) throws Exception {
        Connection conn = getConnection();
        String sql = "DELETE FROM student WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        int rows = stmt.executeUpdate();
        conn.close();
        return rows;
    }

    // Update Student
    public int updateStudent(Student s) throws Exception {
        Connection conn = getConnection();
        String sql = "UPDATE student SET name=?, sem=?, dept=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, s.getName());
        stmt.setInt(2, s.getSem());
        stmt.setString(3, s.getDept());
        stmt.setInt(4, s.getId());

        int rows = stmt.executeUpdate();
        conn.close();
        return rows;
    }

    // Check if Student Exists
    public boolean exists(int id) throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT id FROM student WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        boolean found = rs.next();
        conn.close();
        return found;
    }
}