package org.example.sudokuproject.src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.log4j.Logger;
import org.example.sudokuproject.exceptions.DaoException;
import org.example.sudokuproject.exceptions.RunExeption;


public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String f1leName;
    private static final Logger logger = Logger.getLogger(SudokuBoard.class.getName());

    public FileSudokuBoardDao(String f1leName) {
        this.f1leName = f1leName + ".txt";
    }

    @Override
    public final SudokuBoard read() throws DaoException {
        try (FileInputStream file = new FileInputStream(f1leName);
             ObjectInputStream obj = new ObjectInputStream(file)) {
            SudokuBoard board = (SudokuBoard) obj.readObject();
            return board;
        } catch (IOException | ClassNotFoundException e) {
            try {
                logger.error("Dao exception while 'read'");
                throw new DaoException(e);
            } catch (DaoException ex) {
                logger.error("Dao runtime exception while 'read' ");
                throw new RunExeption(ex);
            }
        }
    }

    @Override
    public final void write(SudokuBoard x) throws DaoException {
        try (FileOutputStream file = new FileOutputStream(f1leName);
             ObjectOutputStream obj = new ObjectOutputStream(file)) {
            obj.writeObject(x);
        } catch (IOException e) {
            try {
                logger.error("Dao exception while 'write'");
                throw new DaoException(e);
            } catch (DaoException ex) {
                logger.error("Dao runtime exception while 'write'");
                throw new RunExeption(ex);
            }
        }
    }

    public void close() throws DaoException {

    }
}