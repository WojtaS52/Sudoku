package org.example.sudokuproject.src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import  org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    private SudokuBoard sboard;


    @BeforeEach
    void setUp() {
        sboard = new SudokuBoard(new BacktrackingSudokuSolver());

    }


    @Test
    public void boardTestHashCode() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver);
        SudokuBoard instance2 = new SudokuBoard(solver);
        instance1.solveGame();
        instance2.solveGame();

        if (!instance1.equals(instance2) && instance1.hashCode() == instance2.hashCode()) {
            fail("Metody equals i hashcode nie są spójne");
        }


    }

    @Test
    public void boardTestHashCodeConsistent() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver);
        SudokuBoard instance2 = new SudokuBoard(solver);
        instance1.solveGame();
        instance2.solveGame();

        SudokuBoard instance3 = instance2;
        if (instance2.equals(instance3) && instance2.hashCode() != instance3.hashCode()) {
            fail("Metody equals i hashcode nie są spójne");
        }
    }

    @Test
    void boardTestToString() {
        SudokuBoard sboard = new SudokuBoard(new BacktrackingSudokuSolver());
        sboard.solveGame();
        if (sboard.toString() == null || sboard.toString() == "") {
            fail("toString method works incorrectly");
        }
    }

    @Test
    public void boardEqualsTestCheckIfEquals(){
        assertEquals(sboard, sboard);
    }

    @Test
    public void boardEqualsTestCheckIfDifferent(){
        String string = null;
        assertNotEquals(sboard, string);
    }

    @Test
    public void boardEqualsTestCheckIfOther(){
        SudokuSolver solver = new BacktrackingSudokuSolver();
        assertNotEquals(sboard, solver);
    }

    @Test
    public void boardEqualsTestCheckIfNotquals(){
        assertNotEquals(sboard, sboard.boardSave);
    }

    @Test
    public void boardDifficultySudokuPrint() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        sudokuBoard.boardDifficultySudokuPrint();
    }

    @Test
    public void ClonableTestBoard() {
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard s2 = s1.clone();
        assertEquals(s1, s2);
    }

    /* @Test
     public void testEquals() throws InvalidFieldInputException {
         SudokuSolver solver = new RecursiveSudokuSolver();
         SudokuBoard instance1 = new SudokuBoard(solver);
         SudokuBoard instance2 = new SudokuBoard(solver);
         SudokuBoard instance3 = null;
         instance1.solveGame();
         instance2.solveGame();
         int a = 5;
         if (instance1.equals(instance3)) {
             fail("Tested object is not checked for null value");
         }
         if (instance2.equals(a)) {
             fail("Object classes are not compared correctly");
         }
         if (instance1.equals(instance2)) {
             fail("Fields \"board\" are not compared correctly");
         }

         SudokuBoard instance4 = new SudokuBoard(solver);
         SudokuBoard instance5 = new SudokuBoard(solver);
         if (!instance4.equals(instance5)) {
             fail("equals() method works incorrectly for equal objects");
         }
         assertTrue(instance4.equals(instance5));
     }

     */
    @Test
    void checkBoardTest(){
        sboard.solveGame();
        sboard.boardToFieldValue();
        assertTrue(sboard.checkCheckBoard());

        sboard.boardSave = new int [][]{{1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1}};

        sboard.boardToFieldValue();
        assertFalse(sboard.checkCheckBoard());

        sboard.boardSave = new int [][]{{2, 4, 7, 1, 4, 8, 4, 8, 3},
                {6, 1, 3, 7, 6, 3, 9, 2, 7},
                {9, 5, 8, 9, 5, 2, 5, 6, 1},
                {8, 6, 2, 3, 8, 9, 1, 9, 8},
                {3, 9, 1, 5, 1, 6, 3, 7, 4},
                {4, 7, 5, 4, 2, 7, 2, 5, 6},
                {7, 3, 9, 8, 3, 4, 6, 4, 5},
                {1, 8, 4, 6, 9, 5, 7, 3, 2},
                {5, 2, 6, 2, 7, 1, 8, 1, 9}};
        sboard.boardToFieldValue();
        assertFalse(sboard.checkCheckBoard());

        sboard.boardSave = new int [][]{{2, 6, 8, 7, 1, 4, 9, 5, 3},
                {9, 3, 1, 8, 5, 6, 4, 2, 7},
                {5, 4, 7, 9, 2, 3, 6, 8, 1},
                {5, 4, 2, 9, 1, 8, 7, 3, 6},
                {3, 1, 7, 6, 5, 2, 9, 8, 4},
                {6, 8, 9, 3, 7, 4, 2, 1, 5},
                {5, 3, 2, 9, 7, 4, 1, 8, 6},
                {4, 1, 8, 6, 3, 2, 7, 5, 9},
                {7, 9, 6, 8, 5, 1, 3, 2, 4}};
        sboard.boardToFieldValue();
        assertFalse(sboard.checkCheckBoard());
    }

    @Test
    void getRowTest() {

        assertNotNull(sboard.getRow(2));
    }

    @Test
    void getColumnTest() {
        assertNotNull(sboard.getColumn(2));
    }


    @Test
    void getBoxTest() {
        assertNotNull(sboard.getBox(2,2));
    }

    @Test
    public void fillBoardSameTest() {
        SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard board2 = new SudokuBoard(new BacktrackingSudokuSolver());
        int[][] testArray1;
        int[][] testArray2;
        board1.solveGame();
        testArray1 = board1.boardSave;

        board2.solveGame();
        testArray2 = board2.boardSave;

        boolean isSame = true;

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if (testArray1[i][j] != testArray2[i][j]) {
                    isSame = false;
                    break;
                }
            }
        }
        assertFalse(isSame);
    }
}