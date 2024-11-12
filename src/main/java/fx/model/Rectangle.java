package fx.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    private Color colorStroke;
    private double size;

    public Rectangle(Color color, Color colorStroke, double x, double y, double size) {
        // calling Shape constructor
        super(color, x, y);
        this.colorStroke = colorStroke;
        this.size = size;
    }

    @Override
    public double area() {
        return size * size;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(color);
        gr.setStroke(colorStroke);
        gr.fillRect(x, y, size, size);
        gr.strokeRect(x, y, size, size);
    }

    @Override
    public String toString() {
        return "Rectangle color is: " + super.color + "; color stroke is: " + colorStroke + "; area is: " + area();
    }
}
