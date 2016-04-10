package LAS4HR.GUI;

import LAS4HR.Application.*;
import LAS4HR.Person.*;
import LAS4HR.Enum.*;
import LAS4HR.Utilities.*;
import LAS4HR.Data.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants; 


public class LeaveReqDlg extends JFrame{
	 
	 private static final long serialVersionUID = 1L;
	 
	 private  Employee fromStaff;
	 private  String endorseCaption = "";
	 private  LeaveRequestDataView myLeaveReqView;
	 private  LeaveRequestDataView myLeaveProcessingView;
	 private  LeaveRequestDataView myAllLeaveReqView;
	
	 private LeaveDtlDlg leaveDtlDlg;	
	 
	 //--------------------------------------------------------------------------
	 //Component(s) for main content
	 private JTabbedPane tabbedPane = new JTabbedPane();
	 //--------------------------------------------------------------------------
	 
	//--------------------------------------------------------------------------
	//Component(s) for {Your Request History}
	 private JButton btnNewRequest =new JButton("New Leave Request");
	 private JTable tbYourRequestLst ;
	 private JScrollPane sPaneTab1 ;
	 private JPanel pnlTab1;
	//--------------------------------------------------------------------------
	 
	//--------------------------------------------------------------------------
	//Component(s) for {Your Processing Request(s)}
	 private JButton btnEndorse = new   JButton("ENDORSE Request");
	 private JButton btnDecline = new JButton("DECLINE Request");
	 private JTable tbYourProcessing ;
	 private JScrollPane sPaneTab2 ;
	 private JPanel pnlTab2;
	 private JPanel pnlTab2_button;
	//--------------------------------------------------------------------------
		
	//--------------------------------------------------------------------------
	//Component(s) for {Your Processing Request(s)}
	 private JTable tbAllRequest;
	 private JScrollPane sPaneTab3 ;
	 private JPanel pnlTab3;
	//--------------------------------------------------------------------------
	 
