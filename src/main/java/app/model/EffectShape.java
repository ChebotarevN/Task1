package app.model;

import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

import java.util.EnumMap;

public class EffectShape {
    private EnumMap<EffectEnum, Effect> effectEnumMap = new EnumMap<>(EffectEnum.class);

    public EffectShape() {
        effectEnumMap.put(EffectEnum.NONE, null);
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(2.0f);
        innerShadow.setOffsetY(2.0f);
        effectEnumMap.put(EffectEnum.INNER_SHADOW, innerShadow);
        BoxBlur blur = new BoxBlur();
        blur.setWidth(5);
        blur.setHeight(5);
        blur.setIterations(3);
        effectEnumMap.put(EffectEnum.BLUR, blur);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(4.0f);
        dropShadow.setOffsetY(4.0f);
        dropShadow.setColor(Color.BLACK);
        effectEnumMap.put(EffectEnum.DROP_SHADOW, dropShadow);
    }

    public Effect getEffect(EffectEnum effectEnum) {
        Effect effect = effectEnumMap.get(effectEnum);
        return effect;
    }
}
