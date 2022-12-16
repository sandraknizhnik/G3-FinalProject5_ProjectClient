package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ServiceRepresentativeAddNewCustomerController implements Initializable{

    @FXML
    private Button BackBTN;
    
    @FXML
    private TextField userName;
    
    @FXML
    private Label ERRORFILEDEMPTY;

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
    private ComboBox<String> MonthOnCard;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private Button SubmitBTN;
    
    @FXML
    private Button exitBTN;

    @FXML
    private ComboBox<String> YearOnCard;

    @FXML
    private ComboBox<String> chooseCustomerType;
    private double xoffset;
	private double yoffset;
	private static ArrayList<String> arrFromServerRet;

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
    	
    	
    	if(checkIfFiledIsEmpty()) {
    		ERRORFILEDEMPTY.setText("No Text Filed Can Be Empty");
    	}
    	
    	else {
    		msg.add("CheckUserNameIsntExist");
    		msg.add(userName.getText());
    		msg.add("123456");
    		msg.add(FirstName.getText());
    		msg.add(LastName.getText());
    		msg.add("Customer");
    		msg.add(EmailText.getText());
    		msg.add(PhoneNumber.getText());
    		msg.add("0");
    		msg.add(ID.getText());
    		msg.add("NotApproved");
    		msg.add(chooseCustomerType.getSelectionModel().getSelectedItem().toString());
    		msg.add(CreditCardNumber.getText());
    		String yearAndMonth = MonthOnCard.getSelectionModel().getSelectedItem().toString() + "." + YearOnCard.getSelectionModel().getSelectedItem().toString();
    		msg.add(yearAndMonth);
    		msg.add(CVVOnCard.getText());
    		ClientUI.chat.accept(msg);
    		if(arrFromServerRet.get(0).equals("Error"))
			{
    			ERRORFILEDEMPTY.setText("Username Already In Use");		
			}
    		
    	
    	}
    		
    		
    }
    public static void getInsertedNewCustomer(ArrayList<String> arrFromServer){
    	arrFromServerRet = arrFromServer;
    }

	private boolean checkIfFiledIsEmpty() {
		return FirstName.getText().trim().isEmpty() || LastName.getText().trim().isEmpty() || CVVOnCard.getText().trim().isEmpty()
				|| CreditCardNumber.getText().trim().isEmpty()  || EmailText.getText().trim().isEmpty() || ID.getText().trim().isEmpty()
				|| PhoneNumber.getText().trim().isEmpty() || userName.getText().trim().isEmpty() || chooseCustomerType.getSelectionModel().getSelectedItem() == null
				|| MonthOnCard.getSelectionModel().getSelectedItem() == null || YearOnCard.getSelectionModel().getSelectedItem() == null;
	}
    
    @FXML
    void pressexitBTN(ActionEvent event) throws IOException {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> isAMember = FXCollections.observableArrayList("Regular","Member");
		ObservableList<String> Month = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
		ObservableList<String> Year = FXCollections.observableArrayList("2022","2023","2024","2025","2026","2027","2028","2029","2030");
		chooseCustomerType.setItems(isAMember);
		MonthOnCard.setItems(Month);
		YearOnCard.setItems(Year);
		
	}


}
