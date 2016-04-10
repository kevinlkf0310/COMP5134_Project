package LAS4HR.Person;

import LAS4HR.Application.*; 

import java.util.Date;

interface Personnel {

	public void setID(String ID);
	public String getID();
	
	public void setLastName(String LastName);
	public String getLastName();
	
	public void setFirstName(String FirstName);
	public String getFirstName();
	
	public void setPassoword(String Password);
	public String getPassword();
	
	public void setSupervisor(Employee Supervisor);
	public Employee getSupervisor();
		
	public Boolean logon();
	public Boolean logon(String ID, String Password);
	public LeaveRequest applyLeaveRequest(Date DateFrom, Date DateTo, String Reason);
	public Boolean endorseLeaveRequest(LeaveRequest Request);
	public Boolean declineLeaveRequest(LeaveRequest Request);
	
}
