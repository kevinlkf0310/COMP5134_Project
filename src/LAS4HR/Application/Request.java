package LAS4HR.Application;  

 
import LAS4HR.Person.*;
import LAS4HR.Enum.*;

public abstract class Request{ 
			
	protected int appID;
	protected int processResult = -1;
	
	protected Employee requestor; 
	protected String reason; 
	protected ProcessingStage processingStage; 
	protected Employee performer;
	private String notificationMsg = "";
	
	public Request(){
		
	}
	
	public Employee getRequestor(){
		 return this.requestor;
	}
	 
	public void setRequestor(Employee Requestor){
	 this.requestor = Requestor;
	}
	 
	 public String getReason(){
		 return this.reason;
	 }
	 
	 public void setReason(String Reason){
		 this.reason = Reason;
	 }
	 
	 public void setProcessingStage(ProcessingStage newStage){
		 this.processingStage = newStage;
	 }
	 
	 public ProcessingStage getProcessingStage(){
		 return this.processingStage;
	 }
	 
	 public void setAppID(int NewID){
		 this.appID = NewID;
	 }
	 
	 public int getAppID(){
		 return this.appID; 
	 }
	 
	 public void setResult (int result){
		 this.processResult  = result;
	 }
	 
	 public int getResult (){
		 return this.processResult;
	 }
	 
	 
	 public Employee getPerformer(){
		 return this.performer ;
	 } 
	 	 
	 public void setPerformer(Employee Performance){
		 this.performer = Performance;	
	 } 
	 
	 
	 public void setNotificationMsg(String message){
		 this.notificationMsg = message;
	 }
	 
	 public String getNotificationMsg(){
		 return this.notificationMsg;
	 } 
	 
}
