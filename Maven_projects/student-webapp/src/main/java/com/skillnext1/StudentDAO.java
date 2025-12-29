package com.skillnext1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StudentDAO {

    public static void save(Student stu) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/skillnext_db",
                "root",
                "root"
            );

            String sql = "INSERT INTO student(name, sem, dept, course, email) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, stu.getName());
            ps.setInt(2, stu.getSem());
            ps.setString(3, stu.getDept());
            ps.setString(4, stu.getCourse());
            ps.setString(5, stu.getEmail());

            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
