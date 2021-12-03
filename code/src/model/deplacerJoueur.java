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
        joueur.x = 100;
        joueur.y = 100;
    }
    public void deplacerJoueur(){
        if(input.contains("LEFT")){
            joueur.x -= 5;
            joueur.img = joueur.imgG;
        }
        if(input.contains("RIGHT")){
            joueur.x += 5;
            joueur.img = joueur.imgD;
        }
        if(input.contains("UP")){
            joueur.y -= 5;
        }
        if(input.contains("DOWN")){
            joueur.y += 5;
        }
    }



}