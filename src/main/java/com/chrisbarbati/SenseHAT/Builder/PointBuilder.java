package com.chrisbarbati.SenseHAT.Builder;

import com.chrisbarbati.SenseHAT.Cartesian.Point;

/**
 * Builder class for Point objects
 */

public class PointBuilder {

    /**
     * Default constructor
     */
    public PointBuilder(){

    }

    /**
     * Construct a new Point object given x and y values
     * @param x
     * @param y
     * @return new Point object
     */
    public Point getPoint(Double x, Double y){
        return new Point(x, y);
    }
}
