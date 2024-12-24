package app.model;

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
        gr.setFill(paint);
        gr.setStroke(colorStroke);
        gr.setEffect(effect);
        gr.fillRect(x - size / 2, y - size / 2, size, size);
        gr.strokeRect(x - size / 2, y - size / 2, size, size);
    }

    @Override
    public String toString() {
        return "Квадрат";
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
