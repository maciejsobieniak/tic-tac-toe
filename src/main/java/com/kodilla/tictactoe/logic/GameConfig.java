package com.kodilla.tictactoe.logic;

import com.kodilla.tictactoe.ai.ComputerDifficulty;

import java.util.Scanner;

public class GameConfig {

    private final Scanner scanner;
    private final String player1Name;
    private final String player2Name;
    private final String startingPlayer;
    private final GameMode gameMode;
    private final ComputerDifficulty computerDifficulty;
    private final int boardSize;
    private final int winLength;

    public GameConfig(Scanner scanner, String player1Name, String player2Name, String startingPlayer, GameMode gameMode, ComputerDifficulty computerDifficulty, int boardSize, int winLength) {
        this.scanner = scanner;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.startingPlayer = startingPlayer;
        this.gameMode = gameMode;
        this.computerDifficulty = computerDifficulty;
        this.boardSize = boardSize;
        this.winLength = winLength;
    }

    public Scanner getScanner() { return scanner; }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() { return player2Name; }

    public Player getStartingPlayer() {
        if (startingPlayer.equals(player1Name)) {
            return Player.X;
        } else {
            return Player.O;
        }
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public ComputerDifficulty getComputerDifficulty() {
        return computerDifficulty;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getWinLength() {
        return winLength;
    }
}
