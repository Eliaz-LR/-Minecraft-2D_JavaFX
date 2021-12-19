package model;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URL;
import java.util.Objects;

public class Sound {
    public void playSound(String fileName){
        String path = "C:\\Users\\Eliaz\\Documents\\GitHub\\JAVA GAME\\Minecraft-2D\\code\\resources\\sounds\\stoneBreakingplacing.mp3";
        Media m = new Media(new File(path).toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        mp.play();
    }
}
