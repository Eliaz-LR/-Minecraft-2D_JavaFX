package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;


public class Cycle {
    private int dayTime;
    private boolean isNight = false;
    private Image moon = new Image("/images/moon.png");

    public void changerEtat(Canvas canvas){
        dayTime++;
        if(dayTime == 1500){
            isNight = !isNight;
            if (isNight) dayTime = 300;
            else dayTime = 0;

        }

        if (isNight){
            canvas.getGraphicsContext2D().setFill(Color.BLACK);
            canvas.getGraphicsContext2D().drawImage(moon, canvas.getWidth()/2-300, canvas.getHeight()/2-300, 80, 75);
        }
        else canvas.getGraphicsContext2D().setFill(Color.LIGHTBLUE);
    }

}
