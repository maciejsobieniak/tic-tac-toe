package com.kodilla.tictactoe.ui;

import com.kodilla.tictactoe.logic.*;

import com.kodilla.tictactoe.ai.ComputerDifficulty;
import com.kodilla.tictactoe.services.SaveGame;
import com.kodilla.tictactoe.services.LoadGameEngine;

import java.io.IOException;
import java.util.Scanner;

public class UserDialogs {

    public static Scanner scanner = new Scanner(System.in);

    public static GameConfig getGameParameters() {

        showMainMenu();

        int choice = checkIfUserInputIsNumber(1, 3);

        if (choice == 1) {
            showNewGameOptionMenu();
            choice = checkIfUserInputIsNumber(1, 3);
            if (choice == 1) {
                return showCreateNewGameDialog(GameMode.PLAYER_VS_PLAYER);
            } else if (choice == 2) {
                return showCreateNewGameDialog(GameMode.PLAYER_VS_COMPUTER);
            } else {
                showMainMenu();
            }
        } else if (choice == 2) {
            return showLoadGameDialog();
        } else {
            showExitMessage();
            System.exit(0);
            return null;
        }
        return null;
    }

    public static void showMainMenu() {
        System.out.println("=======================================");
        System.out.println("======= Welcome to Tic Tac Toe! =======");
        System.out.println("1. Start New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Exit");
        System.out.println("=======================================");
        System.out.print("Please select an option (1, 2, 3): ");

    }

    public static void showExitMessage() {
        System.out.println("Exiting the game. Goodbye!");

    }

    public static void showNewGameOptionMenu() {
        System.out.println("=======================================");
        System.out.println("=========== New Game Menu =============");
        System.out.println("1. Player1 vs Player2");
        System.out.println("2. Player1 vs Computer");
        System.out.println("3. Back to Main Menu");
        System.out.println("=======================================");
        System.out.print("Please select an option (1, 2, 3): ");

    }

    public static GameConfig showCreateNewGameDialog(GameMode gameMode) {
        int numberOfPlayers = (gameMode == GameMode.PLAYER_VS_PLAYER) ? 2 : 1;
        System.out.println("Creating a new game...");
        int boardSize = showCreateBoardSizeDialog();
        String player1Name = showCreatePlayerNameDialog(1);
        String player2Name = (numberOfPlayers == 2) ? showCreatePlayerNameDialog(2) : "Computer";
        int winLength = showCreateWinLengthDialog();
        String startingPlayer = showCreateStartingPlayerDialog(player1Name, player2Name);
        ComputerDifficulty computerDifficulty = (numberOfPlayers == 1) ? showCreateComputerDifficultyDialog() : null;
        System.out.println("New game created with board size " + boardSize + "x" + boardSize);
        System.out.println("Win length set to: " + winLength);
        System.out.println("Game Mode: " + gameMode);
        if (gameMode == GameMode.PLAYER_VS_COMPUTER) { System.out.println("Computer Difficulty: " + computerDifficulty); }
        System.out.println("Starting Player: " + startingPlayer);
        System.out.println("Player 1 - X: " + player1Name);
        System.out.println("Player 2 - O: " + player2Name);
        return new GameConfig(false, player1Name, player2Name, startingPlayer, gameMode, computerDifficulty, boardSize, winLength);
    }

    public static int showCreateWinLengthDialog() {
        System.out.print("Enter win length (3-5): ");
        int winLength = checkIfUserInputIsNumber(3, 5);
        System.out.println("Win length set to: " + winLength);
        return winLength;
    }

    public static String showCreateStartingPlayerDialog(String player1Name, String player2Name) {
        System.out.println("Who will start first?");
        System.out.println("1)" + player1Name + " (X)");
        System.out.println("2)" + player2Name + " (O)");
        System.out.print("Who will start first? (1, 2): ");
        int choice = checkIfUserInputIsNumber(1, 2);
        String startingPlayer = (choice == 1) ? player1Name : player2Name;
        System.out.println(startingPlayer + " will start first.");
        return startingPlayer;
    }

