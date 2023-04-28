package org.example.sudokuproject.src;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SudokubrfTest {

    @Test
    public void brfEqualsTestCheckIfEquals(){
        List<SudokuField> field = Arrays.asList(new SudokuField[9]);
        SudokuField sudok = new SudokuField();
        sudok.setFieldValue(8);
        field.set(0,sudok);
        SudokuRow sudokuRow1 = new SudokuRow(field);
        SudokuRow sudokuRow2 = new SudokuRow(field);

        assertTrue(sudokuRow1.equals(sudokuRow2) && sudokuRow2.equals(sudokuRow1));
    }

    @Test
    public void brfEqualsTestCheckIfDifferent(){
        List<SudokuField> field = Arrays.asList(new SudokuField[9]);
        SudokuField sudok = new SudokuField();
        sudok.setFieldValue(8);
        field.set(0,sudok);
        String string = null;
        assertNotEquals(sudok, string);
    }

    @Test
    public void brfEqualsTestCheckIfNotquals(){
        List<SudokuField> field = Arrays.asList(new SudokuField[9]);
        SudokuField sudok = new SudokuField();
        sudok.setFieldValue(8);
        field.set(0,sudok);
        SudokuRow sudokuRow = new SudokuRow(field);

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver);
        instance1.solveGame();
        assertNotEquals(sudokuRow, instance1);
    }

    @Test
    public void brfEqualsTestCheckIfOther(){
        List<SudokuField> field = Arrays.asList(new SudokuField[9]);
        SudokuField sudok = new SudokuField();
        sudok.setFieldValue(8);
        field.set(0,sudok);
        SudokuRow sudokuRow1 = new SudokuRow(field);
        SudokuRow sudokuRow2 = null;
        assertNotEquals(sudokuRow1, sudokuRow2);
    }

    @Test
    void brfTestToString() {
        List<SudokuField> field = Arrays.asList(new SudokuField[9]);
        SudokuField sudok = new SudokuField();
        field.set(0,sudok);
        SudokuRow sudokuRow1 = new SudokuRow(field);
        assertNotNull(sudokuRow1.toString());
    }

    @Test
    public void brfTestHashCode() {
        List<SudokuField> field = Arrays.asList(new SudokuField[9]);
        SudokuField sudok = new SudokuField();
        sudok.setFieldValue(8);
        field.set(0,sudok);
        SudokuRow sudokuRow1 = new SudokuRow(field);
        SudokuRow sudokuRow2 = new SudokuRow(field);

        if (!sudokuRow1.equals(sudokuRow2) && sudokuRow1.hashCode() == sudokuRow2.hashCode()) {
            fail("Metody equals i hashcode nie są spójne");
        }
    }

    @Test
    public void brfTestHashCodeConsistent() {
        List<SudokuField> field = Arrays.asList(new SudokuField[9]);
        SudokuField sudok = new SudokuField();
        sudok.setFieldValue(8);
        field.set(0,sudok);
        SudokuRow sudokuRow2 = new SudokuRow(field);

        SudokuRow sudokuRow3 = sudokuRow2;
        if (sudokuRow2.equals(sudokuRow3) && sudokuRow2.hashCode() != sudokuRow3.hashCode()) {
            fail("Metody equals i hashcode nie są spójne");
        }
    }

    @Test
    public void ClonableTestColumn() {
        List<SudokuField> board = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {

            board.set(i, new SudokuField());
            board.get(i).setFieldValue(i + 1);

        }
        Sudokubrc brc  = new SudokuColumn(board);
        Sudokubrc brc2;
        brc2 = (SudokuColumn) ((SudokuColumn)brc).clone();
        assertTrue(brc.equals(brc2) && brc.equals(brc));
    }

    @Test
    public void boxClonableTest() {
        List<SudokuField> field = Arrays.asList(new SudokuField[9]);
        SudokuField f = new SudokuField();
        f.setFieldValue(1);
        field.set(1,f);

        SudokuBox sudokuBox1 = new SudokuBox(field);
        Object sudokuBox2 = sudokuBox1.clone();

        assertEquals(sudokuBox1.hashCode(), sudokuBox2.hashCode());
    }


    @Test
    public void rowClonableTest(){
        List<SudokuField> field = Arrays.asList(new SudokuField[9]);
        SudokuField f = new SudokuField();
        f.setFieldValue(1);
        field.set(1,f);

        SudokuRow sudokuRow1 = new SudokuRow(field);
        Object sudokuRow2 = sudokuRow1.clone();

        assertEquals(sudokuRow2.hashCode(), sudokuRow1.hashCode());
    }

    /*@Test
    public void columnClonableTest(){
        List<SudokuField> field = Arrays.asList(new SudokuField[9]);
        SudokuField f = new SudokuField();
        f.setFieldValue(1);
        field.set(1,f);

        SudokuColumn sc1 = new SudokuColumn(field);
        Object sc2 = sc1.clone();

        assertEquals(sc1.hashCode(), sc2.hashCode());
    }*/
}