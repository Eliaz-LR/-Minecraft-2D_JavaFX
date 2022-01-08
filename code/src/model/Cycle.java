package model;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


import java.util.Date;


/*
    Nom : Cycle
    Description : Classe gérant le cycle jour nuit en changeant le fond du canvas à interval régulier

    -Attributs-
    isNight : boolean pour savoir si c'est le jour ou la nuit
    Image : sprite de la Lune.

    -Méthode-
    changerEtat : en fonction de la valeur de isNight, change le fond du canvas.

 */
public class Cycle {
    private boolean isNight = false;
    private Image moon = new Image("/images/moon.png");
    private SimpleDoubleProperty gameTime = new SimpleDoubleProperty();

    public Cycle(){
        gameTime.bind(GameManager.getInstance().gameTimeP);
    }

    public void changerEtat(Canvas canvas){
        if(gameTime.getValue()%1600 == 0) isNight = !isNight;

        if (isNight){
            canvas.getGraphicsContext2D().setFill(Color.hsb(200,1, gameTime.getValue()%1600/100/8/2)); //gameTime.getValue()%1600/100/8/2 permet de faire le ration entre 800 et 0.5 ce qui permet de mofifier la luminosité en fonction du temps
            //canvas.getGraphicsContext2D().drawImage(moon, canvas.getWidth()/2-300, canvas.getHeight()/2-300, 80, 75);
        }
        else canvas.getGraphicsContext2D().setFill(Color.hsb(200,1, 1 - gameTime.getValue()%1600/100/8/2));






        //System.out.println(gameTime.getValue());
        System.out.println(gameTime.getValue()%1600);
    }

}
