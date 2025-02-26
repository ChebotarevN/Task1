package app.model;

import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class EffectShape {
    private HashMap<Integer, Effect> effects;

    public EffectShape() {
        effects = new HashMap<>();
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(2.0f);
        innerShadow.setOffsetY(2.0f);
        effects.put(0, innerShadow);
        BoxBlur blur = new BoxBlur();
        blur.setWidth(5);
        blur.setHeight(5);
        blur.setIterations(3);
        effects.put(1, blur);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(4.0f);
        dropShadow.setOffsetY(4.0f);
        dropShadow.setColor(Color.BLACK);
        effects.put(2, dropShadow);
    }

    public Effect getEffect(int index) {
        Effect effect = effects.get(index);
        return effect;
    }
}
