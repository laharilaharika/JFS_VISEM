package com.skillnext1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addStudent")
public class StudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("studentName");
        String dept = request.getParameter("department");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Student Added Successfully</h2>");
        out.println("Name: " + name + "<br>");
        out.println("Department: " + dept);
    }
}
