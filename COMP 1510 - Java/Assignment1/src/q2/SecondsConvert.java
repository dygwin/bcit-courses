package q2;

import java.util.Scanner;

/**
 * <p>This program takes a value in seconds as input from the user, using the 
 * Scanner class. It then determines how many hours, minutes, and seconds 
 * go into the value by dividing it by the conversion factors, and determining
 * the remainder. It then displays the results to the user in a hh:mm:ss 
 * format.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class SecondsConvert {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //The conversion factors for converting to hours and minutes.
        final int secondsInHour   = 3600;
        final int secondsInMinute = 60;
        
        //The amount of hours, minutes, and seconds to display to the user.
        int hours;
        int minutes;
        int seconds;
        
        //Sets up a scanner object to read the users input.
        Scanner scan = new Scanner(System.in);
        
        //Gets the time in seconds by the user.
        System.out.println("Enter the time in seconds");
        seconds = scan.nextInt();
        scan.close();
        
        //Determines the amount of hours that can fit in the given seconds.
        //Then determines the remainder, and moves on to minutes.
        hours    = seconds / secondsInHour;
        seconds %= secondsInHour;
        minutes  = seconds / secondsInMinute;
        seconds %= secondsInMinute;
        
        //Displays the results in the desired hh:mm:ss format.
        System.out.println(hours + ":" + minutes + ":" + seconds);
        
        System.out.println("Question two was called and ran sucessfully.");
    }

};
