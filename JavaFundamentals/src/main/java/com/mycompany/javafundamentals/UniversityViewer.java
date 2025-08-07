/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafundamentals;

/**
 *
 * @author hilbertmu
 */
import java.sql.*;

public class UniversityViewer {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/university";
        String user = "readonly_user";
        String password = "securepass123";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to MySQL as readonly_user.");

            // 1. Query the view
            String viewQuery = "SELECT * FROM cs_high_gpa_students";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(viewQuery)) {

                System.out.println("\n--- High GPA CS Students ---");
                while (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    double gpa = rs.getDouble("gpa");
                    System.out.printf("Name: %s, Email: %s, GPA: %.2f%n", name, email, gpa);
                }
            }

            // 2. Call the stored procedure
            String callProc = "{ CALL report_student_analytics(?) }";
            try (PreparedStatement stmt = conn.prepareStatement(callProc)) {
                stmt.setString(1, "J"); // Search for students with names starting with 'J'
                try (ResultSet rs = stmt.executeQuery()) {
                    System.out.println("\n--- Report: Students starting with 'J' ---");
                    while (rs.next()) {
                        System.out.printf(
                            "Name: %s, Email: %s, GPA: %.2f, Dept: %s, Enrolled: %d, A's: %d%n",
                            rs.getString("student_name"),
                            rs.getString("email"),
                            rs.getDouble("estimated_gpa"),
                            rs.getString("department_name"),
                            rs.getInt("total_courses"),
                            rs.getInt("total_As")
                        );
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

