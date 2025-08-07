package com.mycompany.javafundamentals;

import java.io.*;
import java.util.Scanner;

public class ContactSaver {

    // Method to collect input from the user
    public static String[] getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline

        return new String[]{name, String.valueOf(age)};
    }

    // Method to write contact info to a file
    public static void writeToFile(String name, String age) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt", true))) {
            writer.write(name + "," + age);
            writer.newLine();
            System.out.println("Contact saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read and display all contacts from the file
    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            System.out.println("\nSaved Contacts:");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    System.out.println("Name: " + parts[0] + ", Age: " + parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Final method to tie everything together
    public static void runContactSaver() {
        String[] input = getUserInput();
        writeToFile(input[0], input[1]);
        readFromFile();
    }

    // Main method for testing or demo
    public static void main(String[] args) {
        runContactSaver();
    }
}
