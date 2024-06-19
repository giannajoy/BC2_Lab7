/*
 * Bootcamp 2 Code
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package gee_lab7_local;

/**
 * Class to hold a 2-d point in space. Both Cartesian and Polar coordinates
 * are supported.
 * @author bc2code
 */
public class Point {
    private double x;
    private double y;
    
    /**
     * Default Point is at (1,1)
     */
    public Point() {
        this(1.0, 1.0);
    }
    
    /**
     * Constructor that sets x and y.
     * @param x initial x coordinate
     * @param y initial y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Get the x coordinate in Cartesian system.
     * @return x coordinate 
     */
    public double getX() {
        return x;
    }
    
    /**
     * Get the y coordinate in Cartesian system.
     * @return y coordinate 
     */
    public double getY() {
        return y;
    }
    
    /**
     * Set the x coordinate in Cartesian system.
     * @param x x coordinate
     */
    public void setX(double x) {
        this.x = x;
    }
    
    /**
     * Set the y coordinate in Cartesian system.
     * @param y y coordinate
     */
    public void setY(double y) {
        this.y = y;
    }
    
    public void setXY(double x, double y) {
        setX(x);
        setY(y);
    }
    
    /**
     * Get the distance coordinate in Polar system.
     * @return distance from origin
     */
    public double getR() {
        return Math.sqrt(x * x + y * y);
    }
    
    /**
     * Get the angle coordinate in Polar system.
     * @return angle coordinate (counterclockwise from East-pointing axis 
     *         in radians)
     */
    public double getTheta() {
        return Math.atan2(y, x);
    }
    
    /**
     * Set the distance coordinate in Polar system.
     * @param r distance from origin
     */
    public void setR(double r) {
        double theta = getTheta();
        x = r * Math.cos(theta);
        y = r * Math.sin(theta);
    }
    
    /**
     * Set the angle coordinate in Polar system.
     * The angle is reduced to the range [0:2pi).
     * @param thetaCoord angle in radians (counterclockwise from East)
     */
    public void setTheta(double thetaCoord) {
        double r = getR();
        x = r * Math.cos(thetaCoord);
        y = r * Math.sin(thetaCoord);
    }
    
    /**
     * Set both the distance and angle coordinates in Polar system.
     * @param r distance coordinate
     * @param theta angle coordinate
     */
    public void setRTheta(double r, double theta) {
        setR(r);
        setTheta(theta);
    }
    
    /**
     * Convert to a string
     * @return string representation of point (using Cartesian)
     */
    @Override
    public String toString() {
        return "Point(x=" + x + ",y=" + y + ")";
    }
    
    /**
     * Compare this point to another.
     * @param other point to compare to
     * @return true if the other point is identical, false otherwise
     */
    public boolean equals(Point other) {
        return x == other.x && y == other.y;
    }
    
    /**
     * Copy this point to a new Point object.
     * @return a new point which is equal to this point
     */
    public Point copy() {
        Point copyObject = new Point(x, y);
        return copyObject;
    }
    
    /**
     * Get the distance between this point and another.
     * @param other point to get distance to
     * @return distance to other
     */
    public double distance(Point other) {
        double dx = other.x - this.x;  // use of this is optional
        double dy = other.y - this.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    /**
     * Convenience method will take in other point as (x,y) and 
     * then calculate distance,
     * @see  #distance(Point) 
     * @param otherx x coord of point to get distance to
     * @param othery y coord of point to get distance to
     * @return distance to other
     */
    public double distance(double otherx, double othery) {
        return this.distance(new Point(otherx, othery));
    }
    
    /**
     * Static method to get distance between two points.
     * @param a first point
     * @param b second point
     * @return distance from a to b
     */
    public static double distance(Point a, Point b) {
        return a.distance(b);
    }
    
    /**
     * Static method to get distance between two points specified
     * by their (x,y) coordinates.
     * @param ax x coord of first point
     * @param ay y coord of first point
     * @param bx x coord of second point
     * @param by y coord of second point
     * @return distance from a to b
     */
    public static double distance(double ax, double ay, double bx, double by) {
        return Point.distance(new Point(ax, ay), new Point(bx, by));
    }
    
    /**
     * Move this point the given distance in the given direction.
     * @param distance units to move
     * @param angle direction to move (radians counterclockwise from East)
     */
    public void move(double distance, double angle) {
        Point temp = Point.pointFromPolar(distance, angle);
        this.x += temp.getX();
        this.y += temp.getY();
    }
    
    /**
     * Construct a Point from Polar coordinates.
     * @param rCoord        distance from origin
     * @param thetaCoord    angle in radians from East
     * @return              a Point with those Polar coordinates
     */
    public static Point pointFromPolar(double rCoord, double thetaCoord) {
        Point rThetaPoint = new Point();
        rThetaPoint.setR(rCoord);
        rThetaPoint.setTheta(thetaCoord);
        return rThetaPoint;
    }
        
    /**
     * Tests.
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.println("some Point tests");
        Point point = new Point();
        System.out.println("default: " + point);
        
        point = new Point(1,-3.5);
        System.out.println("(1,-3.5): " + point);
        
        System.out.println("which is r:" + point.getR() 
                           + " theta:" + point.getTheta());
        
        point.setX(0.0);
        point.setY(0.0);
        System.out.println("reset to zeros: " + point);
        point.setR(Math.sqrt(2));
        point.setTheta(Math.PI / 4);
        System.out.println("set to r=1, theta=pi/4: " + point);
        
        Point p3 = pointFromPolar(Math.sqrt(2), Math.PI/4);
        System.out.println("p3: " + p3);
        System.out.println("point.equals(p3): " + point.equals(p3));
        
        Point p4 = point.copy();
        System.out.println("p3: " + p3);
        System.out.println("p4.equals(p3): " + p4.equals(p3));
        
        double distance = Point.distance(new Point(1,-1), new Point(-1,1));
        System.out.println("distance from (1,-1) to (-1,1), expect 2sqrt(2): "
                           + distance);
    }
}
