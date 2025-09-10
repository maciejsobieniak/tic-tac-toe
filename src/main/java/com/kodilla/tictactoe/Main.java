package com.kodilla.tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            Menu menu = new Menu();
            new Main().run(scanner, menu);
           /* menu.displayMainMenu();
            int choice = menu.readInt(scanner, 1, 2);
            switch(choice) {
                case 1 -> menu.displayNewGameMenu();
                case 2 -> {
                    System.out.println("See you later!");
                    return;
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        // for fast testing
        //Player player1 = new Player("Player 1", 'X');
        //Player player2 = new Player("Player 2", 'O');
        //Board board = new Board();
        //Game game = new Game(player1, player2, board);
        //Menu menu = new Menu();
        //menu.displayMainMenu();
        // Further implementation for handling user input and game loop would go here
        //System.out.println(board);
        //Circle c = new Circle(5);
        //System.out.println("Circle area: " + c.getSymbol());


    }

    private void run(Scanner scanner, Menu menu) {
        boolean exit = false;
        while (!exit) {
            menu.displayMainMenu();
            int choice = menu.readInt(scanner, 1, 2);
            switch(choice) {
                case 1 -> startGame(scanner, menu);
                case 2 -> {
                    System.out.println("See you later!");
                    exit = true;
                }
            }
        }
    }

    private void startGame(Scanner scanner, Menu menu) {
        menu.displayNewGameMenu();
        int choice = menu.readInt(scanner, 1, 3);
        switch(choice) {
            case 1 -> {
                // implement ask about players names
                // implement ask about board size
                // implement ask about winning condition
                // implement ask who starts
                // implement create players, board and game
            }
            case 2 -> {
                // Implement Player vs Computer logic
            }
            case 3 -> {
                System.out.println("Returning to Main Menu...");
                menu.displayMainMenu();
            }
        }
    }
}
