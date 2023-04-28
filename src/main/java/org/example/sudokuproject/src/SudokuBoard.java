package org.example.sudokuproject.src;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.log4j.Logger;
import org.example.sudokuproject.exceptions.InvalidFieldInputException;


public class SudokuBoard implements Serializable, Cloneable {
    public int[][] boardSave = new int[9][9];
    private static final int BOARD_SIZE = 9;
    private SudokuField[][] board = new SudokuField[9][9];
    private final SudokuSolver solver;
    //private int[][] board = new int[9][9];
    private static final Logger logger = Logger.getLogger(SudokuBoard.class.getName());


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder()
                .append(board, that.board)
                .append(solver, that.solver)
                .append(boardSave, that.boardSave)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(board)
                .append(solver)
                .append(boardSave)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "SudokuBoard{"
                + "board="
                + Arrays.toString(board)
                + ", solver="
                + solver
                + ", boardSave=" + Arrays.toString(boardSave)
                + '}';
    }



    public SudokuBoard(SudokuSolver sudokuSolver) {
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                board[i][k] = new SudokuField();
            }
        }
        this.solver = sudokuSolver;
    }


    public int get(int x, int y) {

        return board[x][y].getFieldValue();


    }

    public void set(int x, int y, int value) {

        this.board[x][y].setFieldValue(value);

    }

    public int [][] copyOfBoard() {

        int[][] copyBoard = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                copyBoard[i][j] = get(i, j);
            }
        }
        return copyBoard;
    }


    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInSquare(int[][] board, int number, int row, int column) {
        int localSquareRow = row - row % 3;
        int localSquareColumn = column - column % 3;
        for (int i = localSquareRow; i < localSquareRow + 3; i++) {
            for (int j = localSquareColumn; j < localSquareColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInColumn(board, number, column)
                && !isNumberInRow(board, number, row)
                && !isNumberInSquare(board, number, row, column);
    }



    public void solveGame() {
        solver.solve(this);
    }

    public void printInside() {
        logger.info(" ");
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE / 3; column++) {
                logger.info(board[row][column].getFieldValue()
                        + " "
                        + board[row][column + 1].getFieldValue()
                        + " "
                        + board[row][column     + 2].getFieldValue());
                if (column == 2 || column == 5) {
                    logger.info(" | ");
                }
                if (row == 2 && column == 8 || row == 5 && column == 8) {
                    logger.info(" ");
                    logger.info("---------------");
                }
            }
            logger.info(" ");
        }
    }

    public void printBoard(int[][] board) {
        printInside();
        logger.info(" ");
    }

    public void boardDifficultySudokuPrint() {
        printInside();
        logger.info(" ");
    }

    public SudokuRow getRow(int y) {

        List<SudokuField> field = Arrays.asList(new SudokuField[9]);

        for (int i = 0; i < 9; i++) {
            field.set(i, board[y][i]);
        }
        SudokuRow sudokuRow = new SudokuRow(field);
        return sudokuRow;
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> field = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            field.set(i, board[i][x]);
        }
        SudokuColumn column = new SudokuColumn(field);
        return column;
    }

    public SudokuBox getBox(int x, int y) {
        List<SudokuField> field = Arrays.asList(new SudokuField[9]);
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field.set(index++, board[(x / 3) * 3 + i][(y / 3) * 3 + j]);
            }
        }
        SudokuBox box = new SudokuBox(field);
        return box;
    }

    private boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!getBox(i,j).verify()) {
                    return false;
                }
            }
            if (!getColumn(i).verify()) {
                return false;
            }

            if (!getRow(i).verify()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkCheckBoard() {
        return checkBoard();
    }

    public void boardToFieldValue() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j].setFieldValue(boardSave[i][j]);
            }
        }
    }

    @Override
    public SudokuBoard clone() {
        SudokuBoard sudokuBoard = new SudokuBoard(solver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, get(i, j));
            }
        }
        return sudokuBoard;
    }

    public String boardToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                builder.append(String.valueOf(get(i, k)));
            }
        }
        return builder.toString();
    }

    public SudokuBoard stringToBoard(String str) throws InvalidFieldInputException {
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                set(i, k, Character.getNumericValue(str.charAt(i * 9 + k)));
            }
        }
        return this;
    }
}
