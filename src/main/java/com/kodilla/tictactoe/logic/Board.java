package com.kodilla.tictactoe.logic;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int boardSize;
    private final List<BoardRow> boardRows = new ArrayList<>();
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

    public boolean checkIfboardIsNotFull() {
        for (BoardRow row : boardRows) {
            for (Player player : row.getCols()) {

                if (player == Player.NONE) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkWinCondition(Player player, int winLength) {

        if (player == null || player == Player.NONE) return false;

        if (boardSize < winLength) return false;

        // Poziomo
        for (int row = 0; row < boardSize; row++) {
            int winPoints = 0;

            for (int cols = 0; cols < boardSize; cols++) {
                if (getPlayerAt(row, cols) == player) {

                    if (++winPoints >= winLength) return true;
                } else {
                    winPoints = 0;
                }
            }
        }

        // Pionowo
        for (int cols = 0; cols < boardSize; cols++) {

            int winPoints = 0;

            for (int row = 0; row < boardSize; row++) {

                if (getPlayerAt(row, cols) == player) {

                    if (++winPoints >= winLength) return true;
                } else {
                    winPoints = 0;
                }
            }
        }

        // Przekątne \ (w dół-w-prawo)
        for (int startRow = 0; startRow <= boardSize - winLength; startRow++) {

            for (int startCol = 0; startCol <= boardSize - winLength; startCol++) {

                int winPoints = 0;

                for (int i = 0; i < Math.min(boardSize - startRow, boardSize - startCol); i++) {

                    if (getPlayerAt(startRow + i, startCol + i) == player) {

                        if (++winPoints >= winLength) return true;
                    } else {
                        winPoints = 0;
                    }
                }
            }
        }

        // Przekątne / (w dół-w-lewo)
        for (int startRow = 0; startRow <= boardSize - winLength; startRow++) {

            for (int startCol = winLength - 1; startCol < boardSize; startCol++) {
                int winPoints = 0;

                for (int i = 0; i < Math.min(boardSize - startRow, startCol + 1); i++) {
                    if (getPlayerAt(startRow + i, startCol - i) == player) {
                        if (++winPoints >= winLength) return true;
                    } else {
                        winPoints = 0;
                    }
                }
            }
        }

        return false;
    }

    public List<Move> getEmptyPossibleMovieForComputer() {
        List<Move> emptyPossibleMoves = new ArrayList<>();
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (getPlayerAt(row, col) == Player.NONE) {
                    emptyPossibleMoves.add(new Move(row, col, Player.O));
                }
            }
        }
        return emptyPossibleMoves;
    }

    public boolean isMoveValid(Move move) {
        if (move == null) return false;
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
    }

    public void switchPlayer() {
        whoseMove = (whoseMove == Player.X) ? Player.O : Player.X;
    }

    public Player getWhoseMove() {
        return whoseMove;
    }

    public void setWhoseMove(Player player) {
        whoseMove = player;
    }

    public void displayBoard() {
        System.out.println(this);
    }

    public List<List<Player>> saveBoard(Board board) {
        List<List<Player>> result = new ArrayList<>();
        for (int row = 0; row < board.getBoardSize(); row++) {
            List<Player> rowList = new ArrayList<>();
            for (int col = 0; col < board.getBoardSize(); col++) {
                rowList.add(board.getPlayerAt(row, col));
            }
            result.add(rowList);
        }
        return result;
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
