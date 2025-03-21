package app.linker;

import app.model.Decorate;
import app.model.Momento;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class Composite {
    private ArrayList<Component> array = new ArrayList<>();// агрегатор элементарных объектов
    private ArrayList<double[]> arrayXY = new ArrayList<>();


    public Composite() {
    }


    public void add(Decorate decorate, GraphicsContext gr) {
        for (Component comp : array) {
            if (comp.decorate.equals(decorate)) {
                return;
            }
        }
        Component comp = new Component(decorate);
        comp.decorate.getShape().setColorStroke(Color.RED);
        array.add(comp);
    }

    public void remove() {
        for (Component comp : array) {
            comp.decorate.getShape().setColorStroke(comp.colorStroke);
        }
        array.clear();
    }
    public void saveCoord() {
        for (Component component : array) {
            component.xy = new double[]{component.getDecorate().getShape().getX(), component.getDecorate().getShape().getY()};
        }
    }

    public void changeXY(double x, double y) {
        for (Component comp : array) {
            comp.decorate.getShape().setXY((x + comp.xy[0]), (y + comp.xy[1]));
        }
    }
}
