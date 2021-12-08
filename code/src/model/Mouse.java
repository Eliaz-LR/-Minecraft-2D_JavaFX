package model;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class Mouse {

    Scene mainJeu;
    public double x;
    public double y;

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
                        x = e.getX();
                        y = e.getY();
                    }
                }
        );
    }
    public void resetCoord(){
        x = 0;
        y = 0;
    }
    public Boolean coordSet(){
        if (x == 0 && y == 0){
            return false;
        }
        return true;
    }
}
