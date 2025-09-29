package com.kodilla.tictactoe.services;

import com.kodilla.tictactoe.logic.Board;
import com.kodilla.tictactoe.logic.GameConfig;
import com.kodilla.tictactoe.logic.Player;
import com.kodilla.tictactoe.ui.UserDialogs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SaveGameEngine {

    public static void saveGame(Board board, GameConfig gameConfig) {
        if (board == null || gameConfig == null) {
            UserDialogs.showGameSavedMessageErrorBoardOrGameConfig();
            return;
        }
        if (!board.checkIfboardIsNotFull()) {
            UserDialogs.showGameSavedMessageErrorBoardIsFull();
            return;
        }
        try {
            SaveGame saveGame = new SaveGame(
                    SaveGameEngine.saveBoard(board),
                    board.getWhoseMove(),
                    gameConfig.getPlayer1Name(),
                    gameConfig.getPlayer2Name(),
                    gameConfig.getStartingPlayerName(),
                    gameConfig.getGameMode(),
                    gameConfig.getComputerDifficulty(),
                    gameConfig.getBoardSize(),
                    gameConfig.getWinLength()
            );
            SaveGameEngine.saveToFile(saveGame, "save_game.dat");
            UserDialogs.showLoadGameSuccessMessage();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static List<List<Player>> saveBoard(Board board) {
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

    public static void saveToFile(SaveGame saveGame, String filename) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(saveGame);
        oos.close();
    }

}
