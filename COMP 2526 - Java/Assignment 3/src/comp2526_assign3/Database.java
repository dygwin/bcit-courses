package comp2526_assign3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private List<Student> data;

    public Database() {
        data = new LinkedList<Student>();
    }

    public boolean add(String f, String l, String id) {
        Student temp = new Student(f, l, id);
        data.add(temp);
        return true;
    }

    public void display() {
        for (Student s : data)
            System.out.println(s);
    }

    public boolean readFromFile(String fileName){
        // whether data was successfully read from the file
		boolean success = false;
		// the Student to be read from the file
		Student s = null;
		// the BufferedInputStream used for reading from the file
		BufferedInputStream inputStream = null;
		
		// try to open the buffered FileInputStream for reading
		try {
			// opens a buffered FileInputStream for the file name specified
		    inputStream = new BufferedInputStream(new FileInputStream(fileName));
		    // creates a new StudentRecordReader using the BufferedInputStream 
			StudentRecordReader r = new StudentRecordReader(inputStream);
			
			// try to read Student objects using the StudentRecordReader
			try {
			    // as long as we are getting data, add Student objects using it
			    while ((s = r.readRecord()) != null) {
			        add(s.getFirstName(), s.getLastName(), s.getId());
			    }
			  
			  // we have successfully read data and added students to the database
			  success = true;
            } catch (IOException e) {
              // if an exception occurs, do nothing as success will remain false
            }
			success = true;
		} catch (FileNotFoundException e) {
		    // if the stream could not be opened, success will remain false
        } finally {
            // cleanup by closing the input stream (if open)
		    if (inputStream != null) {
		        try {
                    inputStream.close();
                } catch (IOException e) {
                    // if the input was open and fails to close, print stack trace
                    e.printStackTrace();
                }
		    }
		}
		
		// return whether the read was successful
		return success;
	}

    public void clearDB() {
        data = new LinkedList<Student>();
    }

    public boolean writeToFile(String fileName) {
        // whether the write was successful
		boolean success = false;
		// the BufferedOutputStream used in writing to a file
		BufferedOutputStream outputStream = null;
		// the StudentRecordReader that actually writes to the file
        StudentRecordWriter w = null;
		
		// try to open the BufferedOutputStream for writing
		try {
		    // open a buffered FileOutputStream at the specified fileName
			outputStream = new BufferedOutputStream(new FileOutputStream(fileName));
			// create a StudentRecordWriter using the newly created outputStream
			w = new StudentRecordWriter(outputStream);
			
			// writes each Student data from the database to the file
			for (Student s: data) {
			    w.writeRecord(s);
			}
			
			// sets success to true 
            success = true;
        } catch (IOException e) {
            // if the FileOutputStream fails to open, print a stack trace
            e.printStackTrace();
        } finally {
            // attempt to close the streams (if opened)
            if (outputStream != null) {
                try {
                    outputStream.close();
                    w.close();
                } catch (IOException e) {
                    // if the streams failed to close, print a stack trace
                    e.printStackTrace();
                }
            }
        }
		
		// return whether the read was successful
		return success;
	}
}
