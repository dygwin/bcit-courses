package q4;

import java.util.ArrayList;

/**
 * <p>Represents a specific course in a school. It has values that
 * store the name of the course, and it keeps track of the students
 * that are enrolled in the course. It provides methods to add new
 * students, calculate the average test score of the class, and to
 * print out an entire class roll of all the students in the course.</p>
 * 
 * @author Trevor Broderick
 * @version 1.0
 */
public class Course {
    /**
     * <p>The maximum number of students allowed in the course.</p>
     */
    private static final int MAX_STUDENTS = 5;
    
    /**
     * <p>The name of the course.</p>
     */
    private String name;
    
    /**
     * <p>An ArrayList object to contain all the students in the course.</p>
     */
    private ArrayList<Student> students = new ArrayList<Student>();
    
    /**
     * <p>The constructor for the Course. Sets the name of the course.</p>
     * 
     * @param name the name of the course
     */
    public Course(String name) {
        this.name = name;
    }
    
    /**
     * <p>Adds a new student to the students ArrayList, but only if
     * there are less than 5 students currently in the course. If a
     * sixth student is added, an IllegalArgumentException will be
     * thrown.</p>
     * 
     * @param student the student to add to the course.
     */
    public void addStudent(Student student) {
        if (students.size() < MAX_STUDENTS) {
            students.add(student);
        } else {
            throw new IllegalArgumentException("Can't add more than five "
                    + "students!");
        }
    }
    
    /**
     * <p>Calculates the average test score for the entire course.</p>
     * 
     * @return the courses average test score
     */
    public double average() {
        double totalScores = 0;
		
        for (Student student : students) {
            totalScores += student.average();
        }
		
        return totalScores / students.size();
    }
    
    /**
     * <p>Prints out a roll sheet of all the students in the course,
     * along with their test scores, test average, and addresses.</p>
     */
    public void roll() {
        System.out.println(name + " Roll Sheet");
        System.out.println("----------------------------");
		
        for (Student student : students) {
            System.out.println(student);
            System.out.println("--------------------------");
        }
		
        System.out.println("End of Roll Sheet");
    }
}
