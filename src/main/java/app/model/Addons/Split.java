package app.model.Addons;

import app.model.Decorate;
import javafx.scene.canvas.GraphicsContext;

public class Split implements Addon {
    protected Decorate decorate;
    public Split(Decorate decorate) {
        this.decorate = decorate;
    }

    public void draw(GraphicsContext gr) {
        // Рисуем линии, разделяющие окружность на 8 частей
        for (int i = 0; i < 4; i++) {
            double angle = Math.toRadians(90 * i); // Угол в радианах
            double endX = decorate.getShape().getX() + decorate.getShape().getSize()[0] / 2 * Math.cos(angle);
            double endY = decorate.getShape().getY() + decorate.getShape().getSize()[1] / 2 * Math.sin(angle);
            gr.strokeLine(decorate.getShape().getX(), decorate.getShape().getY(), endX, endY);
        }
    }
}