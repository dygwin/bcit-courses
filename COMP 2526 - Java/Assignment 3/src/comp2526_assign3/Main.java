package comp2526_assign3;

public class Main {//DO NOT ALTER!!

	public static void main(String[] args) {
		Database d = new Database();
		if (d.readFromFile("test.dat"))
			d.display();
		else
			System.out.println("failed to open/read test file");
		d.clearDB();
		d.add("Nancy", "Drew", "A000314590");
		d.add("Foo","Bar","A005231455");
		d.add("Rusty", "Bed", "A001433917");
		if (d.writeToFile("writetest.dat")){
			d.clearDB();
			if (d.readFromFile("writetest.dat"))
				d.display();
			else
				System.out.println("failed to open/read writetest file");
		}
		else
			System.out.println("failed to write out db");

	}

}
