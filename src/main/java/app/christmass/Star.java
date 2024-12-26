package app.christmass;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Star extends TreeDecorator {
    public Star(ChristmasTree tree) {
        super(tree);
    }
    public void draw(Pane panestar) {
        super.draw(panestar);
        drawStar(panestar);
    }
    private void drawStar(Pane panestar) {
        Pane node = new Pane();
        Path star = new Path();
        star.getElements().addAll(new MoveTo(239, 49),
                new LineTo(217, 102),
                new LineTo(239, 91),
                new LineTo(262, 102),
                new ClosePath(),
                new MoveTo(207, 68),
                new LineTo(270, 68),
                new LineTo(239, 91),
                new ClosePath());

        star.setFill(Color.YELLOW);
        star.setFillRule(FillRule.EVEN_ODD);
        node.getChildren().addAll(star);
        panestar.getChildren().addAll(node);
    }
}