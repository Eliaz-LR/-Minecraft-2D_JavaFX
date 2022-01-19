package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Représente le joueur
 */
public class Joueur {

    //les coordonnées du joueur sont ici données en "unités" de canvas
    /**
     * Coordonée x du joueur
     */
    public double x;
    /**
     * Coordonnée y du joueur
     */
    public double y;
    /**
     * Vitesse verticale du joueur
     */
    public double Xspeed;
    /**
     * Vitesse horizontale du joueur
     */
    public double Yspeed;
    /**
     * Permet de savoir si le joueur a le corps tourné vers la droite.
     * (JoueurView est bindé sur cette valeur pour afficher le joueur)
     */
    public SimpleBooleanProperty isFacingRight = new SimpleBooleanProperty();
    public SimpleDoubleProperty xP = new SimpleDoubleProperty();
    public SimpleDoubleProperty yP = new SimpleDoubleProperty();

    public Joueur(){
     isFacingRight.set(true);
    }

}