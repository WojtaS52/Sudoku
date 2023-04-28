package org.example.sudokuproject;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class App extends Application {

    private static ResourceBundle bundle
            = ResourceBundle.getBundle("org.example.sudokuproject.LangBundle");
    private static final Logger logger = Logger.getLogger(App.class.getName());
    private static Stage stageold;

    @Override
    public void start(Stage stage) throws IOException {
        stageold = stage;
        start2(stageold, "hello-view.fxml", "Hello", bundle);
        /*FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
    }

    public static void start2(Stage stage, String filepath,
                              String title, ResourceBundle bundle) throws IOException {
        stageold.close();
        stageold = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(filepath), bundle);
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stageold.setTitle(title);
        stageold.setScene(scene);
        stageold.show();
    }

    public static void main(String[] args) {
        logger.info(bundle.getString("StartApp"));
        launch();
    }
}