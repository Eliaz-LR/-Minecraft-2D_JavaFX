package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import view.PlayerView;

import java.util.ArrayList;

public class deplacerJoueur {
    private Joueur joueur;
    private PlayerView playerView;
    public Boolean IsBlockDownEmpty;
    Scene mainJeu;
    public ArrayList<String> input;
    public deplacerJoueur(Joueur joueur, Scene mainJeu){
        this.joueur = joueur;
        this.mainJeu = mainJeu;
        input = new ArrayList<String>();
        init();
    }

    public deplacerJoueur(Joueur joueur, Scene mainJeu, PlayerView playerView){
        super();
        this.playerView = playerView;
    }

    private void init(){
        mainJeu.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        if (!input.contains(code)) {
                            input.add(code);
                        }
                    }
                }
        );
        mainJeu.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        input.remove(code);
                    }
                });
        joueur.x = 250;
        joueur.y = 0;
    }
    public void deplacerJoueur(){
        if(input.contains("LEFT")){
            if (joueur.Xspeed > -10){
                joueur.Xspeed -= 1;
            }
            joueur.img = joueur.imgG;
        }
        else if(input.contains("RIGHT")){
            if (joueur.Xspeed < 10) {
                joueur.Xspeed += 1;
            }
            joueur.img = joueur.imgD;
        }
        else{
            joueur.Xspeed = 0;
        }
        if(input.contains("UP")){
            if(!IsBlockDownEmpty){
                joueur.Yspeed = -20;
            }
        }
        Gravity();
        checkCollisions();
        joueur.x = joueur.x + joueur.Xspeed;
        joueur.y = joueur.y + joueur.Yspeed;
    }

    private void checkCollisions(){
        if(!IsBlockDownEmpty){
            if (joueur.Yspeed > 0){
                joueur.Yspeed = 0;
            }
        }
    }
    private void Gravity(){
        if(joueur.Yspeed < 15){
            joueur.Yspeed += 1;
        }
    }
}