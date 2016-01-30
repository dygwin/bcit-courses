package comp2526_assign3;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.io.IOException;


public class StudentRecordWriter extends FilterOutputStream {
		private DataOutputStream ds;
		public StudentRecordWriter(OutputStream in) {
			super(in);
			ds = new DataOutputStream(in);
		}
		
		public void writeRecord(Student s) throws IOException{
		    // gets the data of a Student from the database
		    String data = s.getFirstName() + " " + s.getLastName() + " " + s.getId();
		    
		    // writes the Student data to the text file in UTF encoding
		    ds.writeUTF(data);
		}

}
