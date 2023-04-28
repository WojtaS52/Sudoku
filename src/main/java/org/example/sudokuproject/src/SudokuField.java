package org.example.sudokuproject.src;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.log4j.Logger;
import org.example.sudokuproject.exceptions.NullPointerExcepton;

public class SudokuField implements Serializable, Comparable<SudokuField>, Cloneable {

    private int value;
    private static final Logger logger = Logger.getLogger(SudokuBoard.class.getName());

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    @Override
    public String toString() {
        return "SudokuField{"
                + "value="
                + value
                + '}';
    }

    public SudokuField() {

    }

    public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int value) {
        if (value >= 0 && value <= 9) {
            this.value = value;
        }
    }

    @Override
    public int compareTo(SudokuField o) {
        if (o == null) {
            logger.error("NullPointerException during comparing");
            throw new NullPointerExcepton();
        } else {
            return Integer.compare(o.getFieldValue(), this.value);
        }
    }


    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }
}
