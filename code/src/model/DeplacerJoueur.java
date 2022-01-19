package model;

import javafx.scene.Scene;
import java.util.ArrayList;

/**
 * Classe gérant le déplacement du Joueur.
 */
public class DeplacerJoueur {
    private final Scene mainJeu;
    private final Joueur joueur;
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
            if (joueur.xSpeed > -10){
                joueur.xSpeed -= 1;
            }

            if(IsBlockLeftEmpty){
                if(joueur.xSpeed < -10){
                    joueur.xSpeed +=1;
                }
            }
        }
        else if(input.contains("RIGHT") || input.contains("D")){
            joueur.isFacingRight.set(true);
            if (joueur.xSpeed < 10) {
                joueur.xSpeed += 1;
            }

            if(IsBlockRightEmpty){
                if (joueur.xSpeed < 10)
                joueur.xSpeed +=1;
            }
        }
        else{
            joueur.xSpeed = 0;
        }
        if(input.contains("UP") || input.contains("SPACE")){
            if(!IsBlockDownEmpty){
                joueur.ySpeed = -15;
            }
            //check que le bloc du dessus n'est pas plein pour pouvoir jump
            if(!IsBlockUpEmpty){
                joueur.ySpeed = 0;
            }
        }
        gravity.gravityPlayer();
        checkCollisions();

        joueur.x = joueur.x + joueur.xSpeed;
        joueur.y = joueur.y + joueur.ySpeed;
    }

    private void checkCollisions(){
        if(!IsBlockDownEmpty){
            if (joueur.ySpeed > 0){
                joueur.ySpeed = 0;
            }
        }
        if(!IsBlockRightEmpty){
            if(joueur.xSpeed > 0){
                joueur.xSpeed = 0;
            }

        }
        if(!IsBlockLeftEmpty){
            if(joueur.xSpeed < 0){
                joueur.xSpeed = 0;
            }
        }
    }
}