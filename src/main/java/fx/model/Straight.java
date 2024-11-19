package fx.model;

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
        gr.setStroke(colorStroke);
        gr.setLineWidth(2);
        gr.strokePolygon(new double[]{x, x + size * 100}, new double[]{y, y}, 2);
    }

    @Override
    public String toString() {
        return "Цвет линии: " + colorStroke + "; площадь: " + area();
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
