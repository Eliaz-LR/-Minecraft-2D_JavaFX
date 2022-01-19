package model;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Classe gérant l'utilisation de la souris.
 */
public class Mouse {
    /**
     * Scène du jeu actuel
     */
    Scene mainJeu;
    /**
     * Coordonnée x d'un clic souris
     */
    public double ClickedX;
    /**
     * Coordonnée y d'un clic souris
     */
    public double ClickedY;

    /**
     * Coordonnée  x  de la souris
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
     * Lors d'un clique, récupère le bouton et les coordonnées du clic.
     */
    private void init() {
        mainJeu.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        mouseButton = e.getButton();
                        ClickedX = e.getX();
                        ClickedY = e.getY();
                    }
                }
        );

        mainJeu.setOnMouseMoved(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        X = e.getX();
                        Y = e.getY();
                    }
                }
        );
    }

    /**
     * Reset des coordonnées du clic de souris à 0
     */
    public void resetCoord(){
        ClickedX = 0;
        ClickedY = 0;
    }

    /**
     * Vérifie si le clic souris est effectué autre part que en 0x 0y
     * @return
     */
    public Boolean isCoordSet(){
        if (ClickedX == 0 && ClickedY == 0){
            return false;
        }
        return true;
    }
}
