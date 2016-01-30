package q3;

/**
 * <p>This program creates two Address objects and passes them through the
 * constructor of a student object, along with its name and test scores. The
 * students information is then printed out using its toString method. A second
 * student object is then created using the zero-parameter constructor, and its
 * information is printed out by calling its toString method. The test scores
 * for this student object are then manually set and once again the students
 * information is printed out.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class TestStudent {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //The test scores of the first student
        final double student1test1 = 90.5;
        final double student1test2 = 60;
        final double student1test3 = 79;
		
        //The test scores of the second student
        final double student2test1 = 88;
        final double student2test2 = 77.5;
        final double student2test3 = 91;
		
        //The test numbers of each test
        final int    test1         = 1;
        final int    test2         = 2;
        final int    test3         = 3;
        
        //An Address object representing the first students home address
        Address student1Home = new Address("14457 Walnut Dr.",
                "Hollywood", "California", "12425");
        //An Address object representing the first students school address
        Address student1School = new Address("14242 Star Ave",
                "Hollywood", "California", "14423");
        
        //Creates the first Student object and passes through all of its values
        Student student1 = new Student("Todd", "Burgundy",
                student1Home, student1School, student1test1, 
                student1test2, student1test3);
        
        //Prints out all of student1's information
        System.out.println("A student with all values passed through "
                + "the constructor:\n");
        System.out.println(student1);
        
        //Creates a second Student object with no values set
        Student student2 = new Student();
        
        //Prints out all of student2's information so far
        System.out.println("\nA student with no values passed through "
                + "the constructor:\n");
        System.out.println(student2);
        
        //Sets the test scores for all of student2's tests
        student2.setTestScore(test1, student2test1);
        student2.setTestScore(test2, student2test2);
        student2.setTestScore(test3, student2test3);
        
        //Prints out student2's new information
        System.out.println("\nAfter setting the students test scores:\n");
        System.out.println(student2);
        
        System.out.println("\nQuestion three was called and ran sucessfully.");
    }

};
