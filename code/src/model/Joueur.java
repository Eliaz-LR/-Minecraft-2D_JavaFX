package model;

import java.awt.*;

import com.sun.webkit.dom.DocumentImpl;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class Joueur {
    public Image img;
    public Image imgD;
    public Image imgG;

    //les coordonnées du joueur sont ici données en "unités" de canvas
    public double x;
    public double y;
    public double Xspeed;
    public double Yspeed;

    public Joueur(double width, double height){
        //image de base
        this.img = new Image("/images/steve.png", width, height, false, true);
        //image pour aller de chaque coté
        this.imgD = new Image("/images/steve-right.png", width, height, false, true);
        this.imgG = new Image("/images/steve.png", width, height, false, true);
    }




}