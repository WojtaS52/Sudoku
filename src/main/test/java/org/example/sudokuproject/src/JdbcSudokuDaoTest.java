package org.example.sudokuproject.src;





import org.example.sudokuproject.exceptions.DaoException;
import org.example.sudokuproject.exceptions.InvalidFieldInputException;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSudokuDaoTest {

    public JdbcSudokuDaoTest() {

    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {

    }

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
    }

    private SudokuBoard generateBoard() throws InvalidFieldInputException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        return board;
    }

    @Test
    public void testDatabaseReadWrite() throws IOException, InvalidFieldInputException {

        Files.deleteIfExists(Paths.get("./SudokuDatabase"));
        SudokuBoard boardA = generateBoard();

        try (Dao<SudokuBoard> dbDao = SudokuBoardDaoFactory.getDatabaseDao("Test1")) {
            dbDao.write(boardA);
            System.out.println("Saved to database");
        } catch (DaoException e) {
            System.out.println("Cannot save to database");
        } catch (Exception e) {
            System.out.println("Unknown error.");
        }

        try (Dao<SudokuBoard> dbDao = SudokuBoardDaoFactory.getDatabaseDao("Test1");) {
            SudokuBoard board1 = dbDao.read();
            System.out.println("Loaded from database");
        } catch (DaoException ex) {
            System.out.println("Cannot load from database");
        } catch (Exception e) {
            System.out.println("Unknown error.");
        }
    }
}