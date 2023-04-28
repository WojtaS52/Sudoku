package org.example.sudokuproject.exceptions;

import java.sql.SQLException;

public class SqlException extends SQLException {

    public SqlException(String message) {
        super(message);
    }

    public SqlException(Throwable cause) {
        super(cause);
    }

    public SqlException(String message, Throwable cause) {
        super(message, cause);
    }

}