package org.example.sudokuproject.exceptions;

//import org.apache.log4j.Logger;
//import org.example.sudokuproject.src.SudokuBoard;
import java.io.IOException;

public class DaoException extends IOException {

    public DaoException(Throwable cause) {
        super(cause);
    }

   /* public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }*/
}