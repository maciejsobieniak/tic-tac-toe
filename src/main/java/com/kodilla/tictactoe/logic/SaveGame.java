package com.kodilla.tictactoe.logic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.kodilla.tictactoe.ai.ComputerDifficulty;

public class SaveGame implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<List<Player>> board;
    private final Player whoseMove;
    private final String player1Name;
    private final String player2Name;
    private final GameMode gameMode;
    private final ComputerDifficulty computerDifficulty;
    private final int boardSize;
    private final int winLength;

    public SaveGame(List<List<Player>> board, Player whoseMove, String player1Name, String player2Name, GameMode gameMode, ComputerDifficulty computerDifficulty, int boardSize, int winLength) {
        this.board = board;
        this.whoseMove = whoseMove;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.gameMode = gameMode;
        this.computerDifficulty = computerDifficulty;
        this.boardSize = boardSize;
        this.winLength = winLength;
    }

    public void saveToFile(SaveGame saveGame, String filename) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(saveGame);
        oos.close();
    }

    public SaveGame loadFromFile(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        return (SaveGame) ois.readObject();
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

    public Board loadBoard(List<List<Player>> boardList) {
        int size = boardList.size();
        Board board = new Board(size);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Player player = boardList.get(row).get(col);
                if (player != Player.NONE) {
                    board.setPlayerAt(new Move(row, col, player));
                }
            }
        }
        return board;
    }
}
