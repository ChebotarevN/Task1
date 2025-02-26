package app.model.Shapes;

import javafx.scene.paint.Color;

import java.util.HashMap;

public class ShapeFactory {
    private HashMap<Integer, Shape> shapes;

    public ShapeFactory() {
        shapes = new HashMap<>();
        shapes.put(0, new Circle(Color.BLACK, Color.BLACK, 5, 5, 15));
        shapes.put(1, new Straight(Color.BLACK, Color.BLACK, 5, 5, 2));
        shapes.put(2, new Angle(Color.BLACK, Color.BLACK, 5, 5, 2));
        shapes.put(3, new Triangle(Color.BLACK, Color.BLACK, 5, 5, 2));
        shapes.put(4, new Rectangle(Color.BLACK, Color.BLACK, 5, 5, 15));
        shapes.put(5, new Pentagon(Color.BLACK, Color.BLACK, 5, 5, 2));
    }

    public Shape createShape(int index) {
        Shape shape = shapes.get(index);
        return shape;
    }
}