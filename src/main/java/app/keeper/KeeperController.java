package app.keeper;


import app.MemoSelect;
import app.Momento;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Arrays;

public class KeeperController {

    @FXML
    Pane panel;

    private ArrayList<Shape> picture;
    private MemoSelect temp;

    public KeeperController() {
        panel = new Pane();
        initialize();
    }

    public void initialize() {
        Rectangle rectangle1 = new Rectangle(20, 50, Color.LIGHTGRAY);
        rectangle1.setX(200);
        rectangle1.setY(300);
        Rectangle rectangle2 = new Rectangle(30, 20, 100, 50);
        rectangle2.setStroke(Color.BLACK);
        rectangle2.setArcWidth(10);
        Circle circle1 = new Circle(50, 50, 40);
        circle1.setFill(Color.LIGHTGRAY);
        Circle circle2 = new Circle(100, 100, 40, Color.YELLOW);
        circle2.setStroke(Color.BLACK);
        circle2.setStrokeWidth(2.0);
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(50.0, 0.0, 0.0, 50.0, 100.0, 50.0);
        triangle.setFill(Color.WHITE);
        triangle.setStroke(Color.RED);

        // Create the Parallelogram
        Polygon parallelogram = new Polygon();
        parallelogram.getPoints().addAll(30.0, 0.0, 130.0, 0.0, 100.00, 50.0, 0.0, 50.0);
        parallelogram.setFill(Color.YELLOW);
        parallelogram.setStroke(Color.BLACK);

        // Create the Hexagon
        Polygon hexagon = new Polygon(100.0, 0.0, 120.0, 20.0, 120.0,
                40.0, 100.0, 60.0, 80.0,
                40.0, 80.0, 20.0);
        hexagon.setFill(Color.WHITE);
        hexagon.setStroke(Color.BLACK);
        picture = new ArrayList<>();
        picture.addAll(Arrays.asList(rectangle1, rectangle2, circle1, circle2, triangle, parallelogram, hexagon));
        temp = new MemoSelect();
        for (int i = 0; i < picture.size(); i++) {
            Shape a = picture.get(i);
            a.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
                Momento m = new Momento(a);
                temp.push(m);// запоминается текущая выбранная фигура в переменной temp
                m.initState().toFront();
            });
// фигуры добавляются на Панель panel
            panel.getChildren().add(a);

        }

    }

    public void onBegin(MouseEvent mouseEvent) {//захват

//        temp.initState().toFront();

    }


    public void onDrag(MouseEvent mouseEvent) {//движение

//        if (temp == null) return;
//
//        temp.initState().relocate(mouseEvent.getX(), mouseEvent.getY());

    }


    public void onEnd(MouseEvent mouseEvent) {

//        if (temp == null) return;
//
//        // переместить фигуру из Хранителя
//        if (mouseEvent.getTarget().equals(panel)) return;
//        temp.poll().getState().relocate(mouseEvent.getX(), mouseEvent.getY());

    }

    public void onClick(MouseEvent mouseEvent) {
        if (!mouseEvent.getTarget().equals(panel)) return;
        temp.poll().getState().relocate(mouseEvent.getX(), mouseEvent.getY());
    }
}