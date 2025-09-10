package com.kodilla.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class BoardRow {

    private List<Mark> columns = new ArrayList<>();
    private final int size;

    public BoardRow(final int size) {
        this.size = size;
        for (int i = 1; i <= size; i++) {
            columns.add(new None(i));
        }
    }

    public List<Mark> getMarkColumns() {
        return columns;
    }

    public String toString() {
        String result = "|";
        for (int col = 0; col < size; col++){
            result += columns.get(col) + "|";
        }
        return result;
    }
}
