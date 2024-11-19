package fx.abstructcanvasfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CanvasApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(CanvasApplication.class);
        FXMLLoader fxmlLoader = new FXMLLoader(CanvasApplication.class.getResource("canvas.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 790);
        stage.setTitle("AbstractCanvasFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}