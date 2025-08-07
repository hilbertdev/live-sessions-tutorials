/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafundamentals;

import com.mycompany.javafundamentals.CustomCallbackFunctions.OnSaveCallback;

/**
 *
 * @author hilbertmu
 */
public class Dog implements OnSaveCallback {

    @Override
    public void onSuccess(String name) {
        System.out.println("i am a dog i just bark"); 
    }

    @Override
    public void onFailure(String error) {
        System.out.println("Contact for " + error + " saved successfully."); 
    }



}
