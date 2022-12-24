package client;

import java.io.IOException;
import java.util.ArrayList;

import gui.AreaManagerHomePageController;
import gui.AreaManagerReportViewController;
import gui.CustomerMainScreenController;
import gui.LoginScreensController;
import gui.SetMinimumLevelController;
import javafx.collections.ObservableList;
import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient{
	// Class variables *************************************************
   	  ChatIF clientUI; 
	  public static Subscriber subscriber1 = new Subscriber(null,null,null,null,null,null,null,null,null,null);
	  public static boolean awaitResponse = false;
	//******************************************************************
	  
	  
	  //Constructors ****************************************************
	  /**
	   * Constructs an instance of the chat client.
	   *
	   * @param host The server to connect to.
	   * @param port The port number to connect on.
	   * @param clientUI The interface type variable.
	   */
		 
	  public ChatClient(String host, int port, ChatIF clientUI) 
	    throws IOException 
	  {
	    super(host, port); //Call the superclass constructor
	    this.clientUI = clientUI;
	  }

	  //Instance methods ************************************************
	  
	  /**
	   * This method handles all data that comes in from the server.
	   *
	   * @param msg The message from the server.
	   */
	  public void handleMessageFromServer(Object msg) 
	  {
		  System.out.println("--> handleMessageFromServer");
		  ArrayList<String> massageFromServer =(ArrayList<String>) msg;
		  awaitResponse = false;
		  String action = massageFromServer.get(0);
		  massageFromServer.remove(0);
		  switch(action) {
		  case "getUserData":
			  CustomerMainScreenController.getUserData(massageFromServer);
			  break;
		  case "getUserDataAreaManager":
			  AreaManagerHomePageController.getUserData(massageFromServer); 
			  break;
		  case "quit":
			  massageFromServer.clear();
			  quit();
			  break;
		  case "userNameAndPasswordRetVal":
			  LoginScreensController.subscriberDetails(massageFromServer);
			  break;
		  case "getMachineNumber":
			  AreaManagerReportViewController.getMachineData(massageFromServer);
			  break;
		  case "getMachineNumberForSetMinimum":
			  SetMinimumLevelController.getMachineDataMinimumLevel(massageFromServer);
			  break;
		  case "getOrderReportDetails":
			  AreaManagerReportViewController.getOrderReportData(massageFromServer);
			  break;
		  case "getCustomersReportDetails":
			  AreaManagerReportViewController.getCustomersReportData(massageFromServer);
			  break;
		  case "getInventoryReportDetails":
			  AreaManagerReportViewController.getInventoryReportData(massageFromServer);
			  break;
		
		
		  }
		  
		 /*
		  if(massageFromServer.get(0).equals("userNameAndPasswordRetVal")) {
			  
			  if(massageFromServer.size()>1) {
				  subscriber1.setFirsname(massageFromServer.get(0));
				  subscriber1.setLastName(massageFromServer.get(1));
				  subscriber1.setId(massageFromServer.get(2));
				  subscriber1.setPhone_number(massageFromServer.get(3));
				  subscriber1.setEmail(massageFromServer.get(4));
				  subscriber1.setCredit_card_number(massageFromServer.get(5));
				  subscriber1.setSubscriber_number(massageFromServer.get(6));
				  massageFromServer.clear();
			  }
			  else {
				  subscriber1.setFirsname("Subscriber not found");
				  subscriber1.setLastName("Subscriber not found");
				  subscriber1.setId("Subscriber not found");
				  subscriber1.setPhone_number("Subscriber not found");
				  subscriber1.setEmail("Subscriber not found");
				  subscriber1.setCredit_card_number("Subscriber not found");
				  subscriber1.setSubscriber_number("Subscriber not found");
				  massageFromServer.clear();
				  }
			  }*/
		  
		  
		  
	  }

	  /**
	   * This method handles all data coming from the UI            
	   *
	   * @param message The message from the UI.    
	   */ 
	 @SuppressWarnings("unchecked")
	public void handleMessageFromClientUI(Object message)  
	  {
		
		message=(ArrayList<String>) message;
	    try
	    {
	    	
	    	openConnection();//in order to send more than one message
	       	awaitResponse = true;
	    	 
	    	sendToServer(message);
			//  wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	      clientUI.display("Could not send message to server: Terminating client."+ e);
	      quit();
	    }
	  }

	  
	  /**
	   * This method terminates the client.
	   */
	  public void quit()
	  {
	    try
	    {
	      closeConnection();
	    }
	    catch(IOException e) {}
	    System.exit(0);
	  }

}
