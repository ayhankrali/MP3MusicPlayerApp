import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MusicPlayer {
    private Song currentSong;
    private AdvancedPlayer advancedPlayer;
    private Thread musicThread;

    // Constructor
    public MusicPlayer() {
    }

    // Load a song into the player
    public void loadSong(Song song) {
        currentSong = song;

        if (currentSong != null) {
            playCurrentSong();
        }

    }

    // Play the currently loaded song
    public void playCurrentSong() {
        //if (currentSong != null) {
        try {
            // Read mp3 audio data
            FileInputStream fileInputStream = new FileInputStream(currentSong.getFilePath());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            // Create a new advanced player
            advancedPlayer = new AdvancedPlayer(bufferedInputStream);

            // Start music
            startMusicThread();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //}

    // Start a new thread to play music
    private void startMusicThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //play music
                    advancedPlayer.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }).start();


    }
}
