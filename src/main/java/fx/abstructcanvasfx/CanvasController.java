package fx.abstructcanvasfx;

import fx.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class CanvasController implements Initializable {

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
    @FXML
    ListView listView;
    ObservableList<Shape> items;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Square square =new Square();
        Rectangle rectangle = new Rectangle(Color.BLACK, Color.BLACK, 0, 0, 10);
        Circle circle = new Circle(Color.BLACK, Color.BLACK, 0, 0, 10);
        Triangle triangle = new Triangle(Color.BLACK, Color.BLACK, 0, 0, 2);
        items = FXCollections.observableArrayList(circle, rectangle, triangle);
        listView.setItems(items);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @FXML
    protected void onClickDrow() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        ShapeFactory shapeFactory = new ShapeFactory();
        double x = Double.parseDouble(fieldX.getText());
        double size = Double.parseDouble(fieldSize.getText());
        System.out.println(fieldNum.getText());
        for (int i = 0; i < Integer.parseInt(fieldNum.getText()); i++) {
            Shape shape = shapeFactory.createShape(Integer.parseInt(numberSide.getText()),
                    colorPicker.getValue(),
                    colorPickerStroke.getValue(),
                    x + (i * size) + (i * 10),
                    Double.parseDouble(fieldY.getText()),
                    Double.parseDouble(fieldSize.getText())
            );
            shape.draw(graphicsContext);
            System.out.println(shape);
        }
    }

    @FXML
    protected void onClickClear() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        System.out.println("Очищено\n");
    }

    public void drawShape(MouseEvent mouseEvent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int index = listView.getSelectionModel().getSelectedIndex(); //получение индекса выбора из списка
        Shape shape = (Shape) items.get(index).clone(); // создание копии фигуры
        shape.setColor(colorPicker.getValue()); // установка цвета заполнения фигуры по значению элемента управления colorPicker
        shape.setXY(mouseEvent.getX() - shape.getSize() / 2, mouseEvent.getY() - shape.getSize() / 2);
        shape.draw(gc); // рисование копии фигуры в точке, полученной из события MouseEvent x

    }
}