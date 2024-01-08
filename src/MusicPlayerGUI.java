import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MusicPlayerGUI extends JFrame {
    //color configurations
    public static final Color FRAME_COLOR = Color.BLACK;
    public static final Color TEXT_COLOR = Color.WHITE;

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

        // change the frame color
        getContentPane().setBackground(FRAME_COLOR);

        addGuiComponents();

    }

    private void addGuiComponents() {
        // Add toolbar
        addToolbar();

        // Load record image
        ImageIcon imageIcon = loadImage("src/assets/record.png");

        // Check if the imageIcon is not null
        if (imageIcon != null) {
            System.out.println("Image loaded successfully.");

            // Create the JLabel with the loaded image
            JLabel songImage = new JLabel(imageIcon);
            songImage.setBounds(0, 50, getWidth() - 20, 225);
            add(songImage);

            // Song title
            JLabel songTitle = new JLabel("Song Title");
            songTitle.setBounds(0, 285, getWidth() - 10, 30);
            songTitle.setFont(new Font("Dialog", Font.BOLD, 24));
            songTitle.setForeground(TEXT_COLOR);  // Assuming TEXT_COLOR is properly defined
            songTitle.setHorizontalAlignment(SwingConstants.CENTER);
            add(songTitle);  // Add the JLabel

            //song artist
            JLabel songArtist = new JLabel("Artist");
            songArtist.setBounds(0,315,getWidth()-10,30);
            songArtist.setFont(new Font("Dialog", Font.PLAIN, 24));
            songArtist.setForeground(TEXT_COLOR);  // Assuming TEXT_COLOR is properly defined
            songArtist.setHorizontalAlignment(SwingConstants.CENTER);
            add(songArtist);  // Add the JLabel

            //playback slider
            JSlider playbackSlider = new JSlider(JSlider.HORIZONTAL,0,100,0);
            playbackSlider.setBounds(getWidth()/2 - 300/2, 365, 300, 40);
            playbackSlider.setBackground(null);
            add(playbackSlider);

            //playback buttons(i.e , previous , play , next)
            addPlaybackBtns();
            
        } else {
            System.err.println("Error loading image.");
        }
    }

    private void addPlaybackBtns() {
        JPanel playbackBtns = new JPanel();
        playbackBtns.setBounds(0, 435, getWidth() - 10, 80);
        playbackBtns.setBackground(null);

        // Previous button
        ImageIcon prevButtonIcon = loadImage("src/assets/previous.png");

        if (prevButtonIcon != null) {
            JButton prevButton = new JButton(prevButtonIcon);
            prevButton.setBorderPainted(false);
            prevButton.setBackground(null);
            playbackBtns.add(prevButton);
        } else {
            System.err.println("Error loading previous button image.");
        }

        // Add playbackBtns panel to your frame or panel
        add(playbackBtns);
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

        //add the playlist menu
        JMenu playListMenu = new JMenu("PlayList");
        menuBar.add(playListMenu);

        // add the items to playlist menu
        JMenuItem createPlayList = new JMenuItem("Create PlayList");
        playListMenu.add(createPlayList);

        JMenuItem loadPlayList = new JMenuItem("Load PlayList");
        playListMenu.add(loadPlayList);

        add(toolBar);
    }

    private ImageIcon loadImage(String imagePath) {
        try {
            //read image file from the given path
            BufferedImage image = ImageIO.read(new File(imagePath));

            //return an image icon so that our component can render the image
            return new ImageIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //could not find resource
        return null;
    }

}
