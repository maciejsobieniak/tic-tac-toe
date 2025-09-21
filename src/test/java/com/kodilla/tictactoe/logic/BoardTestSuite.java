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
}
