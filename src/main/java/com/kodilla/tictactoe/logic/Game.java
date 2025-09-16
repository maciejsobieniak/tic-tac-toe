package com.kodilla.tictactoe.logic;

import java.util.Scanner;

import com.kodilla.tictactoe.ai.ComputerMove;
import com.kodilla.tictactoe.ui.UserDialogs;

public class Game {

    private final String player1Name;
    private final String player2Name;
    private final int boardSize;
    private final GameMode gameMode;
    private final int winLength;

    public Game(String player1Name, String player2Name, int boardSize, GameMode gameMode, int winLength) {

        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.boardSize = boardSize;
        this.gameMode = gameMode;
        this.winLength = winLength;
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

                if (computerMove != null && board.isMoveValid(computerMove)) {
                    UserDialogs.showAddComputerMoveMessage(computerMove);
                    board.setPlayerAt(computerMove);
                    board.displayBoard();

                    if (board.checkWinCondition(computerMove.getPlayer(), winLength)) {
                        UserDialogs.showWinMessage(getPlayerName(computerMove.getPlayer()));
                        break;
                    } else if (!board.gameNotFinished()) {
                        UserDialogs.showDrawMessage();
                        board.displayBoard();
                        break;
                    }
                    continue;
                }
            }

            Move move = UserDialogs.getMove(scanner, getPlayerName(board.getWhoseMove()), board.getWhoseMove());

            if (move != null && board.isMoveValid(move)) {
                UserDialogs.showAddPlayerMoveMessage(getPlayerName(board.getWhoseMove()), move);
                board.setPlayerAt(move);
                board.displayBoard();

                if (board.checkWinCondition(move.getPlayer(), winLength)) {
                    UserDialogs.showWinMessage(getPlayerName(move.getPlayer()));
                    break;
                } else if (!board.gameNotFinished()) {
                    UserDialogs.showDrawMessage();
                    board.displayBoard();
                    break;
                }
                continue;
            } else {
                UserDialogs.showInvalidMoveMessage();
                continue;
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
}
