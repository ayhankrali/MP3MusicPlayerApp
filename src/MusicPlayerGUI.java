import javax.swing.*;

public class MusicPlayerGUI extends JFrame {
    public MusicPlayerGUI() {
        //calls JFrame constructor to configure out and set the title to "Music Player"
        super("Music Player");

        //set the width and height
        setSize(400, 600);

        //end process when app is closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //launch the app at the center of the screen
        setLocationRelativeTo(null);

        //present the app from being resized
        setResizable(false);

        //set logout to null which always to control the (x,y) coordinates of our components
        // also set the height and width
        setLayout(null);

        addGuiComponents();

    }

    private void addGuiComponents() {
        // add toolbar
        addToolbar();
    }

    private void addToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setBounds(0, 0, getWidth(), 20);

        //prevent toolbar from being moved
        toolBar.setFloatable(false);

        //add drop down menu
        JMenuBar menuBar = new JMenuBar();
        toolBar.add(menuBar);

        //now we will add a song menu where we will place the loading song option
        JMenu songMenu = new JMenu("Song");
        menuBar.add(songMenu);

        //add the "Load song" item in the songMenu
        JMenuItem loadSong = new JMenuItem("Load Song");
        songMenu.add(loadSong);

        add(toolBar);
    }


}
