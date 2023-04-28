/*package org.example.sudokuproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;
import org.example.sudokuproject.exceptions.FxmlLoadException;

public class FxmlLoad {

    private static Stage stage;
    private ResourceBundle bundle
    = ResourceBundle.getBundle("org.example.sudokuproject.LangBundle");

    public static Stage getStage() {
        return stage;
    }

    private static void setStage(Stage stage) {
        FxmlLoad.stage = stage;
    }

    private static Parent loadFxml(String fxml, ResourceBundle bundle) throws FxmlLoadException {
        try {
            return new FXMLLoader(FxmlLoad.class.getResource(fxml), bundle).load();
        } catch (IOException e) {
            throw new FxmlLoadException(bundle.getString("FxmlExceptionMessage"), e);
        }
    }

    public static void buildStage(String filePath, String title,
     ResourceBundle bundle) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("sudokuView.fxml"));
        //stage.setScene(new Scene(loadFxml(filePath, bundle)));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setTitle(title);
        //stage.sizeToScene();
        stage.show();
    }
}
*/