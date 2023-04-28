package org.example.sudokuproject.src;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow extends Sudokubrc {

    public SudokuRow(List<SudokuField> field) {
        super(field);
    }

    @Override
    public Object clone() {
        List<SudokuField> fields = new ArrayList<>(getField());
        return new SudokuRow(fields);
    }
}