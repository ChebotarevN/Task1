package fx.model;

import javafx.scene.paint.Color;

public class ShapeFactory {
    public Shape createShape(int numberOfSides, Color color, Color colorStroke, double x, double y, double size) {
        switch (numberOfSides) {
            case 5:
                return new Pentagon(color, colorStroke, x, y, size);
            case 4:
                return new Rectangle(color, colorStroke, x, y, size);
            case 3:
                return new Triangle(color, colorStroke, x, y, size);
            case 2:
                return new Angle(color, colorStroke, x, y, size);
            case 1:
                return new Straight(color, colorStroke, x, y, size);
            case 0:
                return new Circle(color, colorStroke, x, y, size);
            default:
                return null;
        }
    }
}