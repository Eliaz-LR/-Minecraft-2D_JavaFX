package model;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * Permet de joueur une musique lors du lancement d'un monde
 */
public class Musique {
    /**
     * Thread dédié à la musique
     */
    private Thread musiqueThread;

    /**
     * Instancie le thread, récupère la musique et la lance
     */
    public void playSound(){
        String musicFile = "code/resources/music/sweden.wav";     // For example
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
            musiqueThread  = new Thread(() -> {
                while (true){
                    mediaPlayer.play();
                }
            });
            musiqueThread.start();
    }

    /**
     * Stop le thread de la musique
     */
    public void stopMusique(){
        musiqueThread.stop();
    }
}
