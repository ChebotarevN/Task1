package app.model.Shapes;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Pentagon extends Shape {
    private double size;
    private double[] xPoints, yPoints;

    public Pentagon(Color color, Color colorStroke, double x, double y, double size) {
        super(color, colorStroke, x, y);
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(colorStroke);
        gr.setLineWidth(2);

        // Количество вершин пятиугольника
        int sides = 5;

        // Координаты вершин пятиугольника
        xPoints = new double[sides];
        yPoints = new double[sides];

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
    public void draw(Pane pane, Paint paint) {
        Polygon polygon = new Polygon();
        polygon.setFill(paint);
        polygon.setStroke(colorStroke);
        polygon.setStrokeWidth(2);

        // Количество вершин пятиугольника
        int sides = 5;

        // Координаты вершин пятиугольника
        xPoints = new double[sides];
        yPoints = new double[sides];

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

        polygon.getPoints().addAll(xPoints[0], yPoints[0],
                xPoints[1], yPoints[1],
                xPoints[2], yPoints[2],
                xPoints[3], yPoints[3],
                xPoints[4], yPoints[4]);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), polygon);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        // Рисуем пятиугольник
        pane.getChildren().addAll(polygon);
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
        return new double[]{xPoints[1] - xPoints[4], yPoints[2] - yPoints[0]};
    }

    @Override
    public double setSize(double size) {
        return this.size = size;
    }
}
