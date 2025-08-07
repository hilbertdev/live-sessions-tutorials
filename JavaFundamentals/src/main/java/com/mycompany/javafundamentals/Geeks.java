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

public class Geeks {
    public static void main(String[] args) {
        // SQLite connection URL (creates test.db in project root)
        String url = "jdbc:sqlite:test.db";

        // Query to execute
        String query = "INSERT INTO students (id, name) VALUES (109, 'bhatt')";

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish connection
            Connection c = DriverManager.getConnection(url);

            // Create a table if not exists
            Statement stmt = c.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT)");

            // Insert data
            int count = stmt.executeUpdate(query);
            System.out.println("Rows affected: " + count);

            // Close resources
            stmt.close();
            c.close();
            System.out.println("Connection closed.");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
}

