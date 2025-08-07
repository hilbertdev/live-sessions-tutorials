/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javafundamentals;

/**
 *
 * @author hilbertmu
 */
class Clue {
    private final String description;
    private final boolean isCritical;

    public Clue(String description, boolean isCritical) {
        this.description = description;
        this.isCritical = isCritical;
    }

    public String getDescription() { return description; }
    public boolean isCritical() { return isCritical; }
}


