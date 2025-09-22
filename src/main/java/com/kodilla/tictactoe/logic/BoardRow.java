package com.kodilla.tictactoe.logic;

import java.util.List;
import java.util.ArrayList;

public class BoardRow {

    private final List<Player> cols = new ArrayList<>();

    public BoardRow(int boardSize) {
        for (int col = 0; col < boardSize; col++) {
            cols.add(Player.NONE);
        }
    }

    public List<Player> getCols() {
        return cols;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("|");
        for (Player col : cols) {
            if (col == Player.NONE) {
                result.append(" ").append("|");
            } else {
                result.append(col).append("|");;
            }
        }
        return result.toString();
    }




}
