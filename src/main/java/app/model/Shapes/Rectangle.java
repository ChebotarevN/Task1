package app.model.Shapes;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class Rectangle extends Shape {
    private double size;

    public Rectangle(Color color, Color colorStroke, double x, double y, double size) {
        super(color, colorStroke, x, y);
        this.size = size;
    }

    @Override
    public double area() {
        return size * size;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(colorStroke);
        gr.fillRect(x - size / 2, y - size / 2, size, size);
        gr.strokeRect(x - size / 2, y - size / 2, size, size);
    }

    @Override
    public void draw(Pane pane, Paint paint) {
        javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(x, y, size, size);
        rectangle.setFill(paint);
        rectangle.setStroke(colorStroke);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), rectangle);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        pane.getChildren().addAll(rectangle);
    }

    @Override
    public String toString() {
        return "Квадрат";
    }

    @Override
    public double[] getSize() {
        return new double[]{size, size};
    }

    @Override
    public double setSize(double size) {
        return this.size = size;
    }

    public boolean contains(double clickX, double clickY) {
        double halfSize = size / 2;
        return clickX >= x - halfSize && clickX <= x + halfSize &&
                clickY >= y - halfSize && clickY <= y + halfSize;
    }
}
