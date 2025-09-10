package com.kodilla.tictactoe;

public class Mark {

    protected int number;
    protected char symbol;

    public Mark(int number) {
        this.number = number;
       //this.symbol = ' ';
    }

    public char getSymbol() {
        return symbol;
    }
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
