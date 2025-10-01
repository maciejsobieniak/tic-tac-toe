package com.kodilla.tictactoe;

import com.kodilla.tictactoe.logic.GameConfig;
import com.kodilla.tictactoe.services.SaveGameEngine;
import com.kodilla.tictactoe.ui.UserDialogs;
import com.kodilla.tictactoe.ui.UserFxInterface;
import com.kodilla.tictactoe.logic.fx.GameFx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TicTacToeJavaFxRunner extends Application {

    public static GameConfig gameConfig;

    public static void main(String[] args) {
        gameConfig = UserDialogs.getGameParameters();
        if (gameConfig == null) {
            UserDialogs.showErrorLoadGameConfigMessage();
            return;
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GameFx game = new GameFx(gameConfig, null, null);
        UserFxInterface userFxInterface = new UserFxInterface(gameConfig, game);
        game.setBoardView(userFxInterface.getBoardView());
        game.setUpdateInfoGameLabel(userFxInterface.getInfoLabel());
        game.resetGame();

        userFxInterface.resetButton.setOnAction(e -> game.resetGame());
        userFxInterface.moveButton.setOnAction(e -> {
            game.handleMove(userFxInterface.getRowField(), userFxInterface.getColField());
            userFxInterface.setRowFieldClear();
            userFxInterface.setColFieldClear();
        });

        userFxInterface.saveButton.setOnAction(e -> {
            SaveGameEngine.saveGame(game.getBoard(), game.getGameConfig());
        });

        primaryStage.setTitle("Tic Tac Toe game");
        primaryStage.setScene(new Scene(userFxInterface.getRoot()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
