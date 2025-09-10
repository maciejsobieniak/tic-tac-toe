package com.kodilla.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<BoardRow> rows = new ArrayList<>();
    private final int size;

    public Board(final int size) {
        this.size = size;
        for (int row = 0; row < size; row++) {
            rows.add(new BoardRow(size));
        }
    }

    public Mark getMark(int row, int col) {
        return rows.get(row).getMarkColumns().get(col);
    }

    public void setMark(int row, int col, Mark mark) {
        rows.get(row).getMarkColumns().set(col, mark);
    }

}
