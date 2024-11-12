package fx.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    private Color colorStroke;
    private double size;

    public Triangle(Color color, Color colorStroke, double x, double y, double size) {
        super(color, x, y);
        this.colorStroke = colorStroke;
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(color);
        gr.setStroke(colorStroke);
        gr.setLineWidth(2);

        gr.fillPolygon(new double[]{x + 10, x + (18 * size), x + (30 * size)}, new double[]{y + (20 * size), y + (10 * size), y + (20 * size)}, 3);
        gr.strokePolygon(new double[]{x + 10, x + (18 * size), x + (30 * size)}, new double[]{y + (20 * size), y + (10 * size), y + (20 * size)}, 3);
    }

    @Override
    public double area() {
        return 0;
    }
}
