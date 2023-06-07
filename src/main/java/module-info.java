module atacadojanfer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens model to javafx.fxml;
    exports model;
    exports presenter;
    opens presenter to javafx.fxml;
}