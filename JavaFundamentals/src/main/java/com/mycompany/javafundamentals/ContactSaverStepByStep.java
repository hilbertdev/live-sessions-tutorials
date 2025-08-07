package com.mycompany.javafundamentals;

import java.io.*;
import java.util.Scanner;

public class ContactSaverStepByStep {

    // STEP 1: Get a name from the user
    public static void getNameOnly() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();      
        System.out.println("You entered: " + name);  
    }

    // STEP 2: Get a name and age from the user
    public static String[] getNameAndAge() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Clear newline

        System.out.println("You entered: " + name + " (" + age + ")");
        return new String[]{name, String.valueOf(age)};
    }

    // STEP 3: Write one contact to a file
    public static void writeOneContact(String name, String age) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt", true))) {
            writer.write(name + "," + age);
            writer.newLine();
            System.out.println("Contact written to file.");
        } 
        catch (IOException e)
        {
     
            System.out.println("Write error io exception: " + e.getMessage());
        } 
        catch (Exception ex)
        {
            System.out.println("Write error: " + ex.getMessage());
        }
    }

    // STEP 4: Read all contacts from the file
    public static void readAllContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            System.out.println("\nReading from file:");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    System.out.println("Name: " + parts[0] + ", Age: " + parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }

    // FINAL BOSS: Full workflow
    public static void runFullContactSaver() {
        String[] input = getNameAndAge();
        writeOneContact(input[0], input[1]);
        readAllContacts();
    }

    public static void main(String[] args) {

        // STEP 1: Just get the name
        // getNameOnly();

        // STEP 2: Get name and age
       //  String[] input = getNameAndAge();

        // STEP 3: Write contact to file
        // writeOneContact("Hilbert", "60");

        // STEP 4: Read contacts from file
        // readAllContacts();

        // FINAL BOSS: Everything together
         runFullContactSaver();
    }
}
