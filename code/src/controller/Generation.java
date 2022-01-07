package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.GameManager;

public class Generation {
    @FXML private javafx.scene.control.Button valider_gen;

    public void handleGenerate(ActionEvent actionEvent) {
        Stage stage = (Stage) valider_gen.getScene().getWindow();
        stage.close();
        GameManager.getInstance().startGame(80,30);
    }
}
