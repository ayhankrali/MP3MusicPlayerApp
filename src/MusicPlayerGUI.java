import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MusicPlayerGUI extends JFrame {
    //color configurations
    public static final Color FRAME_COLOR = Color.BLACK;
    public static final Color TEXT_COLOR = Color.WHITE;

    private MusicPlayer musicPlayer;

    //allow  us to use file explorer in our app
    private JFileChooser jFileChooser;
    private JLabel songTitle , songArtist ;



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

        musicPlayer = new MusicPlayer();
        jFileChooser = new JFileChooser();


        //set a default path for file explorer
        jFileChooser.setCurrentDirectory(new File("src/assets"));

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
            songTitle = new JLabel("Song Title");
            songTitle.setBounds(0, 285, getWidth() - 10, 30);
            songTitle.setFont(new Font("Dialog", Font.BOLD, 24));
            songTitle.setForeground(TEXT_COLOR);  // Assuming TEXT_COLOR is properly defined
            songTitle.setHorizontalAlignment(SwingConstants.CENTER);
            add(songTitle);  // Add the JLabel

            //song artist
            songArtist = new JLabel("Artist");
            songArtist.setBounds(0, 315, getWidth() - 10, 30);
            songArtist.setFont(new Font("Dialog", Font.PLAIN, 24));
            songArtist.setForeground(TEXT_COLOR);  // Assuming TEXT_COLOR is properly defined
            songArtist.setHorizontalAlignment(SwingConstants.CENTER);
            add(songArtist);  // Add the JLabel

            //playback slider
            JSlider playbackSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
            playbackSlider.setBounds(getWidth() / 2 - 300 / 2, 365, 300, 40);
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


            //play button
            JButton playButton = new JButton(loadImage("src/assets/play.png"));
            playButton.setBorderPainted(false);
            playButton.setBackground(null);
            playbackBtns.add(playButton);

            //pause button
            JButton pauseButton = new JButton((loadImage("src/assets/pause.png")));
            pauseButton.setBorderPainted(false);
            pauseButton.setBackground(null);
            pauseButton.setVisible(false);
            playbackBtns.add(pauseButton);

            //next button
            JButton nextButton = new JButton(loadImage("src/assets/next.png"));
            nextButton.setBorderPainted(false);
            nextButton.setBackground(null);
            playbackBtns.add(nextButton);

            // Add playbackBtns panel
            add(playbackBtns);


        } else {
            System.err.println("Error loading previous button image.");
        }


    }

    //private void updateSongTitleAndArtist


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
        loadSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFileChooser.showOpenDialog(MusicPlayerGUI.this);
                File selectFile = jFileChooser.getSelectedFile();

                if (selectFile != null) {
                    //create a song obj based a selected file
                    Song song = new Song(selectFile.getPath());

                    //load song in music player

                    musicPlayer.loadSong(song);
                }
            }
        });
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
