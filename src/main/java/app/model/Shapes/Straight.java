package app.model.Shapes;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Straight extends Shape {
    private double size;

    public Straight(Color color, Color colorStroke, double x, double y, double size) {
        super(color, colorStroke, x, y);
        this.size = size;
    }

    @Override
    public double area() {
        return (x + size * 100);
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(colorStroke);
        gr.setLineWidth(2);
        gr.strokePolygon(new double[]{x - size * 25, x + size * 25}, new double[]{y, y}, 2);
    }

    @Override
    public void draw(Pane pane, Paint paint) {
        Polygon polygon = new Polygon();
        polygon.setStroke(colorStroke);
        polygon.setStrokeWidth(2);
        polygon.getPoints().addAll(x - size * 25, y,
                x + size * 25, y);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), polygon);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        pane.getChildren().addAll(polygon);
    }

    @Override
    public String toString() {
        return "Линия";
    }

    @Override
    public double[] getSize() {
        return new double[]{(x + size * 100), 0};
    }

    @Override
    public double setSize(double size) {
        return this.size = size;
    }

    public boolean contains(double clickX, double clickY) {
        double tolerance = 2.0;
        double minX = Math.min(x - size * 25, x + size * 25);
        double maxX = Math.max(x - size * 25, x + size * 25);
        double minY = y;
        double maxY = y;

        if (clickX >= minX - tolerance && clickX <= maxX + tolerance &&
                clickY >= minY - tolerance && clickY <= maxY + tolerance) {
            return true;
        }
        return false;
    }
}
