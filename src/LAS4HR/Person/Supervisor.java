package LAS4HR.Person;

public class Supervisor extends Staff{
	
	public Supervisor(){
		super();
	}
	
	public Supervisor(String ID, String LastName, String FirstName, String Password, Employee Supervisor){
		super (ID, LastName, FirstName, Password, Supervisor);
	}

}
