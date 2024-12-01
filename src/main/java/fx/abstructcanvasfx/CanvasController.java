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
import javafx.util.Callback;

import java.net.URL;
import java.util.HashMap;
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
    private ObservableList<Shape> items;
    private HashMap<Class, String> shapeName = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Square square =new Square();
        Rectangle rectangle = new Rectangle(Color.BLACK, Color.BLACK, 5, 5, 15);
        Circle circle = new Circle(Color.BLACK, Color.BLACK, 5, 5, 15);
        Triangle triangle = new Triangle(Color.BLACK, Color.BLACK, 5, 5, 2);
        Angle angle = new Angle(Color.BLACK, Color.BLACK, 5, 5, 2);
        Pentagon pentagon = new Pentagon(Color.BLACK, Color.BLACK, 5, 5, 2);
        Straight straight = new Straight(Color.BLACK, Color.BLACK, 5, 5, 2);
        items = FXCollections.observableArrayList(circle, straight, angle, triangle, rectangle, pentagon);
        shapeName.put(Circle.class, "Круг");
        shapeName.put(Straight.class, "Линия");
        shapeName.put(Angle.class, "Угол");
        shapeName.put(Triangle.class, "Треугольник");
        shapeName.put(Rectangle.class, "Прямоугольник");
        shapeName.put(Pentagon.class, "Пятиугольник");
        listView.setCellFactory((Callback<ListView<Shape>, ListCell<Shape>>) _ -> new CellList());
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
        shape.setColorStroke(colorPickerStroke.getValue());
        shape.setXY(mouseEvent.getX() - shape.getSize()[0] / 2, mouseEvent.getY() - shape.getSize()[1] / 2);
        if (!fieldSize.getText().isEmpty()) {
            try {
                double size = Double.parseDouble(fieldSize.getText());
                shape.setSize(size);
            } catch (NumberFormatException e) {
                System.out.println("Введена строка в поле size");
            }
        }
        shape.draw(gc); // рисование копии фигуры в точке, полученной из события MouseEvent x
        textLast.setText(shapeName.get(shape.getClass()));
    }
}