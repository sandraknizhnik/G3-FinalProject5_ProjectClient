package gui;


import gui.ClientGuiController;

import java.io.IOException;
import java.util.ArrayList;

import client.ClientController;
import client.ClientUI;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConnectController {
	// Class variables *************************************************
    @FXML
    private Button connect_btn;

    @FXML
    private TextField ip_server;
    
    private double xoffset;

	private double yoffset;
	//******************************************************************
	
	
	/**
	 * when connect button is pressed on client GUI , 
	 * this function will define new chat with the ip and defualt port and close connect view and open the gui view
	 * @param event
	 */
    @FXML
    void pressConnect(ActionEvent event) throws Exception {

    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		primaryStage.initStyle(StageStyle.UNDECORATED);
		
		ClientUI.setMyClientchat(new ClientController(ip_server.getText(), 5555));
		
		LoginScreensController LSC = new LoginScreensController();
		
    	ClientUI.getMyClientchat().openConnection();
    	LSC.start(primaryStage);
    	
    	
    }
    
    
    @FXML
	void prees_exit(ActionEvent event) throws IOException {
		
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		System.exit(1);
		
	}
    
    
    
    /**
	 * open connect gui
	 * @param event
	 */
    public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/connect.fxml"));
		
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
		primaryStage.setTitle("Connect");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
