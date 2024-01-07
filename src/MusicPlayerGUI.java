import javax.swing.*;

public class MusicPlayerGUI extends JFrame {
     public MusicPlayerGUI(){
         //calls JFrame constructor to configure out and set the title to "Music Player"
         super("Music Player");

         //set the width and height
         setSize(400,600);

         //end process when app is closed
         setDefaultCloseOperation(EXIT_ON_CLOSE);

         //launch the app at the center of the screen
         setLocationRelativeTo(null);

         //present the app from being resized
         setResizable(false);

         //set logout to null which always to control the (x,y) coordinates of our components
         // also set the height and width
         setLayout(null);

     }
}
