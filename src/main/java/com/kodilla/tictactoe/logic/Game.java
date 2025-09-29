package com.kodilla.tictactoe.logic;

import com.kodilla.tictactoe.ai.ComputerMove;
import com.kodilla.tictactoe.services.SaveGame;
import com.kodilla.tictactoe.services.LoadGameEngine;
import com.kodilla.tictactoe.services.SaveGameEngine;
import com.kodilla.tictactoe.ui.UserDialogs;

public class Game {

    private final GameConfig gameConfig;

    public Game(GameConfig gameConfig) {

        this.gameConfig = gameConfig;
    }

    public void startNewGame() {

        if (gameConfig == null) {
            UserDialogs.showErrorLoadGameConfigMessage();
            return;
        }

        Board board = new Board(gameConfig.getBoardSize());
        board.setWhoseMove(gameConfig.getStartingPlayer());

        if (gameConfig.gameLoadedStatus()) {
            try {
                SaveGame loadedGame = LoadGameEngine.loadGameFromFile("save_game.dat");
                if (loadedGame == null) {
                    UserDialogs.showErrorLoadGameConfigMessage();
                    return;
                }
                board = LoadGameEngine.loadBoard(loadedGame.getBoard());
                board.setWhoseMove(loadedGame.getWhoseMove());
                gameConfig.setGameLoadedStatusToFalse();
                board.displayBoard();
                play(board);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            board.displayBoard();
            play(board);
        }

    }

    public void play(Board board) {
        while (board.checkIfboardIsNotFull()) {
            Move move;
            do {
                if (checkIfItIsComputerTurn(board)) {
                    move = ComputerMove.getComputerMove(board, gameConfig.getComputerDifficulty(),
                            gameConfig.getWinLength());
                } else {
                    if (UserDialogs.showAskIfSaveGameDialog()) {
                        SaveGameEngine.saveGame(board, gameConfig);
                    }
                    move = UserDialogs.getMove(getPlayerName(board.getWhoseMove()), board.getWhoseMove());
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

    public boolean checkIfItIsComputerTurn(Board board) {
        return gameConfig.getGameMode() == GameMode.PLAYER_VS_COMPUTER && board.getWhoseMove() == Player.O;
    }

    public String getPlayerName(Player player) {
        return (player == Player.X) ? gameConfig.getPlayer1Name() : gameConfig.getPlayer2Name();
    }

}
