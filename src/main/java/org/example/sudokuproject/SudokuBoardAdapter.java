package org.example.sudokuproject;

import org.example.sudokuproject.exceptions.InvalidFieldInputException;
import org.example.sudokuproject.src.SudokuBoard;

public class SudokuBoardAdapter {
    private SudokuBoard board;
    private int row;
    private int column;

    public SudokuBoardAdapter(SudokuBoard board, int row, int column) {
        this.board = board;
        this.row = row;
        this.column = column;
    }

    public int getField() {
        return board.get(row, column);
    }

    public void setField(int value) throws InvalidFieldInputException {
        board.set(row, column, value);
    }

}
