package com.kodilla.tictactoe.logic;

import java.util.Scanner;

import com.kodilla.tictactoe.ai.ComputerMove;
import com.kodilla.tictactoe.ui.UserDialogs;
import com.kodilla.tictactoe.ai.ComputerMove;

public class Game {

    private String player1Name;
    private String player2Name;
    private int boardSize;
    private GameMode gameMode;

    public Game(String player1Name, String player2Name, int boardSize, GameMode gameMode) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.boardSize = boardSize;
        this.gameMode = gameMode;
    }

    public void startNewGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting a new game with board size " + boardSize + "x" + boardSize);
        System.out.println("Player 1: " + player1Name + " (X)");
        System.out.println("Player 2: " + player2Name + " (O)");
        Board board = new Board(boardSize);
        board.displayBoard();
        while (board.gameNotFinished()) {
            if (gameMode == GameMode.PLAYER_VS_COMPUTER && board.getWhoseMove() == Player.O) {
                Move computerMove = ComputerMove.getComputerMove(board);
                System.out.println("Computer moves to (" + computerMove.getRow() + ", " + computerMove.getCol() + ")");
                board.setPlayerAt(computerMove);
                board.displayBoard();
                continue;
            }

            Move move = UserDialogs.getMove(scanner, getPlayerName(board.getWhoseMove()), board.getWhoseMove());
            if (move != null && board.isMoveValid(move)) {
                System.out.println("Player " + move.getPlayer() + " moves to (" + move.getRow() + ", " + move.getCol() + ")");
                board.setPlayerAt(move);
            } else {
                UserDialogs.showInvalidMoveMessage();
                continue;
            }
            board.displayBoard();
            if (!board.gameNotFinished()) {
                UserDialogs.showDrawMessage();
                return;
            }
            if (board.checkWin()) {
                UserDialogs.showWinMessage(scanner, getPlayerName(board.getWhoseMove().getOpponent()));
                return;
            }

        }
        if(UserDialogs.showAskReaplyGameDialog(scanner)) {;
            startNewGame();
        } else {
            UserDialogs.showMainMenu(scanner);
        }

    }

    public String getPlayerName(Player player) {
        return (player == Player.X) ? player1Name : player2Name;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public int getBoardSize() {
        return boardSize;
    }





}
