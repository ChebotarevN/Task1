package fx.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
        gr.setFill(color);
        gr.fillOval(x, y, diametr, diametr);
        gr.strokeOval(x, y, diametr, diametr);

    }

    @Override
    public String toString() {
        return "Цвет круга: " + super.color + "; цвет границы: " + colorStroke + "; площадь: " + area();
    }

    @Override
    public double[] getSize() {
        return new double[]{diametr, diametr};
    }

    @Override
    public double setSize(double size) {
        return diametr = size;
    }
}
