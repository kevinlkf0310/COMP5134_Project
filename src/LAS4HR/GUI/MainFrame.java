package LAS4HR.GUI;

 
import LAS4HR.Application.*;
import LAS4HR.Person.Director;
import LAS4HR.Person.Staff;
import LAS4HR.Person.Supervisor;
import LAS4HR.Utilities.*; 

import java.awt.event.*;
import javax.swing.*; 

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

 

public class MainFrame extends JFrame{
		
	private static final long serialVersionUID = 1L;
	
	 
	private LogonDlg logonDlg;
	private UserMgtDlg userMgtDlg;
	private LeaveReqDlg leaveReqDlg;
	//private ServiceConsole serviceConsole;
	 
	
	//---------------------------------------------------------------------
	//-- Functional button	
	private final JToolBar toolBar = new JToolBar();
	//private  ImageIcon icon = new ImageIcon("logout-icon.png");

	private final JButton btnUserMaintenance = new JButton("Staff Maintenance");
	private final JButton btnLeaveApplication = new JButton("Leave Application");
	private final JButton btnStartService = new JButton("Show Service Console");
	private final JButton btnStopService = new JButton("Hide Service Console");
	//---------------------------------------------------------------------
	
	//---------------------------------------------------------------------
	//-- Application button
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mFile = new JMenu("File");
	private final JMenuItem miLogin = new JMenuItem("Login");
	private final JMenuItem miLogout = new JMenuItem("Logout");
	private final JMenuItem miExit = new JMenuItem("Exit");
	//---------------------------------------------------------------------
	
