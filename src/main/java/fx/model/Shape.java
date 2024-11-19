package fx.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements Cloneable {
    //параметры фигуры - приватные поля
    protected Color color;
    protected double x, y;

    // объявление абстрактных методов
    public abstract double area();

    public abstract void draw(GraphicsContext gr);

    // конструктор
    public Shape(Color color, double x, double y) {
        //System.out.println("Shape constructor called");
        this.color = color;
        this.x = x;
        this.y = y;
    }

    // реализация методов
    public void setColor(Color color) {
        this.color = color;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    public abstract double getSize();
}