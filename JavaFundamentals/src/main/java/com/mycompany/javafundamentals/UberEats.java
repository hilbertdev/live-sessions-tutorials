/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafundamentals;

/**
 *
 * @author hilbertmu
 */
public class UberEats {
    public static void OrderFood(String foodName, ICommunicator comms)
    {
        comms.NotifyCustomer("06342323");
    }
    
    
     public static void main(String[] args) {
         
     var comms = new Email();
       
     OrderFood("Pizza",comms);
    }
}
