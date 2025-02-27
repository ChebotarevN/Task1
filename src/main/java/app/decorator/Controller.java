package app.decorator;

import app.model.*;
import app.model.Addons.Addon;
import app.model.Addons.Split;
import app.model.Addons.Stipple;
import app.model.Shapes.Shape;
import app.model.Shapes.ShapeFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Canvas canvas;

    @FXML
    ColorPicker colorPicker, colorPicker1, colorPickerStroke;

    @FXML
    TextField fieldSize;

    @FXML
    ChoiceBox choiceFill, choiceEffect;

    @FXML
    Label textLast;
    @FXML
    ListView listView;

    @FXML
    HBox boxGradient;

    @FXML
    Pane pane;

    @FXML
    ToggleButton toggleSplit, toggleStipple;

    private ObservableList<Shape> items;
    private ObservableList<String> listFill;
    //private ObservableList<String> listEffect;
    private ObservableList<EffectEnum> listEffect;
    private ArrayList<String> lastShape = new ArrayList<>();
    private Momento momento = new Momento();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ShapeFactory shapeFactory = new ShapeFactory();
        items = FXCollections.observableArrayList(shapeFactory.createShape(0), shapeFactory.createShape(1), shapeFactory.createShape(2),
                shapeFactory.createShape(3), shapeFactory.createShape(4), shapeFactory.createShape(5));
        listView.setItems(items);
        listFill = FXCollections.observableArrayList("Цвет", "Линейный градиент", "Радиальный градиент", "Изображение");
        choiceFill.setItems(listFill);
        choiceFill.setValue(listFill.getFirst());
        choiceFill.getSelectionModel().selectedIndexProperty().addListener((_, _, t1) -> changeFill(t1.intValue()));
        listEffect = FXCollections.observableArrayList(EffectEnum.NONE, EffectEnum.INNER_SHADOW, EffectEnum.BLUR, EffectEnum.DROP_SHADOW, EffectEnum.FADE);
        choiceEffect.setItems(listEffect);
        choiceEffect.setValue(listEffect.getFirst());
    }

    public Effect setEffect() {
        EffectShape effectShape = new EffectShape();
        return effectShape.getEffect((EffectEnum) choiceEffect.getValue());
    }

    public Paint setFill(double x, double y) {
        FillShape fillShape = new FillShape(colorPicker.getValue(), colorPicker1.getValue(), x, y);
        return fillShape.getFill(choiceFill.getItems().indexOf(choiceFill.getValue()));
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
        pane.getChildren().clear();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(null);
        graphicsContext.setEffect(null);
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        textLast.setText("Ничего не нарисовано");
        lastShape.clear();
        System.out.println("Очищено\n");
        canvas.toFront();
    }

    public void drawShape(MouseEvent mouseEvent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        int index = listView.getSelectionModel().getSelectedIndex(); //получение индекса выбора из списка
        Shape shape = (Shape) items.get(index).clone(); // создание копии фигуры
        shape.setColor(colorPicker.getValue()); // установка цвета заполнения фигуры по значению элемента управления colorPicker
        shape.setColorStroke(colorPickerStroke.getValue());
        shape.setXY(mouseEvent.getX(), mouseEvent.getY());
        if (!fieldSize.getText().isEmpty()) {
            try {
                double size = Double.parseDouble(fieldSize.getText());
                shape.setSize(size);
            } catch (NumberFormatException e) {
                System.out.println("Введена строка в поле size");
            }
        }

        Decorate decorate = new Decorate(shape, setFill(mouseEvent.getX(), mouseEvent.getY()), setEffect());
        List<Addon> addons = new ArrayList<>();
        if (toggleStipple.isSelected()) {
            addons.add(new Stipple((decorate)));
        }
        if (toggleSplit.isSelected()) {
            addons.add(new Split(decorate));
        }
        decorate.setAddons(addons);
        if (choiceEffect.getValue().equals(EffectEnum.FADE))
            decorate.draw(pane);
        else
            decorate.draw(gc);
        canvas.toFront();
        momento.push(decorate);
        lastShape.add(shape.toString());
        textLast.setText(lastShape.getLast());
    }

    public void undoLast() {
        if (momento.getSize() > 1) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setEffect(null);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            pane.getChildren().clear();
            lastShape.removeLast();
            momento.poll();
            for (Object item : momento.getListShapes()) {
                if (item instanceof Pane) {
                    pane.getChildren().add((Pane) item);
                } else if (item instanceof Decorate) {
                    ((Decorate) item).draw(gc);
                }
            }
            textLast.setText(lastShape.getLast());
        } else {
            onClickClear();
            textLast.setText("Ничего не нарисовано");
        }
        canvas.toFront();
    }
}