	public MainFrame() throws java.text.ParseException{	
	    initUI();
	    
	    miLogin.setEnabled(true);
	    miLogout.setEnabled(false);
	    
	    SetFunctionEnable(false, false);
	    //SetFunctionEnable(true, true);
	    
	    
	    if (JOptionPane.showConfirmDialog(null, "Do you want to create dummy entry for [Leave Application Request] ?", "Question",
		           JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {					 
	    	
	        //Employee(                       ID,    LastName, FirstName, Password, Supervisor)
	  	    Staff LoEric = new Director      ("001", "Lo", "Eric", "123");		
	  		
	  	    Staff HuangEnyan = new Supervisor("002", "Huang", "Enyan", "123", LoEric);
	  	    Staff LeungKevin = new Supervisor("003", "Leung", "Kevin", "123", LoEric);
	  	    Staff LeePeter = new Supervisor  ("004", "Lee", "Peter", "123", HuangEnyan);
	  	    Staff WongMary = new Supervisor  ("005", "Wong", "Mary", "123", LeePeter);
	  		
	  	    Staff ChanTaiMan = new Staff     ("006", "Chan", "Tai Man", "123",WongMary);
	  	
	  	    //-----------------------------------------------------------------------
	  	    // dummy record for testing and demo
	  	    //gDateFormat = new SimpleDateFormat("yyyy-MM-dd");		 
	  	    LeaveRequest request = null;
	  		
	  	    //-----------------------------------------------------------------------
	  	    // Request #1
	  	    try {
	  		   request = LeungKevin.applyLeaveRequest(GlobalVar.gDateFormat.parse("2016-04-01"), GlobalVar.gDateFormat.parse("2016-04-01"), "DUMMY 01");
	  	    } catch (ParseException e) {
	  		   // TODO Auto-generated catch block
	  		   e.printStackTrace();
	  	    }
	  		
	  	    request.setRequestor(LeungKevin);	
	  	    GlobalVar.gLeaveRequestDB.add(request);
	   	    request.getRequestHandler().processRequest();
	  	    //-----------------------------------------------------------------------
	  		
	  	   //-----------------------------------------------------------------------
	  	   // Request #2
	  	   try {
	  		  	  request = LeungKevin.applyLeaveRequest(GlobalVar.gDateFormat.parse("2016-04-12"), GlobalVar.gDateFormat.parse("2016-04-12"), "DUMMY 02");
	  	   } catch (ParseException e) {
	  	      // TODO Auto-generated catch block
	  			 e.printStackTrace();
	  	   }
	  				
	  	   request.setRequestor(LeungKevin);	
	  	   GlobalVar.gLeaveRequestDB.add(request);
	  	   request.getRequestHandler().processRequest();
	  	   //-----------------------------------------------------------------------
	  		
	  	   //-----------------------------------------------------------------------
	  	   // Request #3
	  	   try {
	  		  request = ChanTaiMan.applyLeaveRequest(GlobalVar.gDateFormat.parse("2016-05-12"), GlobalVar.gDateFormat.parse("2016-05-17"), "DUMMY 03");
	  	   } catch (ParseException e) {
	  	   // TODO Auto-generated catch block
	  		  	e.printStackTrace();
	  	   }
	  						
	  	   request.setRequestor(ChanTaiMan);	
	  	   GlobalVar.gLeaveRequestDB.add(request);
	  	   request.getRequestHandler().processRequest();
	  	  //-----------------------------------------------------------------------
	    }
	  		
	}	
	   
	private void SetFunctionEnable(boolean blnUser, boolean blnApplication){
		btnUserMaintenance.setEnabled(blnUser);
		btnLeaveApplication.setEnabled(blnApplication);
	}
	
	private void initUI() {        
      createMenuBar();
      createToolBar();
      
      setSize(300, 200);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
	
	private void createMenuBar() {
		
		mFile.setMnemonic(KeyEvent.VK_F);
      menuBar.add(mFile);
      setJMenuBar(menuBar); 
      
      //create menu items        
      // ---------------------------------------------------------------------
      //-- create login menu
      miLogin.setMnemonic(KeyEvent.VK_I);
      miLogin.setActionCommand("Login");
              
      miLogin.setToolTipText("Login to System");        
      miLogin.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent event) {
        	 
          	logonDlg = new LogonDlg();
      		logonDlg.setVisible(true);	
      		
      		if (logonDlg.isLogon()){
      			 miLogin.setEnabled(false);
      			//miLogin.setEnabled(true);
      			 miLogout.setEnabled(true);
      			
      			 SetFunctionEnable(true, true);
      			setTitle("Leave Application System for HR Dept - " +  GlobalVar.gCurrentLogonUser.getFullName() + "(" + GlobalFuns.GetEmployeeType(GlobalVar.gCurrentLogonUser) + ")");
      		  
               		}
      		else{
      			miLogin.setEnabled(true);
      			miLogout.setEnabled(false);
      			SetFunctionEnable(false, false);        			
      			setTitle("Leave Application System");
      		}
      		 
          }
      });
      
      mFile.add(miLogin);
      // ---------------------------------------------------------------------
      
      
      // ---------------------------------------------------------------------
      //-- create logout menu       
      miLogout.setMnemonic(KeyEvent.VK_O);
      miLogout.setActionCommand("Logon");
      
      miLogout.setToolTipText("Logout from System");        
      miLogout.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent event) {
          	miLogin.setEnabled(true);
          	miLogout.setEnabled(false);
          	setTitle("Leave Application System");
              SetFunctionEnable(false, false);
          }
      });
      
      mFile.add(miLogout);
      // ---------------------------------------------------------------------
      
      // ---------------------------------------------------------------------
      //-- create exit menu       
      miExit.setMnemonic(KeyEvent.VK_E);
      miExit.setActionCommand("Exit");
      
      miExit.setToolTipText("Exit Application");        
      miExit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent event) {
           
          	JFrame frame = new JFrame();                
              int dialogButton = JOptionPane.YES_NO_OPTION;
              
              int answer = JOptionPane.showConfirmDialog(frame,  "Are you sure to exit ?","Application Exit", dialogButton);
              if (answer == JOptionPane.YES_OPTION) {
              	System.exit(0);
              }                
          }
      });
      
      mFile.add(miExit);
     // ---------------------------------------------------------------------
       
  }
  
  private void createToolBar() {
      
      toolBar.add(btnUserMaintenance);
      toolBar.add(btnLeaveApplication);     
   //   toolBar.add(btnStartService);
     // toolBar.add(btnStopService);
                
      btnUserMaintenance.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent event) {           
          	
          	userMgtDlg = new UserMgtDlg();
        	userMgtDlg.setPreferredSize(new Dimension(800, 600));
          	userMgtDlg.setTitle("Staff Maintenance");
          	userMgtDlg.pack();
          	userMgtDlg.setLocationRelativeTo(null); 
          	userMgtDlg.setVisible(true);
          	userMgtDlg.Show();
          	
          }
      });

      btnLeaveApplication.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent event) {        
        
          	leaveReqDlg = new LeaveReqDlg(GlobalVar.gCurrentLogonUser);
           	leaveReqDlg.setPreferredSize(new Dimension(800, 600));
           	leaveReqDlg.pack();
          	leaveReqDlg.setLocationRelativeTo(null); 
          	leaveReqDlg.setVisible(true);
           	
          }
      });
      
      btnStartService.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent event) {
       
          }
      });
      
      btnStopService.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent event) {
        	   
          }
      });
      
      
      add(toolBar, BorderLayout.NORTH);        
  }
}
