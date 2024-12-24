module app.decorator {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.decorator to javafx.fxml;
    exports app.decorator;
}