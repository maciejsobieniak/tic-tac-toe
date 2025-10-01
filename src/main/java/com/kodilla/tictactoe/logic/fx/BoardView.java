package com.kodilla.tictactoe.logic.fx;

import com.kodilla.tictactoe.logic.Board;
import com.kodilla.tictactoe.logic.Player;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BoardView extends GridPane {

    private boolean inputEnabled = true;
    private GameFx game;

    public BoardView(GameFx game) {
        this.game = game;
        setAlignment(Pos.CENTER);
        setHgap(6);
        setVgap(6);
        setPadding(new javafx.geometry.Insets(12));
    }

    public void drawBoard(Board board) {
        getChildren().clear();
        int size = board.getBoardSize();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Rectangle rect = new Rectangle(40, 40, Color.LIGHTGRAY);
                rect.setStroke(Color.BLACK);
                Player player = board.getPlayerAt(row, col);
                Text text = new Text(player == Player.NONE ? "" : player.toString());
                text.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
                StackPane cell = new StackPane(rect, text);
                cell.setAlignment(Pos.CENTER);
                final int r = row, c = col;
                cell.setOnMouseClicked(e -> {
                    if (inputEnabled) game.playerMove(r, c);
                });
                add(cell, col, row);
            }
        }
    }

    public void disableInput() {
        inputEnabled = false;
    }

    public void enableInput() {
        inputEnabled = true;
    }

}