    public static ComputerDifficulty showCreateComputerDifficultyDialog() {
        System.out.println("Select computer difficulty level:");
        System.out.println("1) Easy");
        System.out.println("2) Medium");
        System.out.println("3) Hard");
        System.out.print("Please select an option (1, 2, 3): ");
        int choice = checkIfUserInputIsNumber(1, 3);
        ComputerDifficulty difficulty = null;
        switch (choice) {
            case 1:
                difficulty = ComputerDifficulty.PVC_EASY;
                break;
            case 2:
                difficulty = ComputerDifficulty.PVC_MEDIUM;
                break;
            case 3:
                difficulty = ComputerDifficulty.PVC_HARD;
                break;
        }
        System.out.println("Computer difficulty set to: " + difficulty);
        return difficulty;
    }

    public static int showCreateBoardSizeDialog() {
        System.out.print("Enter board size (3-10): ");
        int boardSize = checkIfUserInputIsNumber(3, 10);
        System.out.println("Board size set to: " + boardSize + "x" + boardSize);
        return boardSize;
    }

    public static String showCreatePlayerNameDialog(int playerNumber) {
        System.out.print("Enter name for Player " + playerNumber + ": ");
        String playerName = scanner.nextLine().trim();

        while (playerName.isEmpty()) {
            System.out.print("Name cannot be empty. Please enter a valid name for Player " + playerNumber + ": ");
            playerName = scanner.nextLine().trim();
        }

        System.out.println("Player " + playerNumber + " name set to: " + playerName);
        return playerName;
    }

    public static GameConfig showLoadGameDialog() {
        System.out.println("Loading a saved game...");
        try {
            SaveGame loadGameFromSave = LoadGameEngine.loadGameFromFile("save_game.dat");
            return new GameConfig(true, loadGameFromSave.getPlayer1Name(), loadGameFromSave.getPlayer2Name(),
                    loadGameFromSave.getStartingPlayerName(), loadGameFromSave.getGameMode(), loadGameFromSave.getComputerDifficulty(),
                    loadGameFromSave.getBoardSize(), loadGameFromSave.getWinLength());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load the saved game. Starting a new game instead.");
            return getGameParameters();
        }
    }

    public static void showLoadGameSuccessMessage() {
        System.out.println("Game loaded successfully.");
    }

    public static void showInvalidMoveMessage() {
        System.out.println("Invalid move. Please try again.");
    }

    public static void showMoveMessage(String playerName, Move move) {
        System.out.println("Player: " + " " + playerName + " " + move.getPlayer() + " moves to (" + move.getRow() + ", " + move.getCol() + ")");
    }

    public static void showAddComputerMoveMessage(Move move) {
        System.out.println("Player2: Computer moves to (" + move.getRow() + ", " + move.getCol() + ")");
    }

    public  static void showWinMessage(String winnerName) {
        System.out.println("Congratulations " + winnerName + "! You have won the game!");
        System.out.println("=======================================");
        System.out.println("=========== Game Over! ================");
        System.out.println("=======================================");
    }

    public static void showDrawMessage() {
        System.out.println("The game is a draw!");
        System.out.println("=======================================");
        System.out.println("=========== Game Over! ================");
        System.out.println("=======================================");
    }

    public static boolean showAskReaplyGameDialog() {
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

    public static boolean showAskIfNewGameDialog() {
        System.out.print("Do you want to start a new game ? (yes/no): ");
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

    public static boolean showAskIfSaveGameDialog() {
        System.out.print("Do you want to save the current game? (y/n): ");
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

    public static void showGameSavedMessageErrorBoardOrGameConfig() {
        System.out.println("Game saved successfully.");
    }

    public static void showGameSavedMessageErrorBoardIsFull() {
        System.out.println("Game board is full. Cannot save the game.");
    }

    public static void showGameSavedSuccessMessage() {
        System.out.println("Game saved successfully.");
    }

    public static void showErrorLoadGameConfigMessage() {
        System.out.println("Game configuration is missing. Cannot start the game.");
    }

    public static Move getMove(String playerName, Player player) {
        System.out.print(playerName + " (" + player + "), enter your move (row and column) or 'exit' to quit: ");
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                showExitMessage();
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

    public static int checkIfUserInputIsNumber(int min, int max) {
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
