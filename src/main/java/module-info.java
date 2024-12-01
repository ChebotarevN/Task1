module app.keeper {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.keeper to javafx.fxml;
    exports app.keeper;
}