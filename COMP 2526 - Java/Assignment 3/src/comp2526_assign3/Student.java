package comp2526_assign3;

public class Student {//DO NOT ALTER
	private String firstName;
	private String lastName;
	private String id;
	
	public Student(String f, String l, String id) {
		firstName = f;
		lastName = l;
		this.id = id;
	}
	public String toString(){
		return firstName+" "+lastName+" "+id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
