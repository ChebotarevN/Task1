/**
 * Класс для отображения треугольника
 */

package app.model.shapes;

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
        this.size = size;
    }

    public double[] getPointsX() {
        return new double[]{
                x,
                x - size / 2,
                x + size / 2
        };
    }

    public double[] getPointsY() {
        return new double[]{
                y - size * Math.sqrt(3) / 4,
                y + size * Math.sqrt(3) / 4,
                y + size * Math.sqrt(3) / 4
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
        polygon.getPoints().addAll(
                x, y - size * Math.sqrt(3) / 4,
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

        pane.getChildren().add(polygon);
    }

    @Override
    public double area() {
        double[] xCoords = {x + 10, x + (18 * size), x + (30 * size)};
        double[] yCoords = {y + (20 * size), y + (10 * size), y + (20 * size)};

        double area = 0.0;
        for (int i = 0; i < 1; i++) {
            double triangleArea = Math.abs(
                    0.5 * (xCoords[0] * (yCoords[1] - yCoords[2]) +
                            xCoords[1] * (yCoords[2] - yCoords[0]) +
                            xCoords[2] * (yCoords[0] - yCoords[1])));
            area += triangleArea;
        }
        return area;
    }

    @Override
    public String toString() {
        return "Треугольник";
    }

    @Override
    public double[] getSize() {
        return new double[]{
                size * Math.sqrt(3) / 4,
                size * Math.sqrt(3) / 2};
    }

    @Override
    public double setSize(double size) {
        return this.size = size;
    }

    @Override
    public boolean contains(double clickX, double clickY) {
        double[] xPoints = getPointsX();
        double[] yPoints = getPointsY();

        double A = area(
                xPoints[0], yPoints[0],
                xPoints[1], yPoints[1],
                xPoints[2], yPoints[2]);
        double A1 = area(
                clickX, clickY,
                xPoints[1], yPoints[1],
                xPoints[2], yPoints[2]);
        double A2 = area(
                xPoints[0], yPoints[0],
                clickX, clickY,
                xPoints[2], yPoints[2]);
        double A3 = area(
                xPoints[0], yPoints[0],
                xPoints[1], yPoints[1],
                clickX, clickY);

        return Math.abs(A - (A1 + A2 + A3)) < 0.0001;
    }

    private double area(
            double x1, double y1,
            double x2, double y2,
            double x3, double y3) {
        return Math.abs(
                (x1 * (y2 - y3) +
                        x2 * (y3 - y1) +
                        x3 * (y1 - y2)) / 2.0);
    }
}