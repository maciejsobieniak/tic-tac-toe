package com.kodilla.tictactoe.services;

import com.kodilla.tictactoe.logic.Board;
import com.kodilla.tictactoe.logic.Move;
import com.kodilla.tictactoe.logic.Player;

import java.io.*;
import java.util.List;

public class LoadGameEngine {

    public static SaveGame loadGameFromFile(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        return (SaveGame) ois.readObject();
    }

    public static Board loadBoard(List<List<Player>> boardList) {
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
