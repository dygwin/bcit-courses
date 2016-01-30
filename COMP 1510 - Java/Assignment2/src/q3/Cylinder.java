package q3;

/**
 * <p>This class defines the attributes and behaviors of a Cylinder object. Each
 * object has its own radius and height, and has methods that can access and
 * modify those values outside of this class. Each object has a method that 
 * can calculate the surface area and volume of the given cylinder, as well as
 * a toString method that will print a line describing the attributes of the 
 * cylinder.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class Cylinder {
    /**
     * <p>An instance variable where the radius of the cylinder is stored, and
     * it cannot be accessed outside of this class.</p>
     */
    private double radius;
    /**
     * <p>An instance variable where the height of the cylinder is stored, and
     * it cannot be accessed outside of this class.</p>
     */
    private double height;
    /**
     * <p>The constructor for the Cylinder class. Takes the radius and height of
     * a cylinder as parameters.</p>
     * 
     * @param r the radius of the cylinder
     * @param h the height of the cylinder
     */
    public Cylinder(double r, double h) {
        radius = r;
        height = h;
    }
    /**
     * <p>Sets the radius of the cylinder to the given value.</p>
     * 
     * @param r the new radius of the cylinder
     */
    public void setRadius(double r) {
        radius = r;
    }
    /**
     * <p>Sets the height of the cylinder to the given value.</p>
     * 
     * @param h the new height of the cylinder
     */
    public void setHeight(double h) {
        height = h;
    }
    /**
     * <p>Returns the radius of the cylinder.</p>
     * 
     * @return the radius of the cylinder
     */
    public double getRadius() {
        return radius;
    }
    /**
     * <p>Returns the height of the cylinder.</p>
     * 
     * @return the height of the cylinder
     */
    public double getHeight() {
        return height;
    }
    /**
     * <p>Calculates the volume of the cylinder and returns the results.</p>
     * 
     * @return the volume of the cylinder
     */
    public double getVolume() {
        double volume = Math.PI * Math.pow(radius, 2) * height;
        return volume;
    }
    /**
     * <p>Calculates the surface area of the cylinder and returns the
     * results.</p>
     * 
     * @return the surface area of the cylinder
     */
    public double getSurfaceArea() {
        double surfaceArea = 2 * Math.PI * radius * (radius + height);
        return surfaceArea;
    }
    /**
     * <p>Returns a string detailing the radius and height of the cylinder.</p>
     * 
     * @return a string detailing the attributes of the cylinder.
     */
    public String toString() {
        String message = "This cylinder has a radius of " + radius 
                + " and a height of " + height + ".";
        return message;
    }
};
