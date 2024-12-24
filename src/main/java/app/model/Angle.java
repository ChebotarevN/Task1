package app.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Angle extends Shape {
    private double size;

    public Angle(Color color, Color colorStroke, double x, double y, double size) {
        super(color, colorStroke, x, y);
        this.size = size;
    }

    @Override
    public double area() {
        return (x * size - x) + (y * size - y);
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setLineWidth(2);
        gr.setStroke(paint);
        gr.setEffect(effect);
        gr.strokePolygon(new double[]{x - (size * 25), x + size * 25}, new double[]{y + (size * 25), y + (size * 25)}, 2);
        gr.strokePolygon(new double[]{x - (size * 25), x - (size * 25)}, new double[]{y - (size * 25), y + (size * 25)}, 2);
    }

    @Override
    public String toString() {
        return "Угол";
    }

    @Override
    public double[] getSize() {
        return new double[]{size * 50, size * 50};
    }

    @Override
    public double setSize(double size) {
        return this.size = size;
    }
}
