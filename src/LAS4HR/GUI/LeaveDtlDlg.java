package LAS4HR.GUI;


import LAS4HR.Application.LeaveRequest; 
import LAS4HR.Person.Employee;
import LAS4HR.Utilities.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.Font;
import java.awt.Color;
import java.util.Date;
import javax.swing.text.MaskFormatter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LeaveDtlDlg extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtReason; 
	private JLabel lblDateFrom;
	private JPanel buttonPane;

	private JFormattedTextField fDateFrom;
	private JFormattedTextField fDateTo;
	
	 private Employee fromStaff;
	 
	private Date dateFrom;
	private Date dateTo ;
	
	/**
	 * Create the dialog.
	 */
	public LeaveDtlDlg(Employee staff) {
		fromStaff = staff;
		setBounds(100, 100, 600, 300);
		
		setTitle("Apply Leave Request - " + fromStaff.getFullName() + "(" + GlobalFuns.GetEmployeeType(fromStaff) + ")");
	       
		 
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new CompoundBorder());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblDateFrom = new JLabel(" Date From: ");
			lblDateFrom.setForeground(Color.BLUE);
			lblDateFrom.setFont(new Font("Arial", Font.BOLD, 12));
		}
		
		JLabel label = new JLabel("");		
		JLabel label_1 = new JLabel("");
		
		txtReason = new JTextField();
		txtReason.setFont(new Font("Arial", Font.PLAIN, 12));
		txtReason.setColumns(10);
		
		JLabel label_2 = new JLabel("");	
	
		
		fDateFrom = new JFormattedTextField(GlobalVar.gDateFormat);
		fDateFrom.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblDateTo = new JLabel(" Date To: ");
		lblDateTo.setForeground(Color.BLUE);
		lblDateTo.setFont(new Font("Arial", Font.BOLD, 12));
		
		fDateTo = new JFormattedTextField(GlobalVar.gDateFormat);
		fDateTo.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblReason = new JLabel("Reason:");
		lblReason.setForeground(Color.BLUE);
		lblReason.setFont(new Font("Arial", Font.BOLD, 12));
		
		
		
		try {
          MaskFormatter dateMask = new MaskFormatter("####-##-##");
          dateMask.install(fDateFrom);
          dateMask.install(fDateTo);
           
  		  txtReason.setText("Demo...");
  		
          {
          	buttonPane = new JPanel();
          	buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
          	{
          		JButton btnOK = new JButton("OK");
          		btnOK.addActionListener(new ActionListener() {
          			@Override
					public void actionPerformed(ActionEvent e) {
          		
          				dateFrom = (Date)  fDateFrom.getValue();
          				dateTo = (Date)  fDateTo.getValue();
	 		
          				setVisible(false);
          				LeaveRequest request = fromStaff.applyLeaveRequest(dateFrom, dateTo, txtReason.getText().trim() );
          				
          				request.setRequestor(fromStaff);
          				GlobalFuns.ShowMessageDialog("Your leave request has been created and submit !", JOptionPane.INFORMATION_MESSAGE); 
           				
          				GlobalVar.gLeaveRequestDB.add(request);
          				request.getRequestHandler().processRequest();
          				
          				/*
          				LeaveRequestHandler nextHandler = new LeaveRequestHandler(fromStaff.getSupervisor(), ActionOnRequest.NOTIFY) ;
          				request.getRequestHandler().add(nextHandler );
          				request.getRequestHandler().processRequest();
          				*/     
          			}
          		});
          		btnOK.setFont(new Font("Arial", Font.PLAIN, 12));
          		btnOK.setActionCommand("OK");
          		buttonPane.add(btnOK);
          		getRootPane().setDefaultButton(btnOK);
          	}
          	{
          		JButton btnCancel = new JButton("Cancel");
          		btnCancel.addActionListener(new ActionListener() {
          			@Override
					public void actionPerformed(ActionEvent e) {
          				 setVisible(false);
          			}
          		});
          		btnCancel.setFont(new Font("Arial", Font.PLAIN, 12));
          		btnCancel.setActionCommand("Cancel");
          		buttonPane.add(btnCancel);
          	}
          }
          GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
          gl_contentPanel.setHorizontalGroup(
          	gl_contentPanel.createParallelGroup(Alignment.LEADING)
          		.addGroup(gl_contentPanel.createSequentialGroup()
          			.addGap(183)
          			.addComponent(label)
          			.addGap(4)
          			.addComponent(label_1)
          			.addGap(124)
          			.addComponent(label_2))
          		.addGroup(gl_contentPanel.createSequentialGroup()
          			.addGap(29)
          			.addComponent(lblDateTo)
          			.addGap(28)
          			.addComponent(fDateFrom, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
          		.addGroup(gl_contentPanel.createSequentialGroup()
          			.addGap(28)
          			.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
          				.addComponent(lblDateFrom, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
          				.addGroup(gl_contentPanel.createSequentialGroup()
          					.addComponent(lblReason)
          					.addGap(35)
          					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
          						.addComponent(txtReason, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
          						.addComponent(fDateTo, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))))
          		.addGroup(gl_contentPanel.createSequentialGroup()
          			.addGap(10)
          			.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE))
          );
          gl_contentPanel.setVerticalGroup(
          	gl_contentPanel.createParallelGroup(Alignment.LEADING)
          		.addGroup(gl_contentPanel.createSequentialGroup()
          			.addGap(12)
          			.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
          				.addComponent(label)
          				.addComponent(label_1)
          				.addComponent(label_2))
          			.addGap(21)
          			.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
          				.addComponent(lblDateTo)
          				.addComponent(fDateFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
          			.addGap(10)
          			.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
          				.addComponent(fDateTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
          				.addComponent(lblDateFrom, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
          			.addPreferredGap(ComponentPlacement.RELATED)
          			.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
          				.addComponent(txtReason, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
          				.addComponent(lblReason))
          			.addGap(49)
          			.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
          );
          contentPanel.setLayout(gl_contentPanel);
      } catch (Exception ex) {
          
      }
	}
	
	
}
