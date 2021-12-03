package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Joueur;

public class PlayerView extends ImageView {

    public Image img = new Image("/images/steve.png", 160/2, 360/2, true, true);
    public DoubleProperty x = new SimpleDoubleProperty();
    public DoubleProperty y = new SimpleDoubleProperty();

    public PlayerView(Joueur j){
        this.setImage(img);
        x.bind(j.xP);
        y.bind(j.yP);
    }
    public void flip(){
      this.setScaleX(-1);
    }
}
