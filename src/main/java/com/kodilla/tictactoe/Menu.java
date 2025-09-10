package com.kodilla.tictactoe;

import java.util.Scanner;

public class Menu {

    public void displayMainMenu() {
        System.out.println("=======================================");
        System.out.println("======= Welcome to Tic Tac Toe! =======");
        System.out.println("1. Start New Game");
        System.out.println("2. Exit");
        System.out.println("=======================================");
        System.out.print("Please select an option (1, 2): ");
    }

    public void displayNewGameMenu() {
        System.out.println("=======================================");
        System.out.println("======= New Game Wizard=======");
        System.out.println("1. Player1 vs Player2");
        System.out.println("2. Player1 vs Computer");
        System.out.println("3. Exit to Main Menu");
        System.out.println("=======================================");
        System.out.print("Please select an option (1, 2, 3): ");
    }

    public void displayGameMenu() {
        System.out.println("=======================================");
        System.out.println("1. Make a Move");
        System.out.println("2. View Board");
        System.out.println("3. Exit to Main Menu");
        System.out.println("=======================================");
        System.out.print("Please select an option: ");
    }

    public void playGame() {
        // Implementation for playing the game would go here
    }

    public int readInt(Scanner sc, int min, int max) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                int value = Integer.parseInt(s);
                if (value < min || value > max) {
                    System.out.printf("Please select a number from the range to select an option ("+ min + "-" + max + "): ");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("This is not a number. Please select a number from the range to select an option ("+ min + "-" + max + "): ");
            }
        }
    }
}
