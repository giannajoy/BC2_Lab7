/*
 * Bootcamp 2 Code
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package gee_lab7_local;

/**
 * Holds location and direction of the pen.
 * @author bc2code
 */
public class RenderPoint {
    public Point location;
    public double heading;
    
    public RenderPoint() {
        this(0, 0, 0);
    }
    
    public RenderPoint(int x, int y, int degrees) {
        this.location = new Point(x, y);
        this.heading = Math.toRadians(degrees);
    }
    
    public RenderPoint copy() {
        RenderPoint other = new RenderPoint();
        other.location = this.location.copy();
        other.heading = this.heading;
        return other;
    }
    
    public int getX() {
        return (int)location.getX();
    }
    
    public int getY() {
        return (int)location.getY();
    }
    
    public int getHeading() {
        return (int)Math.toDegrees(heading);
    }
    
    public void rotate(int degrees) {
        heading += Math.toRadians(degrees);
    }
    
    public void move(int distance) {
        location.move(distance, heading);
    }
    
    @Override
    public String toString() {
        return "RenderPoint(" + getX() + "," + getY() + "," 
                + getHeading() + ")";
    }
}
