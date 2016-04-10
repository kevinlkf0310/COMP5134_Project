package LAS4HR.Handler;

import LAS4HR.Application.*; 
import LAS4HR.Enum.*;
import LAS4HR.Person.*;
import LAS4HR.Utilities.*;
 
import java.util.Observable;  

public class LeaveRequestHandler extends RequestHandler {
	
	protected  static LeaveRequest request = new LeaveRequest(); 

	@SuppressWarnings("static-access")
	public LeaveRequestHandler(Employee performer, LeaveRequest request, ActionOnRequest actionPerform){
		this.actionPerform = actionPerform;
		this.performer = performer;
		this.request = request;
		this.getRequest().setPerformer(performer);
	}


	@SuppressWarnings("static-access")
	public LeaveRequest getRequest(){
		return this.request;
	}
	
	public LeaveRequestHandler(Employee performer, ActionOnRequest actionPerform){
		this.actionPerform = actionPerform;
		this.performer = performer;
		this.getRequest().setPerformer(performer);
	}
	
	@Override
	public void processRequest() {
		
		GlobalFuns.Debug("<<processRequest>> : " + request.getAppID() + ": " +  this.performer.getFullName()  + " --> " + actionPerform.toString());
		
		//---------------------------------------------------------------
		//-- send notification to requestor for acknowledge confirmation
		if (actionPerform == ActionOnRequest.SUBMIT) {
			registerObserver(this.performer);
			request.setNotificationMsg("LAS4HR: Hello " + this.performer.getFullName() + ", your new request with ID - " + request.getAppID() + ", is received by system and being process.");
			notifyObservers(this, this.getRequest());
			deleteObserver(this.performer);
			request.setNotificationMsg("");
		}
		//---------------------------------------------------------------
		
		LeaveRequestHandler nextHandler  = null;
		
		if ((actionPerform == ActionOnRequest.SUBMIT) || (actionPerform == ActionOnRequest.ENDORSE)){
			//---------------------------------------------------------------
			//-- find direct or indirect supervisor as another performer to process
			Employee nextPerformer = this.performer.getSupervisor();
			
			//-- if direct or indirect supervisor as another performer to process
			if (nextPerformer!=null){
				
				GlobalFuns.Debug ("<<SUBMIT>> OR <<ENDORSE>> : next Performer --> " + nextPerformer.getFullName());
				
				//---------------------------------------------------------------
				// create next handler to process
				nextHandler = new LeaveRequestHandler(nextPerformer, ActionOnRequest.NOTIFY) ;				
				request.setPerformer(nextPerformer);
				this.performer = nextPerformer;
				request.setProcessingStage(ProcessingStage.WaitForPerformer);				
				request.setNotificationMsg("LAS4HR: Hello, " + request.getPerformer().getFullName() + ", there is a request with ID - " + request.getAppID() + ", is pending for your handling.");
				add(nextHandler);				
				//---------------------------------------------------------------
				
				GlobalFuns.Debug("Route to " + nextPerformer.getFullName());
			}
		    //---------------------------------------------------------------
		}
		else if (actionPerform == ActionOnRequest.APPROVED){
			//---------------------------------------------------------------
			// send notification to requestor to APPROVED acknowledgement
			request.setProcessingStage(ProcessingStage.Approved);	
			
			GlobalFuns.Debug(request.getAppID() + ": " +  actionPerform.toString() + " " + this.performer.getFullName()  + " --> " + actionPerform.toString());
			registerObserver(request.getRequestor());
			
			request.setNotificationMsg("LAS4HR: Hello, " + request.getRequestor().getFullName() + ", your request with ID " + request.getAppID() + ", is [APPROVED] by " + this.performer.getFullName() + ".");
			
			notifyObservers(this, this.getRequest());
			deleteObserver(request.getRequestor());
		}	
		else if (actionPerform == ActionOnRequest.DECLINE){
			//---------------------------------------------------------------
			// send notification to requestor to DECLINED acknowledgement			
			request.setProcessingStage(ProcessingStage.Declined);
			
			GlobalFuns.Debug(request.getAppID() + ": " +  actionPerform.toString() + " " +  this.performer.getFullName()  + " --> " + actionPerform.toString());
			registerObserver(request.getRequestor());
			request.setNotificationMsg("LAS4HR: Sorry, " + request.getRequestor().getFullName() + ", your request with ID " + request.getAppID() + ", is [DECLINED] by " + this.performer.getFullName() + ".");
			notifyObservers(this, this.getRequest());
			deleteObserver(request.getRequestor());
		}
	

		if (nextHandler!= null)
		{	
			nextHandler.processRequest();
			this.registerObserver(this.performer);
			notifyObservers(this, this.getRequest());
			this.deleteObserver(this.performer);
			
			request.setNotificationMsg("");
		} 
		
	} 

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
