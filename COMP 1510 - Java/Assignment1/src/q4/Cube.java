package q4;

import java.util.Scanner;

/**
 * <p>Takes the side length of a cube as input from the user using the
 * scanner class and stores it as an integer value. It then calculates the
 * volume and surface area of a cube with the given side length, and displays
 * the results to the user.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class Cube {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //Sets the amount of sides of a cube.
        final int sides = 6;
        //Sets the amount of dimensions the cube contains (Length Width Height).
        final int dimensions = 3;
        
        //Set to the input given by the user.
        int sideLength;
        
        //Stores the results of the calculations.
        int surfaceArea;
        int volume;
        
        //Sets up a scanner object to read the users input.
        Scanner scan = new Scanner(System.in);
        
        //Gets the side length of the cube from the user.
        System.out.println("Enter the side length of a cube: ");
        sideLength = scan.nextInt();
        scan.close();
        
        //Calculates the surface area and volume of a cube with given length.
        surfaceArea = (int) (sides * Math.pow(sideLength, 2));
        volume      = (int) Math.pow(sideLength, dimensions);
        
        //Displays the results of the calculations to the user.
        System.out.println("The cubes surface area is " + surfaceArea);
        System.out.println("The cubes volume is " + volume);
        
        System.out.println("Question four was called and ran sucessfully.");
    }

};
