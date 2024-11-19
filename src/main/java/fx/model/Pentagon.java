package fx.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pentagon extends Shape {
    private double size;

    public Pentagon(Color color, Color colorStroke, double x, double y, double size) {
        super(color, colorStroke, x, y);
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
        int n = 5;
        double[] xCoords = {x + 2, x + (7 * size), x + (13 * size), x + (10 * size), x + (3 * size)};
        double[] yCoords = {y + (8 * size), y + (2 * size), y + (8 * size), y + (14 * size), y + (14 * size)};
        double area = 0.0;
        for (int i = 0; i < n - 2; i++) {
            int v1 = 0;
            int v2 = i + 1;
            int v3 = i + 2;
            double triangleArea = Math.abs(
                    0.5
                            * (xCoords[v1] * (yCoords[v2] - yCoords[v3])
                            + xCoords[v2]
                            * (yCoords[v3] - yCoords[v1])
                            + xCoords[v3]
                            * (yCoords[v1] - yCoords[v2])));
            area += triangleArea;
        }
        System.out.println("Площадь пятиугольника: " + area);
        return area;
    }

    @Override
    public String toString() {
        return "Цвет пятиугольника: " + super.color + "; цвет границы: " + colorStroke + "; площадь: " + area();
    }

    @Override
    public double[] getSize() {
        return new double[]{size * 15, size * 15};
    }

    @Override
    public double setSize(double size) {
        return this.size = size;
    }
}
