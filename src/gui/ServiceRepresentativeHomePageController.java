package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ServiceRepresentativeHomePageController implements Initializable  {

    @FXML
    private Button AddNewCustomer;

    @FXML
    private Button ExitBtn;

    @FXML
    private Label NameOfUserLabel;

    @FXML
    private Button SignOutbtn;
    private double xoffset;
	private double yoffset;
	private static ArrayList<String> arrFromServerRet;

    @FXML
    void pressAddNewCustomer(ActionEvent event) throws Exception {
    	Stage primaryStage = new Stage();
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		primaryStage.initStyle(StageStyle.UNDECORATED);
		ServiceRepresentativeAddNewCustomerController sracc = new ServiceRepresentativeAddNewCustomerController();
		sracc.start(primaryStage);
    }

    @FXML
    void pressExitBtn(ActionEvent event) throws IOException {
    	ArrayList<String> msg = new ArrayList<>();
		msg.add("quit");
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ClientUI.chat.accept(msg);
		System.exit(1);
    }

    @FXML
    void pressSignOut(ActionEvent event) throws Exception {
    	ArrayList<String> msg = new ArrayList<>();
    	msg.add("SignOut");
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ClientUI.chat.accept(msg);
		Stage primaryStage = new Stage();
		primaryStage.initStyle(StageStyle.UNDECORATED);
		LoginScreensController LSC = new LoginScreensController();
    	LSC.start(primaryStage);
    	
    }
    
 public void start(Stage primaryStage) throws Exception {
    	
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/ServicrRepresentativeHomePage.fxml"));
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
 
 
 	public static void getUserData(ArrayList<String> arrFromServer){
 		arrFromServerRet = arrFromServer;
 	}
 	
 
 	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<String> msg1 = new ArrayList<>();
		msg1.add("getUserDataServiceRep");
 	try {
			ClientUI.chat.accept(msg1);
		} catch (IOException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}

 		NameOfUserLabel.setText("Welcome back " + arrFromServerRet.get(0));
 		
 		
		
	}


}
