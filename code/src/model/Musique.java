package model;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * Permet de jouer une musique lors du lancement d'un monde
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
        String musicFile = "/music/sweden.wav";     // For example
        Media sound = null;
        try {
            sound = new Media(getClass().getResource(musicFile).toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void stopSound(){
        musiqueThread.stop();
    }
}
