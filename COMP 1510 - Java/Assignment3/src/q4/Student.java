package q4;

import java.text.DecimalFormat;

/**
 * <p>Represents a college student. Contains both a full constuctor
 * that sets all of the students values, and a zero-parameter
 * constuctor that sets all the scores to zero and leaves the other
 * fields null. Contains methods to manually set and retrieve the
 * test scores, a method to calculate the average test score, and
 * a toString method that returns all of the students information.</p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Student {
    /** 
     * <p>The test number of test one.</p> 
     */
    private static final int TEST_1 = 1;
    
    /** 
     * <p>The test number of test two.</p> 
     */
    private static final int TEST_2 = 2;
    
    /** 
     * <p>The test number of test three.</p> 
     */
    private static final int TEST_3 = 3;
    
    /** 
     * <p>The amount of tests that exist.</p> 
     */
    private static final int NUM_OF_TESTS = 3;
    
    /** 
     * <p>First name of this student.</p>
     */
    private String firstName;

    /** 
     * <p>Last name of this student.</p>
     */
    private String lastName;

    /** 
     * <p>Home address of this student.</p>
     */
    private Address homeAddress;

    /** 
     * <p>School address of this student. 
     * Can be shared by other students.</p> 
     */
    private Address schoolAddress;
    
    /** 
     * <p>The score achieved on test number 1.</p> 
     */
    private double test1Score;
    
    /** 
     * <p>The score achieved on test number 2.</p> 
     */
    private double test2Score;
    
    /** 
     * <p>The score achieved on test number 3.</p> 
     */
    private double test3Score;
    
    /**
     * <p>DecimalFormat object formats the average to two decimal places.</p>
     */
    private DecimalFormat formatter = new DecimalFormat("##.##");
    
    /**
    * <p>Constructor: Sets up this student with the specified values.</p>
    * @param first the first name of the student
    * @param last the last name of the student
    * @param home the home address of the student
    * @param school the school address of the student
    * @param score1 the score on test one
    * @param score2 the score on test two
    * @param score3 the score on test three
    */
    public Student(String first, String last, Address home, Address school,
            double score1, double score2, double score3) {
        firstName     = first;
        lastName      = last;
        homeAddress   = home;
        schoolAddress = school;
        test1Score    = score1;
        test2Score    = score2;
        test3Score    = score3;
    }
    
    /**
     * <p>Constructor: Sets up a student with 0 for the test scores.</p>
     */
    public Student() {
        test1Score = 0;
        test2Score = 0;
        test3Score = 0;   
    }
    
    /**
     * <p>Sets the given test number to the given score.</p>
     * @param testNumber the number of the test
     * @param score the score achieved on the test
     */
    public void setTestScore(int testNumber, double score) {
        if (testNumber == TEST_1) {
            test1Score = score;
        } else if (testNumber == TEST_2) {
            test2Score = score;
        } else if (testNumber == TEST_3) {
            test3Score = score;
        } else {
            throw new IllegalArgumentException("Must be test 1, 2, or 3!");
        }
    }
    
    /**
     * <p>Returns the test score for the given test number.</p>
     * 
     * @param testNumber the number of the test
     * @return the score of the test
     */
    public double getTestScore(int testNumber) {
        if (testNumber == TEST_1) {
            return test1Score;
        } else if (testNumber == TEST_2) {
            return test2Score;
        } else if (testNumber == TEST_3) {
            return test3Score;
        } else {
            throw new IllegalArgumentException("Must be test 1, 2, or 3!");
        }
    }
    
    /**
     * <p>Returns the average of all the tests taken.</p>
     * 
     * @return the average test score
     */
    public double average() {
        return (test1Score + test2Score + test3Score) / NUM_OF_TESTS;
    }
    
    /**
    * <p>Returns a string description of this Student object.</p>
    * 
    * @return formatted name and addresses of student
    */
    public String toString() {
        String result;

        result  = firstName + " " + lastName + "\n";
        result += "--------------------------\n";
        result += "Home Address:\n" + homeAddress + "\n";
        result += "School Address:\n" + schoolAddress + "\n";
        result += "Test1 Score:\n" + formatter.format(test1Score) + "\n";
        result += "Test2 Score:\n" + formatter.format(test2Score) + "\n";
        result += "Test3 Score:\n" + formatter.format(test3Score) + "\n";
        result += "Average Test Score:\n" + formatter.format(average());

        return result;
    }
}
