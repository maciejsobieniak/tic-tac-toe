package com.kodilla.tictactoe.ai;
import com.kodilla.tictactoe.logic.Board;
import com.kodilla.tictactoe.logic.Move;
import com.kodilla.tictactoe.logic.Player;

import java.util.List;
import java.util.Random;

public class ComputerMove {

    public static Move getComputerMove(Board board, ComputerDifficulty difficulty , int winLength) {
        switch (difficulty) {
            case PVC_EASY:
                return getRandomMove(board);
            case PVC_MEDIUM:
                return getMediumMove(board, winLength);
            case PVC_HARD:
                return getRandomMove(board);
            default:
                return getRandomMove(board);
        }
    }

    private static Move getRandomMove(Board board) {
        Random random = new Random();
        List<Move> emptyFields = board.getEmptyPossibleMovieForComputer();
        Move move = emptyFields.get(random.nextInt(emptyFields.size()));
        move.setPlayer(Player.O);
        return move;
    }

    private static Move getMediumMove(Board board , int winLength) {
        List<Move> possibleMoves = board.getEmptyPossibleMovieForComputer();
        // 1. Sprawdź, czy komputer może wygrać
        for (Move move : possibleMoves) {
            Board tempBoard = cloneBoard(board);
            tempBoard.setPlayerAt(new Move(move.getRow(), move.getCol(), Player.O));
            if (tempBoard.checkWinCondition(Player.O, winLength)) {
                return move;
            }
        }
        // 2. Sprawdź, czy gracz może wygrać i zablokuj
        for (Move move : possibleMoves) {
            Board tempBoard = cloneBoard(board);
            tempBoard.setPlayerAt(new Move(move.getRow(), move.getCol(), Player.X));
            if (tempBoard.checkWinCondition(Player.X, winLength)) {
                return move;
            }
        }
        // 3. Jeśli środek jest wolny, zajmij go
        for (Move move : possibleMoves) {
            if (move.getRow() == board.getBoardSize() / 2 && move.getCol() == board.getBoardSize() / 2) {
                return move;
            }
        }

        int[][] corners = {
            {0, 0},
            {0, board.getBoardSize() - 1},
            {board.getBoardSize() - 1, 0},
            {board.getBoardSize() - 1, board.getBoardSize() - 1}
        };
        // 4. Zajmij róg, jeśli jest wolny
        for (int[] corner : corners) {
            for (Move move : possibleMoves) {
                if (move.getRow() == corner[0] && move.getCol() == corner[1]) {
                    return move;
                }
            }
        }

        // 3. W innym przypadku losowy ruch
        return getRandomMove(board);
    }

    // Pomocnicza metoda do klonowania planszy
    private static Board cloneBoard(Board board) {
        Board newTempBoard = new Board(board.getBoardSize());
        for (int row = 0; row < board.getBoardSize(); row++) {
            for (int col = 0; col < board.getBoardSize(); col++) {
                Player player = board.getPlayerAt(row, col);
                if (player != Player.NONE) {
                    newTempBoard.setPlayerAt(new Move(row, col, player));
                }
            }
        }
        newTempBoard.setWhoseMove(board.getWhoseMove());
        return newTempBoard;
    }

}
