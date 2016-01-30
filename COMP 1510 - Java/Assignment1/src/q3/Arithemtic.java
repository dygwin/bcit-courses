package q3;

import java.util.Scanner;

/**
 * <p>This program uses the Scanner class to get two floating point numbers
 * as input from the user. It then calculates the sum, difference, quotients,
 * and product of the two numbers, and displays the results to the user.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class Arithemtic {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //These will be assigned to the numbers the user inputs.
        double num1;
        double num2;
        
        //Initializes the variables that will store the final results.
        double sum;
        double difference;
        double quotient1;
        double quotient2;
        double product;
        
        //Sets up a scanner object to read the users input.
        Scanner scan = new Scanner(System.in);
        
        //Gets the two numbers as input from the user.
        System.out.println("Enter a number: ");
        num1 = scan.nextDouble();
        
        System.out.println("Enter a second number: ");
        num2 = scan.nextDouble();
        scan.close();
        
        //Calculates the results of various operations between the two numbers.
        sum        = num1 + num2;
        difference = Math.abs(num2 - num1);
        quotient1  = num1 / num2;
        quotient2  = num2 / num1;
        product    = num1 * num2;
        
        //Displays the results of the operations to the user.
        System.out.println("The sum of " + num1 + " and " + num2 
                + " is " + sum);
        System.out.println("The difference between " + num1 + " and " + num2 
                + " is " + difference);
        System.out.println("The quotient of " + num1 + " divided by " + num2 
                + " is " + quotient1);
        System.out.println("The quotient of " + num2 + " divided by " + num1 
                + " is " + quotient2);
        System.out.println("The product of " + num1 + " and " + num2 
                + " is " + product);
        
        System.out.println("Question three was called and ran sucessfully.");
    }

};
