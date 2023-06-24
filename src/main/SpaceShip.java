package main;

import java.awt.*;
public class SpaceShip extends Polygon
{
    // Determines the speed the ship moves
    private double xVelocity = 0, yVelocity = 0;

    // Get the board width and height
    int gBWidth = Asteroids.boardWidth;
    int gBHeight = Asteroids.boardHeight;

    // Center of spaceship
    private double centerX = gBWidth /2, centerY = gBHeight /2;

    // Will hold the x & y coordinates for the ship
    // Everything is based on coordinates from the center
    // It is done this way so that rotation works properly
    public static int[] polyXArray = {-13, 14, -13, -5, -13};
    public static int[] polyYArray = {-15, 0, 15, 0, -15};

    // Width and height of ship
    private int shipWidth = 27, shipHeight = 30;

    // Upper left hand corner of spaceship
    private double uLeftXPos = getXCenter() + this.polyXArray[0];
    private double uLeftYPos = getYCenter() + this.polyYArray[0];

    // Defines if the ship should rotate
    private double rotationAngle = 0, movingAngle = 0;

    // SpaceShip constructor
    public SpaceShip(){

        // Creates a Polygon by calling the super or parent class Polygon
        super(polyXArray, polyYArray, 5);
    }

    // Gets & sets the values for the x & y center of ship
    public double getXCenter(){ return centerX; }
    public double getYCenter(){ return centerY; }


    public void setXCenter(double xCent){ this.centerX = xCent; }
    public void setYCenter(double yCent){ this.centerY = yCent; }


    public void increaseXPos(double incAmount){ this.centerX += incAmount; }
    public void increaseYPos(double incAmount){ this.centerY += incAmount; }


    // Gets & sets the x & y for upper left hand corner of ship
    public double getuLeftXPos(){ return uLeftXPos; }
    public double getuLeftYPos(){ return uLeftYPos; }


    public void setuLeftXPos(double xULPos){ this.uLeftXPos = xULPos; }
    public void setuLeftYPos(double yULPos){ this.uLeftYPos = yULPos; }


    // Gets the ShipWidth
    public int getShipWidth() { return shipWidth; }
    public int getShipHeight() { return shipHeight; }


    // Gets, sets, increases and decreases the ship velocity
    public double getXVelocity(){ return xVelocity; }
    public double getYVelocity(){ return yVelocity; }


    public void setXVelocity(double xVel){ this.xVelocity = xVel; }
    public void setYVelocity(double yVel){ this.yVelocity = yVel; }


    public void increaseXVelocity(double xVelInc){ this.xVelocity += xVelInc; }
    public void increaseYVelocity(double yVelInc){ this.yVelocity += yVelInc; }


    public void decreaseXVelocity(double xVelDec){ this.xVelocity -= xVelDec; }
    public void decreaseYVelocity(double yVelDec){ this.yVelocity -= yVelDec; }


    // Set and increase the ship movement angle
    public double getMovingAngle(){ return movingAngle; }
    public void setMovingAngle(double moveAngle){ this.movingAngle = moveAngle; }


    public void increaseMovingAngle(double moveAngleInc){ this.movingAngle += moveAngleInc; }


    // Calculate the ship angle of movement
    public double shipXMoveAngle(double xMoveAngle){

        return (double) (Math.cos(xMoveAngle + Math.PI / 180));
    }


    public double shipYMoveAngle(double yMoveAngle){

        return (double) (Math.sin(yMoveAngle + Math.PI / 180));
    }


    // Gets & increases or decreases the rotation angle of the ship
    public double getRotationAngle(){ return rotationAngle; }

    public void increaseRotationAngle(){

        if(getRotationAngle() >= 355){ rotationAngle = 0;}

        else { rotationAngle += 5; }

    }


    public void decreaseRotationAngle(){

        if(getRotationAngle() < 0){ rotationAngle = 355;}

        else { rotationAngle -= 5; }
    }


    // Artificial rectangle that is used for collision detection
    public Rectangle getBounds(){

        return new Rectangle(getShipWidth() - 14, getShipHeight() - 15, getShipWidth(), getShipHeight());
    }


    public void move(){

        // Increase the x origin value based on current velocity
        this.increaseXPos(this.getXVelocity());

        // If the ship goes off the board flip it to the other side of the board
        /*
        if(this.getXCenter() < 0){
            this.setXCenter(gBWidth);
        } else if(this.getXCenter() > gBWidth){
            this.setXCenter(0);
        }
         */

        // Increase the y origin value based on current velocity
        this.increaseYPos(this.getYVelocity());

        // If the ship goes off the board flip it to the other side of the board
        if(this.getYCenter() < 0){
            this.setYCenter(gBHeight);
        } else if(this.getYCenter() > gBHeight){
            this.setYCenter(0);
        }

    }

}
