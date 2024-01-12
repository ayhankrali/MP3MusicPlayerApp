import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MusicPlayer extends PlaybackListener {
    private Song currentSong;
    private AdvancedPlayer advancedPlayer;
    private Thread musicThread;


    //pause boolean flag used to indicate whether the player has been paused
    private boolean isPaused;

    //stares in the last frame when the playback is finished
    private int currentFrame;

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

    public void pauseSong() {
        if (advancedPlayer != null) {
            //update isPaused flag
            isPaused = true;

            //then I want to stop the player
            stopSong();
        }
    }

    public void stopSong() {
        if (advancedPlayer != null) {
            advancedPlayer.stop();
            advancedPlayer.close();
            advancedPlayer = null;
        }
    }


    // Play the currently loaded song
    public void playCurrentSong() {

        try {
            // Read mp3 audio data
            FileInputStream fileInputStream = new FileInputStream(currentSong.getFilePath());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            // Create a new advanced player
            advancedPlayer = new AdvancedPlayer(bufferedInputStream);
            advancedPlayer.setPlayBackListener(this);

            // Start music
            startMusicThread();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Start a new thread to play music
    private void startMusicThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (isPaused) {
                        //resume music from last frame
                        advancedPlayer.play(currentFrame,Integer.MAX_VALUE);
                    } else {
                        //play music
                        advancedPlayer.play();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }).start();


    }

    @Override
    public void playbackStarted(PlaybackEvent evt) {
        // this method gets called in the beginning of the song
        System.out.println("Playback Started");
    }

    @Override
    public void playbackFinished(PlaybackEvent evt) {
        //this method gets called when the song finishes or if the player gets closed
        System.out.println("Playback Finished");

        if (isPaused) {
            currentFrame = evt.getFrame();
        }

    }
}
