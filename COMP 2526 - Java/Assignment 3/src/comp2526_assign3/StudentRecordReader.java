package comp2526_assign3;

import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class StudentRecordReader extends FilterInputStream {
    private DataInputStream ds;

    public StudentRecordReader(InputStream in) {
        super(in);
        ds = new DataInputStream(in);
    }
    
    public Student readRecord() throws IOException {
        // read from the file in UTF encoding and store it in data
        String data = ds.readUTF();
        
        // if no data was read, return null
        if (data == null) {
            return null;
        }
        
        // a Scanner object used to extract the Student data from the String data
        Scanner dataScanner = new Scanner(data);
        
        // read the first value in data (the first name)
        String firstName = dataScanner.next();
        // read the second value in data (the last name)
        String lastName = dataScanner.next();
        //read the third value in data (the student ID)
        String ID = dataScanner.next();
        
        // close the Scanner object
        dataScanner.close();
        
        // return a new Student using the read data
        return new Student(firstName, lastName, ID);
    }

}
