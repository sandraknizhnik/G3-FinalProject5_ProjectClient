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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AreaManagerReportViewController implements Initializable {
	@FXML
	private Button backBtn;

	@FXML
	private ImageView calendarImg;

	@FXML
	private ImageView ekrutLogoImage;

	@FXML
	private Button exitBtn;

	@FXML
	private ImageView machineNumberImg;

	@FXML
	private Label machineNumberLable;

	@FXML
	private ImageView managmentAreaImage;

	@FXML
	private Label managmentAreaLable;

	@FXML
	private ComboBox<String> monthComboBox;

	@FXML
	private ComboBox<String> numberMachineComboBox;

	@FXML
	private Label reportLable;

	@FXML
	private ComboBox<String> reportTypeComboBox;

	@FXML
	private Button showReportBtn;

	@FXML
	private Label specificAreaLable;

	@FXML
	private ImageView typeImg;

	@FXML
	private Label typeLable;

	@FXML
	private ComboBox<String> yearComboBox;
	
	@FXML
	private Label errorReportDataLable;

	private double xoffset;
	private double yoffset;
	private static ArrayList<String> arrFromServerRet;
	private static ArrayList<String> arrReportData;
	private ObservableList<String> machineNumberList; // for numberMachineComboBox
	private ObservableList<String> reportTypeList; // for reportTypeComboBox
	private ObservableList<String> yearList; // for yearComboBox
	private ObservableList<String> monthList; // for monthComboBox

	private String selectedMachineNumber;
	private String selectedMonth;
	private String selectedYear;
	private String selectedReportType;

	// start window to provide movement window
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/AreaManagerReportView.fxml"));

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
		msg1.add("getMachineNumber");
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
		reportTypeList = FXCollections.observableArrayList("Orders", "Inventory", "Customers");
		reportTypeComboBox.setItems(reportTypeList);
		yearList = FXCollections.observableArrayList("2022", "2021", "2020", "2019", "2018", "2017", "2016");
		yearComboBox.setItems(yearList);
		monthList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
		monthComboBox.setItems(monthList);
		specificAreaLable.setText(arrFromServerRet.get(arrFromServerRet.size() - 1)); // show the area label
	}

	public static void getMachineData(ArrayList<String> massageFromServer) {
		arrFromServerRet = massageFromServer;
	}

	@FXML
	void chooseMonth(ActionEvent event) {
		selectedMonth = monthComboBox.getSelectionModel().getSelectedItem();// get selected month in c.b
	}

	@FXML
	void chooseNumberMachine(ActionEvent event) {
		selectedMachineNumber = numberMachineComboBox.getSelectionModel().getSelectedItem();// get selected machine
																							// number in c.b
	}

	@FXML
	void chooseTypeReport(ActionEvent event) {
		selectedReportType = reportTypeComboBox.getSelectionModel().getSelectedItem();// get selected report type
																						// in c.b
	}

	@FXML
	void chooseYear(ActionEvent event) {
		selectedYear = yearComboBox.getSelectionModel().getSelectedItem();// get selected year in c.b
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

	// show next window for orders report
	@FXML
	void pressShowReport(ActionEvent event) throws Exception {
		if(checkValidReportData()) {
			ArrayList<String> msg = new ArrayList<>();
			msg.add("getOrderReportDetails");
			msg.add(selectedMachineNumber);
			msg.add(selectedMonth);
			msg.add(selectedYear);
			//msg.add(selectedReportType);
			ClientUI.chat.accept(msg);
			if(arrReportData.get(0).equals("Error"))
			{
				errorReportDataLable.setText("\t\t\t\tNo such report");		
			}
			else { //showing report view if the user put current details
				Stage primaryStage = new Stage();
				((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
				primaryStage.initStyle(StageStyle.UNDECORATED);
				OrderReportViewController orvc = new OrderReportViewController();
				OrderReportViewController.getOrderReportDetails(arrReportData);
				orvc.start(primaryStage);
			}
		}
	}
	
	
	boolean checkValidReportData() {
		if ((selectedMachineNumber==null)||(selectedMonth==null)||(selectedYear==null)||(selectedReportType==null)){
			errorReportDataLable.setText("Inorder to view report you must choose year\n month, machine number and report type");
			return false;
		}
		return true;
	}

	public static void getOrderReportData(ArrayList<String> massageFromServer) {
		arrReportData = massageFromServer;	
	}
	
	
}
