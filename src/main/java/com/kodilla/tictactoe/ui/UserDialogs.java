package com.kodilla.tictactoe.ui;

import com.kodilla.tictactoe.logic.Game;
import com.kodilla.tictactoe.logic.GameMode;
import com.kodilla.tictactoe.logic.Move;
import com.kodilla.tictactoe.logic.Player;

import java.util.Scanner;

public class UserDialogs {
    //private static Game game;

    public static void createMainMenu() {
        Scanner scanner = new Scanner(System.in);
        showMainMenu(scanner);
    }

    public static void showMainMenu(Scanner scanner) {
        System.out.println("=======================================");
        System.out.println("======= Welcome to Tic Tac Toe! =======");
        System.out.println("1. Start New Game");
        System.out.println("2. Exit");
        System.out.println("=======================================");
        System.out.print("Please select an option (1, 2): ");
        int choice = checkIfUserInputIsNumber(scanner, 1, 2);
        switch (choice) {
            case 1: showNewGameOptionMenu(scanner); break;
            case 2: System.out.println("Exiting the game. Goodbye!"); System.exit(0);
        }
    }

    public static void showNewGameOptionMenu(Scanner scanner) {
        System.out.println("=======================================");
        System.out.println("=========== New Game Menu =============");
        System.out.println("1. Player1 vs Player2");
        System.out.println("2. Player1 vs Computer");
        System.out.println("3. Back to Main Menu");
        System.out.println("=======================================");
        System.out.print("Please select an option (1, 2, 3): ");
        int choice = checkIfUserInputIsNumber(scanner, 1, 3);
        switch (choice) {
            case 1: System.out.println("Player vs Player mode selected."); showCreateNewGameDialog(scanner, GameMode.PLAYER_VS_PLAYER); break;
            case 2: System.out.println("Player vs Computer mode selected."); showCreateNewGameDialog(scanner, GameMode.PLAYER_VS_COMPUTER); break;
            case 3: showMainMenu(scanner);
        }
    }

    public static void showCreateNewGameDialog(Scanner scanner, GameMode gameMode) {
        int numberOfPlayers = (gameMode == GameMode.PLAYER_VS_PLAYER) ? 2 : 1;
        System.out.println("Creating a new game...");
        int boardSize = showCreateBoardSizeDialog(scanner);
        String player1Name = showCreatePlayerNameDialog(scanner, 1);
        String player2Name = (numberOfPlayers == 2) ? showCreatePlayerNameDialog(scanner, 2) : "Computer";
        System.out.println("New game created with board size " + boardSize + "x" + boardSize);
        System.out.println("Player 1 - X: " + player1Name);
        System.out.println("Player 2 - O: " + player2Name);
        Game game = new Game(player1Name, player2Name, boardSize, gameMode);
        game.startNewGame();
    }

    public static int showCreateBoardSizeDialog(Scanner scanner) {
        System.out.print("Enter board size (3-10): ");
        int boardSize = checkIfUserInputIsNumber(scanner, 3, 10);
        System.out.println("Board size set to: " + boardSize + "x" + boardSize);
        return boardSize;
    }

    public static String showCreatePlayerNameDialog(Scanner scanner, int playerNumber) {
        System.out.print("Enter name for Player " + playerNumber + ": ");
        String playerName = scanner.nextLine().trim();
        while (playerName.isEmpty()) {
            System.out.print("Name cannot be empty. Please enter a valid name for Player " + playerNumber + ": ");
            playerName = scanner.nextLine().trim();
        }
        System.out.println("Player " + playerNumber + " name set to: " + playerName);
        return playerName;
    }

    public static void showInvalidMoveMessage() {
        System.out.println("Invalid move. Please try again.");
    }

    public static void showDrawMessage() {
        System.out.println("The game is a draw!");
        System.out.println("=======================================");
        System.out.println("=========== Game Over! ================");
        System.out.println("=======================================");
    }

    public static boolean showAskReaplyGameDialog(Scanner scanner) {
        System.out.print("Do you want to repeat game ? (yes/no): ");
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.print("Invalid input. Please enter 'yes' or 'no': ");
            }
        }
    }

    public static Move getMove(Scanner scanner, String playerName, Player player) {
        System.out.print(playerName + " (" + player + "), enter your move (row and column) or 'exit' to quit: ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
            }
            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                System.out.print("Invalid input. Please enter row and column separated by space: ");
                continue;
            }
            try {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                return new Move(row, col, player);
            } catch (NumberFormatException e) {
                System.out.print("Invalid numbers. Please enter valid integers for row and column: ");
            }
        }
    }

    public static int checkIfUserInputIsNumber(Scanner scanner, int min, int max) {
        while (true) {
            String inputString = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(inputString);
                if (value < min || value > max) {
                    System.out.printf("Please select a number from the range to select an option (" + min + "-" + max + "): ");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("This is not a number. Please select a number from the range to select an option (" + min + "-" + max + "): ");
            }
        }
    }
}
