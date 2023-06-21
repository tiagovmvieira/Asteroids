package main;

// Extending the Polygon class because Rocks will be Polygons
import java.awt.Polygon;

public class Rock extends Polygon
{
    // Upper left hand corner of the Polygon
    int uLeftXPos, uLeftYPos;

    // Used to change the direction of the asteroid when
    // it hits something and determines how fast it moves
    int xDirection = 1;
    int yDirection = 1;

    // Get the board width and height
    int width = Asteroids.boardWidth;
    int heigth = Asteroids.boardHeight;

    // Will hold the x & y coordinates for the Polygons len(polyXArray) = len(sPolyXArray)
    int[] polyXArray, polyYArray;

    // x & y positions available for other methods
    public static int[] sPolyXArray = {10,17,26,34,27,36,26,14,8,1,5,1,10};
    public static int[] sPolyYArray = {0,5,1,8,13,20,31,28,31,22,16,7,0};

    // Creates a new asteroid
    public Rock(int[] polyXArray, int[] polyYArray, int pointsInPoly, int randomStartXPos, int randomStartYPos){
        // Creates a Polygon by calling the super or parent class of Rock Polygon
        super(polyXArray, polyYArray, pointsInPoly);

        // Randomly generate a speed for the Polygon
        this.xDirection = (int) (Math.random() * 4 + 1);
        this.yDirection = (int) (Math.random() * 4 + 1);

        // Holds the starting x & y position for the Rock
        this.uLeftYPos = randomStartYPos;
        this.uLeftXPos = randomStartXPos;

    }

    public void move(){

        // Get the upper left and top most point for the Polygon

        int uLeftXpos = super.xpoints[0];
        int uLeftYpos = super.ypoints[0];

        // If the Rock hits a wall it will go in the opposite direction
        if (uLeftXpos < 0 || (uLeftXpos + 25) > width){
            xDirection = -xDirection;
        } else if (uLeftYpos < 0 || (uLeftYpos + 50) > heigth){
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

    // public method available for creating Polygon x point arrays
    public static int[] getPolyXArray(int randomStartXPos){

        // Clones the array so that the original shape isn't changed for the asteroid

        int[] tempPolyXArray = (int[]) sPolyXArray.clone();

        for(int i = 0; i < tempPolyXArray.length; i++){

            tempPolyXArray[i] += randomStartXPos;
        }

        return tempPolyXArray;
    }

    // public method available for creating Polygon y point arrays
    public static int[] getPolyYArray(int randomStartYPos){

        // Clones the array so that the original shape isn't changed for the asteroid
        int[] tempPolyYArray = (int[]) sPolyYArray.clone();

        for(int i = 0; i < tempPolyYArray.length; i++){

            tempPolyYArray[i] += randomStartYPos;
        }

        return tempPolyYArray;
    }

}
