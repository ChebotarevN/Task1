package fx.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    private double size;

    public Triangle(Color color, Color colorStroke, double x, double y, double size) {
        super(color, colorStroke, x, y);
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
        int n = 3;
        double[] xCoords = {x + 10, x + (18 * size), x + (30 * size)};
        double[] yCoords = {y + (20 * size), y + (10 * size), y + (20 * size)};
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
        System.out.println("Площадь треугольника: " + area);
        return area;
    }

    @Override
    public String toString() {
        return "Цвет треугольника: " + super.color + "; цвет границы: " + colorStroke + "; площадь: " + area();
    }

    @Override
    public double[] getSize() {
        return new double[]{size * 35, size * 30};
    }

    @Override
    public double setSize(double size) {
        return this.size = size;
    }
}
