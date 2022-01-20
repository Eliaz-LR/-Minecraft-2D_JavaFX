package model;


/**
 *  Classe simulant l'effet de la gravité sur le joueur
 *
 */
public class Gravity {
    private final Joueur joueur;

    /**
     *  Constructeur prenant une instance de Joueur
     * @param joueur Instance du joueur.
     */
    public Gravity(Joueur joueur){
        this.joueur = joueur;
    }

    /**
     *  Déplace le joueur vers le bas lorsque sa vitesse est inférieur à une certaine valeur
     */
    public void gravityPlayer(){
        if(joueur.ySpeed < 15){
            joueur.ySpeed +=1;
        }
    }
}
