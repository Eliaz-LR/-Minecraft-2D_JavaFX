package model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 * Classe affichant le temps de jeu du joueur.
 */
public class Time implements InvalidationListener {
    /**
     * Temps de jeu (compté en tour de boucle).
     */
    private long time;

    /**
     * Affiche la durée de jeu de la session actuelle.
     * @param canvas Canvas sur lequel la durée va être affichée.
     */
    public void displayTime(Canvas canvas){
        canvas.getGraphicsContext2D().setStroke(Color.RED);
        canvas.getGraphicsContext2D().strokeText("Temps de jeu : " + time , 50,50);
    }

    /**
     * Lors d'une notification du GameManager, récupère le temps.
     * @param observable
     */
    @Override
    public void invalidated(Observable observable) {

        if(observable instanceof GameManager){
           time = GameManager.getInstance().getTime();
        }
    }
}
