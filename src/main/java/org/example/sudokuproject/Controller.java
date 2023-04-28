package org.example.sudokuproject;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.example.sudokuproject.src.BacktrackingSudokuSolver;
import org.example.sudokuproject.src.SudokuBoard;

public class Controller {

    @FXML
    private Label welcomeText;
    public javafx.scene.control.Button enButton;
    public javafx.scene.control.Button plButton;

    private final AlertWindow alert = new AlertWindow();
    private static String difficulty;
    private Difficulty diffLevel;
    private final Locale localeEn = new Locale("en");
    private final Locale localePl = new Locale("pl");
    ResourceBundle authors = ResourceBundle.getBundle("org.example.sudokuproject.Authors");
    ResourceBundle bundle = ResourceBundle.getBundle("org.example.sudokuproject.LangBundle");
    private static final Logger logger = Logger.getLogger(Controller.class.getName());

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public static String getDifficulty() {
        String diff = difficulty;
        if (diff.equals("Łatwy")) {
            diff = "Easy";
        } else if (diff.equals("Średni")) {
            diff = "Medium";
        } else if (diff.equals("Trudny")) {
            diff = "Hard";
        }
        return diff;
    }

    @FXML
    public void onActionEasyDifficulty(ActionEvent e) throws IOException {
        Controller.difficulty = "Łatwy";
        difficulty = getDifficulty();
        diffLevel = Difficulty.Easy;
        logger.info(bundle.getString("_Easy"));
        start();
    }

    @FXML
    public void onActionMediumDifficulty(ActionEvent e) throws IOException {
        Controller.difficulty = "Średni";
        difficulty = getDifficulty();
        diffLevel = Difficulty.Medium;
        logger.info(bundle.getString("_Medium"));
        start();

    }

    @FXML
    public void onActionHardDifficulty(ActionEvent e) throws IOException {
        Controller.difficulty = "Trudny";
        difficulty = getDifficulty();
        diffLevel = Difficulty.Hard;
        logger.info(bundle.getString("_Hard"));
        start();

    }

    @FXML
    public void start() throws IOException {
        generateBoard();
    }

    public void generateBoard() throws IOException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        sudokuBoard = diffLevel.selectDifficulty(sudokuBoard, difficulty);
        sudokuBoard.boardDifficultySudokuPrint();
        App.start2(new Stage(),"sudokuView.fxml", "Sudoku", bundle);
    }

    @FXML
    public void onActionPlButton(ActionEvent event) throws IOException {
        bundle = ResourceBundle.getBundle("org.example.sudokuproject.LangBundle", localePl);
        Locale.setDefault(localePl);
        App.start2(new Stage(),"hello-view.fxml", "Sudoku", bundle);
        logger.info(bundle.getString("SelectPL"));
    }

    @FXML
    public void onActionEnButton(ActionEvent event) throws IOException {
        bundle = ResourceBundle.getBundle("org.example.sudokuproject.LangBundle", localeEn);
        Locale.setDefault(localeEn);
        App.start2(new Stage(),"hello-view.fxml", "Sudo-ku", bundle);
        logger.info(bundle.getString("SelectEn"));
    }

    @FXML
    public void onActionButtonAuthors(ActionEvent actionEvent) {
        alert.messegeBox(bundle.getString("Authors"),
                authors.getObject("1. ") + "\n"
                        + authors.getObject("2. "), Alert.AlertType.INFORMATION);
    }
}
