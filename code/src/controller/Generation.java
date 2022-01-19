package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.GameManager;

/**
 * Permet de choisir entre différentes tailles de monde.
 */
public class Generation {
    @FXML private javafx.scene.control.Button genGrand;
    @FXML private javafx.scene.control.Button genMoyen;
    @FXML private javafx.scene.control.Button genPetit;

    public void genPetit(ActionEvent actionEvent) {
        gen(50,30);
    }
    public void genMoyen(ActionEvent actionEvent) {
        gen(90,35);
    }
    public void genGrand(ActionEvent actionEvent) {
        gen(150,40);
    }

    /**
     * Génère le monde en fonction de la taille passé en paramètre.
     * @param width
     * @param height
     */
    private void gen(int width, int height) {
        Stage stage = (Stage) genGrand.getScene().getWindow();
        stage.close();
        GameManager.getInstance().startGame(width,height);
    }
}
