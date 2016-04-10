package LAS4HR.Person;
 
 

public class Staff extends Employee{

	public Staff(){
		super();
	}
	
	public Staff(String ID, String LastName, String FirstName, String Password, Employee Supervisor){
		super (ID, LastName, FirstName, Password, Supervisor);
	}
	 
	
}
