package gui;

import java.io.IOException;
import java.util.ArrayList;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginScreensController {
	
	@FXML
    private Label ErrorUserPass;

    @FXML
    private Button LogInbtn;

    @FXML
    private Button RegisterMamberbtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    private String userNameStr;
    private String passwordStr;
    private double xoffset;
	private double yoffset;
	private static ArrayList<String> arrFromServerRet;

    @FXML
    void pressLogIn(ActionEvent event) throws IOException {
    	ArrayList<String> msg = new ArrayList<>();
    	userNameStr = username.getText();
    	passwordStr = password.getText();
    	
    	if(userNameStr.trim().isEmpty() || passwordStr.trim().isEmpty()) {
    		ErrorUserPass.setText("Invalid UserName Or Password !");
    	}
    	else {
    		msg.add("UserNameAndPasswordCheck");
    		msg.add(userNameStr);
    		msg.add(passwordStr);
    		ClientUI.chat.accept(msg);
    		
    		if(arrFromServerRet.get(0).equals("Error"))
			{
    			ErrorUserPass.setText("Username Or Password Incorrect");		
			}
			else {
				
				System.out.println("Student ID Found");
	
			 
				
			}
    	}
    }
    
    public static void subscriberDetails(ArrayList<String> arrFromServer){
    	arrFromServerRet = arrFromServer;
    }
    
    
    
    @FXML
    void pressRegisterMamber(ActionEvent event) {

    }
    
    
    
    public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/loginScreens.fxml"));
		
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
		primaryStage.setTitle("User Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
