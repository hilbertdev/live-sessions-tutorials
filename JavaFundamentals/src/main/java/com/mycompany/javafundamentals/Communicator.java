/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafundamentals;

/**
 *
 * @author hilbertmu
 */
public class Communicator implements ICommunicator { //SMS

    @Override
    public void NotifyCustomer(String ContactDetail) {
       
          System.out.println("Message sent to contact details:"); 
    }
    
}
