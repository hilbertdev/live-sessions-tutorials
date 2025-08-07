/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafundamentals;

/**
 *
 * @author hilbertmu
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class UniversityReportGUI extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/university?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "readonly_user";
    private static final String DB_PASSWORD = "securepass123";

    private JTable resultTable;
    private JTextField prefixInput;

    public UniversityReportGUI() {
        setTitle("University Reporting Tool");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top input panel
        JPanel inputPanel = new JPanel();
        prefixInput = new JTextField(10);
        JButton fetchButton = new JButton("Fetch Student Report");
        inputPanel.add(new JLabel("Name starts with:"));
        inputPanel.add(prefixInput);
        inputPanel.add(fetchButton);

        add(inputPanel, BorderLayout.NORTH);

        // Result table
        resultTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultTable);
        add(scrollPane, BorderLayout.CENTER);

        // View button
        JButton viewButton = new JButton("View High GPA CS Students");
        add(viewButton, BorderLayout.SOUTH);

        fetchButton.addActionListener(e -> fetchStudentReport());
        viewButton.addActionListener(e -> fetchHighGPAView());
    }

    private void fetchStudentReport() {
        String prefix = prefixInput.getText().trim();
        if (prefix.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a prefix to search.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String proc = "{ CALL report_student_analytics(?) }";
            PreparedStatement stmt = conn.prepareStatement(proc);
            stmt.setString(1, prefix);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Name", "Email", "Year", "Department", "Courses", "A's", "GPA"}, 0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("student_name"),
                    rs.getString("email"),
                    rs.getInt("enrollment_year"),
                    rs.getString("department_name"),
                    rs.getInt("total_courses"),
                    rs.getInt("total_As"),
                    rs.getDouble("estimated_gpa")
                });
            }

            resultTable.setModel(model);

        } catch (SQLException e) {
            showError(e);
        }
    }

    private void fetchHighGPAView() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cs_high_gpa_students");

            DefaultTableModel model = new DefaultTableModel(new Object[]{"Name", "Email", "GPA"}, 0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getDouble("gpa")
                });
            }

            resultTable.setModel(model);

        } catch (SQLException e) {
            showError(e);
        }
    }

    private void showError(SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UniversityReportGUI().setVisible(true);
        });
    }
}

