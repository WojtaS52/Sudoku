package org.example.sudokuproject.exceptions;

import java.io.IOException;

public class FxmlLoadException extends IOException {

    public FxmlLoadException(String message) {
        super(message);
    }

    public FxmlLoadException(Throwable cause) {
        super(cause);
    }

    public FxmlLoadException(String message, Throwable cause) {
        super(message,cause);
    }

}