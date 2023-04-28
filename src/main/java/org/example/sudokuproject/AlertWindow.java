package org.example.sudokuproject;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import org.apache.log4j.Logger;
import org.example.sudokuproject.src.SudokuBoard;


public class AlertWindow {
    private static final Logger logger = Logger.getLogger(SudokuBoard.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("org.example.sudokuproject.LangBundle");

    public void messegeBox(String title, String messege, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(messege);
        logger.info(bundle.getString("ShowMessage") + messege);
        alert.showAndWait();
    }

}
