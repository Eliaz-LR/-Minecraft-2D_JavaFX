package model;

public class Gravity {
    public Joueur joueur;

    public Gravity(Joueur joueur){
        this.joueur = joueur;
    }

    public void gravityPlayer(){
        if(joueur.Yspeed < 15){
            joueur.Yspeed +=1;
        }
    }
}
