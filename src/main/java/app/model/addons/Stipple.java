/**
 * Эффект пунктирного контура для фигуры.
 */

package app.model.addons;

import app.model.Decorate;
import javafx.scene.canvas.GraphicsContext;

public class Stipple implements Addon {
    protected Decorate decorate;

    public Stipple(Decorate decorate) {
        this.decorate = decorate;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setLineDashes(10, 60);
        gr.setLineWidth(5);
        decorate.getShape().draw(gr);
        gr.setLineDashes(null);
        gr.setLineWidth(1);
    }
}