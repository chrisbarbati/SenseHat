package com.chrisbarbati.SenseHAT.Builder;

import com.chrisbarbati.SenseHAT.Cartesian.*;

/**
 * Builder class for Line objects
 */

public class LineBuilder {

    /**
     * Default constructor
     */
    public LineBuilder(){

    }

    /**
     * Construct a new Line object given two points
     * @param x1 the x-coordinate of the first point
     * @param y1 the y-coordinate of the first point
     * @param x2 the x-coordinate of the second point
     * @param y2 the y-coordinate of the second point
     * @return new Line object
     */

    public Line getLine(Double x1, Double y1, Double x2, Double y2){

        PointBuilder pb = new PointBuilder();

        Point start = pb.getPoint(x1, y1);
        Point end = pb.getPoint(x2, y2);

        return new Line(start, end);
    }
}
