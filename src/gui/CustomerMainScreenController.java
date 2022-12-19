package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ClientController;
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

public class CustomerMainScreenController implements Initializable {
	
	
	
	public CustomerMainScreenController() {
		super();
		
		
	}
	@FXML
    private Label WelcomeLabel;
	
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
	private static ArrayList<String> arrFromServerRet;

	@FXML
    void PressNewOrder(ActionEvent event) throws Exception {
		Stage primaryStage = new Stage();
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		primaryStage.initStyle(StageStyle.UNDECORATED);
		MakingOrderScreenController mosc = new MakingOrderScreenController();
		mosc.start(primaryStage);		
	}

    @FXML
    void pressExitBTN(ActionEvent event) throws IOException {
    	ArrayList<String> msg = new ArrayList<>();
		msg.add("quit");
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ClientUI.chat.accept(msg);
		System.exit(1);
    }
    
    

    @FXML
    void pressOrderManagment(ActionEvent event) {
    	
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
    public static void getUserData(ArrayList<String> arrFromServer){
    	arrFromServerRet = arrFromServer;
    }
    
    
    public void start(Stage primaryStage) throws Exception {
    	
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/CustomerMainScreen.fxml"));
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<String> msg1 = new ArrayList<>();
		msg1.add("getUserData");
    	try {
			ClientUI.chat.accept(msg1);
		} catch (IOException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
    	WelcomeLabel.setText("Welcome back " + arrFromServerRet.get(1));
    	UserStatus.setText(arrFromServerRet.get(3));
    	if(UserStatus.getText().equals("NotApproved")) {
    		NewOrderBTN.setDisable(true);
    	}
		
	}

}
