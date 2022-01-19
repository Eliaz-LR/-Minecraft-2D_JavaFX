package model;

import javafx.scene.Scene;
import java.util.ArrayList;

/**
 * Classe gérant le déplacement du Joueur.
 */
public class DeplacerJoueur {
    private Scene mainJeu;
    private Joueur joueur;
    public Boolean IsBlockUpEmpty;
    public Boolean IsBlockDownEmpty;
    public Boolean IsBlockRightEmpty;
    public Boolean IsBlockLeftEmpty;
    public Gravity gravity;

    public ArrayList<String> input;
    public DeplacerJoueur(Joueur joueur, Scene mainJeu){
        this.joueur = joueur;
        this.mainJeu = mainJeu;
        input = new ArrayList<>();
        gravity = new Gravity(joueur);
        init();
    }

    private void init(){
        mainJeu.setOnKeyPressed(
                event -> {
                    String code = event.getCode().toString();
                    if (!input.contains(code)) {
                        input.add(code);
                    }
                }
        );
        mainJeu.setOnKeyReleased(
                event -> {
                    String code = event.getCode().toString();
                    input.remove(code);
                });
    }
    public void deplacerJoueur(){
        if(input.contains("LEFT") || input.contains("Q")){
            joueur.isFacingRight.set(false);
            if (joueur.Xspeed > -10){
                joueur.Xspeed -= 1;
            }

            if(IsBlockLeftEmpty){
                if(joueur.Xspeed < -10){
                    joueur.Xspeed +=1;
                }
            }
        }
        else if(input.contains("RIGHT") || input.contains("D")){
            joueur.isFacingRight.set(true);
            if (joueur.Xspeed < 10) {
                joueur.Xspeed += 1;
            }

            if(IsBlockRightEmpty){
                if (joueur.Xspeed < 10)
                joueur.Xspeed +=1;
            }
        }
        else{
            joueur.Xspeed = 0;
        }
        if(input.contains("UP") || input.contains("SPACE")){
            if(!IsBlockDownEmpty){
                joueur.Yspeed = -15;
            }
            //check que le bloc du dessus n'est pas plein pour pouvoir jump
            if(!IsBlockUpEmpty){
                joueur.Yspeed = 0;
            }
        }
        gravity.gravityPlayer();
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
}