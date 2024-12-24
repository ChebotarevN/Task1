package app.decorator;

import app.model.*;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.*;
import javafx.scene.shape.FillRule;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Canvas canvas;

    @FXML
    ColorPicker colorPicker, colorPicker1, colorPickerStroke;

    @FXML
    TextField fieldX, fieldY;

    @FXML
    TextField fieldSize;

    @FXML
    TextField fieldNum, numberSide;

    @FXML
    ChoiceBox choiceFill, choiceEffect;

    @FXML
    Label textLast;
    @FXML
    ListView listView;

    @FXML
    HBox boxGradient;

    private ObservableList<Shape> items;
    private ObservableList<String> listFill;
    private ObservableList<String> listEffect;
    private HashMap<Class, String> shapeName = new HashMap<>();
    private Momento momento = new Momento();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rectangle rectangle = new Rectangle(Color.BLACK, Color.BLACK, 5, 5, 15);
        Circle circle = new Circle(Color.BLACK, Color.BLACK, 5, 5, 15);
        Triangle triangle = new Triangle(Color.BLACK, Color.BLACK, 5, 5, 2);
        Angle angle = new Angle(Color.BLACK, Color.BLACK, 5, 5, 2);
        Pentagon pentagon = new Pentagon(Color.BLACK, Color.BLACK, 5, 5, 2);
        Straight straight = new Straight(Color.BLACK, Color.BLACK, 5, 5, 2);
        items = FXCollections.observableArrayList(circle, straight, angle, triangle, rectangle, pentagon);
        listView.setItems(items);
        listFill = FXCollections.observableArrayList("Цвет", "Линейный градиент", "Радиальный градиент", "Изображение");
        choiceFill.setItems(listFill);
        choiceFill.setValue("Цвет");
        choiceFill.getSelectionModel().selectedIndexProperty().addListener((observableValue, o, t1) -> {
            changeFill(t1.intValue());
        });
        listEffect = FXCollections.observableArrayList("Non effect", "Inner Shadow", "Blur", "Drop Shadow");
        choiceEffect.setItems(listEffect);
        choiceEffect.setValue("Non effect");

    }

    public Effect setEffect() {
        String effect = (String) choiceEffect.getValue();
        switch (effect) {
            case "Inner Shadow":
                InnerShadow innerShadow = new InnerShadow();
                innerShadow.setOffsetX(2.0f);
                innerShadow.setOffsetY(2.0f);
                return innerShadow;
            case "Blur":
                BoxBlur blur = new BoxBlur();
                blur.setWidth(5);
                blur.setHeight(5);
                blur.setIterations(3);
                return blur;
            case "Drop Shadow":
                DropShadow dropShadow = new DropShadow();
                dropShadow.setOffsetX(4.0f);
                dropShadow.setOffsetY(4.0f);
                dropShadow.setColor(Color.BLACK);
                return dropShadow;
            default:
                return null;
        }
    }

    public Paint setFill() {
        String fill = (String) choiceFill.getValue();
        return switch (fill) {
            case "Цвет" -> colorPicker.getValue();
            case "Линейный градиент" ->
                    new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, colorPicker.getValue()), new Stop(1, colorPicker1.getValue()));
            case "Радиальный градиент" -> new RadialGradient(0,                  // Focus angle
                    0.5,                // Focus distance
                    100, 100,           // Center coordinates
                    100,                // Radius
                    true,               // Proportional
                    CycleMethod.NO_CYCLE, // Cycle method
                    new Stop(0, colorPicker.getValue()),
                    new Stop(0.5, colorPicker1.getValue()));
            case "Изображение" -> new ImagePattern(new Image("file:src/main/resources/app/img1.jpg"));
            default -> null;
        };
    }

    public void changeFill(int num) {
        String choice = listFill.get(num);
        switch (choice) {
            case "Цвет", "Изображение":
                boxGradient.setVisible(false);
                break;
            default:
                boxGradient.setVisible(true);
        }
    }

    @FXML
    protected void onClickClear() {
        momento = new Momento();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(null);
        graphicsContext.setEffect(null);
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        textLast.setText("Ничего не нарисовано");
        System.out.println("Очищено\n");
    }

    public void drawShape(MouseEvent mouseEvent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int index = listView.getSelectionModel().getSelectedIndex(); //получение индекса выбора из списка
        Shape shape = (Shape) items.get(index).clone(); // создание копии фигуры
        shape.setColor(colorPicker.getValue()); // установка цвета заполнения фигуры по значению элемента управления colorPicker
        shape.setColorStroke(colorPickerStroke.getValue());
        shape.setXY(mouseEvent.getX(), mouseEvent.getY());
        shape.setPaint(setFill());
        shape.setEffect(setEffect());
        if (!fieldSize.getText().isEmpty()) {
            try {
                double size = Double.parseDouble(fieldSize.getText());
                shape.setSize(size);
            } catch (NumberFormatException e) {
                System.out.println("Введена строка в поле size");
            }
        }
        momento.push(shape);
        for (Shape item : momento.getListShapes()) {
            item.draw(gc);
        }
        textLast.setText(shapeName.get(shape.getClass()));
    }

    public void undoLast() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setEffect(null);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        momento.poll();
        for (Shape item : momento.getListShapes()) {
            item.draw(gc);
        }
        if (momento.getSize() == 0)
            textLast.setText("Ничего не нарисовано");
    }
}