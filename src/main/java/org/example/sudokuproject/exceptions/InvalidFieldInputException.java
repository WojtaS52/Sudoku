package org.example.sudokuproject.exceptions;

import java.io.IOException;

public class InvalidFieldInputException extends IOException {

    public InvalidFieldInputException() {

    }

    public InvalidFieldInputException(String message) {
        super(message);
    }

    public InvalidFieldInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFieldInputException(Throwable cause) {
        super(cause);
    }

}