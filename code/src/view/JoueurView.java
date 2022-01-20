package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Joueur;

/**
 * Classe affichant le joueur à l'écran, pour cela est relié à des valeurs de la classe Joueur.
 */
public class JoueurView  extends ImageView {
    /**
     * Coordonnée x du Joueur
     */
    public SimpleDoubleProperty x = new SimpleDoubleProperty();
    /**
     * Coordonnée y du Joueur
     */
    public SimpleDoubleProperty y = new SimpleDoubleProperty();
    /**
     * Direction auquel fait face Joueur
     */
    public SimpleBooleanProperty isFacingRight = new SimpleBooleanProperty();
    /**
     * Image représentant le Joueur
     */
    public Image img;
    public Image imgD;
    public Image imgG;

    public JoueurView(Joueur joueur, double width, double height){

        x.bind(joueur.xP);
        y.bind(joueur.yP);
        isFacingRight.bind(joueur.isFacingRight);
        //image de base
        this.img = new Image("/images/steve.png", width, height, false, true);
        //image pour aller de chaque coté
        this.imgD = new Image("/images/steve-right.png", width, height, false, true);
        this.imgG = new Image("/images/steve.png", width, height, false, true);
    }

    /**
     * Affiche le visuel du joueur d'un certain coté en fonction de la direction la classe Joueur.
     */
    public void playerDirection(){
        if(isFacingRight.get()){
            img = imgD;
        }
        else img = imgG;
    }
}
