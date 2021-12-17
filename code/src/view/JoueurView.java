package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Joueur;

public class JoueurView  extends ImageView {
    public SimpleDoubleProperty x = new SimpleDoubleProperty();
    public SimpleDoubleProperty y = new SimpleDoubleProperty();
    public SimpleBooleanProperty isFacingRight = new SimpleBooleanProperty();
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

    public void playerDirection(){
        if(isFacingRight.get()){
            img = imgD;
        }
        else img = imgG;
    }
}
