package gui;

import java.io.IOException;
import java.util.ArrayList;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerMainScreenController {

    @FXML
    private Button NewOrderBTN;

    @FXML
    private Button OrderManagmantBTN;

    @FXML
    private Button SignOutbtn;

    @FXML
    private Label UserStatus;

    @FXML
    private Button exitBTN;
    private double xoffset;
	private double yoffset;
	private String userNameStr;
    @FXML
    void PressNewOrder(ActionEvent event) {

    }

    @FXML
    void pressExitBTN(ActionEvent event) throws IOException {
    	System.out.println(userNameStr);
    	ArrayList<String> msg = new ArrayList<>();
    	signOutFromDB(this.userNameStr);
		msg.add("quit");
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ClientUI.chat.accept(msg);
		System.exit(1);

    }
    void signOutFromDB(String userName) throws IOException {
    	ArrayList<String> msg = new ArrayList<>();
    	msg.add("signOutUser");
    	msg.add(userName);
    	ClientUI.chat.accept(msg);
    }
    

    @FXML
    void pressOrderManagment(ActionEvent event) {
    }

    @FXML
    void pressSignOut(ActionEvent event) throws IOException {
    	signOutFromDB(this.userNameStr);
    }
    
    public void start(Stage primaryStage, String userNameStr) throws Exception {
    	
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/CustomerMainScreen.fxml"));
		this.userNameStr = userNameStr;
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
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
