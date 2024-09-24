module fx.calculateprocentfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens fx.calculateprocentfx to javafx.fxml;
    exports fx.calculateprocentfx;
}