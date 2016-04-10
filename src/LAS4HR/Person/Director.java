package LAS4HR.Person;
 


import javax.swing.JOptionPane;

import LAS4HR.Utilities.GlobalFuns;

public class Director extends Supervisor{

	public Director(){
		super();
	}
	 
	public Director(String ID, String LastName, String FirstName, String Password){
		super (ID, LastName, FirstName, Password, null);
	}
		
	@Override 
	public Employee getSupervisor(){
		return null;
	}
	
	@Override 
	public void setSupervisor(Employee Supervisor){
		GlobalFuns.ShowMessageDialog("Director cannot assign Supervisor", JOptionPane.ERROR_MESSAGE); 
	}
}
