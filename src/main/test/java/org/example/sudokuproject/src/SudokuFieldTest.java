package org.example.sudokuproject.src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {

    private SudokuField sfield;

    @BeforeEach
    void setUp() {
        sfield = new SudokuField();
    }

    @Test
    public void getFieldValueTest() {
        assertEquals(sfield.getFieldValue(),0);
    }

    @Test
    public void setFieldValueTest1() {
        sfield.setFieldValue(8);
        assertEquals(sfield.getFieldValue(),8);
    }

    @Test
    public void setFieldValueTest2() {
        sfield.setFieldValue(5);
        assertEquals(sfield.getFieldValue(),5);
    }

    @Test
    public void setFieldValueTest3() {
        sfield.setFieldValue(10);
        assertNotEquals(sfield.getFieldValue(),10);
    }

    @Test
    public void setFieldValueTest4() {
        sfield.setFieldValue(-1);
        assertNotEquals(sfield.getFieldValue(),-1);
    }

    @Test
    public void fieldEqualsTestCheckIfEquals(){
        SudokuField sudok1 = new SudokuField();
        sudok1.setFieldValue(8);
        SudokuField sudok2 = new SudokuField();
        sudok2.setFieldValue(8);

        assertTrue(sudok1.equals(sudok2) && sudok2.equals(sudok1));
    }

    @Test
    public void fieldEqualsTestCheckIfTheSame(){
        SudokuField sudok1 = new SudokuField();
        sudok1.setFieldValue(8);

        assertTrue(sudok1.equals(sudok1) && sudok1.equals(sudok1));
    }

    @Test
    public void fieldEqualsTestCheckIfDifferent(){
        SudokuField sudok = new SudokuField();
        sudok.setFieldValue(8);
        String string = null;
        assertNotEquals(sudok, string);
    }

    @Test
    public void fieldEqualsTestCheckIfNotquals(){
        SudokuField sudok = new SudokuField();
        sudok.setFieldValue(8);

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver);
        instance1.solveGame();
        assertNotEquals(sudok, instance1);
    }

    @Test
    public void fieldEqualsTestCheckIfOther(){
        SudokuField sudok1 = new SudokuField();
        sudok1.setFieldValue(8);
        SudokuField sudok2 = new SudokuField();
        sudok2.setFieldValue(2);

        assertNotEquals(sudok1, sudok2);
    }

    @Test
    void testToString() {
        SudokuField sudok = new SudokuField();
        sudok.setFieldValue(0);
        assertNotNull(sudok.toString());
    }

    @Test
    public void brfTestHashCode() {
        SudokuField sudok1 = new SudokuField();
        sudok1.setFieldValue(8);
        SudokuField sudok2 = new SudokuField();
        sudok2.setFieldValue(8);

        if (!sudok1.equals(sudok2) && sudok1.hashCode() == sudok2.hashCode()) {
            fail("Metody equals i hashcode nie są spójne");
        }
    }
    @Test
    public void compareToTest() {

        SudokuField f1 = new SudokuField();
        SudokuField f2 = new SudokuField();
        f1.setFieldValue(5);
        f2.setFieldValue(5);
        assertEquals(f1.compareTo(f2),0);
    }

    public void compareToTest2() {
        //<0
        SudokuField f1 = new SudokuField();
        SudokuField f2 = new SudokuField();
        f1.setFieldValue(2);
        f2.setFieldValue(4);
        assertTrue(f1.compareTo(f2) < 0);

        //>0
        f1.setFieldValue(6);
        f2.setFieldValue(3);
        assertTrue(f1.compareTo(f2) > 0);


    }


    @Test
    public void compareToTestNull() {
        SudokuField f1 = new SudokuField();
        f1.setFieldValue(3);
        SudokuField nullField = null;
        assertThrows(NullPointerException.class, () -> {
            f1.compareTo(nullField); });

    }

    @Test
    public void ClonableTest() {
        SudokuField f1 = new SudokuField();
        f1.setFieldValue(3);
        try {
            SudokuField f2 = f1.clone();
            assertEquals(f1, f2);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}