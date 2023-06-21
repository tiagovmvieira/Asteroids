package main;

// Extending the Polygon class because SpaceShip will be Polygon
import java.awt.Polygon;
public class SpaceShip extends Polygon
{
    // Upper left and hand corner of spaceship
    int uLeftXPos = 500, uLeftYPos = 400;

    // Determines the direction the ship moves
    int xDirection = 0;
    int yDirection = 0;

    // Get the board width and height
    int width = Asteroids.boardWidth;
    int height = Asteroids.boardHeight;

    // Will hold the x & y coordinates for the ship
    public static int[] polyXArray = {500, 527, 500, 508, 500};
    public static int[] polyYArray = {400, 415, 430, 415, 400};

    // Creates a new spaceship
    public SpaceShip(){

        // Creates a polygon by calling the super or parent class of Rock Polygon
        super(polyXArray, polyYArray, 5);

    }

    public void move(){

        // Gets the upper left and top most point for the Polygon

        int uLeftXPos = super.xpoints[0];
        int uLeftYPos = super.ypoints[0];

        // If the ship hits a wall it will go in opposite direction
        if (uLeftXPos < 0 || (uLeftXPos + 25) > width){
            xDirection = -xDirection;
        } else if (uLeftYPos < 0 || (uLeftYPos + 50) > height){
            yDirection = -yDirection;
        }

        // Change the values of the points for the Polygon
        for(int i = 0; i < super.xpoints.length; i++){
            super.xpoints[i] += xDirection;
        }

        for(int i = 0; i < super.ypoints.length; i++){
            super.ypoints[i] += yDirection;
        }

    }

}
