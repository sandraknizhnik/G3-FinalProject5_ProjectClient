package client;

import java.io.IOException;
import java.util.ArrayList;

import logic.Subscriber;
import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient{
	// Class variables *************************************************
   	  ChatIF clientUI; 
	  public static Subscriber s = new Subscriber(null,null,null,null,null,null,null);
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
		  ArrayList<String> m =(ArrayList<String>) msg;
		  awaitResponse = false;
		  if(m.get(0).equals("quit")) {
			  m.clear();
			  quit();
		  }
		  if(m.get(0).equals("display")) {
			  m.remove(0); 
			  if(m.size()>1) {
				  s.setFirsname(m.get(0));
				  s.setLastName(m.get(1));
				  s.setId(m.get(2));
				  s.setPhone_number(m.get(3));
				  s.setEmail(m.get(4));
				  s.setCredit_card_number(m.get(5));
				  s.setSubscriber_number(m.get(6));
				  m.clear();
			  }
			  else {
				  s.setFirsname("Subscriber not found");
				  s.setLastName("Subscriber not found");
				  s.setId("Subscriber not found");
				  s.setPhone_number("Subscriber not found");
				  s.setEmail("Subscriber not found");
				  s.setCredit_card_number("Subscriber not found");
				  s.setSubscriber_number("Subscriber not found");
				  m.clear();
				  
			  }
		  }
		  
		  
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
