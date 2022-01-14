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
    private int timeForCycle = 1600;
    private double currentTime;
    private double heure = timeForCycle/24;
    //heure = 1/24eme du cycle
    //heure 0 : midi, 12 : minuit
    private Image moon = new Image("/images/moon.png");
    private SimpleDoubleProperty gameTime = new SimpleDoubleProperty();

    public Cycle(){
        gameTime.bind(GameManager.getInstance().gameTimeP);
    }

    public void changerEtat(Canvas canvas){
        currentTime = gameTime.get();
        currentTime=currentTime%timeForCycle;
        canvas.getGraphicsContext2D().setFill(Color.hsb(197,0.5, getLight()));
        System.out.println((currentTime/timeForCycle)*24);
        drawMoon(canvas);
    }

    private void drawMoon(Canvas canvas){
        canvas.getGraphicsContext2D().setGlobalAlpha(1-getLight());
        canvas.getGraphicsContext2D().drawImage(moon,canvas.getWidth()/2-300, canvas.getHeight()/2-300, 80, 75);
        canvas.getGraphicsContext2D().setGlobalAlpha(1);
    }

    //fonction non utilisée mais grave belle, svp suprimez pas
    private double getHue(){
        Color color1 = Color.hsb(197,0.5,1);
        Color color2 = Color.hsb(35,1,1);
        double percent = 1 - getLight();
        double resultRed = color1.getRed() + percent * (color2.getRed() - color1.getRed());
        double resultGreen = color1.getGreen() + percent * (color2.getGreen() - color1.getGreen());
        double resultBlue = color1.getBlue() + percent * (color2.getBlue() - color1.getBlue());
        return (new Color(resultRed, resultGreen, resultBlue, 1)).getHue();
    }

    private double getLight(){
        //if jour
        if (currentTime<=heure*5 || currentTime>=heure*19){
            return 1;
        }
        //if nuit
        if (currentTime>=heure*7 && currentTime<=heure*17){
            return 0;
        }

        //if matin
        if (currentTime>heure*17 && currentTime<heure*19){
            double adjustedTime = currentTime-heure*17;
            double x = 1/(2*heure);
            return (x*adjustedTime);
        }
        //if soir
        if (currentTime>heure*5 && currentTime<heure*7){
            double adjustedTime = currentTime-heure*5;
            double x = 1/(2*heure);
            return (1-x*adjustedTime);
        }

        return 0;
    }

}
