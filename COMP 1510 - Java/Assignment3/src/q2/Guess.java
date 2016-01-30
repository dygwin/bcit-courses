package q2;

import java.util.Scanner;
import java.util.Random;

/**
 * <p>This program uses a random number generator to choose a number from
 * 1 to 100 (inclusive), and then asks the user to try guessing the number
 * chosen. When the user guesses wrong, it keeps a tally of how many guesses
 * they have made, and warns them if they are guessing too high or too low.
 * If the user enters zero or guesses correctly, they are then told how many
 * guesses it took them and are asked if they want to play again, prompting
 * them to enter 1 for yes or 0 for no.<p>
 * <p>If the user enters yes, a new number is chosen and they must once again
 * guess. If the user enters no, they are thanked for playing and the
 * program terminates. If they enter any input other than 1 or 0, they are
 * warned for invalid entry, and asked to once again enter 1 or 0.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class Guess {
    /**
     * <p>The maximum number the random number generator can generate.</p>
     */
    private static final int MAX_NUMBER = 100;
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //Scanner object to get input from the user
        Scanner scan     = new Scanner(System.in);
		
        //Random number generator to generate the guessed number
        Random generator = new Random();
		
        //Whether or not the user wants to play the game again
        boolean playAgain;
        
        do {
            //The users guess
            int guess;
			
            //The amount of times the user guessed
            int numGuesses = 0;
			
            //Generates a random number between 1 and 100 (inclusive)
            int number     = generator.nextInt(MAX_NUMBER) + 1;
			
            //The users choice when deciding if they want to play again
            int choice;
            
            //Ensures the game wont restart if the user doesn't say to
            playAgain = false;
            
            System.out.println("Guess the number! (1-100) (0 to quit):");
			
            do {
                //Ensure that the guess is an integer
                while (!scan.hasNextInt()) {
                    System.out.println("That isn't an integer! "
                            + "Enter an integer:");
							
                    scan.next();
                }
                guess = scan.nextInt();
                
                //Increase the total amount of guesses made by the user
                numGuesses++;
				
                //Prints out whether the guess is too high or too low
                if (guess > number && guess != 0) {
                    System.out.println("Too high!");
                } else if (guess < number && guess != 0) {
                    System.out.println("Too low!");
                }
				
                //The do loop is exited if they guess correctly or type 0
            } while (guess != number && guess != 0);
            
            if (guess == number) {
                //If correctly guessed, says how many guesses they took
                System.out.println("Correct! It took you " + numGuesses 
                        + " guesses.");
            } else {
                //If they exit the program without guessing correctly
                System.out.println("The answer was " + number + ".");
            }
			
            System.out.println("Do you want to play again? (1 yes/ 0 no)");
			
            do {
                //Ensures the user enters an integer
                while (!scan.hasNextInt()) {
                    System.out.println("Invalid entry. Enter 1 for yes or 0 "
                            + "for no:");
							
                    scan.next();
                }
                choice = scan.nextInt();
                if (choice == 1) {
                    //If they enter 1, play the game again
                    playAgain = true;
                } else if (choice == 0) {
                    //If they enter 0, exit the game
                    System.out.println("Thanks for playing!");
                } else {
                    //If dont enter 1 or 0, give them an error message
                    System.out.println("Invalid entry. Enter 1 for yes or 0 "
                            + "for no:");
                }
				
              //If they dont enter 1 or 0, make them enter a new number
            } while (choice != 1 && choice != 0);
			
          //If they chose to play again, run the game again
        } while (playAgain);
		
        scan.close();
    }

};
