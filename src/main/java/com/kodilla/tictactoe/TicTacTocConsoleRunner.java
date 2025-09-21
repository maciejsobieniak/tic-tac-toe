package com.kodilla.tictactoe;

import com.kodilla.tictactoe.logic.Game;
import com.kodilla.tictactoe.logic.GameConfig;
import com.kodilla.tictactoe.ui.UserDialogs;

import java.util.Scanner;

public class TicTacTocConsoleRunner {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameConfig gameConfig = UserDialogs.getGameParameters(sc);
        Game game = new Game(gameConfig);
        game.startNewGame();
        while (true) {
            if (UserDialogs.showAskReaplyGameDialog(sc)) {
                game.startNewGame();
            } else if (UserDialogs.showAskIfNewGameDialog(sc)) {
                gameConfig = UserDialogs.getGameParameters(sc);
                game = new Game(gameConfig);
                game.startNewGame();
            } else {
                UserDialogs.showExitMessage();
                break;
            }
        }
    }
}
