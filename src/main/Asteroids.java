package main;

// Layout used by the JPanel
import java.awt.BorderLayout;

// Define color of shapes
import java.awt.Color;

// Allows us to draw and render shapes on components
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Will hold all of our Rock objects
import java.util.ArrayList;

// Runs commands after a given delay
import java.util.concurrent.ScheduledThreadPoolExecutor;

// Defines time units. In this cae TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Asteroids extends JFrame {

    public static int boardWidth = 1000;
    public static int boardHeight = 800;

    public static void main(String[] args){

        new Asteroids();

    }


    public Asteroids(){

        // Define the defaults for the JFrame
        this.setSize(boardWidth, boardHeight);
        this.setTitle("Asteroids");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 87)
                {
                    System.out.println("Forward");
                } else if (e.getKeyCode() == 83)
                {
                    System.out.println("Backward");
                }

            }

        });

        GameDrawingPanel gamePanel = new GameDrawingPanel();

        // Make the drawing are take up the rest of the frame
        this.add(gamePanel, BorderLayout.CENTER);

        // Used to execute code after a given delay
        // The attribute is corePoolSize - the number of threads to keep in
        // the pool, if they are idle

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

        executor.scheduleAtFixedRate(new RepaintTheBoard(this), 0, 20, TimeUnit.MILLISECONDS);

        this.setVisible(true);

    }

    // Class RepaintTheBoard implements the runnable interface (thread)
    // By creating this thread we can continually redraw the screen
    // while other code continues to execute
    private class RepaintTheBoard implements Runnable{

        Asteroids theBoard;

        public RepaintTheBoard(Asteroids theBoard){
            this.theBoard = theBoard;
        }

        public void run() {
            theBoard.repaint();

        }
    }


    private class GameDrawingPanel extends JComponent {

        // Holds every Rock created
        private ArrayList<Rock> rocks = new ArrayList<Rock>();

        // Get the original x & y points for the polygon (static for every Rock)
        int[] polyXArray = Rock.sPolyXArray;
        int[] polyYArray = Rock.sPolyYArray;

        // Create a SpaceShip
        SpaceShip theShip = new SpaceShip();

        // Gets the game board height and weight
        int width = Asteroids.boardWidth;
        int height = Asteroids.boardHeight;

        // Creates 50 Rock objects and stores them in the ArrayList
        public GameDrawingPanel() {

            for (int i = 0; i < 50; i++) {

                // Find a random x & y starting point to place the Rock
                // The -40 part is on there to keep the Rock on the screen
                int randomStartXPos = (int) (Math.random() * (Asteroids.boardWidth - 40) + 1);
                int randomStartYPos = (int) (Math.random() * (Asteroids.boardHeight - 40) + 1);

                Rock rock = new Rock(Rock.getPolyXArray(randomStartXPos), Rock.getPolyYArray(randomStartYPos),
                        13, randomStartXPos, randomStartYPos);

                rocks.add(rock);
            }

        }

        public void paint(Graphics g) {

            // Allow us to make many settings changes in regard to graphics
            Graphics2D graphicSettings = (Graphics2D) g;

            // Draw a black background that is as big as the game board
            graphicSettings.setColor(Color.BLACK);
            graphicSettings.fillRect(0, 0, getWidth(), getHeight());

            // Set rendering rules
            graphicSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // Set the drawing color to white
            graphicSettings.setPaint(Color.WHITE);

            // Cycle through all the Rock objects
            for (Rock rock : rocks) {
                // Move the Rock polygon
                rock.move();

                // Stroke the polygon Rock on the screen
                graphicSettings.draw(rock);
            }

            theShip.move();
            graphicSettings.draw(theShip);

        }

    }

}
