package model;

import javafx.scene.Scene;
import java.util.ArrayList;

/**
 * Classe gérant le déplacement du Joueur.
 */
public class DeplacerJoueur {
    private final Scene mainJeu;
    /**
     * Joueur à déplacer.
     */
    private final Joueur joueur;
    /**
     * Booléen pour savoir si le bloc du dessus est vide (ce qui corresponde à un bloc d'air).
     */
    public Boolean IsBlockUpEmpty;
    /**
     * Booléen pour savoir si le bloc du dessous est vide (ce qui corresponde à un bloc d'air).
     */
    public Boolean IsBlockDownEmpty;
    /**
     * Booléen pour savoir si le bloc à droite est vide (ce qui corresponde à un bloc d'air).
     */
    public Boolean IsBlockRightEmpty;
    /**
     * Booléen pour savoir si le bloc à gauche est vide (ce qui corresponde à un bloc d'air).
     */
    public Boolean IsBlockLeftEmpty;
    /**
     * Instance de la classe Gravité.
     */
    public Gravity gravity;
    /**
     * Tableau contenant les touches appuyées.
     */
    public ArrayList<String> input;


    public DeplacerJoueur(Joueur joueur, Scene mainJeu){
        this.joueur = joueur;
        this.mainJeu = mainJeu;
        input = new ArrayList<>();
        gravity = new Gravity(joueur);
        init();
    }

    /**
     * Réupère la dernière touche appuyée et l'ajoute au tableau input.
     */
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

    /**
     * En fonction des touches appuyées, déplace le joueur dans la bonne direction.
     */
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

    /**
     * Réduit la vitesse à 0 si il rentre en collision avec un joueur.
     */
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