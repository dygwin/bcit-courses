package q4;

import java.text.DecimalFormat;

/**
 * <p>This program has many variables detailing the attributes of students
 * and their test scores. Four students are then created using these 
 * attributes, along with their home and school addresses. A new Course
 * is then created, and all four of the students are added to the course
 * by calling its addStudent() method. The roll sheet for the course
 * is then printed out, along with the courses average test score.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class TestCourse {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //The test scores for the first student
        final double student1test1 = 68;
        final double student1test2 = 88;
        final double student1test3 = 76;
        //The test scores for the second student
        final double student2test1 = 98;
        final double student2test2 = 89;
        final double student2test3 = 93;
        //The test scores for the third student
        final double student3test1 = 40;
        final double student3test2 = 57;
        final double student3test3 = 62;
        //The test scores for the fourth student
        final double student4test1 = 77;
        final double student4test2 = 76;
        final double student4test3 = 79;
        //A DecimalFormat object to format the average to two decimal places
        DecimalFormat formatter = new DecimalFormat("##.##");
        
        //The home address for the first student
        Address student1Home = new Address("1424 Walnut Dr.", "Fry", 
                "Illinois", "12442");
        //The home address for the second student
        Address student2Home = new Address("4241 Coconut Ave", "Sirena",
                "Illinois", "15425");
        //The home address for the third student
        Address student3Home = new Address("4541 Banana Str.", "Sirena",
                "Illinois", "16535");
        //The home address for the fourth student
        Address student4Home = new Address("143 A Donut Dr.", "Welkin",
                "Illinois", "26653");
        
        //The school address that all of the students share
        Address schoolAddress = new Address("4644 Wayside Way", "Sirena",
                "Illinois", "15775");
        
        //Creates the first Student with its test scores and addresses
        Student student1 = new Student("George", "Lucas", student1Home,
                schoolAddress, student1test1, student1test2, student1test3);
        //Creates the second Student with its test scores and addresses
        Student student2 = new Student("Raymond", "Red", student2Home,
                schoolAddress, student2test1, student2test2, student2test3);
        //Creates the third Student with its test scores and addresses
        Student student3 = new Student("Shelly", "Cooper", student3Home,
                schoolAddress, student3test1, student3test2, student3test3);
        //Creates the fourth Student with its test scores and addresses
        Student student4 = new Student("Valeyrie", "Poxleitner", student4Home,
                schoolAddress, student4test1, student4test2, student4test3);
        
        //Creates a Course object with the name "Programming"
        Course programming = new Course("Programming");
        
        //Adds the four students to the programming course
        programming.addStudent(student1);
        programming.addStudent(student2);
        programming.addStudent(student3);
        programming.addStudent(student4);
        
        //Prints out the roll sheet for the course
        programming.roll();
        
        //Prints the courses total average test score formatted to two decimals
        System.out.println("\nTotal average test score:\n" 
                + formatter.format(programming.average()));
    }

};
