package fx.model;

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
        gr.setStroke(colorStroke);
        gr.setLineWidth(2);
        gr.strokePolygon(new double[]{x, x + size * 50}, new double[]{y, y}, 2);
        gr.strokePolygon(new double[]{x, x}, new double[]{y, y + size * 50}, 2);
    }

    @Override
    public String toString() {
        return "Цвет угла: " + colorStroke + "; площадь: " + area();
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
