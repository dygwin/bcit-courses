package q1;

import java.util.Random;

import java.text.DecimalFormat;

/**
 * <p>This program generates a random phone number utilizing the Random class.
 * First it generates three separate digits between 0-7, and concatenates them 
 * together into a string to form the first set of numbers (the area code). 
 * Next it generates a random number between 0-635, to be used as the second 
 * set in the phone number. Then, it generates another random number between 
 * 0-9999 to be used as the last set of numbers in the phone number. Lastly, it
 * pads the sets of numbers with zeros (if needed) so that it remains sets of 
 * threes and fours, and prints the phone number out to the user.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class PhoneNumbers {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //The maximum values for each set of numbers
        final int firstSetMax  = 8;
        final int secondSetMax = 636;
        final int thirdSetMax  = 10000;
        
        //Initializes a new random number generator
        Random generator = new Random();
        
        //Sets up DecimalFormat objects to pad the numbers with zeros
        DecimalFormat setOfThree = new DecimalFormat("000");
        DecimalFormat setOfFour  = new DecimalFormat("0000");
        
        //Generates three numbers between 0-7
        int firstDigit  = generator.nextInt(firstSetMax);
        int secondDigit = generator.nextInt(firstSetMax);
        int thirdDigit  = generator.nextInt(firstSetMax);
        
        //Concatenates the three digits into a set
        String firstSet = "" + firstDigit + secondDigit + thirdDigit;
        
        //Generates a number between 0-635
        int secondSet = generator.nextInt(secondSetMax);
        
        //Generates a number between 0-9999
        int thirdSet = generator.nextInt(thirdSetMax);
        
        //Prints out the phone number, padding the sets with zero if needed
        System.out.println(firstSet + "-" + setOfThree.format(secondSet) + "-" 
                + setOfFour.format(thirdSet));
        
        System.out.println("Question one was called and ran sucessfully.");
    }

};
