/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafundamentals;

/**
 *
 * @author hilbertmu
 */
import java.io.*;

public class CopyBinaryFile {

    public static void main(String[] args) {
        CreateSourceDat();
        File inputFile = new File("source.dat");
        File outputFile = new File("destination.dat");

        try (InputStream inputStream = new FileInputStream(inputFile); OutputStream outputStream = new FileOutputStream(outputFile)) {

            int byteData;
            while ((byteData = inputStream.read()) != -1) {
                outputStream.write(byteData);
            }
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.out.println("Error during file copy: " + e.getMessage());
        }
    }

    public static void CreateSourceDat() {
        byte[] data = "Hello Binary!".getBytes();

        try (FileOutputStream fos = new FileOutputStream("source.dat")) {
            fos.write(data);
            System.out.println("source.dat created successfully.");
        } catch (IOException e) {
            System.out.println("Error writing source.dat: " + e.getMessage());
        }
    }
}
