package com.kodilla.tictactoe.logic;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTestSuite {

    private static int testCounter = 0;

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("Start tests board module");
    }

    @BeforeEach
    public void before() {
        testCounter++;
        System.out.println("Preparing to execute test #" + testCounter);
        System.out.println("Test Case: begin");
    }

    @AfterEach
    public void after() {
        System.out.println("Test Case: end");
    }

    @Test
    @DisplayName("Test getBoardSize method")

    void testGetBoardSize() {
        // Given
        int boardSize = 5;
        Board board = new Board(boardSize);

        // When
        int resultSize = board.getBoardSize();

        // Then
        assertEquals(boardSize, resultSize);
    }

    @Test
    @DisplayName("Test checkIfboardIsNotFull method on empty board")

    void testCheckIfboardIsNotFullOnEmptyBoard() {
        // Given
        int boardSize = 3;
        Board board = new Board(boardSize);

        // When
        boolean result = board.checkIfboardIsNotFull();

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Test checkIfboardIsNotFull method on full board")

    void testCheckIfboardIsNotFullOnFullBoard() {
        // Given
        int boardSize = 3;
        Board board = new Board(boardSize);
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                board.setPlayerAt(new Move(row, col, Player.X));
            }
        }
        // When
        boolean result = board.checkIfboardIsNotFull();
        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Test O player winning conditions in row")

    void testOWinInRows() {
        Board board = new Board(3);
        for (int col = 0; col < 3; col++) {
            board.setPlayerAt(new Move(1, col, Player.O));
        }
        assertTrue(board.checkWinCondition(Player.O, 3));
    }

    @Test
    @DisplayName("Test O player winning conditions in column")

    void testOWinInColumns() {
        Board board = new Board(3);
        for (int row = 0; row < 3; row++) {
            board.setPlayerAt(new Move(row, 2, Player.O));
        }
        assertTrue(board.checkWinCondition(Player.O, 3));
    }

    @Test
    @DisplayName("Test O player winning conditions in diagonal left to right")

    void testOWinInLeftToRight() {
        Board board = new Board(3);
        for (int i = 0; i < 3; i++) {
            board.setPlayerAt(new Move(i, i, Player.O));
        }
        assertTrue(board.checkWinCondition(Player.O, 3));
    }

    @Test
    @DisplayName("Test O player winning conditions in diagonal right to left")

    void testOWinInRightToLeft() {
        Board board = new Board(3);
        for (int i = 0; i < 3; i++) {
            board.setPlayerAt(new Move(i, 2 - i, Player.O));
        }
        assertTrue(board.checkWinCondition(Player.O, 3));
    }

    @Test
    @DisplayName("Test X player winning conditions in row")

    void testXWinInRows() {
        Board board = new Board(3);
        for (int col = 0; col < 3; col++) {
            board.setPlayerAt(new Move(0, col, Player.X));
        }
        assertTrue(board.checkWinCondition(Player.X, 3));
    }

    @Test
    @DisplayName("Test X player winning conditions in column")

    void testXWinInColumns() {
        Board board = new Board(3);
        for (int row = 0; row < 3; row++) {
            board.setPlayerAt(new Move(row, 1, Player.X));
        }
        assertTrue(board.checkWinCondition(Player.X, 3));
    }

    @Test
    @DisplayName("Test X player winning conditions in diagonal left to right")

    void testXWinInLeftToRight() {
        Board board = new Board(3);
        for (int i = 0; i < 3; i++) {
            board.setPlayerAt(new Move(i, i, Player.X));
        }
        assertTrue(board.checkWinCondition(Player.X, 3));
    }

    @Test
    @DisplayName("Test X player winning conditions in diagonal right to left")

    void testXWinInRightToLeft() {
        Board board = new Board(3);
        for (int i = 0; i < 3; i++) {
            board.setPlayerAt(new Move(i, 2 - i, Player.X));
        }
        assertTrue(board.checkWinCondition(Player.X, 3));
    }

    @Test
    @DisplayName("Test draw")

    void testDrawSituation() {
        Board board = new Board(3);
        // X O X
        // X X O
        // O X O
        board.setPlayerAt(new Move(0, 0, Player.X));
        board.setPlayerAt(new Move(0, 1, Player.O));
        board.setPlayerAt(new Move(0, 2, Player.X));
        board.setPlayerAt(new Move(1, 0, Player.O));
        board.setPlayerAt(new Move(1, 1, Player.X));
        board.setPlayerAt(new Move(1, 2, Player.X));
        board.setPlayerAt(new Move(2, 0, Player.O));
        board.setPlayerAt(new Move(2, 1, Player.X));
        board.setPlayerAt(new Move(2, 2, Player.O));
        assertFalse(board.checkWinCondition(Player.X, 3));
        assertFalse(board.checkWinCondition(Player.O, 3));
        assertFalse(board.checkIfboardIsNotFull());
    }

    @Test
    @DisplayName("Test invalid move throws exception")

    void testInvalidMoveThrowsException() {
        Board board = new Board(3);
        board.setPlayerAt(new Move(0, 0, Player.X));
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            board.setPlayerAt(new Move(3, 3, Player.O));
        });
        assertNotNull(exception);
    }
}
