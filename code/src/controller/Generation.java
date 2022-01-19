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

    /**
     * Appel la méthode gen avec une petite taille de monde(50x30)
     * @param actionEvent
     */
    public void genPetit(ActionEvent actionEvent) {
        gen(50,30);
    }
    /**
     * Appel la méthode gen avec une taille moyenne de monde(90x35)
     * @param actionEvent
     */
    public void genMoyen(ActionEvent actionEvent) {
        gen(90,35);
    }
    /**
     * Appel la méthode gen avec une grande taille de monde(150x40)
     * @param actionEvent
     */
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
