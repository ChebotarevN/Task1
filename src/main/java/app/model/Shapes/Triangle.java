package app.model.Shapes;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Triangle extends Shape {
    private double size;

    public Triangle(Color color, Color colorStroke, double x, double y, double size) {
        super(color, colorStroke, x, y);
        this.colorStroke = colorStroke;
        this.size = size;
    }

    public double[] getPointsX() {
        return new double[]{
                x, // верхняя вершина
                x - size / 2, // левая нижняя вершина
                x + size / 2  // правая нижняя вершина
        };
    }

    public double[] getPointsY() {
        return new double[]{
                y - size * Math.sqrt(3) / 4, // верхняя вершина (высота треугольника)
                y + size * Math.sqrt(3) / 4, // левая нижняя вершина
                y + size * Math.sqrt(3) / 4  // правая нижняя вершина
        };
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(colorStroke);
        gr.setLineWidth(2);

        gr.fillPolygon(getPointsX(), getPointsY(), 3);
        gr.strokePolygon(getPointsX(), getPointsY(), 3);
    }

    @Override
    public void draw(Pane pane, Paint paint) {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(x, y - size * Math.sqrt(3) / 4,
                x - size / 2, y + size * Math.sqrt(3) / 4,
                x + size / 2, y + size * Math.sqrt(3) / 4);
        polygon.setFill(paint);
        polygon.setStroke(colorStroke);
        polygon.setStrokeWidth(2);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), polygon);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        pane.getChildren().addAll(polygon);
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
        return new double[]{size * Math.sqrt(3) / 4, size * Math.sqrt(3) / 2};
    }

    @Override
    public double setSize(double size) {
        return this.size = size;
    }

    public boolean contains(double clickX, double clickY) {
        double[] xPoints = getPointsX();
        double[] yPoints = getPointsY();

        // Получаем координаты вершин треугольника
        double x1 = xPoints[0], y1 = yPoints[0];
        double x2 = xPoints[1], y2 = yPoints[1];
        double x3 = xPoints[2], y3 = yPoints[2];

        // Вычисляем площади треугольников, образованных точкой и вершинами
        double A = area(x1, y1, x2, y2, x3, y3); // Площадь основного треугольника
        double A1 = area(clickX, clickY, x2, y2, x3, y3); // Площадь треугольника 1
        double A2 = area(x1, y1, clickX, clickY, x3, y3); // Площадь треугольника 2
        double A3 = area(x1, y1, x2, y2, clickX, clickY); // Площадь треугольника 3

        // Если сумма площадей A1, A2, A3 равна площади A, точка внутри треугольника
        return Math.abs(A - (A1 + A2 + A3)) < 0.0001;
    }

    // Метод для вычисления площади треугольника по координатам вершин
    private double area(double x1, double y1, double x2, double y2, double x3, double y3) {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
    }
}
