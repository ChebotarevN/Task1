package fx.calculateprocentfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    Procent procent = new Procent(0);
    int numProcent;

    @FXML
    private Label procent15, procent10, procent3;

    @FXML
    TextField inputText;

    @FXML
    CheckBox checkBox;

    @FXML
    Button button10;

    @FXML
    protected void onClickProcent15() {
        numProcent = 15;
        procent.setSum(Float.parseFloat(inputText.getText()));
        float resSum15 = procent.getSum() + procent.countPr(numProcent);
        procent15.setText(resSum15 + " рублей");
    }

    @FXML
    protected void onClickProcent10() {
        if (checkBox.isSelected())
            numProcent = 10;
        else
            numProcent = 9;
        procent.setSum(Float.parseFloat(inputText.getText()));
        float resSum10 = procent.countSumrnd(numProcent);
        procent10.setText(resSum10 + " рублей");
    }

    @FXML
    protected void onClickProcent3() {
        numProcent = 3;
        procent.setSum(Float.parseFloat(inputText.getText()));
        float resSum3 = procent.countSum(procent.getSum(), numProcent);
        procent3.setText(resSum3 + " рублей");
    }

    @FXML
    protected void onChoiceProcent() {
        if (checkBox.isSelected()) {
            button10.setText("Procent 10%");
        } else {
            button10.setText("Procent 9%");
        }
        procent10.setText("");
    }
}