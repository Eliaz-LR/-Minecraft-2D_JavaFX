package model;

/**
 * Classe vérifiant les collisions entre le joueur et les blocs.
 */
public class Collisionneur {

    /**
     * Vérifie si le joueur rentre en collision avec des blocs autour de lui.
     * @param grid Grid de bloc
     * @param deplacerJoueur Classe permettant de déplacer le joueur.
     * @param coo_joueur_dans_monde Coordonnées du joueur.
     */
    public  static void checkBlocks(DrawGrid grid, DeplacerJoueur deplacerJoueur, Coordonnees coo_joueur_dans_monde){
        //verification pour les blocs en DESSOUS
        deplacerJoueur.IsBlockDownEmpty = grid.monde.getType((int) coo_joueur_dans_monde.x, 1 + (int) coo_joueur_dans_monde.y).toString().equals("Air") || grid.monde.getType((int) coo_joueur_dans_monde.x, 1 + (int) coo_joueur_dans_monde.y).toString().equals("Tronc");

        //vérification pour les blocs au-DESSUS
        deplacerJoueur.IsBlockUpEmpty = grid.monde.getType((int) coo_joueur_dans_monde.x, (int) coo_joueur_dans_monde.y - 1).toString().equals("Air");

        //vérification pour les blocs à GAUCHE (bloc du bas puis du haut)
        deplacerJoueur.IsBlockLeftEmpty = grid.monde.getType((int) (coo_joueur_dans_monde.x - 0.5), (int) coo_joueur_dans_monde.y).toString().equals("Air") && grid.monde.getType((int) (coo_joueur_dans_monde.x - 0.5), (int) coo_joueur_dans_monde.y - 1).toString().equals("Air");

        //vérification pour les blocs à DROITE (bloc du bas puis du haut)
        deplacerJoueur.IsBlockRightEmpty = grid.monde.getType((int) (coo_joueur_dans_monde.x + 0.5), (int) coo_joueur_dans_monde.y).toString().equals("Air") && grid.monde.getType((int) (coo_joueur_dans_monde.x + 0.5), (int) (coo_joueur_dans_monde.y - 1)).toString().equals("Air");
    }

}
