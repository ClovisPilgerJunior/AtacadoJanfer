module atacadojanfer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens model to javafx.fxml;
    exports model;
}