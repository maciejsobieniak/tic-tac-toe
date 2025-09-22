package com.kodilla.tictactoe.logic;

import java.util.Scanner;

import com.kodilla.tictactoe.ai.ComputerMove;
import com.kodilla.tictactoe.ui.UserDialogs;

public class Game {

    private final GameConfig gameConfig;

    public Game(GameConfig gameConfig) {

        this.gameConfig = gameConfig;
    }

    public void startNewGame() {

        Scanner scanner = gameConfig.getScanner();
        Board board = new Board(gameConfig.getBoardSize());
        board.setWhoseMove(gameConfig.getStartingPlayer());
        board.displayBoard();


        while (board.checkIfboardIsNotFull()) {
            Move move;
            do {
                if (checkIfItIsComputerTurn(board)) {
                    move = ComputerMove.getComputerMove(board, gameConfig.getComputerDifficulty(), gameConfig.getWinLength());
                } else {
                    if (checkIfwantToSaveGame(scanner)) {
                        saveCurrentGame(board);
                    }
                    move = UserDialogs.getMove(scanner, getPlayerName(board.getWhoseMove()), board.getWhoseMove());
                    if (!board.isMoveValid(move)) {
                        UserDialogs.showInvalidMoveMessage();
                    }
                }
            } while (!board.isMoveValid(move));
            if (makeMove(board, move)) {
                break;
            }
        }

    }

    private boolean makeMove(Board board, Move move) {
        UserDialogs.showMoveMessage(getPlayerName(board.getWhoseMove()), move);
        board.setPlayerAt(move);
        board.switchPlayer();
        board.displayBoard();

        if (board.checkWinCondition(move.getPlayer(), gameConfig.getWinLength())) {
            UserDialogs.showWinMessage(getPlayerName(move.getPlayer()));
            return true;
        } else if (!board.checkIfboardIsNotFull()) {
            UserDialogs.showDrawMessage();
            board.displayBoard();
            return true;
        }
        return false;
    }

    /*public boolean checkIfwantToLoadGame(Scanner scanner) {
        return UserDialogs.showAskIfLoadGameDialog(scanner);
    }*/

    public boolean checkIfwantToSaveGame(Scanner scanner) {
        return UserDialogs.showAskIfSaveGameDialog(scanner);
    }

    public boolean checkIfItIsComputerTurn(Board board) {
        return gameConfig.getGameMode() == GameMode.PLAYER_VS_COMPUTER && board.getWhoseMove() == Player.O;
    }

    public String getPlayerName(Player player) {
        return (player == Player.X) ? gameConfig.getPlayer1Name() : gameConfig.getPlayer2Name();
    }

    public void saveCurrentGame(Board board) {
        try {
            SaveGame saveGame = new SaveGame(
                    board.saveBoard(board),
                    board.getWhoseMove(),
                    gameConfig.getPlayer1Name(),
                    gameConfig.getPlayer2Name(),
                    gameConfig.getGameMode(),
                    gameConfig.getComputerDifficulty(),
                    gameConfig.getBoardSize(),
                    gameConfig.getWinLength()
            );
            saveGame.saveToFile(saveGame, getPlayerName(board.getWhoseMove()) + ".dat");
            System.out.println("Game saved successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
