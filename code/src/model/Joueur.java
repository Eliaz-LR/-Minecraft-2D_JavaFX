package model;

import java.awt.*;

import com.sun.webkit.dom.DocumentImpl;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class Joueur {

    //les coordonnées du joueur sont ici données en "unités" de canvas
    public double x;
    public double y;
    public double Xspeed;
    public double Yspeed;
    public SimpleBooleanProperty isFacingRight = new SimpleBooleanProperty();
    public SimpleDoubleProperty xSpeedP = new SimpleDoubleProperty();
    public SimpleDoubleProperty ySpeedP = new SimpleDoubleProperty();
    public SimpleDoubleProperty xP = new SimpleDoubleProperty();
    public SimpleDoubleProperty yP = new SimpleDoubleProperty();

    public Joueur(){
     isFacingRight.set(true);
    }

}