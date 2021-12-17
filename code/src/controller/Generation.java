package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.GameManager;

public class Generation {
    @FXML private javafx.scene.control.Button btn_generate;

    public void handleGenerate(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_generate.getScene().getWindow();
        stage.close();
        GameManager.getInstance().startGame(80,30);
    }
}
