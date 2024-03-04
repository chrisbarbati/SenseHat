package com.chrisbarbati.SenseHAT.Cartesian;

/**
 * Class to represent a line segment in two-dimensional space
 */

public class Line {
    private Point start; //One point of line
    private Point end; //Other point of line
    private Double slope; //Slope of line
    private Double b; //Y-intercept of line

    /**
     * Constructor for a line segment where only the two points are known
     * 
     * @param start the first endpoint of the line segment
     * @param end the second endpoint of the line segment
     */
    public Line(Point start, Point end) {
        setStart(start);
        setEnd(end);
        setSlope(calculateSlope(start, end));
        setB(calculateYIntercept(start, slope));
    }

    /**
     * Full constructor where both points, slope, and y-intercept are known
     * @param start the first endpoint of the line segment
     * @param end the second endpoint of the line segment
     * @param slope the slope of the line segment
     * @param b 
     */
    public Line(Point start, Point end, Double slope, Double b) {
        setStart(start);
        setEnd(end);
        setSlope(slope);
        setB(b);
    }

    /**
     * Calculate the slope of the line with two known points
     * 
     * slope = (y2-y1)/(x2-x1)
     * 
     * @param start
     * @param end
     * @return the slope of the line
     */
    private Double calculateSlope(Point start, Point end){
        Double slope = (end.getY() - start.getY()) / (end.getX() - start.getX());
        return slope;
    }

    /**
     * Calculate the y-intercept of the line with a known slope and point
     * 
     * y-intercept = y - (slope * x)
     * 
     * @param start
     * @param slope
     * @return the y-intercept of the line
     */
    private Double calculateYIntercept(Point start, Double slope){
        Double b = start.getY() - (slope * start.getX());
        return b;
    }

    /**
     * Find the x-coordinate of the point on the line given a y-coordinate
     * @param y
     * @return the x-coordinate of the point on the line
     */
    public Double findXGivenY(Double y){
        return (y - b) / slope;
    };

    /**
     * Find the y-coordinate of the point on the line given an x-coordinate
     * @param x
     * @return the y-coordinate of the point on the line
     */
    public Double findYGivenX(Double x){
        return (slope * x) + b;
    };


    //Getters and setters

    /**
     * Getter for the first endpoint of the line segment
     * @return the first endpoint of the line segment
     */

    public Point getStart() {
        return start;
    }

    /**
     * Setter for the first endpoint of the line segment
     * @param start the new first endpoint of the line segment
     */

    public void setStart(Point start) {
        this.start = start;
    }

    /**
     * Getter for the second endpoint of the line segment
     * @return the second endpoint of the line segment
     */

    public Point getEnd() {
        return end;
    }

    /**
     * Setter for the second endpoint of the line segment
     * @param end the new second endpoint of the line segment
     */

    public void setEnd(Point end) {
        this.end = end;
    }

    /**
     * Getter for the slope of the line segment
     * @return the slope of the line segment
     */

    public Double getSlope() {
        return slope;
    }

    /**
     * Setter for the slope of the line segment
     * @param slope the new slope of the line segment
     */

    public void setSlope(Double slope) {
        this.slope = slope;
    }

    /**
     * Getter for the y-intercept of the line segment
     * @return the y-intercept of the line segment
     */

    public Double getB() {
        return b;
    }

    /**
     * Setter for the y-intercept of the line segment
     * @param b the new y-intercept of the line segment
     */

    public void setB(Double b) {
        this.b = b;
    }
    
}
