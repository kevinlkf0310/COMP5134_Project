package LAS4HR.Person;

import LAS4HR.Utilities.*;
import LAS4HR.Application.*; 
import LAS4HR.Enum.*; 

import java.util.Date;
import javax.swing.JOptionPane;

import java.util.Observable;  
import java.util.Observer;  


abstract public class Employee implements Personnel, Observer{

	protected String id = "";
	protected String lastName = "";
	protected String firstName = "";
	protected String password = "";
	protected Employee supervisor = null;
	
	public Employee(){
	}
	
	public Employee(String ID, String LastName, String FirstName, String Password, Employee Supervisor){
		this.id = ID;
		this.lastName = LastName;
		this.firstName = FirstName;
		this.password = Password;
		this.supervisor = Supervisor;
	}
	
	
	@Override
	public void setID(String ID){
		this.id = ID;
	}
	
	@Override
	public String getID(){
		return this.id;	
	}
	
	@Override
	public void setLastName(String LastName){
		this.lastName = LastName;
	}
	
	@Override
	public String getLastName(){
		return this.lastName;
	}
	
	@Override
	public void setFirstName(String FirstName){
		this.firstName = FirstName;
	}
	
	@Override
	public String getFirstName(){
		return this.firstName;
	}
	
	@Override
	public void setPassoword(String Password){
		this.password = Password;
	}
	
	@Override
	public String getPassword(){
		return this.password;
	}
	
	@Override
	public void setSupervisor(Employee Supervisor){
		this.supervisor = Supervisor;
	}
	
	@Override
	public Employee getSupervisor(){
		return this.supervisor;
	}

	
	public String getFullName(){
		return  this.lastName + ' ' + this.firstName ;
	}
	 
	
	@Override
	public Boolean logon(String ID, String Password){
		this.id = ID;
		this.password = Password;
		return logon();
	}
		
		
	@Override
	public Boolean logon(){
		
		Boolean result = false;
		
		//hardcode admin user for testing
		if (this.id.equals("admin") && this.password.equals("password")) {
			result = true;
	    }
		else {
			GlobalVar.gCurrentLogonUser =  GlobalVar.gEmployeeDM.getStaffByID(this.id);
	    
			if (GlobalVar.gCurrentLogonUser != null){
				if (GlobalVar.gCurrentLogonUser.getPassword().compareTo(password) == 0){
					result = true;
				}
			}
		}
		
		return result;
	}
	
	@Override
	public LeaveRequest applyLeaveRequest(Date DateFrom, Date DateTo, String Reason){
		
		LeaveRequest request =  new LeaveRequest(this, DateFrom, DateTo, Reason, 
				ProcessingStage.Create);
	 			
		return request;

	}
	
	public Boolean endorseLeaveRequest(LeaveRequest request){ 
		
		Boolean result = false;
		
		if (this instanceof Director){
			request.getRequestHandler().setActionPerform(ActionOnRequest.APPROVED);
		}
		else{
			request.getRequestHandler().setActionPerform(ActionOnRequest.ENDORSE);
		}

		request.getRequestHandler().processRequest();
		GlobalFuns.Debug("Employee.endorseLeaveRequest, App ID --> " + request.getRequestHandler().getRequest().getAppID());
		
		result = true;
		return result;
	}
	
	public Boolean declineLeaveRequest(LeaveRequest request){
		Boolean result = false;
		
		request.getRequestHandler().setActionPerform(ActionOnRequest.DECLINE);
		request.getRequestHandler().processRequest();     
		
		result = true;
		return result;				
	}
	
 
	
	@Override
	public void update(Observable arg0, Object arg1) {
			  
	if (arg1 instanceof LeaveRequest){
	  GlobalFuns.ShowMessageDialog(((LeaveRequest) arg1).getNotificationMsg(), JOptionPane.INFORMATION_MESSAGE);
	}
	else{
		   GlobalFuns.ShowMessageDialog("Unkown message from [" + arg1.toString() + "]", JOptionPane.INFORMATION_MESSAGE);
		}
    } 
	 
}
