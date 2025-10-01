package com.kodilla.tictactoe.ui;

import com.kodilla.tictactoe.logic.GameConfig;
import com.kodilla.tictactoe.logic.fx.GameFx;
import com.kodilla.tictactoe.logic.fx.BoardView;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class UserFxInterface {
    private final BorderPane root;
    public final TextField rowField;
    public final TextField colField;
    public final Button moveButton;
    public final Button resetButton;
    public final Button saveButton;
    public final Label updateInfoGameLabel;
    public final BoardView boardView;

    public UserFxInterface(GameConfig gameConfig, GameFx game) {
        updateInfoGameLabel = new Label("Lets start the game!");
        boardView = new BoardView(game);
        rowField = new TextField();
        rowField.setPromptText("row (0 - " + (gameConfig.getBoardSize() - 1) + ")");
        rowField.setPrefColumnCount(8);

        colField = new TextField();
        colField.setPromptText("column (0 - " + (gameConfig.getBoardSize() - 1) + ")");
        colField.setPrefColumnCount(8);

        moveButton = new Button("Make move");
        resetButton = new Button("New game");
        saveButton = new Button("Save game");

        ToolBar top = new ToolBar(
                new Label("Row:"), rowField,
                new Label("Column:"), colField,
                new Separator(),
                moveButton,
                new Separator(),
                resetButton,
                new Separator(),
                saveButton
        );

        VBox bottom = new VBox(new Separator(), updateInfoGameLabel, resetButton);
        root = new BorderPane();
        root.setTop(top);
        root.setCenter(boardView);
        root.setBottom(bottom);
    }

    public BorderPane getRoot() {
        return root;
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public Label getInfoLabel() {
        return updateInfoGameLabel;
    }

    public String getRowField() {
        return rowField.getText();
    }

    public String getColField() {
        return colField.getText();
    }

    public void setRowFieldClear() {
        rowField.clear();
    }

    public void setColFieldClear() {
        colField.clear();
    }
}

