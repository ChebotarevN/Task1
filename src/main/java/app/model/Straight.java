package app.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Straight extends Shape {
    private double size;

    public Straight(Color color, Color colorStroke, double x, double y, double size) {
        super(color, colorStroke, x, y);
        this.size = size;
    }

    @Override
    public double area() {
        return (x + size * 100);
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(paint);
        gr.setLineWidth(2);
        gr.setEffect(effect);
        gr.strokePolygon(new double[]{x - size * 25, x + size * 25}, new double[]{y, y}, 2);
    }

    @Override
    public String toString() {
        return "Линия";
    }

    @Override
    public double[] getSize() {
        return new double[]{(x + size * 100), 0};
    }

    @Override
    public double setSize(double size) {
        return this.size = size;
    }
}
