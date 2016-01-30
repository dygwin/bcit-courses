package q1;

import java.text.NumberFormat;

/**
 * <p>This class defines a Household object, and stores information
 * regarding how many members live in the household, the income of the
 * house hold, the household's ID, and whether the household is below
 * the low income cut-off level. Methods are provided to get the ID, 
 * number of members, and income of the household, in addition to stating
 * whether it is below the low income cut-off. A toString method is also
 * provided to print out the relevant information of the household.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class Household {
    /**
     * <p>Represents a case where the household contains only one member.</p>
     */
    private static final int MEMBERS1 = 1;
    
    /**
     * <p>Represents a case where the household contains two members.</p>
     */
    private static final int MEMBERS2 = 2;
    
    /**
     * <p>Represents a case where the household contains three members.</p>
     */
    private static final int MEMBERS3 = 3;
    
    /**
     * <p>Represents a case where the household contains four members.</p>
     */
    private static final int MEMBERS4 = 4;
    
    /**
     * <p>Represents a case where the household contains five members.</p>
     */
    private static final int MEMBERS5 = 5;
    
    /**
     * <p>Represents a case where the household contains six members.</p>
     */
    private static final int MEMBERS6 = 6;
    
    /**
     * <p>Represents a case where the household contains seven members.</p>
     */
    private static final int MEMBERS7 = 7;
    
    /**
     * <p>The cutoff number for a household with one member.</p>
     */
    private static final int CUTOFF1 = 22229;
    
    /**
     * <p>The cutoff number for a household with two members.</p>
     */
    private static final int CUTOFF2 = 27674;
    
    /**
     * <p>The cutoff number for a household with three members.</p>
     */
    private static final int CUTOFF3 = 34022;
    
    /**
     * <p>The cutoff number for a household with four members.</p>
     */
    private static final int CUTOFF4 = 41307;
    
    /**
     * <p>The cutoff number for a household with five members.</p>
     */
    private static final int CUTOFF5 = 46850;
    
    /**
     * <p>The cutoff number for a household with six members.</p>
     */
    private static final int CUTOFF6 = 52838;
    
    /**
     * <p>The cutoff number for a household with seven members.</p>
     */
    private static final int CUTOFF7 = 58827;
    
    /**
     * <p>The additional cutoff added for each member after seven.</p>
     */
    private static final int ADDITIONAL_CUTOFF = 5989;
    
    /**
     * <p>The identification number of the household.</p>
     */
    private int id;
    /**
     * <p>The collective income of all the members in the household.</p>
     */
    private int income;
    /**
     * <p>The amount of members in the household.</p>
     */
    private int members;
    /**
     * <p>Whether or not the income is below the low income cut-off level.
     * Set to false by default.</p>
     */
    private boolean lowIncome;
    /**
     * <p>NumberFormat object used to correctly format the income of the
     * households.</p>
     */
    private NumberFormat money = NumberFormat.getCurrencyInstance();
    
    /**
     * <p>The constructor of the Household class. Takes the households
     * identification number, income, and number of members as parameters.</p>
     * 
     * @param id the identification number of the household
     * @param income the income of the household
     * @param members the number of members living in the household
     */
    public Household(int id, int income, int members) {
        this.id      = id;
        this.income  = income;
        this.members = members;
        
        //Checks if the household has low income
        lowIncomeCheck();
    }
    /**
     * <p>Returns the income of the househouse.</p>
     * 
     * @return the income of the household
     */
    public int getIncome() {
        return income;
    }
    /**
     * <p>Returns the identification number of the household.<p>
     * 
     * @return the id of the household
     */
    public int getId() {
        return id;
    }
    /**
     * <p>Returns whether the household has an income less than the low
     * income cut-off level.</p>
     * 
     * @return the state of the households income
     */
    public boolean hasLowIncome() {
        return lowIncome;
    }
    /**
     * <p>Checks whether or not the income of the household is below the low
     * income cut-off table for its respective number of members.
     * The numbers are set for households with members up to seven, and for
     * addition members the income is simply increased by a set value.
     * If the income is below the cut-off level, then it will set lowIncome to
     * true.</p>
     */
    private void lowIncomeCheck() {
        switch (members) {
        case MEMBERS1:
            //Households with one member
            if (income < CUTOFF1) {
                lowIncome = true;
            }
            break;
        case MEMBERS2:
            //Households with two members
            if (income < CUTOFF2) {
                lowIncome = true;
            }
            break;
        case MEMBERS3:
            //Households with three members
            if (income < CUTOFF3) {
                lowIncome = true;
            }
            break;
        case MEMBERS4:
            //Households with four members
            if (income < CUTOFF4) {
                lowIncome = true;
            }
            break;
        case MEMBERS5:
            //Households with five members
            if (income < CUTOFF5) {
                lowIncome = true;
            }
            break;
        case MEMBERS6:
            //Households with six members
            if (income < CUTOFF6) {
                lowIncome = true;
            }
            break;
        case MEMBERS7:
            //Households with seven members
            if (income < CUTOFF7) {
                lowIncome = true;
            }
            break;
        default:
            //Households with more than seven members
            if (income < ((members - MEMBERS7) * ADDITIONAL_CUTOFF) + CUTOFF7) {
                lowIncome = true;
            }
        }
    }
    /**
     * <p>A toString method that returns the identification number, income, and
     * number of members in the household. This method is automatically invoked
     * when trying to print a household object.</p>
     * 
     * @return the id, income, and number of members of the household
     */
    public String toString() {
        return id + "\t" + money.format(income) + "\t" + members;
    }

};
