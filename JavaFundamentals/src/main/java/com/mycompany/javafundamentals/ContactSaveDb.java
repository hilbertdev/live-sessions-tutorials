package com.mycompany.javafundamentals;

import java.sql.*;
import java.util.Scanner;

public class ContactSaveDb {

    // File-based DB instead of in-memory
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sys?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "H1lb@tM00ch";
// saves to file

    /**
     * Initialize the database and create table if it doesn't exist.
     * Demonstrates the use of SQL CREATE TABLE statement.
     */
    public static Connection initDatabase() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        String createTableSQL = "CREATE TABLE IF NOT EXISTS contacts (name TEXT, age INTEGER)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        }
        System.out.println("Database initialized at: " + new java.io.File("contacts.db").getAbsolutePath());
        return conn;
    }
    
      public static Connection initDatabaseV2() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        String createTableSQL = "CREATE TABLE IF NOT EXISTS contacts (name TEXT, age INTEGER)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        }
        System.out.println("Database initialized at: " + new java.io.File("contacts.db").getAbsolutePath());
        return conn;
    }


    /**
     * Collect user input for name and age.
     */
    public static String[] getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return new String[]{name, String.valueOf(age)};
    }

    /**
     * Insert a new contact using PreparedStatement (preferred for parameters
     * and security). Example SQL: INSERT INTO contacts (name, age) VALUES (?,
     * ?)
     */
    public static void insertContact(Connection conn, String name, String age) throws SQLException {
        String sql = "INSERT INTO contacts (name, age) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, Integer.parseInt(age));
            pstmt.executeUpdate();
            System.out.println("Contact inserted.");
        }
    }

    /**
     * Show all contacts using a Statement and SELECT query. //getContacts
     * Example SQL: SELECT name, age FROM contacts
     */
    public static void showAllContacts(Connection conn) throws SQLException {
        String sql = "SELECT name, age FROM contacts";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nSaved Contacts:");
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name") + ", Age: " + rs.getInt("age"));
            }
        }
    }

    /**
     * Update contact age using PreparedStatement. Example SQL: UPDATE contacts
     * SET age = ? WHERE name = ?
     */
    public static void updateAge(Connection conn, String name, int newAge) throws SQLException {
        String sql = "UPDATE contacts SET age = ? WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newAge);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            System.out.println("Age updated.");
        }
    }

    /**
     * Delete a contact using PreparedStatement. Example SQL: DELETE FROM
     * contacts WHERE name = ?
     */
    public static void deleteContact(Connection conn, String name) throws SQLException {
        String sql = "DELETE FROM contacts WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Contact deleted.");
        }
    }

    public static void main(String[] args) {
        try (Connection conn = initDatabase()) {

            // 1. Insert new contact (using PreparedStatement)
            String[] input = getUserInput();
            insertContact(conn, input[0], input[1]);

            // 2. Show all contacts (using Statement)
            showAllContacts(conn);

            // 3. Update age (using PreparedStatement)
            updateAge(conn, input[0], Integer.parseInt(input[1]) + 1);
            showAllContacts(conn);

            // 4. Delete contact (using PreparedStatement)
            //deleteContact(conn, input[0]);
            showAllContacts(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
