module org.example.sudokuproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    requires java.desktop;
    requires log4j;
    requires java.sql;


    opens org.example.sudokuproject to javafx.fxml;
    exports org.example.sudokuproject;
}