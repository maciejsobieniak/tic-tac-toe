package com.kodilla.tictactoe;

import com.kodilla.tictactoe.logic.Game;
import com.kodilla.tictactoe.logic.GameConfig;
import com.kodilla.tictactoe.ui.UserDialogs;

import java.util.Scanner;

public class TicTacTocConsoleRunner {

    public static void main(String[] args) {

        GameConfig gameConfig = UserDialogs.getGameParameters();
        Game game = new Game(gameConfig);
        game.startNewGame();
        while (true) {
            if (UserDialogs.showAskReaplyGameDialog()) {
                game.startNewGame();
            } else if (UserDialogs.showAskIfNewGameDialog()) {
                gameConfig = UserDialogs.getGameParameters();
                game = new Game(gameConfig);
                game.startNewGame();
            } else {
                UserDialogs.showExitMessage();
                break;
            }
        }
    }
}