	 private int selectedRow ;
	 private LeaveRequest selectedRequest ;
	 
	   
	 public LeaveReqDlg(Employee staff){
			btnEndorse.setEnabled(false);
			btnDecline.setEnabled(false);
			fromStaff = staff;
			
			setTitle("Leave Application System - " + fromStaff.getFullName() + "(" + GlobalFuns.GetEmployeeType(fromStaff) + ")");
			    
			 
			 if (fromStaff instanceof Director) {
				 endorseCaption=  "APPROVE";
			 }
			 else{
				 endorseCaption =  "ENDORSE";
			 }
			 
			myLeaveReqView = new  LeaveRequestDataView(fromStaff, RequestRecordType.myHistory);
			myLeaveProcessingView = new LeaveRequestDataView(fromStaff, RequestRecordType.myPending);
			myAllLeaveReqView = new  LeaveRequestDataView();
			
			GlobalVar.gLeaveRequestDB.registerObserver(myLeaveReqView);
			GlobalVar.gLeaveRequestDB.registerObserver(myLeaveProcessingView);
			GlobalVar.gLeaveRequestDB.registerObserver(myAllLeaveReqView);	
			 
			CreateActionListener();
			
			if (fromStaff instanceof Director){
				btnNewRequest.setEnabled(false);
				btnEndorse.setText(endorseCaption  + " Request");
			 }
			
			if ((fromStaff instanceof Director) || (fromStaff instanceof Supervisor)) {
				btnEndorse.setEnabled(true);
				btnDecline.setEnabled(true);
			}
			
			//-------------------------------------------------------------------------
			//setup {Your Request History} tab
			tbYourRequestLst = new JTable(myLeaveReqView );
			tbYourRequestLst.setRowMargin(4);
			tbYourRequestLst.setRowHeight(20);		 
			tbYourRequestLst.setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 12));
			tbYourRequestLst.getTableHeader().setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 14));
			 
			sPaneTab1= new JScrollPane(tbYourRequestLst);
			pnlTab1 =new JPanel(new BorderLayout());
					
			pnlTab1.add( sPaneTab1, BorderLayout.NORTH);
			pnlTab1.add(btnNewRequest, BorderLayout.SOUTH);
			//-------------------------------------------------------------------------
			
			//-------------------------------------------------------------------------
			//setup {Your Processing Request(s)}
			tbYourProcessing = new JTable(myLeaveProcessingView);
			tbYourProcessing.setRowMargin(4);
			tbYourProcessing.setRowHeight(20);		 
			tbYourProcessing.setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 12));
			tbYourProcessing.getTableHeader().setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 14));
			
			sPaneTab2= new JScrollPane(tbYourProcessing);		
			pnlTab2 = new JPanel(new BorderLayout());
			pnlTab2_button = new JPanel(new BorderLayout());
					
			pnlTab2_button.add(btnEndorse, BorderLayout.NORTH);
			pnlTab2_button.add(btnDecline, BorderLayout.SOUTH);
			
			pnlTab2.add(sPaneTab2, BorderLayout.NORTH);
			pnlTab2.add(pnlTab2_button, BorderLayout.SOUTH);
			//-------------------------------------------------------------------------
		
			//-------------------------------------------------------------------------
			//setup {All Request History} tab
			tbAllRequest = new JTable(myAllLeaveReqView);
			tbAllRequest.setRowMargin(4);
			tbAllRequest.setRowHeight(20);		 
			tbAllRequest.setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 12));
			tbAllRequest.getTableHeader().setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 14));
					 
			sPaneTab3 = new JScrollPane(tbAllRequest);
			pnlTab3 = new JPanel(new BorderLayout());
							
			pnlTab3.add( sPaneTab3, BorderLayout.NORTH); 
			//-------------------------------------------------------------------------
				
					
			//-------------------------------------------------------------------------
			//setup  tabbedPane	
			tabbedPane.addTab("Your Request History", pnlTab1);				
			tabbedPane.addTab("Your Processing Request(s)", pnlTab2);
			tabbedPane.addTab("ALL Requests (for Verification/Checking)", pnlTab3);
			
		    tabbedPane.setTabPlacement(SwingConstants.TOP);
		    tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		     
		    this.add(tabbedPane, BorderLayout.CENTER);	 
		    //-------------------------------------------------------------------------	    
		    
		    
			 //-----------------------------------------------------------------------------------------
			 // add Mouse ActionListener 
			 tbYourProcessing.addMouseListener(new java.awt.event.MouseAdapter()
			 { 
			    	 @Override
					public void mousePressed(java.awt.event.MouseEvent  me) { 
			    	        int row=tbYourProcessing.rowAtPoint(me.getPoint());
			    	        
			    	        String strID = tbYourProcessing.getValueAt(row,0).toString();
			    	        selectedRow = row;
			    	        
			    	        if (selectedRow < 0){
			    	        	strID = "";
			    	        }
			    	        
			    	        selectedRequest = myLeaveProcessingView.getRequestByAppID(Integer.parseInt(strID));
			    	    }
			       }
			      );
			 
	 } 
	  
	 
	 private void CreateActionListener(){
		//-----------------------------------------------------------------------------------------
		 // add ActionListener	 
		btnNewRequest.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
	
				leaveDtlDlg = new LeaveDtlDlg(fromStaff);
				leaveDtlDlg.pack();
				leaveDtlDlg.setLocationRelativeTo(null); 
				leaveDtlDlg.setVisible(true);	 
				
			 }
		 }); 
		
		
		//-----------------------------------------------------------------------------------------
		// add ActionListener	 
		btnEndorse.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 
				 if (selectedRequest != null){
				    if (JOptionPane.showConfirmDialog(null, fromStaff.getFirstName() + ", are you sure to " + endorseCaption  + " this request from [" + selectedRequest.getRequestor().getFullName()  + "] ?", "CONFIRMATION",
						           JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {					 
				    	  fromStaff.endorseLeaveRequest(selectedRequest);	
				    	  
					 	  GlobalFuns.Debug("ID: " + selectedRequest.getAppID() + " --> " + selectedRequest.getPerformer().getFullName());
				   }
				 }
				 else{
					 GlobalFuns.ShowMessageDialog("Please select the leave request before [ENDORSE].", JOptionPane.ERROR_MESSAGE);
				 }
			 }
		 });   
		
		//-----------------------------------------------------------------------------------------
		// add ActionListener	 
		btnDecline.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 if (selectedRequest != null){
				    if (JOptionPane.showConfirmDialog(null, fromStaff.getFirstName() + ", are you sure to DECLINE this request from [" + selectedRequest.getRequestor().getFullName()  + "] ?", "CONFIRMATION",
						           JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {					 
				    	 fromStaff.declineLeaveRequest(selectedRequest);	
				   }
				 }
				 else{
					 GlobalFuns.ShowMessageDialog("Please select the leave request before [DECLINE].", JOptionPane.ERROR_MESSAGE);
				 }
			 }
		 });   
	 }
	 
}