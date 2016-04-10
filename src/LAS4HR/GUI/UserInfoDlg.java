package LAS4HR.GUI;

 
import LAS4HR.Utilities.*; 
import LAS4HR.Enum.*;
import LAS4HR.Person.*;
 

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
 

public class UserInfoDlg extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JLabel lblID;
	private JPanel buttonPane;
	private JComboBox<String> cboSupervisor;
	private JPasswordField pwdPassword;
	private JPasswordField pwdCfnPassword;
	private JLabel lblCfnPassword;
		
	private EditMode editMode ;
	private int editRow;
	private Staff currentStaff;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserInfoDlg() {
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		setBounds(100, 100, 380, 350);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						if (isValidData()){
							Staff supervisor = getSelectedSupervisor();
						
						    if (supervisor != null){
							   //----------------------------------------------------------------------------------------------------
							   //-- change the Staff or Supervisor Type 
							   if (((supervisor instanceof Supervisor) == false) && ((supervisor instanceof Director) == false)){
								   GlobalFuns.ShowMessageDialog("not supervisor", JOptionPane.INFORMATION_MESSAGE);
								   GlobalVar.gEmployeeDM.setStaffToSupervisor(supervisor);
							   }							     
							   //----------------------------------------------------------------------------------------------------
								   
							   if (currentStaff instanceof Supervisor){
								   currentStaff = new Supervisor(getID(), getLastName(), getFirstName(), getPassword(), supervisor);
							   }
							   else{
								  currentStaff = new Staff(getID(), getLastName(), getFirstName(), getPassword(), supervisor);
							   }							 
						    }
						    else{
							   currentStaff = new Director(getID(), getLastName(), getFirstName(), getPassword());
						    }
						
						    if (editMode == EditMode.EDIT){ 
							   GlobalVar.gEmployeeDM.setEmployee(editRow, currentStaff);
						    }
						    else{
							   GlobalVar.gEmployeeDM.addEmployee(currentStaff);
						    }
						    
						    GlobalFuns.ShowMessageDialog("Create / Modify [" + GlobalFuns.GetEmployeeType( currentStaff) + ": " + currentStaff.getFullName() +  "] successfully", JOptionPane.INFORMATION_MESSAGE);
						    setVisible(false);
						}
					}
				});
				
				btnSave.setFont(new Font("Arial", Font.BOLD, 12));
				btnSave.setActionCommand("Save");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				btnCancel.setFont(new Font("Arial", Font.BOLD, 12));
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		{
			lblID = new JLabel("Staff ID :");
			lblID.setFont(new Font("Arial", Font.BOLD, 12));
			lblID.setForeground(Color.BLUE);
		}
		
		txtID = new JTextField();
		txtID.setFont(new Font("Arial", Font.PLAIN, 12));
		txtID.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name :");
		lblLastName.setVerticalAlignment(SwingConstants.TOP);
		lblLastName.setForeground(Color.BLUE);
		lblLastName.setFont(new Font("Arial", Font.BOLD, 12));
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Arial", Font.PLAIN, 12));
		txtLastName.setColumns(10);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFirstName.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name :");
		lblFirstName.setVerticalAlignment(SwingConstants.TOP);
		lblFirstName.setForeground(Color.BLUE);
		lblFirstName.setFont(new Font("Arial", Font.BOLD, 12));
		
		JLabel lblSupervisor = new JLabel("Supervisor :");
		lblSupervisor.setVerticalAlignment(SwingConstants.TOP);
		lblSupervisor.setForeground(Color.BLUE);
		lblSupervisor.setFont(new Font("Arial", Font.BOLD, 12));
		 
		cboSupervisor = new JComboBox(LAS4HR.Utilities.GlobalVar.gEmployeeDM.getSupervisorList());
		cboSupervisor.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setVerticalAlignment(SwingConstants.TOP);
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 12));
		
		pwdPassword = new JPasswordField();
		pwdPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		pwdPassword.setEchoChar('*');
		
		pwdCfnPassword = new JPasswordField();
		pwdCfnPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		pwdCfnPassword.setEchoChar('*');
		
		lblCfnPassword = new JLabel("Confirm Pass :");
		lblCfnPassword.setVerticalAlignment(SwingConstants.TOP);
		lblCfnPassword.setForeground(Color.BLUE);
		lblCfnPassword.setFont(new Font("Arial", Font.BOLD, 12));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(lblSupervisor, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboSupervisor, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblCfnPassword, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(pwdCfnPassword)
												.addComponent(pwdPassword, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addComponent(lblID, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtID, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblID)
						.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLastName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName)
						.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(pwdPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pwdCfnPassword, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCfnPassword, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupervisor)
						.addComponent(cboSupervisor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	private String getID() {
	      return txtID.getText().trim();
	   }
	 
	private String getLastName() {
	        return txtLastName.getText().trim();
	    }
	 
	private String getFirstName() {
	        return txtFirstName.getText().trim();
	    }
	 
	private String getPassword() {
		 	return new String(pwdPassword.getPassword());
	    }
	 
	private String getConfirmPassword() {
	 	return new String(pwdCfnPassword.getPassword());
    }
	
	private Staff getSelectedSupervisor()
	 {
		 String ID;
		 int index ;
		 String selectedItem = cboSupervisor.getSelectedItem().toString();
		 
		 index = selectedItem.indexOf('-');
		 
		 if (index > 0) {
		     ID = selectedItem.substring(0,  index);
		 }
		 else{
			 ID = selectedItem; 
		 }
		
		 return GlobalVar.gEmployeeDM.getStaffByID((String)ID.trim());
	 }
	  
	public void setEditMode(EditMode NewMode){
		 this.editMode = NewMode;
	 }
	 
	public void setEditRow(int Row){
		 this.editRow = Row;
	 }
	 
	public void setEditStaff(Staff editStaff){
		 currentStaff = editStaff;
	 }
	 
	public void InitStaffInfo(){
		 
		 txtID.setText(currentStaff.getID());
		 txtLastName.setText(currentStaff.getLastName());
		 txtFirstName.setText(currentStaff.getFirstName());
		 		 
		 pwdPassword.setText(currentStaff.getPassword());
		 pwdCfnPassword.setText(currentStaff.getPassword());		 		 
		 
		 txtID.setEnabled(false);
		 if (currentStaff instanceof Director){
			 cboSupervisor.setSelectedItem("");
		 }
		 else{
			 if (currentStaff.getSupervisor() != null ){
			    cboSupervisor.setSelectedItem(currentStaff.getSupervisor().getID() + "-" + currentStaff.getSupervisor().getFullName());			 
			 }			
		 }		 
	 }
	 
	private Boolean isValidData(){
		Boolean validation = true;
		
		if (editMode == EditMode.INSERT){
			if (GlobalVar.gEmployeeDM.IsDuplicateID(getID())){
				validation = false;
				GlobalFuns.ShowMessageDialog("The new staff ID [" + getID() + "] is duplicated, please input again!", JOptionPane.ERROR_MESSAGE );
			}
		}
		
		if (getPassword().compareTo(getConfirmPassword()) != 0){
			validation = false;
			GlobalFuns.ShowMessageDialog("Password does not match, please input again!", JOptionPane.ERROR_MESSAGE );
		}
		
		
		return validation;
	}
}
