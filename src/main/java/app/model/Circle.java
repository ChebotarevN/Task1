package app.model;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
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
