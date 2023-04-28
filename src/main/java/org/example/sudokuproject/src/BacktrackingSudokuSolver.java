package org.example.sudokuproject.src;

import java.io.Serializable;
import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {

    private static final int BOARD_SIZE = 9;

    @Override
    public void solve(SudokuBoard board) {
        //SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        Random rand = new Random();
        int randomNumbersCount = 7 + (rand.nextInt(14));
        for (int i = 0; i <= randomNumbersCount; i++) {
            int randomPlaceSelector = rand.nextInt(81);
            int randomNumber = 1 + rand.nextInt(9);
            int row = (int)Math.ceil(randomPlaceSelector / 9);
            int column = randomPlaceSelector % 9;
            if (board.isValidPlacement(board.copyOfBoard(), randomNumber, row, column)) {
                board.set(row, column, randomNumber);
            }
        }
        solveBoard(board);
        board.boardSave = board.copyOfBoard();
    }

    public boolean solveBoard(SudokuBoard board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (board.get(row, column) == 0) {
                    for (int numberToTry = 1; numberToTry <= BOARD_SIZE; numberToTry++) {
                        if (board.isValidPlacement(board.copyOfBoard(), numberToTry, row, column)) {
                            board.set(row, column, numberToTry);

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board.set(row, column, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        //board.printBoard(board.copyOfBoard());
        return true;
    }

}