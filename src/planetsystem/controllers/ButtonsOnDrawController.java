package planetsystem.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import planetsystem.model.Model;

public class ButtonsOnDrawController {
    @FXML
    public Button stopButton;

    @FXML
    public Button showOrbits;

    Model model;
    boolean showOrbitsFlag = false;

    public void setModel(Model model) {
        this.model = model;
    }

    public void stopButtonAction() {
        model.changeStopFlag();
        if (!model.getStopFlag())
            stopButton.setText("Пауза");
        else {
            stopButton.setText("Возобновить");
        }
    }

    public void showOrbitsAction() {
        if (!showOrbitsFlag) {
            showOrbits.setText("Скрыть орбиты");
            model.setShowOrbitsFlag(1);
        } else {
            showOrbits.setText("Отобразить орбиты");
            model.setShowOrbitsFlag(2);
        }
        showOrbitsFlag = !showOrbitsFlag;
    }
}
