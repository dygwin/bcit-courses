package q1;

import java.util.Scanner;
/**
 * <p>This program takes the total amount of money as input from the user and
 * multiplies it by 100 in order to get rid of decimals and decrease the chance
 * of calculation errors. It then finds the largest amount of each money 
 * increment that can go into it by first dividing the value by the largest
 * increment, storing that amount into its respective variable, then determining
 * the remainder and repeating this process until there is no longer a 
 * remainder. It then prints out the results to the user.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class Change {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //The different increments of money, multiplied by 100.
        final int ten = 1000;
        final int five = 500;
        final int toonie = 200;
        final int loonie = 100;
        final int quarter = 25;
        final int dime = 10;
        final int nickle = 5;
        final int penny = 1;
        
        //Used to get rid of decimals in the money value.
        final int multiplier = 100;
        
        //Initializes what will store the possible amount of each value.
        int amountTens;
        int amountFives;
        int amountToonies;
        int amountLoonies;
        int amountQuarters;
        int amountDimes;
        int amountNickles;
        int amountPennies;
        
        //Stores the initial value of money.
        double money;
        
        //Sets up a scanner object to read the users input.
        Scanner scan = new Scanner(System.in);
        
        //Gets the total amount of money from the user.
        System.out.println("Enter the total amount of money: ");
        money = scan.nextDouble();
        scan.close();
        
        //Gets rid of decimal places in the money value.
        money *= multiplier;
        
        //Calculates how many times each increment goes into the money value.
        //Determines the remainder, and stores it back into money.
        amountTens     = (int) (money / ten);
        money          = money % ten;
		
        amountFives    = (int) (money / five);
        money          = money % five;
		
        amountToonies  = (int) (money / toonie);
        money          = money % toonie;
		
        amountLoonies  = (int) (money / loonie);
        money          = money % loonie;
		
        amountQuarters = (int) (money / quarter);
        money          = money % quarter;
		
        amountDimes    = (int) (money / dime);
        money          = money % dime;
		
        amountNickles  = (int) (money / nickle);
        money          = money % nickle;
		
        amountPennies  = (int) (money / penny);
        
        //Prints out the results of each increment.
        System.out.println("Tens: " + amountTens);
        System.out.println("Fives: " + amountFives);
        System.out.println("Toonies: " + amountToonies);
        System.out.println("Loonies: " + amountLoonies);
        System.out.println("Quarters: " + amountQuarters);
        System.out.println("Dimes: " + amountDimes);
        System.out.println("Nickles: " + amountNickles);
        System.out.println("Pennies: " + amountPennies);
        
        System.out.println("Question one was called and ran sucessfully.");
    }

};
