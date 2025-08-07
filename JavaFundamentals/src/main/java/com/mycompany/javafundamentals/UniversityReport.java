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

public class UniversityReport {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/university?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "readonly_user";
    private static final String DB_PASSWORD = "securepass123";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to University DB.");

            // 1. Query the view
            Statement stmt = conn.createStatement();
            ResultSet viewRs = stmt.executeQuery("SELECT * FROM cs_high_gpa_students");

            System.out.println("---- High GPA CS Students ----");
            while (viewRs.next()) {
                System.out.println(viewRs.getString("name") + " - GPA: " + viewRs.getDouble("gpa"));
            }

            // 2. Call the stored procedure
            String proc = "{ CALL report_student_analytics(?) }";
            PreparedStatement pstmt = conn.prepareStatement(proc);
            pstmt.setString(1, "J"); // Only students whose name starts with 'J'
            ResultSet procRs = pstmt.executeQuery();

            System.out.println("\n---- Students Starting with 'J' ----");
            while (procRs.next()) {
                System.out.println(
                    procRs.getString("student_name") + " - GPA: " + procRs.getDouble("estimated_gpa")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

