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
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AreaManagerHomePageController implements Initializable{

	 @FXML
	 private ImageView approveRequestImg;

	 @FXML
	 private Button approveRequestsBtn;

	 @FXML
	 private ImageView ekrutLogoImage;

	 @FXML
	 private ImageView executionOrderImg;

	 @FXML
	 private Button exitBtn;

	 @FXML
	 private Label lastUpdatesLable;

	 @FXML
	 private TextArea lastUpdatesTextArea;

	 @FXML
	 private Button logOutbtn;

	 @FXML
	 private ImageView managmentAreaImage;

	 @FXML
	 private Label managmentAreaLable;

	 @FXML
	 private ImageView minLevelImage;

	 @FXML
	 private ImageView refreshImage;

	 @FXML
	 private Button sendMsgToOperationBtn;

	 @FXML
	 private Button setMinimummachinelevelBtn;

	 @FXML
	 private Label specificAreaLable;

	 @FXML
	 private ImageView watchReportImage;

	 @FXML
	 private Button watchReportsBtn;

	 @FXML
	 private Label welcomeBackLable;
	    
    private double xoffset;
	private double yoffset;
	private static ArrayList<String> arrFromServerRet;

	// press exit button from area manager page
    @FXML
    void pressExitBtn(ActionEvent event) throws IOException {
    	ArrayList<String> msg = new ArrayList<>();
		msg.add("quit");
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ClientUI.chat.accept(msg);
		System.exit(1);
    }

	// press logOut button from area manager page
    @FXML
    void pressLogOut(ActionEvent event) throws Exception {
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
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/AreaManagerHomePage.fxml"));
		
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
		msg1.add("getUserDataAreaManager");
    	try {
			ClientUI.chat.accept(msg1);
		} catch (IOException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
    	welcomeBackLable.setText("Welcome back " + arrFromServerRet.get(1));
    	specificAreaLable.setText(arrFromServerRet.get(3));
	}
    
	//show next window for choosing reports
    @FXML
    void pressWatchReports(ActionEvent event) throws Exception {
    	Stage primaryStage = new Stage();
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		primaryStage.initStyle(StageStyle.UNDECORATED);
		AreaManagerReportViewController amrvc = new AreaManagerReportViewController();
		amrvc.start(primaryStage);
		
    }
    
    //show next window for setting minimum level
    @FXML
    void pressSetMinimummachinelevel(ActionEvent event) throws Exception {
    	Stage primaryStage = new Stage();
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		primaryStage.initStyle(StageStyle.UNDECORATED);
		SetMinimumLevelController smlc = new SetMinimumLevelController();
		smlc.start(primaryStage);
    }  

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    void pressApproveRequests(ActionEvent event) {

    }

    @FXML
    void pressExecutionOrder(ActionEvent event) {

    }







}
