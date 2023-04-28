package org.example.sudokuproject.src;

import org.example.sudokuproject.exceptions.DaoException;

public interface Dao<T> extends AutoCloseable {
    T read() throws DaoException;

    void write(T obj) throws DaoException;
}
