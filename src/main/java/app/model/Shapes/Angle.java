package app.model.Shapes;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Angle extends Shape {
    private double size;

    public Angle(Color color, Color colorStroke, double x, double y, double size) {
        super(color, colorStroke, x, y);
        this.size = size;
    }

    @Override
    public double area() {
        return (x * size - x) + (y * size - y);
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(colorStroke);
        gr.setLineWidth(2);
        gr.strokeLine(x - (size * 25), y + (size * 25), x + size * 25, y + (size * 25));
        gr.strokeLine(x - (size * 25), y - (size * 25), x - (size * 25), y + (size * 25));
    }

    @Override
    public void draw(Pane pane, Paint paint) {
        Line line1 = new Line(x - (size * 25), y + (size * 25), x + size * 25, y + (size * 25));
        Line line2 = new Line(x - (size * 25), y - (size * 25), x - (size * 25), y + (size * 25));
        line1.setStrokeWidth(2);
        line1.setStroke(paint);

        line2.setStrokeWidth(2);
        line2.setStroke(paint);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), line1);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

        fadeTransition = new FadeTransition(Duration.millis(1000), line2);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

        pane.getChildren().addAll(line1, line2);
    }

    @Override
    public String toString() {
        return "Угол";
    }

    @Override
    public double[] getSize() {
        return new double[]{size * 50, size * 50};
    }

    @Override
    public double setSize(double size) {
        return this.size = size;
    }
}
