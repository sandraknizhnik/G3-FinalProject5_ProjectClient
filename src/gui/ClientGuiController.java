package gui;

import java.io.IOException;
import java.util.ArrayList;

import client.ChatClient;
import client.ClientController;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.Subscriber;
import ocsf.server.ConnectionToClient;


public class ClientGuiController {
	
	// Class variables *************************************************
	private Subscriber s;
	@FXML
	private TextField creditNumber;
	@FXML
	private TextField Id;
	@FXML
	private Button display_btn;
	 @FXML
	private TextField id_display;

	@FXML
	private TextField subscriber_number;
	
	@FXML
	private TextArea textarea;

	@FXML
	private Button update_btn;
	
	@FXML
	private Button exit_btn;
	
	private double xoffset;
	private double yoffset;
	//******************************************************************
	
	/**
	 * when exit button is pressed on , 
	 * we hide the window and deliver the message to server
	 * @param event
	 */
	@FXML
	void prees_exit(ActionEvent event) throws IOException {
		ArrayList<String> msg = new ArrayList<>();
		msg.add("quit");
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		sendMessageToServer(msg);
		System.exit(1);
		
		
	/**
	 * function use chat in order to deliver message to server
	 * @param event
	 */	
	}
	public void sendMessageToServer(ArrayList<String> msg) throws IOException {
		ClientUI.chat.accept(msg);
	}
	
	/**
	 * when display button is pressed on , 
	 * this function will will send the data to server and when the data return will display him
	 * @param event
	 */
	@FXML
	void press_display(ActionEvent event) throws IOException {
		ArrayList<String> msg = new ArrayList<>();
		String id;
		id= getidDisplay();
		
		if(id.trim().isEmpty())
		{

			System.out.println("You must enter an id number");	
		}
		else
		{
			msg.add("display");
			msg.add(id);
			sendMessageToServer(msg);
			
			if(ChatClient.s.getFirstname().equals("Error"))
			{
				System.out.println("Student ID Not Found");
				loadsubscriber(ChatClient.s);
				
			}
			else {
				System.out.println("Student ID Found");
				loadsubscriber(ChatClient.s);
			
				
			}
		}
	}

	
	/**
	 * when update button is pressed on , 
	 * this function will will send the data to server and when the data return will display him
	 * @param event
	 */
	@FXML
	void press_update(ActionEvent event) throws IOException {

		ArrayList<String> msg = new ArrayList<>();
		String creditNumber, subscriber_number, id;
		creditNumber = getCreditNumber();
		subscriber_number = getSubscriberNumber();
		id = getid();
		msg.add("update");
		msg.add(id);
		msg.add(subscriber_number);
		msg.add(creditNumber);
		ClientUI.chat.accept(msg);

	}

	/**
	 * functions that get the input from the gui

	 */
	private String getCreditNumber() {
		return creditNumber.getText();
	}

	private String getid() {
		return Id.getText();
	}
	private String getidDisplay() {
		return id_display.getText();
	}

	private String getSubscriberNumber() {
		return subscriber_number.getText();
	}
	
	
	/**
	 * functions that load the data that return to the screen

	 */
	public void loadsubscriber(Subscriber s) {
		this.s=s;
		this.textarea.setText(s.toString());
	}	

	
	
	/**
	 * open the gui for display and update sub
	 * @param event
	 */
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/ClientGui.fxml"));
		//event handler for when the mouse is pressed AND dragged to move the window
		root.setOnMousePressed(event1 -> {
            xoffset = event1.getSceneX();
            yoffset = event1.getSceneY();
        });

        // event handler for when the mouse is pressed AND dragged to move the window
        root.setOnMouseDragged(event1 -> {
            primaryStage.setX(event1.getScreenX()-xoffset);
            primaryStage.setY(event1.getScreenY()-yoffset);
        });
        //
		Scene scene = new Scene(root);
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
