package q2;

import java.util.Scanner;
import java.text.DecimalFormat;

/**
 * <p>This program gets height and radius of a cylinder from the user using the
 * a Scanner object, and stores them as doubles. It then calculates the volume
 * and surface area of a cylinder with those given specifications, formats them
 * to four decimal places using a DecimalFormat object, and prints the results
 * out to the user.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class CylinderStats {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //Sets up a scanner object to get input from the user
        Scanner scan = new Scanner(System.in);
        
        //Sets up a DecimalFormat object to format our results to four decimals
        DecimalFormat fourFmt = new DecimalFormat("0.####");
        
        //Stores the radius and height we get from the user
        double radius;
        double height;
        
        //Stores the results to the calculations
        double surfaceArea;
        double volume;
        
        //Gets the radius and height of the cylinder from the user
        System.out.println("Enter the radius of the cylinder: ");
        radius = scan.nextDouble();
        System.out.println("Enter the height of the cylinder: ");
        height = scan.nextDouble();
        scan.close();
        
        //Calculates the surface area of the cylinder
        surfaceArea = 2 * Math.PI * radius * (radius + height);
        
        //Calculates the volume of the cylinder
        volume = Math.PI * Math.pow(radius, 2) * height;
        
        //Outputs the surface area and volume, formatted to four decimal places
        System.out.println("Surface Area: " + fourFmt.format(surfaceArea));
        System.out.println("Volume: " + fourFmt.format(volume));
        
        System.out.println("Question two was called and ran sucessfully.");
    }

};
