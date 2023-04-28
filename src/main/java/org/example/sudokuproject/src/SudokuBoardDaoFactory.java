package org.example.sudokuproject.src;

public class SudokuBoardDaoFactory {

    public static Dao<SudokuBoard> getFileDao(String f1leName) {
        return new FileSudokuBoardDao(f1leName);
    }

    public static Dao<SudokuBoard> getDatabaseDao(String filename) {
        return new JdbcSudokuDao(filename);
    }
}