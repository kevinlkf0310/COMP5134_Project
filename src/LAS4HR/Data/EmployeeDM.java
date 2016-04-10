package LAS4HR.Data;


import LAS4HR.Person.*;


import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
 

public class EmployeeDM extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String[] columnNames = {"Staff ID", "Last Name", "First Name", "Supervisor"};
	private static ArrayList<Staff> allEmployee = new ArrayList<Staff>();

	public EmployeeDM()	{
		super();
		allEmployee  =  new ArrayList<Staff>();	
	}
	
	@Override
    public int getRowCount() {
        return allEmployee.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public String getColumnName(int columnIndex)
    { 
    	return columnNames[columnIndex];  
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Employee row =  allEmployee.get(rowIndex);

    	switch (columnIndex) {
    		case 0:  
    			return row.getID();
    			
    		case 1:  
    			return row.getLastName();
    		
    		case 2:  
    			return row.getFirstName();
    		
    		case 3:  
    			//return  this.getSupervisorName(row.getSupervisorID());
    			if (row.getSupervisor() != null){    			
    				return  row.getSupervisor().getFullName();
    			}
    			else
    			{
    				return "";
    			}
    			
    	    default: 
    	    	return row.getID();
    	}    	
    }

    public void addEmployee(Staff Employee) {              
    	allEmployee.add(Employee);
        fireTableRowsInserted(0, getRowCount());
    }
     
    
    public void removeEmployee(int rowIndex) {
    	allEmployee.remove(rowIndex);
    	fireTableRowsDeleted(rowIndex, rowIndex);
    	fireTableDataChanged();    	
    }
    
    
    //----------------------------------------------------------------------------------------------
    // default data for demo
    public void initial(){
      	
    	//Employee(                       ID,    LastName, FirstName, Password, Supervisor)
		Staff LoEric = new Director      ("001", "Lo", "Eric", "123");		
		
		Staff HuangEnyan = new Supervisor("002", "Huang", "Enyan", "123", LoEric);
		Staff LeungKevin = new Supervisor("003", "Leung", "Kevin", "123", LoEric);
		Staff LeePeter = new Supervisor  ("004", "Lee", "Peter", "123", HuangEnyan);
		Staff WongMary = new Supervisor  ("005", "Wong", "Mary", "123", LeePeter);
		
		Staff ChanTaiMan = new Staff     ("006", "Chan", "Tai Man", "123",WongMary);
		Staff LeeMango = new Staff       ("007", "Lee", "Mango", "123", WongMary);
		Staff AuApple = new Staff        ("008", "Au", "Apple", "123", LeungKevin);
				
		this.addEmployee(LoEric);
		this.addEmployee(HuangEnyan);
		this.addEmployee(LeungKevin);
		this.addEmployee(LeePeter);
		this.addEmployee(WongMary);
		this.addEmployee(ChanTaiMan);
		this.addEmployee(LeeMango);
		this.addEmployee(AuApple);
		
	}
   //----------------------------------------------------------------------------------------------
 
    public Staff getStaffByID(String ID) {
    	
    	Staff foundStaff =  null;
    	
    	for (Staff staff : allEmployee) {
    		 if (staff.getID().compareTo(ID)==0){
    			 if (staff instanceof Director){
    				 foundStaff = new Director();
				 }
				 else if (staff instanceof Supervisor){
					 foundStaff = new Supervisor();
				 }
				 else if (staff instanceof Staff){
					 foundStaff =  new Staff();
				 }
    			 
    			 foundStaff = staff;
    			 break;
    		 }
    	}

    	return foundStaff;
	}
 
    public String[] getSupervisorList(){
    	
    	String [] supervisorList = new String[getRowCount()+1];
    	
    	int counter = 0;
    	for (Staff staff : allEmployee) {
    		supervisorList[counter] = staff.getID() + "-" +  staff.getFullName();
    		counter++;    		
    	}

    	supervisorList[counter] = "";		
    	return supervisorList;
    }
    
    
    public void setEmployee(int RowIndex, Staff Employee){
    	allEmployee.set(RowIndex, Employee);
    	fireTableDataChanged();   
    }
    
    public Boolean IsDuplicateID(String ID){
    	Boolean duplicate = false;
    	
    	for (Staff staff : allEmployee) {
    		if (staff.getID().compareTo(ID) == 0){
    			duplicate = true;
    		}
    	}
    	
    	return duplicate;
    }
    
    public void setStaffToSupervisor(Staff Employee){
    	Staff foundStaff =  null;
    	int rowIndex;    	
    	rowIndex = 0;
    	
    	for (Staff staff : allEmployee) {
    		 if (staff.getID().compareTo(Employee.getID())==0){
    			 if (staff instanceof Director){
    				 foundStaff = new Director();  
				 }
				 else{
					 foundStaff = new Supervisor();  
				 }
    			 
    			 foundStaff.setID(Employee.getID());
    			 foundStaff.setFirstName(Employee.getFirstName());
    			 foundStaff.setLastName(Employee.getLastName());
    			 foundStaff.setPassoword(Employee.getPassword());
    			 foundStaff.setSupervisor(Employee.getSupervisor());
    			  
    			 break;
    		 }
    		 else{
    			 rowIndex++;
    		 }
    	}
 	   
    	setEmployee(rowIndex, foundStaff);	
    }
    
}
