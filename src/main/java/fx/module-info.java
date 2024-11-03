module fx.abstructcanvasfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens fx.abstructcanvasfx to javafx.fxml;
    exports fx.abstructcanvasfx;
}