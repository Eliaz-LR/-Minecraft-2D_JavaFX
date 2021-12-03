package model;

import java.awt.*;
import javafx.scene.image.Image;

public class Joueur {
    public Image img = new Image("/images/steve.png", 160/2, 360/2, true, true);
    //les coordonnées du joueur sont ici données en "unités" de canvas
    public float x;
    public float y;

}