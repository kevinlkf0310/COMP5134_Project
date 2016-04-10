package LAS4HR.Data;

 
import LAS4HR.Utilities.*; 
import LAS4HR.Application.*; 

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;  
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

abstract class LeaveRequestViewModel extends AbstractTableModel implements Observer{
	
	private static final long serialVersionUID = 1L;	
	
	String[] columnNames = {"ID", "Submitter", "Date From", "Date From", "Reason", "Performer", "Status"};
	
 	protected  List<LeaveRequest> allRequests = new ArrayList<LeaveRequest>();

	public LeaveRequestViewModel()	{
		super();
		allRequests  =  new ArrayList<LeaveRequest>();
	}
	
	@Override
    public int getRowCount() {
		return allRequests.size();
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
    	LeaveRequest row =  allRequests.get(rowIndex);
    	    	 
    	switch (columnIndex) {
    		case 0:  
    			return row.getAppID();
			
    		case 1:  
    			return row.getRequestor().getFullName();
    			
    		case 2:  
    			return GlobalVar.gDateFormat.format(row.getDateFrom());
    		
    		case 3:  
    			return GlobalVar.gDateFormat.format(row.getDateTo());
    		
    		case 4:  
    			return row.getReason();
    			
    		case 5:  
    			return row.getPerformer().getID() + " -- " + row.getPerformer().getFullName();    			
    			
    		case 6:  
    			return row.getProcessingStage().toString();
    			
    	    default: 
    	    	return row.getRequestor().getFullName();
    	}
    }
    
    @Override
	public void update(Observable arg0, Object arg1) {
    	allRequests = GlobalVar.gLeaveRequestDB.getHistoryAll();
    	
    	fireTableRowsInserted(0, getRowCount());
    	fireTableDataChanged();    	
     } 
    
    public LeaveRequest getRequestByAppID(int AppID) {
    	LeaveRequest foundRequest =  null;
    	
    	for (LeaveRequest request : allRequests) {
    		 if (request.getAppID() == AppID){
    			 foundRequest = request;
    			 break;
			 }
		}
    
    	return foundRequest;
	} 
      
}