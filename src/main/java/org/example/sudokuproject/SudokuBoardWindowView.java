package org.example.sudokuproject;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.log4j.Logger;
import org.example.sudokuproject.exceptions.DaoException;
import org.example.sudokuproject.exceptions.FxmlLoadException;
import org.example.sudokuproject.exceptions.PropertyBuilderNoSuchMethodException;
import org.example.sudokuproject.src.BacktrackingSudokuSolver;
import org.example.sudokuproject.src.Dao;
import org.example.sudokuproject.src.SudokuBoard;
import org.example.sudokuproject.src.SudokuBoardDaoFactory;
import org.example.sudokuproject.src.SudokuSolver;

public class SudokuBoardWindowView {

    @FXML
    private GridPane sudokuGrid;
    @FXML
    private TextField textFieldSave;
    @FXML
    private TextField textFieldLoad;
    @FXML
    private Button buttonSave;


    private final StringConverter converter = new Converter();
    private JavaBeanIntegerProperty[][] fieldValueProperty = new JavaBeanIntegerProperty[9][9];
    private Difficulty difficulty = Difficulty.Hard;
    private final SudokuSolver solver = new BacktrackingSudokuSolver();
    private SudokuBoard board = new SudokuBoard(solver);
    private final AlertWindow alert = new AlertWindow();

    private final ResourceBundle bundle
            = ResourceBundle.getBundle("org.example.sudokuproject.LangBundle");
    private static final Logger logger
            = Logger.getLogger(SudokuBoardWindowView.class.getName());
    private static SudokuBoard sudokuBoardFromDB;

    public static SudokuBoard getSudokuBoardFromDB() {
        return sudokuBoardFromDB;
    }

    @FXML
    public void initialize() throws NoSuchMethodException {
        logger.info(bundle.getString("LoadingMessage"));
        if (SudokuBoardWindowView.getSudokuBoardFromDB() == null) {
            board.solveGame();
            difficulty.selectDifficulty(board, Controller.getDifficulty());
        } else {
            board = SudokuBoardWindowView.getSudokuBoardFromDB();
        }
        fillGrid();
    }

    private void fillGrid() throws PropertyBuilderNoSuchMethodException {
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                TextField label = new TextField();
                label.setMinSize(50,65);
                label.setFont(Font.font(20));
                if (board.get(i, k) != 0) {
                    label.setDisable(true);
                }
                try {
                    fieldValueProperty[i][k] = JavaBeanIntegerPropertyBuilder.create()
                            .bean(new SudokuBoardAdapter(board, i, k))
                            .name("Field")
                            .build();
                } catch (NoSuchMethodException e) {
                    logger.error(bundle.getString("ErrorB"));
                    throw new PropertyBuilderNoSuchMethodException();
                }
                label.textProperty().bindBidirectional(fieldValueProperty[i][k], converter);
                label.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable,
                                        String previous, String current) {
                        if (!(current.matches("[1-9]") || current.equals(""))) {
                            label.setText(previous);
                        }
                    }
                });
                if (board.get(i, k) == 0) {
                    label.clear();
                }
                sudokuGrid.add(label, k, i);
            }
        }
    }

    @FXML
    public void onActionButtonSave() throws DaoException {
        String recordName = textFieldSave.getText();
        try (Dao<SudokuBoard> dbDao = SudokuBoardDaoFactory.getDatabaseDao(recordName)) {
            dbDao.write(board);
            logger.info(bundle.getString("SaveDataBase"));
        } catch (DaoException e) {
            logger.warn(bundle.getString("NotSaveDB"));
            alert.messegeBox(bundle.getString("Warning"),
                    bundle.getString("SaveNameAlreadyExsist"), Alert.AlertType.WARNING);
        } catch (Exception e) {
            logger.error(bundle.getString("UError"));
        }
    }

    @FXML
    public void onActionButtonLoad() throws FxmlLoadException {
        String recordName = textFieldLoad.getText();
        try (Dao<SudokuBoard> dbDao = SudokuBoardDaoFactory.getDatabaseDao(recordName);) {
            board = dbDao.read();
            logger.info(bundle.getString("LoadSuccess"));
        } catch (DaoException ex) {
            logger.error(bundle.getString("NotLoadDB"));
        } catch (Exception e) {
            logger.error(bundle.getString("ErrorE"));
        }
        sudokuBoardFromDB = board;
        try {
            App.start2(new Stage(),"sudokuView.fxml", "Sudoku Game (Loaded)", bundle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* Odczyt i zapis plik
        @FXML
    public void onActionButtonSave() throws DaoException {
        try (Dao<SudokuBoard> fileDao = SudokuBoardDaoFactory.getFileDao("SudokuGameSave");) {
            fileDao.write(board);
            logger.info(bundle.getString("InfoSave"));
        } catch (DaoException e) {
            logger.error(bundle.getString("ErrorS"));
        } catch (Exception e) {
            logger.error(bundle.getString("ErrorE"));
        }
    }

    @FXML
    public void onActionButtonLoad() throws FileException {
        try (Dao<SudokuBoard> fileDao = SudokuBoardDaoFactory.getFileDao("SudokuGameSave");) {
            board = fileDao.read();
            logger.info(bundle.getString("InfoLoad"));
        } catch (DaoException e) {
            logger.error(bundle.getString("ErrorD"));
        } catch (Exception e) {
            logger.error(bundle.getString("ErrorE"));
        }
    }*/
}
