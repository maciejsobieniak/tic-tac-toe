package com.kodilla.tictactoe;

import com.kodilla.tictactoe.logic.GameConfig;
import com.kodilla.tictactoe.services.SaveGameEngine;
import com.kodilla.tictactoe.ui.UserDialogs;
import com.kodilla.tictactoe.logic.fx.GameFx;
import com.kodilla.tictactoe.logic.fx.BoardView;
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
        Label infoLabel = new Label("Rozpocznij grę!");
        BoardView boardView = new BoardView(null);
        GameFx game = new GameFx(gameConfig, boardView, infoLabel);
        boardView.setGame(game);

        TextField rowField = new TextField();
        rowField.setPromptText("wiersz (0 - " + (gameConfig.getBoardSize() - 1) + ")");
        rowField.setPrefColumnCount(8);

        TextField colField = new TextField();
        colField.setPromptText("kolumna (0 - " + (gameConfig.getBoardSize() - 1) + ")");
        colField.setPrefColumnCount(8);

        Button moveButton = new Button("Zagraj");
        Button resetButton = new Button("Nowa gra");
        Button saveButton = new Button("Zapisz grę");
        resetButton.setOnAction(e -> game.resetGame());
        moveButton.setOnAction(e -> {
            game.handleMove(rowField.getText(), colField.getText());
            rowField.clear();
            colField.clear();
        });
        saveButton.setOnAction(e -> {
            SaveGameEngine.saveGame(game.getBoard(), game.getGameConfig());
        });


        ToolBar top = new ToolBar(
                new Label("Wiersz:"), rowField,
                new Label("Kolumna:"), colField,
                new Separator(),
                moveButton,
                new Separator(),
                resetButton,
                new Separator(),
                saveButton

        );


        VBox bottom = new VBox(new Separator(), infoLabel, resetButton);
        BorderPane root = new BorderPane();
        root.setTop(top);

        root.setCenter(boardView);
        root.setBottom(bottom);

        primaryStage.setTitle("Tic Tac Toe game");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
