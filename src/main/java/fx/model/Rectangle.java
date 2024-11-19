package fx.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    private double size;

    public Rectangle(Color color, Color colorStroke, double x, double y, double size) {
        // calling Shape constructor
        super(color, colorStroke, x, y);
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
        return "Цвет прямоугольника: " + super.color + "; цвет границы: " + colorStroke + "; площадь: " + area();
    }

    @Override
    public double[] getSize() {
        return new double[]{size, size};
    }

    @Override
    public double setSize(double size) {
        return this.size = size;
    }
}
