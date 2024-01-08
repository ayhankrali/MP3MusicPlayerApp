import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MusicPlayer {
    private Song currentSong;

    //use JLayer library to create a Advanced Player obj which will handle a playing the music
    private AdvancedPlayer advancedPlayer;


    //constructor
    public MusicPlayer() {

    }

    public void loadSong(Song song) {
        currentSong = song;

        //play the current song if not null
        if (currentSong != null) {
            playCurrentSong();

        }
    }

    private void playCurrentSong() {
       try{
           //read mp3 audio data
           FileInputStream fileInputStream = new FileInputStream(currentSong.getFilePath());
           BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

           // create a new advanced player
           advancedPlayer = new AdvancedPlayer(bufferedInputStream);

           //start music
           startMusicThread();

       }catch (Exception e){
           e.printStackTrace();

      }
    }

    private void startMusicThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    //play music
                    advancedPlayer.play();
                }catch (Exception e){
                   e.printStackTrace();
                }
            }
        }).start();
    }

}
