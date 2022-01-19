package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;


/**
    Classe gérant le cycle jour nuit en changeant le fond du canvas à interval régulier.
    Cette classe dessine aussi la lune dans le ciel la nuit.
 */
public class Cycle {
    //VOUS POUVEZ CHANGER CETTE VARIABLE POUR CHANGER LA DUREE D'UN CYCLE JOUR/NUIT
    /**
     * Duree d'un cycle jour/nuit.
     */
    private static int timeForCycle = 1600;
    /**
     * "Heure" du cycle jour/nuit en nb de Cycles.
     */
    private double currentTime;
    /**
     * Durée d'une heure avec ce systeme de temps.
     */
    private static double heure = timeForCycle/24;
    //heure = 1/24eme du cycle
    //heure 0 : midi, 12 : minuit
    private Image moon = new Image("/images/moon.png");
    private SimpleDoubleProperty gameTime = new SimpleDoubleProperty();

    public Cycle(){
        gameTime.bind(GameManager.getInstance().gameTimeP);
    }

    /**
     * Fonction qui dessine le ciel en fonction du temps passé.
     * @param canvas
     */
    public void changerEtat(Canvas canvas){
        currentTime = gameTime.get();
        currentTime=currentTime%timeForCycle;
        canvas.getGraphicsContext2D().setFill(Color.hsb(197,0.5, getLight()));
        //System.out.println((currentTime/timeForCycle)*24);
        drawMoon(canvas);
    }

    private void drawMoon(Canvas canvas){
        canvas.getGraphicsContext2D().setGlobalAlpha(1-getLight());
        canvas.getGraphicsContext2D().drawImage(moon,canvas.getWidth()/2-300, canvas.getHeight()/2-300, 80, 75);
        canvas.getGraphicsContext2D().setGlobalAlpha(1);
    }

    //fonction non utilisée mais grave belle, svp suprimez pas. Son but est de rendre le ciel orange pour le couché/levé de soleil.
    private double getHue(){
        Color color1 = Color.hsb(197,0.5,1);
        Color color2 = Color.hsb(35,1,1);
        double percent = 1 - getLight();
        double resultRed = color1.getRed() + percent * (color2.getRed() - color1.getRed());
        double resultGreen = color1.getGreen() + percent * (color2.getGreen() - color1.getGreen());
        double resultBlue = color1.getBlue() + percent * (color2.getBlue() - color1.getBlue());
        return (new Color(resultRed, resultGreen, resultBlue, 1)).getHue();
    }

    /**
     * Fonction qui renvoie la luminosité du ciel, est aussi  utilisée pour d'autres choses.
     * @return la luminosité du ciel entre 1 si jour et 0 si nuit.
     */
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
