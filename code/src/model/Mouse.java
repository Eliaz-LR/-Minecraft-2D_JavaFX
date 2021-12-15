package model;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Mouse {

    Scene mainJeu;
    public double ClickedX;
    public double ClickedY;
    public double X;
    public double Y;
    public MouseButton mouseButton;


    public Mouse(Scene mainJeu) {
        this.mainJeu = mainJeu;
        init();
    }

    private void init() {
        mainJeu.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        mouseButton = e.getButton();
                        ClickedX = e.getX();
                        ClickedY = e.getY();
                    }
                }
        );

        mainJeu.setOnMouseMoved(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        X = e.getX();
                        Y = e.getY();
                    }
                }
        );
    }
    public void resetCoord(){
        ClickedX = 0;
        ClickedY = 0;
    }
    public Boolean isCoordSet(){
        if (ClickedX == 0 && ClickedY == 0){
            return false;
        }
        return true;
    }
}
