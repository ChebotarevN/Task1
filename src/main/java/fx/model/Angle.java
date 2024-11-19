package fx.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Angle extends Shape {
    private Color colorStroke;
    private double size;

    public Angle(Color color, Color colorStroke, double x, double y, double size) {
        super(color, x, y);
        this.colorStroke = colorStroke;
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
        gr.strokePolygon(new double[]{x, x * size}, new double[]{y, y}, 2);
        gr.strokePolygon(new double[]{x, x}, new double[]{x, y * size}, 2);
    }

    @Override
    public String toString() {
        return "Цвет угла: " + colorStroke + "; площадь: " + area();
    }
}
