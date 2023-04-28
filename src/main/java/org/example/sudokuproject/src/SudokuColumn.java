package org.example.sudokuproject.src;

import java.util.ArrayList;
import java.util.List;

public class SudokuColumn extends Sudokubrc {

    public SudokuColumn(List<SudokuField> field) {
        super(field);
    }

    @Override
    public Object clone() {
        List<SudokuField> fields = new ArrayList<>(getField());
        return new SudokuColumn(fields);
    }
}
