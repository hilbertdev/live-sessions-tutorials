package com.mycompany.javafundamentals;

import java.io.*;
import java.util.Scanner;

public class CustomCallbackFunctions {

    // --- Callback Interfaces ---

    interface OnSaveCallback {
        void onSuccess(String name);
        void onFailure(String error);
    }

    interface OnReadCallback {
        void onContactRead(String name, String age);
    }

    // --- Input Logic ---

    public static String[] getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline

        return new String[]{name, String.valueOf(age)};
    }

    // --- File Writing Logic with Callback ---

    public static void writeToFile(String name, String age, OnSaveCallback callback) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt", true))) {
            writer.write(name + "," + age);
            writer.newLine();
            if (callback != null) {
                callback.onSuccess(name);
            }
        } catch (IOException e) {
            if (callback != null) {
                callback.onFailure(e.getMessage());
            }
        }
    }

    // --- File Reading Logic with Callback ---

    public static void readFromFile(OnReadCallback callback) {
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && callback != null) {
                    callback.onContactRead(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // --- Main Control Flow ---
    
   

    public static void runContactSaver() {
        String[] input = getUserInput();

        // Define behavior for save
        OnSaveCallback saveCallback = new OnSaveCallback() {
            public void onSuccess(String name) {
                System.out.println("Contact for " + name + " saved successfully.");
            }

            public void onFailure(String error) {
                System.out.println("Failed to save contact: " + error);
            }
        };

        // Define behavior for reading
        OnReadCallback readCallback = (name, age) -> {
            System.out.println("Name: " + name + ", Age: " + age);
        };

        // Save and read using callbacks
        writeToFile(input[0], input[1], saveCallback);
        System.out.println("\n🔍 Reading saved contacts:");
        readFromFile(readCallback);
    }

    // --- Entry Point ---
    public static void main(String[] args) {
        runContactSaver();
    }
}
