/**
 * Класс, представляющий фигуру "Пятиугольник".
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

public class Pentagon extends Shape {
    private double size;
    private double[] xPoints;
    private double[] yPoints;

    public Pentagon(Color color, Color colorStroke, double x, double y, double size) {
        super(color, colorStroke, x, y);
        this.size = size;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(colorStroke);
        gr.setLineWidth(2);

        int sides = 5;
        xPoints = new double[sides];
        yPoints = new double[sides];
        double angleStep = 2 * Math.PI / sides;
        double radius = size;

        for (int i = 0; i < sides; i++) {
            double angle = angleStep * i - Math.PI / 2;
            xPoints[i] = x + radius * Math.cos(angle);
            yPoints[i] = y + radius * Math.sin(angle);
        }

        gr.fillPolygon(xPoints, yPoints, sides);
        gr.strokePolygon(xPoints, yPoints, sides);
    }

    @Override
    public void draw(Pane pane, Paint paint) {
        Polygon polygon = new Polygon();
        polygon.setFill(paint);
        polygon.setStroke(colorStroke);
        polygon.setStrokeWidth(2);

        int sides = 5;
        xPoints = new double[sides];
        yPoints = new double[sides];
        double angleStep = 2 * Math.PI / sides;
        double radius = size;

        for (int i = 0; i < sides; i++) {
            double angle = angleStep * i - Math.PI / 2;
            xPoints[i] = x + radius * Math.cos(angle);
            yPoints[i] = y + radius * Math.sin(angle);
        }

        polygon.getPoints().addAll(
                xPoints[0], yPoints[0],
                xPoints[1], yPoints[1],
                xPoints[2], yPoints[2],
                xPoints[3], yPoints[3],
                xPoints[4], yPoints[4]
        );

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
        double coefficient = 1.0 / 4.0 * Math.sqrt(5 * (5 + 2 * Math.sqrt(5)));
        double radius = size;
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

    @Override
    public boolean contains(double clickX, double clickY) {
        int intersectCount = 0;
        for (int i = 0; i < xPoints.length; i++) {
            double x1 = xPoints[i];
            double y1 = yPoints[i];
            double x2 = xPoints[(i + 1) % xPoints.length];
            double y2 = yPoints[(i + 1) % yPoints.length];

            if (clickY > Math.min(y1, y2)) {
                if (clickY <= Math.max(y1, y2)) {
                    if (clickX <= Math.max(x1, x2)) {
                        double xIntersect = (clickY - y1) * (x2 - x1) / (y2 - y1) + x1;
                        if (y1 == y2 || clickX <= xIntersect) {
                            intersectCount++;
                        }
                    }
                }
            }
        }
        return intersectCount % 2 != 0;
    }
}