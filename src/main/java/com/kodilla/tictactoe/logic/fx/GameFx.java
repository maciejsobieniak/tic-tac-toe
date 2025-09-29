package com.kodilla.tictactoe.logic.fx;


import com.kodilla.tictactoe.logic.*;
import com.kodilla.tictactoe.ai.ComputerMove;
import com.kodilla.tictactoe.services.LoadGameEngine;
import com.kodilla.tictactoe.services.SaveGame;
import com.kodilla.tictactoe.ui.UserDialogs;
import javafx.scene.control.Label;

public class GameFx {

    private final GameConfig gameConfig;
    private Board board;
    private final BoardView boardView;
    private final Label updateInfoGameLabel;

    public GameFx(GameConfig gameConfig, BoardView boardView, Label updateInfoGameLabel) {
        this.gameConfig = gameConfig;
        this.boardView = boardView;
        this.updateInfoGameLabel = updateInfoGameLabel;
        resetGame();
    }

    public void resetGame() {
        board = new Board(gameConfig.getBoardSize());
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
                updateInfoGameLabel.setText("Game is loaded! Player move: " + board.getWhoseMove());
                boardView.enableInput();
                boardView.drawBoard(board);
                if (checkIfItIsComputerTurn()) {
                    computerMove();
                }
            } catch (Exception e) {
                updateInfoGameLabel.setText("Save file not found or corrupted. Starting new game.");
                gameConfig.setGameLoadedStatusToFalse();
            }
        } else {
            boardView.enableInput();
            boardView.drawBoard(board);
            updateInfoGameLabel.setText("New Game! Player move: " + board.getWhoseMove());
            if (checkIfItIsComputerTurn()) {
                computerMove();
            }
        }
    }

    public void playerMove(int row, int col) {
        Player current = board.getWhoseMove();
        Move move = new Move(row, col, current);
        if (!board.isMoveValid(move)) {
            updateInfoGameLabel.setText("Invalid move. Please try again.");
        } else {
            makeMove(move);
        }
    }

    public void computerMove() {
        Move move = ComputerMove.getComputerMove(board, gameConfig.getComputerDifficulty(), gameConfig.getWinLength());
        makeMove(move);
    }

    public boolean checkIfItIsComputerTurn() {
        return gameConfig.getGameMode() == GameMode.PLAYER_VS_COMPUTER && board.getWhoseMove() == Player.O;
    }

    public void makeMove(Move move) {
        board.setPlayerAt(move);
        boardView.drawBoard(board);
        if (board.checkWinCondition(move.getPlayer(), gameConfig.getWinLength())) {
            updateInfoGameLabel.setText("Win: " + move.getPlayer());
            boardView.disableInput();
        } else if (!board.checkIfboardIsNotFull()) {
            updateInfoGameLabel.setText("Draw!");
            boardView.disableInput();
        } else {
            board.switchPlayer();
            updateInfoGameLabel.setText("Player move: " + board.getWhoseMove());
            if (checkIfItIsComputerTurn()) {
                computerMove();
            }
        }
    }

    public void handleMove(String rowField, String colField) {
        try {
            int row = Integer.parseInt(rowField);
            int col = Integer.parseInt(colField);
            if (row < 0 || row >= gameConfig.getBoardSize() || col < 0 || col >= gameConfig.getBoardSize()) {
                updateInfoGameLabel.setText("Invalid move! Please enter valid integers from range 0 - " + (gameConfig.getBoardSize() - 1));
            } else {
                playerMove(row, col);
            }
        } catch (NumberFormatException ex) {
            updateInfoGameLabel.setText("Invalid move! Please enter valid integers.");
        }
    }

    public Board getBoard() {
        return board;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }
}
