package app.model;

import javafx.animation.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Circle extends Shape {
    private double diametr;
    double radius;


    public Circle(Color color, Color colorStroke, double x, double y, double diametr) {
        super(color, colorStroke, x, y);
        this.diametr = diametr;
        radius = diametr / 2;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw(Pane pane) {
        javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(x, y, radius);
        circle.setStroke(colorStroke);
        circle.setFill(paint);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), circle);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Timeline.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        pane.getChildren().addAll(circle);
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(colorStroke);
        gr.setFill(paint);
        gr.setEffect(effect);
        gr.fillOval(x - radius, y - radius, diametr, diametr);
        gr.strokeOval(x - radius, y - radius, diametr, diametr);
    }

    @Override
    public String toString() {
        return "Круг";
    }

    @Override
    public double[] getSize() {
        return new double[]{diametr, diametr};
    }

    @Override
    public double setSize(double size) {
        radius = size / 2;
        return diametr = size;
    }
}
