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

public class SimpleWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My First Window");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
