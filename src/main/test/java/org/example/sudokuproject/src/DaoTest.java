package org.example.sudokuproject.src;

import org.example.sudokuproject.exceptions.DaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DaoTest {

    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    @Test
    public void getFileDaoTest() {
        Assertions.assertNotNull(factory.getFileDao("xyz"));
    }


    private SudokuBoardDaoFactory factoryy = new SudokuBoardDaoFactory();

    private SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());

    private Dao<SudokuBoard> fileSudokuBoardDao;
    private SudokuBoard sudokuBoardSecond;

    @Test
    public void writeReadTest() {



        try(Dao<SudokuBoard> fileSudokuBoardDao = factoryy.getFileDao("Legia")){
            fileSudokuBoardDao.write(sudokuBoard);
            sudokuBoardSecond = fileSudokuBoardDao.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Assertions.assertNotEquals(sudokuBoard, sudokuBoardSecond);
    }


    @Test
    public void readTest() {
        fileSudokuBoardDao = factoryy.getFileDao("test");
        Assertions.assertThrows(RuntimeException.class, () -> fileSudokuBoardDao.read());

    }

    @Test
    public void exceptionWriteTest() {
        fileSudokuBoardDao = factoryy.getFileDao("?#$%^^");
        Assertions.assertThrows(RuntimeException.class, () -> fileSudokuBoardDao.write(sudokuBoard));
    }

}