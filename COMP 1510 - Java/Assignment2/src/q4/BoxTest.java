package q4;

/**
 * <p>This class sets final integer values that specify the attributes of each
 * Box object it will create. It then creates two Box objects, box1 and box2,
 * and sets their height, width, and depth to these attributes. Next, it prints
 * out the attributes for each box. Then, it sets the width, height, and full
 * state of box1 to new values, and the height and depth of box2 as well.
 * Finally, it prints out the new attributes of each of the boxes by invoking
 * their toString methods.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class BoxTest {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        //The initial height, width, and depth of box1
        final int initBox1Height = 5;
        final int initBox1Width  = 6;
        final int initBox1Depth  = 8;
        //The initial height, width, and depth of box2
        final int initBox2Height = 3;
        final int initBox2Width  = 4;
        final int initBox2Depth  = 7;
        //The new height and width of box1
        final int newBox1Height  = 10;
        final int newBox1Width   = 11;
        //The new height and depth of box2
        final int newBox2Height  = 12;
        final int newBox2Depth   = 14;
        
        //Instantiates two new Box objects with a height, width, and depth
        Box box1 = new Box(initBox1Height, initBox1Width, initBox1Depth);
        Box box2 = new Box(initBox2Height, initBox2Width, initBox2Depth);
        
        //Prints out the height, width, and depth of box1, and if its full
        System.out.println("The height of box1 is " + box1.getHeight());
        System.out.println("The width of box1 is " + box1.getWidth());
        System.out.println("The depth of box1 is " + box1.getDepth());
        System.out.println("Whether box1 if full is " + box1.getFull());
        //Prints out the height, width, and depth of box2, and if its full
        System.out.println("\nThe height of box2 is " + box2.getHeight());
        System.out.println("The width of box2 is " + box2.getWidth());
        System.out.println("The depth of box2 is " + box2.getDepth());
        System.out.println("Whether box2 is full is " + box2.getFull());
        
        //Updates the height and width, of box1, and whether it is full
        box1.setHeight(newBox1Height);
        box1.setWidth(newBox1Width);
        box1.setFull(true);
        //Updates the height and depth of box2
        box2.setHeight(newBox2Height);
        box2.setDepth(newBox2Depth);
        
        //Prints out the new height, width, and depth, and fullness of each box
        System.out.println("\nUpdated box1: " + box1);
        System.out.println("Updated box2: " + box2);
        
        System.out.println("\nQuestion four was called and ran sucessfully.");
    }

};
