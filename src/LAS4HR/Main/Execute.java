package LAS4HR.Main;


import LAS4HR.Data.*; 
import LAS4HR.GUI.*;
import LAS4HR.Person.*;
import LAS4HR.Utilities.*; 

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;

 

public class Execute {

	private JFrame frmMainFrame;
	
    
	public static void main(String[] args) {
		
       //------------------------------------------------------------------------------------------------------
       // display main form 
        EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Execute window = new Execute();
					window.frmMainFrame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
      //------------------------------------------------------------------------------------------------------
	}

	
	/**
	 * Create the application.
	 */
	public Execute() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//for testing only 		
		Staff LoEric = new Director      ("001", "Lo", "Eric", "123");		
		Staff LeungKevin = new Supervisor("003", "Leung", "Kevin", "123", LoEric);
		
		GlobalVar.gCurrentLogonUser = LeungKevin;		
		GlobalVar.gEmployeeDM = new  EmployeeDM();
		GlobalVar.gEmployeeDM.initial();

		GlobalVar.gLeaveRequestDB = new LeaveRequestDB();				
 	  	
		frmMainFrame = new  MainFrame();
		frmMainFrame.setTitle("Leave Application System for HR Dept");
		frmMainFrame.setBounds(100, 100, 800, 600);
		frmMainFrame.setLocationRelativeTo(null);
		frmMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		
	  	  
	}
}
