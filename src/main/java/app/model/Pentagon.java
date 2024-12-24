package app.model;

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
        gr.setFill(paint);
        gr.setStroke(colorStroke);
        gr.setLineWidth(2);
        gr.setEffect(effect);

        // Количество вершин пятиугольника
        int sides = 5;

        // Координаты вершин пятиугольника
        double[] xPoints = new double[sides];
        double[] yPoints = new double[sides];

        // Угол между соседними вершинами
        double angleStep = 2 * Math.PI / sides;

        // Радиус (расстояние от центра до вершины)
        double radius = size;

        // Вычисляем координаты вершин
        for (int i = 0; i < sides; i++) {
            double angle = angleStep * i - Math.PI / 2; // Начинаем с верхней вершины
            xPoints[i] = x + radius * Math.cos(angle);
            yPoints[i] = y + radius * Math.sin(angle);
        }

        // Рисуем пятиугольник
        gr.fillPolygon(xPoints, yPoints, sides);
        gr.strokePolygon(xPoints, yPoints, sides);
    }

    @Override
    public double area() {
        // Константа для вычисления площади правильного пятиугольника
        double coefficient = 1.0 / 4.0 * Math.sqrt(5 * (5 + 2 * Math.sqrt(5)));
        // Радиус описанной окружности
        double radius = size;
        // Площадь
        return coefficient * radius * radius;
    }

    @Override
    public String toString() {
        return "Пятиугольник";
    }

    @Override
    public double[] getSize() {
        return new double[]{size, size};
    }

    @Override
    public double setSize(double size) {
        return this.size = size;
    }
}
