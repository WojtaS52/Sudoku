package org.example.sudokuproject.src;

import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public abstract class Sudokubrc implements Cloneable {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Sudokubrc sudokubrc = (Sudokubrc) o;

        return new EqualsBuilder().append(field, sudokubrc.field).isEquals();
    }

    @Override
    public String toString() {
        return "Sudokubrc{"
                + "field="
                + field
                + '}';
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(field).toHashCode();
    }

    //protected SudokuField[] field;
    private List<SudokuField> field;

    public Sudokubrc(List<SudokuField> field) {
        this.field = field;
    }

    public List<SudokuField> getField() {
        return field;
    }


    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (field.get(i).getFieldValue() == field.get(j).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }


}
