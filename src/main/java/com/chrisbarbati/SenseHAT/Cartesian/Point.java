package com.chrisbarbati.SenseHAT.Cartesian;

/**
 * Class representing a point in two-dimensional space
 */

public class Point {
    private double x;
    private double y;

    /**
     * Constructor for a point
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Getters and setters

    /**
     * Getter for the x-coordinate of the point
     * @return the x-coordinate of the point
     */
    public double getX() {
        return x;
    }

    /**
     * Setter for the x-coordinate of the point
     * @param x the new x-coordinate of the point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Getter for the y-coordinate of the point
     * @return the y-coordinate of the point
     */
    public double getY() {
        return y;
    }

    /**
     * Setter for the y-coordinate of the point
     * @param y the new y-coordinate of the point
     */
    public void setY(double y) {
        this.y = y;
    }


}
