package futuremctests;

public class Camera {
    public float camX;
    public float camY;

    private Joueur joueur;

    public Camera(Joueur joueur) {
        this.joueur = joueur;
        this.camX = joueur.x;
        this.camY = joueur.y;
    }

    public void update() {
        this.camX = this.joueur.x;
        this.camY = this.joueur.y;
    }
}
