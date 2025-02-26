package app.model;

import javafx.scene.image.Image;
import javafx.scene.paint.*;

import java.util.HashMap;

public class FillShape {
    private HashMap<Integer, Paint> fills;

    public FillShape(Color color, Color color1, double x, double y) {
        fills = new HashMap<>();
        fills.put(0, color);

        fills.put(1, new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, color),
                new Stop(1, color1)));
        fills.put(2, new RadialGradient(0,                  // Focus angle
                0.5,                // Focus distance
                x - 40, y,           // Center coordinates
                150,                // Radius
                false,               // Proportional
                CycleMethod.NO_CYCLE, // Cycle method
                new Stop(0, color),
                new Stop(0.5, color1)));
        fills.put(3, new ImagePattern(new Image("file:src/main/resources/app/img1.jpg")));
    }

    public Paint getFill(int index) {
        Paint fill = fills.get(index);
        return fill;
    }
}
