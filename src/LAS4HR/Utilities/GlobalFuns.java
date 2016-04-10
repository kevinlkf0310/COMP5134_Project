package LAS4HR.Utilities;
 

import javax.swing.*;

import LAS4HR.Person.Employee;

public class GlobalFuns {

	public static void ShowMessageDialog(String Message, int MessageType){
		
		switch (MessageType) {
		 case  JOptionPane.ERROR_MESSAGE:
			 ShowErrorMessageDialog(Message);
			 break;
			 
		 case  JOptionPane.WARNING_MESSAGE:
		 	 ShowWarningMessageDialog(Message);
			 break;	
		
		 case  JOptionPane.INFORMATION_MESSAGE:
			 ShowInformationMessageDialog(Message);
			 break;	
				 
		 default:
			 ShowDefaultMessageDialog(Message);
		     break;
		}		
	}
	
	public static String GetEmployeeType(Employee EmployeeType){
		int index ;
		String typeName = EmployeeType.toString();
		
	 	typeName = typeName.replace("LAS4HR.Person.", ""); 
	    index = typeName.indexOf('@');
		 
		if (index > 0) {
			 typeName = typeName.substring(0,  index);
		} 
		 
		return typeName;
	}
	
	private static void ShowErrorMessageDialog(String ErrorMsg){
		JOptionPane.showMessageDialog(null, ErrorMsg,  "Error", JOptionPane.ERROR_MESSAGE);
	}

	private static void ShowWarningMessageDialog(String WarningMsg){
		JOptionPane.showMessageDialog(null, WarningMsg,  "Warning", JOptionPane.WARNING_MESSAGE);
	}
	
	private static void ShowInformationMessageDialog(String InfoMsg){
		JOptionPane.showMessageDialog(null, InfoMsg,  "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private static void ShowDefaultMessageDialog(String Message){
		JOptionPane.showMessageDialog(null, Message);
	}

	public static void Debug(String Message){
		System.out.println(Message);
	}
}
