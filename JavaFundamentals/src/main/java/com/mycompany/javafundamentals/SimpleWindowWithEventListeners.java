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

public class SimpleWindowWithEventListeners {

    public static void main(String[] args) {
        JFrame frame = new JFrame("My First Window");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame.setLayout(new GridLayout(2, 2)); // arranges components
        //frame.setLayout(new FlowLayout()); // arranges components
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Center");
        JButton button = new JButton("North");
        JLabel label1 = new JLabel("East");
        JButton button2 = new JButton("South");

        // Add event listener to the button
        button.addActionListener(e -> {
            var action = e.getActionCommand();
            var eventType = e.getID();
            if (action.equals("Click Me")) {
                System.out.println(eventType); //1001
            } else {
                System.out.println("Button clicked!");

            }
        });

        frame.add(label);
        frame.add(button);
        frame.add(label1);
        frame.add(button2);
        
        frame.setVisible(true); // Show the frame after adding components
    }
}
