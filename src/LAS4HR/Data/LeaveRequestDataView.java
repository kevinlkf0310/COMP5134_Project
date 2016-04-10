package LAS4HR.Data;


import LAS4HR.Person.*;
import LAS4HR.Utilities.*;
import LAS4HR.Enum.*;

import java.util.Observable;  
import java.util.Observer;
 
   

public class LeaveRequestDataView extends LeaveRequestViewModel implements Observer{
	
	private static final long serialVersionUID = 1L;
	private Employee owner;
	private RequestRecordType requestRecordType;
	
	public LeaveRequestDataView(Employee Owner, RequestRecordType  recordType){
		super();
		this.owner = Owner;
		 
		requestRecordType = recordType;
		update(null, null);
	}
	
	public LeaveRequestDataView(){
		super();		 
		update(null, null);
	}
	
	public Employee getOwner(){
		 return this.owner ;
	}
	
	public void setOwner(Employee owner){
		 this.owner = owner;
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		 
		
		if (owner == null){
			allRequests =  GlobalVar.gLeaveRequestDB.getHistoryAll();
		}
		else {
			if (requestRecordType == RequestRecordType.myHistory){
				allRequests = GlobalVar.gLeaveRequestDB.getHistoryByRequestor(this.owner);
			}
			else{
				allRequests = GlobalVar.gLeaveRequestDB.getPendingRequestByPerformer(this.owner);
			}
		}
		
    	fireTableRowsInserted(0, getRowCount());
    	fireTableDataChanged();  
    } 		
}
