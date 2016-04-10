package LAS4HR.GUI;
 
import LAS4HR.Utilities.*; 
import LAS4HR.Enum.*;
import LAS4HR.Person.*; 

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;


public class UserMgtDlg  extends JFrame{

	 private UserInfoDlg userInfoDlg;	
	 private static final long serialVersionUID = 1L;
	 
	 private JTable tStaffLst ;
	 private TableColumnModel tcm;
	 
	 private JButton btnNew = new    JButton("Add New Staff");
	 private JButton btnEdit = new   JButton("  Edit Staff ");
	 private JButton btnDelete = new JButton(" Delete Staff");
	 private JButton btnClose = new  JButton("    Close    ");
	 JScrollPane scrollPane ;
	 
	 private int selectedRow = -1;
	 private Staff selectedStaff ;
	  
	 public UserMgtDlg(){
		
	 } 
	   
	 private void LoadDefaultData(){
		    
         tStaffLst  = new JTable(GlobalVar.gEmployeeDM);        
		 tcm = tStaffLst.getColumnModel();
		 
		 //-----------------------------------------------------------------------------------------
		 // initialize table layout
		 tStaffLst.setRowMargin(4);
		 tStaffLst.setRowHeight(20);		 
		 tStaffLst.setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 12));
		 tStaffLst.getTableHeader().setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 16));
		 
		 scrollPane = new JScrollPane(tStaffLst);
		     
		 for (int i = 0; i < (tcm.getColumnCount()); i++) {
		     tcm.getColumn(i).setPreferredWidth(150);
		 }
		     
		 tStaffLst.setPreferredScrollableViewportSize(tStaffLst.getPreferredSize());
		 tStaffLst.repaint();
		 scrollPane.repaint();		 
		 //-----------------------------------------------------------------------------------------
	 }
	 
	 public void Show(){
		 LoadDefaultData();
		   
		 //-----------------------------------------------------------------------------------------
		 // add ActionListener	 
		 btnNew.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
	
				userInfoDlg = new UserInfoDlg();
				userInfoDlg.setEditMode(EditMode.INSERT);
					
				userInfoDlg.setTitle("User Detail Information");
				userInfoDlg.pack();
				userInfoDlg.setLocationRelativeTo(null); 
				userInfoDlg.setVisible(true);	    	     
			 }
		 });
		
		 btnEdit.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				EditSelectStaff();
			 }
		 });
		
		 btnDelete.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {				 
				 String deleteStaffDtl =  GlobalFuns.GetEmployeeType(selectedStaff) + ": ";
				 
				 deleteStaffDtl +=  selectedStaff.getFullName();
				 
				 if (JOptionPane.showConfirmDialog(null, "Are you sure to delete [" + deleteStaffDtl  + "] ?", "WARNING",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					 GlobalVar.gEmployeeDM.removeEmployee(selectedRow);	
				 } 
			 }
		 });
		  
		 btnClose.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) { 
				 setVisible(false);	
			 }
		 });
		 
		 //-----------------------------------------------------------------------------------------
		 
		 JPanel btnPanel = new JPanel();
		     
		 btnPanel.add(btnNew);
		 btnPanel.add(btnEdit);
		 btnPanel.add(btnDelete);
		 btnPanel.add(btnClose);
		   
		 this.add(scrollPane, BorderLayout.CENTER);
		 this.add(btnPanel, BorderLayout.SOUTH);
		 this.pack();
		 this.setLocation(150, 150);
		 this.setVisible(true);
		 this.setBounds(100, 100, 800, 600);
		     
		 //-----------------------------------------------------------------------------------------
		 // add Mouse ActionListener 
		 tStaffLst.addMouseListener(new java.awt.event.MouseAdapter()
		 { 
		    	 @Override
				public void mousePressed(java.awt.event.MouseEvent  me) { 
		    	        int row=tStaffLst.rowAtPoint(me.getPoint());
			    	
		    	        selectedRow = row;
		    	        selectedStaff = LAS4HR.Utilities.GlobalVar.gEmployeeDM.getStaffByID(tStaffLst.getValueAt(row,0).toString());
		    	         
		    	        if (me.getClickCount() == 2) {
		    	        	EditSelectStaff();
		    	        }
		    	    }
		       }
		      );
	     }
	 
	 	 public void EditSelectStaff(){
			userInfoDlg = new UserInfoDlg();
			userInfoDlg.setEditMode(EditMode.EDIT);
						
			userInfoDlg.setTitle("User Detail Information");
			userInfoDlg.setEditStaff(selectedStaff);
			userInfoDlg.setEditRow(selectedRow);
			userInfoDlg.InitStaffInfo();
			userInfoDlg.pack();
			userInfoDlg.setLocationRelativeTo(null);
			userInfoDlg.setVisible(true);	   
	 	 }
	 	  
		
        //-----------------------------------------------------------------------------------------
}

