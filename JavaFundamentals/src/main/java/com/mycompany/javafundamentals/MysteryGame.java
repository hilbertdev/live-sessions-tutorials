package com.mycompany.javafundamentals;

import java.util.*;
import java.io.*;

public class MysteryGame {

    enum Difficulty {
        EASY, MEDIUM, HARD;
    }

    private List<Clue> clues;
    private Map<String, Boolean> suspectQuestioned;
    private Difficulty difficulty;

    public MysteryGame() {
        clues = new ArrayList<>();
        suspectQuestioned = new HashMap<>();   
        difficulty = Difficulty.MEDIUM; // default
    }

    public void loadCluesFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // TODO: Parse clue format, e.g., "Clue description|true"
                // Split and create Clue object, then add to clues list
            }
        } catch (IOException e) {
            System.out.println("Error reading clues file: " + e.getMessage());
        }
    }

    public void showClues() {
        for (Clue clue : clues) {
            System.out.println("- " + clue.getDescription());
        }
    }

    public void questionSuspect(String name) {
        if (!suspectQuestioned.containsKey(name)) {
            suspectQuestioned.put(name, true);
            // TODO: Simulate suspect response
            System.out.println(name + " has been questioned.");
        } else {
            System.out.println(name + " was already questioned.");
        }
    }

    public void makeAccusation(String name) {
        // TODO: Evaluate based on clues and suspect info
        System.out.println("You accused: " + name);
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public static void main(String[] args) {
        MysteryGame game = new MysteryGame();
        game.loadCluesFromFile("data/clues.txt");

        // TODO: Add menu loop with options to view clues, question suspects, make accusation, etc.
    }
}
