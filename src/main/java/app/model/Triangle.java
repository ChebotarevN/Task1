package app.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape {
    private double size;

    public Triangle(Color color, Color colorStroke, double x, double y, double size) {
        super(color, colorStroke, x, y);
        this.colorStroke = colorStroke;
        this.size = size;
    }

    public double[] getPointsX() {
        return  new double[]{
                x, // верхняя вершина
                x - size / 2, // левая нижняя вершина
                x + size / 2  // правая нижняя вершина
        };
    }

    public double[] getPointsY() {
        return  new double[]{
                y - size * Math.sqrt(3) / 4, // верхняя вершина (высота треугольника)
                y + size * Math.sqrt(3) / 4, // левая нижняя вершина
                y + size * Math.sqrt(3) / 4  // правая нижняя вершина
        };
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(paint);
        gr.setStroke(colorStroke);
        gr.setLineWidth(2);
        gr.setEffect(effect);

        gr.fillPolygon(getPointsX(), getPointsY(), 3);
        gr.strokePolygon(getPointsX(), getPointsY(), 3);
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
        return "Треугольник";
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
