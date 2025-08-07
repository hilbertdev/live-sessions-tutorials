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
import java.awt.*;

public class SimpleWindowWithComponents {

    public static void main(String[] args) {
        JFrame frame = new JFrame("My First Window");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout before adding components
        frame.setLayout(new FlowLayout()); // arranges components

        JLabel label = new JLabel("Hello Swing!");
        JButton button = new JButton("Click Me");

        frame.add(label);
        frame.add(button);

        frame.setVisible(true); // Show the frame after adding components
    }
}

