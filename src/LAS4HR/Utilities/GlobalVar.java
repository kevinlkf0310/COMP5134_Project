package LAS4HR.Utilities;

import LAS4HR.Person.*;  
import LAS4HR.Data.*;  

import java.text.SimpleDateFormat;


public class GlobalVar {
	
	public static Employee gCurrentLogonUser ;	
	public static EmployeeDM gEmployeeDM= new EmployeeDM();
	
	public static SimpleDateFormat gDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static LeaveRequestDB gLeaveRequestDB ;
	
	 
	
}
