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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ServiceRepresentativeAddNewCustomerController {

    @FXML
    private Button BackBTN;

    @FXML
    private TextField CVVOnCard;

    @FXML
    private TextField CreditCardNumber;

    @FXML
    private TextField EmailText;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField ID;

    @FXML
    private TextField LastName;

    @FXML
    private ComboBox<?> MonthOnCard;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private Button SubmitBTN;

    @FXML
    private ComboBox<?> YearOnCard;

    @FXML
    private ComboBox<?> chooseCustomerType;
    private double xoffset;
	private double yoffset;

    @FXML
    void pressBackBTN(ActionEvent event) throws Exception {
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		primaryStage.initStyle(StageStyle.UNDECORATED);
		ServiceRepresentativeHomePageController srhpc = new ServiceRepresentativeHomePageController();
		srhpc.start(primaryStage);

    }

    @FXML
    void pressSubmitBTN(ActionEvent event) throws IOException {
    	ArrayList<String> msg = new ArrayList<>();
		msg.add("quit");
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ClientUI.chat.accept(msg);
		System.exit(1);

    }
    
    public void start(Stage primaryStage) throws Exception {
    	
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/ServiceRepresentativeAddNewCustomer.fxml"));
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
