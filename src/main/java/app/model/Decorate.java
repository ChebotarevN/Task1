package app.model;

import app.model.Addons.Addon;
import app.model.Shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.util.List;

public class Decorate {

    protected Shape shape;
    protected Paint paint;
    protected Effect effect;
    protected List<Addon> addons;


    public Decorate(Shape shape, Paint paint, Effect effect) {
        this.shape = shape;
        this.paint = paint;
        this.effect = effect;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void draw(GraphicsContext gr) {
        gr.setFill(paint);
        gr.setEffect(effect);
        shape.draw(gr);
        for(Addon a: addons) {
            a.draw(gr);
        }
    }

    public void draw(Pane pane) {
        shape.draw(pane, paint);
    }

    public void setAddons(List<Addon> addons) {
        this.addons = addons;
    }

    public Shape getShape() {
        return shape;
    }
}
