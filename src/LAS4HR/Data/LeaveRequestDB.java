package LAS4HR.Data;


import LAS4HR.Application.*;
import LAS4HR.Enum.ProcessingStage;
import LAS4HR.Person.*; 

import java.util.ArrayList;

import java.util.Observable;  
import java.util.Observer;

public class LeaveRequestDB extends Observable implements Observer{
	
	private static ArrayList<LeaveRequest> allRequests = new ArrayList<LeaveRequest>();
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();  
	
	public LeaveRequestDB()	{
		super();
		allRequests  =  new ArrayList<LeaveRequest>();	
	}
	
	public void add(LeaveRequest request){
		request.setAppID(this.getRequestNewID());
		
		allRequests.add(request);	
		setChanged();		
		notifyObservers(this, request);  
	}
 	 
	
	public ArrayList<LeaveRequest> getPendingRequestByPerformer(Employee  perform){
		ArrayList<LeaveRequest>  performerQueue =  new ArrayList<LeaveRequest>();

		for (LeaveRequest request : allRequests ){
		  if ((request.getPerformer().getID().compareTo(perform.getID()) == 0)  
				   && (request.getProcessingStage() == ProcessingStage.WaitForPerformer)){
			  performerQueue.add(request);		
		  }
		}
		
		return performerQueue;
	}
	
	public ArrayList<LeaveRequest> getHistoryByRequestor(Employee  requestor){
		ArrayList<LeaveRequest>  requestorHistory =  new ArrayList<LeaveRequest>();

		for (LeaveRequest request : allRequests ){
			  if (request.getRequestor().getID().compareTo(requestor.getID()) == 0){
				  requestorHistory.add(request);		
			  }
		}
		
		return requestorHistory;
	}
	
	public ArrayList<LeaveRequest> getHistoryAll(){
		
		/*
		ArrayList<LeaveRequest>  allQueue =  new ArrayList<LeaveRequest>();

		for (LeaveRequest request : allRequests){
			allQueue.add(request);		
		}
		
		return allQueue;
		*/
		
		return allRequests; 
	}
	
	
	 
	private int getRequestNewID() {
	    	int newID = 0;
	    	int maxID = -1;
	    	
	    	for (LeaveRequest request : allRequests) {
	    		 if (request.getAppID() >= maxID){
	    			 newID = request.getAppID();
				 }  			 
			}
	    
	    	return newID + 1;
	}
	
	 public int getRequestRowIndex(int AppID) {
	    int rowID = -1;
	    int index = 0;
	    	
	    for (LeaveRequest request : allRequests) {
	     if (request.getAppID() == AppID){ 
	    	 rowID = index;
	    	 break;
		 }
	     else{
	    	 index++;
	     }    			 
		}
	   
	   	return rowID;
	} 
	 
	 public ArrayList<Observer> getObservers() {  
	        return observers;  
	}  

	public void setObservers(ArrayList<Observer> observers) {  
     this.observers = observers;  
	}  
	
	
	public void notifyObservers(Observable observable, LeaveRequest request) {
		
	  for (Observer ob : observers) {
   		 ob.update(observable, request);
      }           
	  
	  clearChanged();   
	}  
 
	public void registerObserver(Observer observer) {  
		observers.add(observer);  
	}  

	public void removeObserver(Observer observer) {  
		observers.remove(observer);  
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	} 
 
}
