package fx.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class CellList extends ListCell<Shape> {
    @Override
    public void updateItem(Shape item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            Canvas cnv = new Canvas();
            cnv.setHeight(60);// задание размера элемента отображения
            cnv.setWidth(100);
            GraphicsContext gr = cnv.getGraphicsContext2D();
            Shape item1 = (Shape) item.clone();//текущая фигура из списка ObservableList
            item1.draw(gr); // ее отрисовка на канве
            setGraphic(cnv); //установка канвы вместо cell
        }
    }
}
