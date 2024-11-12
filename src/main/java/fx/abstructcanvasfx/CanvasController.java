package fx.abstructcanvasfx;

import fx.model.Shape;
import fx.model.ShapeFactory;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import fx.model.Circle;
import fx.model.Rectangle;

public class CanvasController {

    @FXML
    Canvas canvas;

    @FXML
    ColorPicker colorPicker, colorPickerStroke;

    @FXML
    TextField fieldX, fieldY;

    @FXML
    TextField fieldSize;

    @FXML
    TextField fieldNum, numberSide;

    @FXML
    Label textLast;

//    @FXML
//    protected void onClickRectangle() {
//        double width = Double.parseDouble(fieldSize.getText());
//        double x = Double.parseDouble(fieldX.getText());
//        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
//        for (int i = 0; i < Integer.parseInt(fieldNum.getText()); i++) {
//            Rectangle rectangle = new Rectangle(colorPicker.getValue(),
//                    colorPickerStroke.getValue(),
//                    x + (width * i) + (i * 10),
//                    Double.parseDouble(fieldY.getText()),
//                    Double.parseDouble(fieldLenght.getText()),
//                    Double.parseDouble(fieldWidth.getText()));
//            rectangle.draw(graphicsContext);
//            System.out.println(rectangle + "\n");
//        }
//        textLast.setText("Последний элемент: прямоугольник");
//    }
//
//    @FXML
//    protected void onClickCircle() {
//        double radius = Double.parseDouble(fieldRadius.getText());
//        double x = Double.parseDouble(fieldX.getText());
//        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
//        for (int i = 0; i < Integer.parseInt(fieldNum.getText()); i++) {
//            Circle circle = new Circle(colorPicker.getValue(),
//                    colorPickerStroke.getValue(),
//                    x + (i * radius) + (i * 10),
//                    Double.parseDouble(fieldY.getText()),
//                    Double.parseDouble(fieldRadius.getText()));
//            circle.draw(graphicsContext);
//            textLast.setText("Последний элемент: круг");
//            System.out.println(circle + "\n");
//        }
//    }

    @FXML
    protected void onClickDrow() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        ShapeFactory shapeFactory = new ShapeFactory();
        double x = Double.parseDouble(fieldX.getText());
        double size = Double.parseDouble(fieldSize.getText());
        for (int i = 0; i < Integer.parseInt(fieldNum.getText()); i++) {
            Shape shape = shapeFactory.createShape(Integer.parseInt(numberSide.getText()),
                    colorPicker.getValue(),
                    colorPickerStroke.getValue(),
                    x + (i * size) + (i * 10),
                    Double.parseDouble(fieldY.getText()),
                    Double.parseDouble(fieldSize.getText())
            );
            shape.draw(graphicsContext);
        }
    }

    @FXML
    protected void onClickClear() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        System.out.println("Очищено\n");
    }
}