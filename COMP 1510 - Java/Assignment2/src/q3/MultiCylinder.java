package q3;

/**
 * <p>This program creates two new cylinder objects, each with its own unique
 * radius and height. It then prints out the current radius and height of each
 * cylinder. Next, it updates both cylinder's radius and height to new values,
 * and prints out what the new radius and heights are. Finally, it prints out
 * the surface area and volume of each cylinder with these new updated values.
 * </p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class MultiCylinder {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //The initial radius and height of cylinder1
        final int initCyl1Radius = 4;
        final int initCyl1Height = 7;
        //The initial radius and height of cylinder2
        final int initCyl2Radius = 10;
        final int initCyl2Height = 13;
        //The new radius and height of cylinder1
        final int newCyl1Radius  = 5;
        final int newCyl1Height  = 1;
        //The new radius and height of cylinder2
        final int newCyl2Radius  = 8;
        final int newCyl2Height  = 9;
        
        //Instantiates two new cylinder objects
        Cylinder cylinder1 = new Cylinder(initCyl1Radius, initCyl1Height);
        Cylinder cylinder2 = new Cylinder(initCyl2Radius, initCyl2Height);
        
        //Prints out the current radius and height of cylinder1
        System.out.println("Radius of cylinder1 :" + cylinder1.getRadius());
        System.out.println("Height of cylinder1 :" + cylinder1.getHeight());
        //Prints out the current radius and height of cylinder2
        System.out.println("\nRadius of cylinder2 :" + cylinder2.getRadius());
        System.out.println("Height of cylinder2 :" + cylinder2.getHeight());
        
        //Updates cylinder1's radius and height
        cylinder1.setRadius(newCyl1Radius);
        cylinder1.setHeight(newCyl1Height);
        //Updates cylinder2's radius and height
        cylinder2.setRadius(newCyl2Radius);
        cylinder2.setRadius(newCyl2Height);
        
        //Prints out the new radius and height of cylinder1
        System.out.println("\nRadius of modified cylinder1 :" 
                + cylinder1.getRadius());
        System.out.println("Height of modified cylinder1 :" 
                + cylinder1.getHeight());
        //Prints out the new radius and height of cylinder2
        System.out.println("\nRadius of modified cylinder2 :" 
                + cylinder2.getRadius());
        System.out.println("Height of modified cylinder2 :" 
                + cylinder2.getHeight());
        
        //Prints out the new volume and surface area of cylinder1
        System.out.println("\nVolume of modified cylinder1 :" 
                + cylinder1.getVolume());
        System.out.println("Surface area of modified cylinder1 :" 
                + cylinder1.getSurfaceArea());
        //Prints out the new volume and surface area of cylinder2
        System.out.println("\nVolume of modified cylinder2 :" 
                + cylinder2.getVolume());
        System.out.println("Surface area of modified cylinder2 :" 
                + cylinder2.getSurfaceArea());
        
        System.out.println("\nQuestion three was called and ran sucessfully.");
    }

};
