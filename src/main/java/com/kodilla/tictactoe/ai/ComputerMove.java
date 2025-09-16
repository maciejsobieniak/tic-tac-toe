package com.kodilla.tictactoe.ai;
import com.kodilla.tictactoe.logic.Board;
import com.kodilla.tictactoe.logic.Move;
import com.kodilla.tictactoe.logic.Player;

public class ComputerMove {

    public static Move getComputerMove(Board board) {

        int row = (int) (Math.random() * board.getBoardSize());
        int col = (int) (Math.random() * board.getBoardSize());

        while (!board.isMoveValid(new Move(row, col, Player.O))) {
            row = (int) (Math.random() * board.getBoardSize());
            col = (int) (Math.random() * board.getBoardSize());
        }

        return new Move(row, col, Player.O);
    }


}
