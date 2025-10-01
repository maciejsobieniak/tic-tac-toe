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
    private BoardView boardView;
    private Label updateInfoGameLabel;

    public GameFx(GameConfig gameConfig, BoardView boardView, Label updateInfoGameLabel) {
        this.gameConfig = gameConfig;
        this.boardView = boardView;
        this.updateInfoGameLabel = updateInfoGameLabel;
    }

    public void resetGame() {
        board = new Board(gameConfig.getBoardSize());
        board.setWhoseMove(gameConfig.getStartingPlayer());
        if (gameConfig.gameLoadedStatus()) {
            SaveGame loadedGame = LoadGameEngine.gameLoad("save_game.dat");
            if (loadedGame != null) {
                board = LoadGameEngine.loadBoard(loadedGame.getBoard());
                board.setWhoseMove(loadedGame.getWhoseMove());
                gameConfig.setGameLoadedStatusToFalse();
                UserDialogs.showLoadGameFxMessage(getPlayerName(board.getWhoseMove()), board.getWhoseMove(), updateInfoGameLabel);
                boardView.enableInput();
                boardView.drawBoard(board);
                if (checkIfItIsComputerTurn()) {
                    computerMove();
                }
            } else {
                UserDialogs.showGameSavedMessageErrorBoardOrGameConfig();
            }
        } else {
            boardView.enableInput();
            boardView.drawBoard(board);
            UserDialogs.showNewGameFxMessage(getPlayerName(board.getWhoseMove()), board.getWhoseMove(), updateInfoGameLabel);
            if (checkIfItIsComputerTurn()) {
                computerMove();
            }
        }
    }

    public void playerMove(int row, int col) {
        Player current = board.getWhoseMove();
        Move move = new Move(row, col, current);
        if (!board.isMoveValid(move)) {
            UserDialogs.showInvalidMoveFxMessage(updateInfoGameLabel);
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
            UserDialogs.showWinFxMessage(getPlayerName(board.getWhoseMove()), board.getWhoseMove(), updateInfoGameLabel);
            boardView.disableInput();
        } else if (!board.checkIfboardIsNotFull()) {
            UserDialogs.showDrawFxMessage(updateInfoGameLabel);
            boardView.disableInput();
        } else {
            board.switchPlayer();
            UserDialogs.showWhoseMoveFxMessage(getPlayerName(board.getWhoseMove()), board.getWhoseMove(), updateInfoGameLabel);
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
                UserDialogs.showInvalidInputFxMessage(gameConfig.getBoardSize(), updateInfoGameLabel);
            } else {
                playerMove(row, col);
            }
        } catch (NumberFormatException ex) {
            UserDialogs.showInvalidInputIntegerFxMessage(updateInfoGameLabel);
        }
    }

    public Board getBoard() {
        return board;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public String getPlayerName(Player player) {
        return (player == Player.X) ? gameConfig.getPlayer1Name() : gameConfig.getPlayer2Name();
    }

    public Label setUpdateInfoGameLabel(Label update) {
        return this.updateInfoGameLabel = update;
    }

    public BoardView setBoardView(BoardView boardView) {
        return this.boardView = boardView;
    }
}
