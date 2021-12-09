package model;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class deplacerJoueur {
    private Joueur joueur;
    public Boolean IsBlockUpEmpty;
    public Boolean IsBlockDownEmpty;
    public Boolean IsBlockRightEmpty;
    public Boolean IsBlockLeftEmpty;

    Scene mainJeu;
    public ArrayList<String> input;
    public deplacerJoueur(Joueur joueur, Scene mainJeu){
        this.joueur = joueur;
        this.mainJeu = mainJeu;
        input = new ArrayList<String>();
        init();
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
            if(IsBlockLeftEmpty){
                if(joueur.Xspeed < -10){
                    joueur.Xspeed +=1;
                }
            }
        }
        else if(input.contains("RIGHT")){
            if (joueur.Xspeed < 10) {
                joueur.Xspeed += 1;
            }
            joueur.img = joueur.imgD;
            if(IsBlockRightEmpty){
                if (joueur.Xspeed < 10)
                joueur.Xspeed +=1;
            }
        }
        else{
            joueur.Xspeed = 0;
        }
        if(input.contains("UP")){
            if(!IsBlockDownEmpty){
                joueur.Yspeed = -15;
            }
            if(!IsBlockUpEmpty){
                joueur.Yspeed = 0;
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
        if(!IsBlockRightEmpty){
            if(joueur.Xspeed > 0){
                joueur.Xspeed = 0;
            }

        }
        if(!IsBlockLeftEmpty){
            if(joueur.Xspeed < 0){
                joueur.Xspeed = 0;
            }
        }
    }
    private void Gravity(){
        if(joueur.Yspeed < 15){
            joueur.Yspeed += 1;
        }
    }
}