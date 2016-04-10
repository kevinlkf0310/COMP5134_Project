package LAS4HR.Application;


import LAS4HR.Person.*;
import LAS4HR.Enum.*;
import LAS4HR.Handler.*;

import java.util.Date;

public class LeaveRequest extends Request {
	 protected Date dateFrom = new Date();
	 protected Date dateTo= new Date();
	 protected LeaveRequestHandler  handler;
	 
	 public LeaveRequest() {
	 }
	  
	 public LeaveRequest(Employee Requestor, Date LeaveFrom, Date LeaveTo, String Reason,  ProcessingStage Stage) {
		 this.requestor = Requestor;
		 this.dateFrom = LeaveFrom ;
		 this.dateTo = LeaveTo ;
		 this.reason = Reason; 
		 this.processingStage = Stage;	
		 this.performer = Requestor.getSupervisor();
		 this.appID = -1;
		 
		 handler = new LeaveRequestHandler(Requestor, this, ActionOnRequest.SUBMIT );
	 }
	   
	 
	 public Date getDateFrom(){
		 return this.dateFrom;
	 }
	 
	 public void setDateFrom(Date DateFrom){
		 this.dateFrom = DateFrom;
	 }
	 
	 public Date getDateTo(){
		 return this.dateTo;
	 }
	 
	 public void setDateTo(Date DateTo){
		 this.dateTo = DateTo;
	 }
	 
	 public LeaveRequestHandler getRequestHandler(){
		 return handler;
	 }
}
