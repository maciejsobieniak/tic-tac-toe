package com.kodilla.tictactoe.logic;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int boardSize;
    private List<BoardRow> boardRows = new ArrayList<>();
    private Player whoseMove = Player.X;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            boardRows.add(new BoardRow(boardSize));
        }
    }

    public int getBoardSize() {
        return boardSize;
    }

    public boolean gameNotFinished() {
        for (BoardRow row : boardRows) {
            for (Player player : row.getCols()) {
                if (player == Player.NONE) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkWinCondition(Player player) {
        // Check rows
        for (BoardRow row : boardRows) {
            if (row.getCols().stream().allMatch(p -> p == player)) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < boardSize; col++) {
            boolean allMatch = true;
            for (int row = 0; row < boardSize; row++) {
                if (boardRows.get(row).getCols().get(col) != player) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) {
                return true;
            }
        }

        // Check diagonals
        boolean allMatch = true;
        for (int i = 0; i < boardSize; i++) {
            if (boardRows.get(i).getCols().get(i) != player) {
                allMatch = false;
                break;
            }
        }
        if (allMatch) {
            return true;
        }

        allMatch = true;
        for (int i = 0; i < boardSize; i++) {
            if (boardRows.get(i).getCols().get(boardSize - 1 - i) != player) {
                allMatch = false;
                break;
            }
        }
        return allMatch;
    }

    public boolean isMoveValid(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize && getPlayerAt(row, col) == Player.NONE;
    }

    public Player getPlayerAt(int row, int col) {
        return boardRows.get(row).getCols().get(col);
    }

    public void setPlayerAt(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        Player player = move.getPlayer();
        boardRows.get(row).getCols().set(col, player);
        whoseMove = (whoseMove == Player.X) ? Player.O : Player.X;

    }

    public Player getWhoseMove() {
        return whoseMove;
    }

    public void displayBoard() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int r = 0; r < boardSize; r++) {
            BoardRow row = boardRows.get(r);
            if (r == boardSize - 1) {
                result.append(row.toString()).append("\n");
                continue;
            }
            result.append(row.toString()).append("\n");
            for (int i = 0; i < boardSize; i++) {
                if (i == boardSize - 1) {
                    result.append("--\n");
                    continue;
                }
                if (i > 0) {
                    result.append("-+");
                    continue;
                }
                result.append("--+");

            }

        }
        return result.toString();
    }
}
