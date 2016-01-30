package q1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.io.File.separator;
import java.text.DecimalFormat;


/**
 * <p>This program reads a max of 50 integers in from an integers.txt file, and 
 * stores them in an ArrayList of Integer objects. It then calculates the mean,
 * variance, and sample standard deviation of the integers, and uses a
 * DecimalFormat object to format the mean and standard deviation to two
 * decimal places before printing them.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class Statistics {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     * @throws FileNotFoundException if integers.txt isn't found
     */
    public static void main(String[] args) throws FileNotFoundException {
        //Holds the integers read in from the text file
        ArrayList<Integer> numbers = new ArrayList<Integer>();
		
        //The maximum size of the numbers ArrayList
        final int maxSize            = 50;
        //The sum of the integers
        double    sum                = 0;
        //The mean(average) of the numbers
        double    mean               = 0;
        //The squared differences of the numbers; Used to find the variance
        double    squaredDifferences = 0;
        //The variance of the numbers; Used to find the standard deviation
        double    variance           = 0;
        //The standard deviation of the numbers
        double    standardDeviation  = 0;
        
        //Used to format the results to two decimal places
        DecimalFormat resultFmt = new DecimalFormat("#.##");
        
        //Scanner object used to read the integers from the integers.txt file
        Scanner numberScanner = new Scanner(new File("src" 
                + separator + "q1" + separator + "integers.txt"));
        
        //Read all of the integers in from the integers.txt file
        while (numberScanner.hasNextInt() && numbers.size() < maxSize) {
            //Adds the numbers to the numbers ArrayList
            numbers.add(numberScanner.nextInt());
        }
        
        //Displays a message if the max number limit was reached
        if (numbers.size() == maxSize) {
            System.out.println("Max number limit reached. (50)");
        }
        
        //Closes the file scanner
        numberScanner.close();
        
        //Calculates the sum of all of integers in the numbers ArrayList
        for (Integer num : numbers) {
            sum += num;
        }
        
        //Calculates the mean
        mean = sum / numbers.size();
        
        //Calculates the squared differences
        for (Integer num : numbers) {
            squaredDifferences += Math.pow(num - mean, 2);
        }
        
        //Calculates the variance; Uses N - 1 for sample standard deviation
        variance = squaredDifferences / (numbers.size() - 1);
        
        //Calculates the sample standard deviation
        standardDeviation = Math.sqrt(variance);
        
        //Prints the mean and standard deviation, formatted to two decimals
        System.out.println("Mean: " + resultFmt.format(mean));
        System.out.println("Standard Deviation: " 
                + resultFmt.format(standardDeviation));
    }

};
