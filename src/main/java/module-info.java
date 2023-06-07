module atacadojanfer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens model to javafx.fxml;
    exports model;
}