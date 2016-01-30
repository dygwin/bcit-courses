package q1;

import static java.io.File.separator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.NumberFormat;

/**
 * <p>This class reads information in from a survey.txt file, and stores the
 * data in multiple household objects. The data in the text file is stored in
 * an ID > INCOME > MEMBERS format. Each household object is stored in 
 * a households ArrayList. Each household object's information is printed into
 * the output, then the average income is calculated and printed out, along
 * with which households are above the average. Finally, the percentage of
 * households that are below the low income cut-off is calculated and printed
 * into the output.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class Survey {
    /**
     * <p>An ArrayList object that holds the many Household objects created
     * from the information in the text file.</p>
     */
    private static ArrayList<Household> households = new ArrayList<Household>();

    /**
     * <p>The number of Household objects created from the text file.
     * Its value is set to zero by default.</p>
     */
    private static int numHouseholds;
    
    /**
     * <p>The number of Household objects with a low income.
     * Its value is set to zero by default.</p>
     */
    private static int lowIncomeCount;
    
    /**
     * <p>The total amount of income of all the household objects.
     * Its value is set to zero by default.</p>
     */
    private static double totalIncome;
    
    /**
     * <p>The average income of all the households.
     * Its value is set to zero by default.</p>
     */
    private static double averageIncome;
    
    /**
     * <p>The percent of households that have a low income.
     * Its value is set to zero by default.</p>
     */
    private static double percentLowIncome;
    
    /**
     * <p>A NumberFormat object used to format the incomes.</p>
     */
    private static NumberFormat money = NumberFormat.getCurrencyInstance();
    
    /**
     * <p>A NumberFormat object used to format the percentage of households
     * that have a low income.</p>
     */
    private static NumberFormat percent = NumberFormat.getPercentInstance();
    
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     * @throws FileNotFoundException if the survey.txt file doesn't exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        //Sets up a scanner object to scan the survey.txt file
        Scanner scan = new Scanner(new File("src" + separator + "survey.txt"));
        
        //Processes all of the information inside of survey.txt
        while (scan.hasNextInt()) {
            //Stores the current id read in by the text file
            int id;
            //Stores the current income read in by the text file
            int income;
            //Stores the current number of members read in by the text file
            int members;
            
            //Stores the first number as the id
            id = scan.nextInt();
            //Stores the second number as the income
            income = scan.nextInt();
            //Stores the third number as the number of members
            members = scan.nextInt();
            
            //Adds this income to the total income
            totalIncome += income;
            
            //Creates a new Household object with its id, income, and members
            Household household = new Household(id, income, members);
            
            //Adds the household object to the households ArrayList
            households.add(numHouseholds, household);
            
            //Increases the counter for the number of households created
            numHouseholds++;
        }
        
        scan.close();
        
        //Prints the results and statistics
        printResults();
    }
    
    /**
     * <p>Prints the various results and statistics of the household 
     * objects, including each of their attributes, the average income,
     * the households that exceed the average income, and the percent
     * of households below the low income cut-off.</p>
     */
    private static void printResults() {
        //Calculate the average income out of all the households
        averageIncome = totalIncome / households.size();
        
        //Prints out the id, income, and number of members for each household
        System.out.println("ID\tIncome\t\tMembers");
        for (Household house : households) {
            System.out.println(house);
        }
        
        //Prints the average income of all the households
        System.out.println("\nAverage Income:\n" + money.format(averageIncome));
        
        //Prints the households whose income exceeds the average
        System.out.println("\nHouseholds that exceed the average:\nID\tIncome");
        for (Household house : households) {
            if (house.getIncome() > averageIncome) {
                int houseId = house.getId();
                int houseIncome = house.getIncome();
				
                System.out.println(houseId + "\t" + money.format(houseIncome));
            }
        }
        
        //Prints out the percentage of households with a low income
        System.out.println("\nPercentage of households with income below the "
                + "low income cut-off level:");
        for (Household house : households) {
            if (house.hasLowIncome()) {
                lowIncomeCount++;
            }
        }
		
        percentLowIncome = lowIncomeCount / (double) numHouseholds;
        System.out.println(percent.format(percentLowIncome));
    }
}
