package LAS4HR.Handler;

import LAS4HR.Application.*; 
import LAS4HR.Enum.*;
import LAS4HR.Person.*;

import java.util.Observer; 
import java.util.Observable;   
import java.util.ArrayList;  

public abstract class RequestHandler extends Observable implements Observer {
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();  
	
 	protected ActionOnRequest actionPerform;
 	protected Employee performer; 
	protected RequestHandler nextHandler;
	
	public void setActionPerform(ActionOnRequest actionPerformer){
		this.actionPerform = actionPerformer;
	}
	
	public void add(RequestHandler next){
		if (nextHandler == null){
			this.nextHandler = next;
		}
		else{
			nextHandler.add(next);
		}			
	}
	
	public void setNextStep(RequestHandler root)
	{
		if (nextHandler == null)
			nextHandler = root;
		else
			nextHandler.setNextStep(root);
	}
	
	
	public Employee getPerformer()	{
		return  performer;
	}
	 
	public void setPerformer(Employee performer)	{
		  this.performer = performer;
	}
	
	public ArrayList<Observer> getObservers() {  
	        return observers;  
	}  

	public void setObservers(ArrayList<Observer> observers) {  
        this.observers = observers;  
    }  
	
    public void notifyObservers(Observable observable, LeaveRequest request) {  
         for (Observer ob : observers) {  
        	if (ob instanceof Employee){
               //GlobalFuns.Debug("notifyObservers --> observers --> " + ((Employee) ob).getID());
        	   //GlobalFuns.Debug("notifyObservers --> request.Performer --> " + request.getPerformer().getID());
        	           	   
        	   if (((Employee) ob).getID() ==  request.getPerformer().getID()) {
        		   ob.update(observable, request);
        	   }
            }
         }  
         
     	clearChanged();
    }  
  
    
    public void registerObserver(Observer observer) {  
         observers.add(observer);  
          
    }  
  
    public void removeObserver(Observer observer) {  
         observers.remove(observer);  
          
    }  
		
	abstract public void processRequest() ;
	//abstract protected Boolean actionOnRequest();

	public void update() {
		// TODO Auto-generated method stub
		
	}

}
