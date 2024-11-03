package fx.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    Color colorStroke;
    double length;
    double width;

    public Rectangle(Color color, Color colorStroke, double x, double y, double length, double width) {
        // calling Shape constructor
        super(color, x, y);
        this.colorStroke = colorStroke;
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(color);
        gr.setStroke(colorStroke);
        gr.fillRect(x, y, width, length);
        gr.strokeRect(x, y, width, length);
    }

    @Override
    public String toString() {
        return "Rectangle color is: " + super.color + "; color stroke is: " + colorStroke + "; area is: " + area();
    }
}
