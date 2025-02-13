package app.decorator;

import app.christmass.*;
import app.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;

import java.net.URL;
import java.util.ArrayList;
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

    @FXML
    Pane pane;

    private ObservableList<Shape> items;
    private ObservableList<String> listFill;
    private ObservableList<String> listEffect;
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
        choiceFill.setValue("Цвет");
        choiceFill.getSelectionModel().selectedIndexProperty().addListener((_, _, t1) -> changeFill(t1.intValue()));
        listEffect = FXCollections.observableArrayList("Non effect", "Inner Shadow", "Blur", "Drop Shadow", "Fade Transition");
        choiceEffect.setItems(listEffect);
        choiceEffect.setValue("Non effect");
    }

    @FXML
    public void treeButton() {
        ChristmasTree tree = new ChristmasTreeImpl();
        tree.draw(pane);

        tree = new Presents(new ChristmasTreeImpl());
        tree.draw(pane);

        tree = new Star(new ChristmasTreeImpl());
        tree.draw(pane);

        tree = new Girland(new ChristmasTreeImpl());
        tree.draw(pane);

        canvas.toFront();
        momento.push(pane.getChildren().getLast());
        lastShape.add("Ёлка с украшениями");
        textLast.setText(lastShape.getLast());
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
        if (choiceEffect.getValue().equals("Fade Transition"))
            shape.draw(pane);
        else
            shape.draw(gc);
        canvas.toFront();
        momento.push(shape);
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
                } else if (item instanceof Shape) {
                    ((Shape) item).draw(gc);
                }
            }
            textLast.setText(lastShape.getLast());
        } else {
            onClickClear();
            textLast.setText("Ничего не нарисовано");
        }
        canvas.toFront();
    }

    public void addLights(ActionEvent actionEvent) {
        ChristmasTree tree = new Girland(new ChristmasTreeImpl());
        tree.draw(pane);
        momento.push(pane.getChildren().getLast());
        lastShape.add("Гирлянда");
        textLast.setText(lastShape.getLast());
        canvas.toFront();
    }

    public void addPresents(ActionEvent actionEvent) {
        ChristmasTree tree = new Presents(new ChristmasTreeImpl());
        tree.draw(pane);
        momento.push(pane.getChildren().getLast());
        lastShape.add("Подарки");
        textLast.setText(lastShape.getLast());
        canvas.toFront();
    }

    public void addStar(ActionEvent actionEvent) {
        ChristmasTree tree = new Star(new ChristmasTreeImpl());
        tree.draw(pane);
        momento.push(pane.getChildren().getLast());
        lastShape.add("Звезда");
        textLast.setText(lastShape.getLast());
        canvas.toFront();
    }

    public void addTree(ActionEvent actionEvent) {
        ChristmasTree tree = new ChristmasTreeImpl();
        tree.draw(pane);
        momento.push(pane.getChildren().getLast());
        lastShape.add("Ёлка");
        textLast.setText(lastShape.getLast());
        canvas.toFront();
    }
}