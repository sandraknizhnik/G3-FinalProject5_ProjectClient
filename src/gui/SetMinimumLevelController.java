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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SetMinimumLevelController implements Initializable{

    @FXML
    private Button backBtn;

    @FXML
    private ImageView ekrutLogoImage;

    @FXML
    private Label enterMinLevelLable;

    @FXML
    private Label errorDataLable;

    @FXML
    private Button exitBtn;

    @FXML
    private ImageView machineNumberImg;


    @FXML
    private ImageView minLevelImage;

    @FXML
    private Label machineNumberLable;

    @FXML
    private ImageView managmentAreaImage;

    @FXML
    private Label managmentAreaLable;

    @FXML
    private Label minLevelLable;

    @FXML
    private ComboBox<String> numberMachineComboBox;

    @FXML
    private Label specificAreaLable;

    @FXML
    private TextField specificMinLevelText;

    @FXML
    private Button updateReportBtn;
    
	private double xoffset;
	private double yoffset;
	private static ArrayList<String> arrFromServerRet;
	private String selectedMachineNumber;
	private ObservableList<String> machineNumberList; // for numberMachineComboBox
	
	// start window to provide movement window
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/SetMinimumLevelView.fxml"));

		// event handler for when the mouse is pressed AND dragged to move the window
		root.setOnMousePressed(event1 -> {
			xoffset = event1.getSceneX();
			yoffset = event1.getSceneY();
		});

		// event handler for when the mouse is pressed AND dragged to move the window
		root.setOnMouseDragged(event1 -> {
			primaryStage.setX(event1.getScreenX() - xoffset);
			primaryStage.setY(event1.getScreenY() - yoffset);
		});
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<String> msg1 = new ArrayList<>();
		msg1.add("getMachineNumberForSetMinimum");
		try {
			ClientUI.chat.accept(msg1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		machineNumberList = FXCollections.observableArrayList();

		for (int i = 0; i < arrFromServerRet.size() - 1; i++) { // copying to machineNumberList
			machineNumberList.add(arrFromServerRet.get(i));
		}
		numberMachineComboBox.setItems(machineNumberList); // show machines number for specific area
		specificAreaLable.setText(arrFromServerRet.get(arrFromServerRet.size() - 1)); // show the area label
	}
	
	@FXML
	void chooseNumberMachine(ActionEvent event) {
		selectedMachineNumber = numberMachineComboBox.getSelectionModel().getSelectedItem();// get selected machine
																							// number in c.b
	}
	
	public static void getMachineDataMinimumLevel(ArrayList<String> massageFromServer) {
		arrFromServerRet = massageFromServer;
	}
	
	// press back button from area manager page
	@FXML
	void pressBackBtn(ActionEvent event) throws Exception {
		ArrayList<String> msg = new ArrayList<>();
		msg.add("getUserDataAreaManager");
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		ClientUI.chat.accept(msg);
		Stage primaryStage = new Stage();
		primaryStage.initStyle(StageStyle.UNDECORATED);
		AreaManagerHomePageController amhpc = new AreaManagerHomePageController();
		amhpc.start(primaryStage);
	}

	// press exit button from area manager page
	@FXML
	void pressExitBtn(ActionEvent event) throws IOException {
		ArrayList<String> msg = new ArrayList<>();
		msg.add("quit");
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		ClientUI.chat.accept(msg);
		System.exit(1);
	}

    @FXML
    void pressUpdate(ActionEvent event) {
		if ((selectedMachineNumber==null)||(specificMinLevelText.getText().trim().isEmpty())){
			errorDataLable.setText("Inorder to set minimum level you must choose\n machine number and enter minimum level");
		}
    }
}

