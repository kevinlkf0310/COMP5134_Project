1.  How to execute the application

1.1	This project (application) would be run directly by double click  "LAS4HR.jar"
1.2	"COMP5134 Project - Quick Reference Guide.doc" will show the detail on how to use it.
1.3	Login account for testing

	Director:  
	Staff ID: 	001
	Pwd:      	123

	Supervisor:
	Staff ID:	002	
	Pwd:	123

	Staff ID: 	003
	Pwd:	123

	Normal Staff:
	Staff ID:	006
	Pwd:	123

2.  How to read the source code
	
2.1	There are eight subfolder under LAS4HR\src\LAS4HR

	(1) <<Application>> Subfolder: 
	      (a)  Define the abstract class of <<Request>> (Request.java), and <<LeaveRequest>> (LeaveRequest.java) class is extend from Request class
	      (b)  Abstract Request could be acting as base class for other request nature, e.g. PO Request, assume it follow similar workflow.
	
	(2) <<Data>> Subfolder: 
	     (a)  The classes under this folder are mainly used for data storage (e.g. Staff Info, Leave Application information) during application run time.
	         (b)  <<EmployeeDM>> class provide storeage for instance of Employee (Staff, Supervisor, and Director)
                           (c)  <<LeaveRequestDB>> class provide storage for instance of LeaveRequest, moreover, it extend Observable implements Observer for sending notification.
	         (d)  <<LeaveRequestDataView>> extend from <<LeaveRequestViewModel>>, it serves as providing the dataview to display record in "Leave Application" form.
	     
	(3) <<Enum>> Subfolder: 
	     (a)  Those classes mainly for define indicator by Enum type, it would standardize and meaningful to system
	        (b) <<ActionOnRequest>> 	: define the action performer by Staff, e.g. submit, endorse (approve) , and decline application
	        (c) <<EditMode>> 	: define the mode for "insert" or "modify" the staff record, it is mainly use in "UserInfoDlg" form
	        (d) <<ProcessingStage>>	: define the processing stage of an Leave Request, it help for implement "chain-of-responsibility" design pattern
	        (e) <<RequestRecordType>>	: define what kind of leave request record return to caller by "Requestor" or Performer (Supervisor), or all record  	

	(4) <<GUI>> Subfolder: 
	     (a)  All interface form (JFrame, JDialog) are consildated into this folder		
                            (b)  <<MainFrame>>			: main form of the application after startup
	          (c)  <<LogonDlg>>			: provide interface for staff to logon system	 		
	          (d)  <<UserMgtDlg>> and <<UserInfoDlg>>	: to display all current staff(s) record, and create / modify the desired staff info respectively.
	          (e)  <<LeaveReqDlg>> and <<LeaveDtlDlg>> 	: to display the corresponding LeaveRequest historical record, and pending process record(s), 
						  provide buttons for trigger to create "Leave Application Request", "Endorse" or Decline request.
	(5) <<Handler>> Subfolder: 
	      (a)  define the abstact class to handler (process) the request by "chain-of-responsibility" design pattern	   
                           (b) <<RequestHandler>>		: abstract class for define and implment Request Handler, it also extends Observable implements Observer 
				  	  for synchronize the status between different objects
	         (c) <<LeaveRequestHandler>>	: extend from <<RequestHandler>> to implement the "processRequest", the next supervisor/director is located 
					  dynamically it endorese fine previous supervisor.

   	(6) <<Main>> Subfolder: 
	      (a) it is the starting point of the application
	      (b) <<Execute>> will display the main form after startup
	
	(7) <<Person>> Subfolder: 
	       (a) It defines the blueprint of users (include Staff, Supervisor, and Director) in system.
	       (b) <<Employee>> is an abstract class, it also implement the interface <<Personnel>> to make sure each staff is same.
 	       (c) <<Staff>> extends from <<Employee>>
 	       (d) <<Supervisor>> extends (inherit) from <<Staff>>
 	       (e) <<Director>> extends (inherit) from <<Supervisor>>

	(8) <<Utilities>> Subfolder: 
                         (a):  <<GlobalVar>>	:  define the global variable
                         (b):  <<GlobalFuns>>	:  define the global function, e.g. pop up message box.
