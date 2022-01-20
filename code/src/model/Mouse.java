package model;

import javafx.scene.Scene;
import javafx.scene.input.MouseButton;

/**
 * Classe gérant l'utilisation de la souris.
 */
public class Mouse {
    /**
     * Scène du jeu actuel
     */
    private Scene mainJeu;
    /**
     * Coordonnée x d'un clic souris
     */
    public double clickedX;
    /**
     * Coordonnée y d'un clic souris
     */
    public double clickedY;

    /**
     * Coordonnée x de la souris
     */
    public double X;
    /**
     * Coordonnée y de la souris
     */
    public double Y;


    public MouseButton mouseButton;


    public Mouse(Scene mainJeu) {
        this.mainJeu = mainJeu;
        init();
    }

    /**
     * Lors d'un clic, récupère le bouton et les coordonnées du clic.
     */
    private void init() {
        mainJeu.setOnMouseClicked(
                e -> {
                    mouseButton = e.getButton();
                    clickedX = e.getX();
                    clickedY = e.getY();
                }
        );

        mainJeu.setOnMouseMoved(
                e -> {
                    X = e.getX();
                    Y = e.getY();
                }
        );
    }

    /**
     * Reset des coordonnées du clic de souris à 0
     */
    public void resetCoord(){
        clickedX = 0;
        clickedY = 0;
    }

    /**
     * Vérifie si le clic souris est effectué autre part qu'en 0x 0y.
     * @return Retourne un booléen.
     */
    public Boolean isCoordSet(){
        return clickedX != 0 || clickedY != 0;
    }
}
