package com.example;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/skillnext_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Get connection
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Insert student
    public int addStudent(Student s) throws Exception {
        Connection conn = getConnection();
        String sql = "INSERT INTO student (name, sem, dept) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, s.getName());
        stmt.setInt(2, s.getSem());
        stmt.setString(3, s.getDept());

        int rows = stmt.executeUpdate();
        stmt.close();
        conn.close();
        return rows;
    }

    // Fetch all students
    public List<Student> getAllStudents() throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT * FROM student";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Student> list = new ArrayList<>();
        while (rs.next()) {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setSem(rs.getInt("sem"));
            s.setDept(rs.getString("dept"));
            list.add(s);
        }

        rs.close();
        stmt.close();
        conn.close();
        return list;
    }

    // Update student
    public int updateStudent(Student s) throws Exception {
        Connection conn = getConnection();
        String sql = "UPDATE student SET name=?, sem=?, dept=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, s.getName());
        stmt.setInt(2, s.getSem());
        stmt.setString(3, s.getDept());
        stmt.setInt(4, s.getId());

        int rows = stmt.executeUpdate();
        stmt.close();
        conn.close();
        return rows;
    }

    // Delete student
    public int deleteStudent(int id) throws Exception {
        Connection conn = getConnection();
        String sql = "DELETE FROM student WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);
        int rows = stmt.executeUpdate();
        stmt.close();
        conn.close();
        return rows;
    }

    // Check if student exists
    public boolean exists(int id) throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT id FROM student WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        boolean found = rs.next();

        rs.close();
        stmt.close();
        conn.close();
        return found;
    }

    // Branch-wise (Department-wise) student count
    public Map<String, Integer> getBranchWiseCount() throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT dept, COUNT(*) AS total FROM student GROUP BY dept";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        Map<String, Integer> branchCount = new HashMap<>();
        while (rs.next()) {
            branchCount.put(rs.getString("dept"), rs.getInt("total"));
        }

        rs.close();
        stmt.close();
        conn.close();
        return branchCount;
    }
}