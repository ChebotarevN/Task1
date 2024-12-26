package app.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class Shape implements Cloneable {
    //параметры фигуры - приватные поля
    protected Color color, colorStroke;
    protected double x, y;
    protected Paint paint;
    protected Effect effect;

    // объявление абстрактных методов
    public abstract double area();

    public abstract void draw(GraphicsContext gr);

    // конструктор
    public Shape(Color color, Color colorStroke, double x, double y) {
        //System.out.println("Shape constructor called");
        this.color = color;
        this.colorStroke = colorStroke;
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Pane pane);

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    // реализация методов
    public void setColor(Color color) {
        this.color = color;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void setColorStroke(Color colorStroke) {
        this.colorStroke = colorStroke;
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

    public abstract double[] getSize();

    public abstract double setSize(double size);
}