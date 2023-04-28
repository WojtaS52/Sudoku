package org.example.sudokuproject.exceptions;

public class RunExeption extends RuntimeException {

    public RunExeption(Throwable cause) {
        super(cause);
    }

    public RunExeption(String message) {
        super(message);
    }

    public RunExeption(String message, Throwable cause) {
        super(message, cause);
    }



}
