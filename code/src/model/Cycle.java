package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import org.w3c.dom.css.RGBColor;


/*
    Nom : Cycle
    Description : Classe gérant le cycle jour nuit en changeant le fond du canvas à interval régulier

    -Attributs-
    dayTime : s'incrémente à chaque passage dans la boucle de jeu
    isNight : boolean pour savoir si c'est le jour ou la nuit
    Image : sprite de la Lune.

    -Méthode-
    changerEtat : en fonction de la valeur de isNight, change le fond du canvas.

 */
public class Cycle {
    private int dayTime;
    private boolean isNight = false;
    private Image moon = new Image("/images/moon.png");

    public void changerEtat(Canvas canvas){
        dayTime++;
        if(dayTime == 500){ //Durée normale 1500
            isNight = !isNight;
            if (isNight) dayTime = 300;
            else dayTime = 0;

        }

        if (isNight){
            for(double luminosite = 1.0; luminosite > 0; luminosite = luminosite-0.00001 ){
                System.out.println(luminosite);
                canvas.getGraphicsContext2D().setFill((Color.hsb(122,1,luminosite)));

            }
            canvas.getGraphicsContext2D().drawImage(moon, canvas.getWidth()/2-300, canvas.getHeight()/2-300, 80, 75);
        }
        else canvas.getGraphicsContext2D().setFill(Color.hsb(200,1,1));
    }

}
