package fx.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pentagon extends Shape {
    private Color colorStroke;
    private double size;

    public Pentagon(Color color, Color colorStroke, double x, double y, double size) {
        super(color, x, y);
        this.colorStroke = colorStroke;
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(colorStroke);
        gr.setFill(color);
        gr.setLineWidth(2);
        gr.fillPolygon(new double[]{x + 2, x + (7 * size), x + (13 * size), x + (10 * size), x + (3 * size)},
                new double[]{y + (8 * size), y + (2 * size), y + (8 * size), y + (14 * size), y + (14 * size)}, 5);
        gr.strokePolygon(new double[]{x + 2, x + (7 * size), x + (13 * size), x + (10 * size), x + (3 * size)},
                new double[]{y + (8 * size), y + (2 * size), y + (8 * size), y + (14 * size), y + (14 * size)}, 5);
    }

    @Override
    public double area() {
        return 0;
    }
}
