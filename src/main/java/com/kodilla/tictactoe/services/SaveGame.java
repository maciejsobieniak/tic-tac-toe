package com.kodilla.tictactoe.services;

import java.io.*;
import java.util.List;
import com.kodilla.tictactoe.ai.ComputerDifficulty;
import com.kodilla.tictactoe.logic.GameMode;
import com.kodilla.tictactoe.logic.Player;

public class SaveGame implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<List<Player>> board;
    private final Player whoseMove;
    private final String player1Name;
    private final String player2Name;
    private final String startingPlayer;
    private final GameMode gameMode;
    private final ComputerDifficulty computerDifficulty;
    private final int boardSize;
    private final int winLength;

    public SaveGame(List<List<Player>> board, Player whoseMove, String player1Name, String player2Name, String startingPlayer,
                    GameMode gameMode, ComputerDifficulty computerDifficulty, int boardSize, int winLength) {
        this.board = board;
        this.whoseMove = whoseMove;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.startingPlayer = startingPlayer;
        this.gameMode = gameMode;
        this.computerDifficulty = computerDifficulty;
        this.boardSize = boardSize;
        this.winLength = winLength;
    }

    public List<List<Player>> getBoard() {
        return board;
    }
    public Player getWhoseMove() {
        return whoseMove;
    }
    public String getPlayer1Name() {
        return player1Name;
    }
    public String getPlayer2Name() {
        return player2Name;
    }

    public String getStartingPlayerName() {
        return startingPlayer;
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
