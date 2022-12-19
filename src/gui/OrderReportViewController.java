package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OrderReportViewController implements Initializable{

    @FXML
    private Label CanceledOrdersLable;

    @FXML
    private Label MostPurchasedLable;

    @FXML
    private Label OrderReportLabel;

    @FXML
    private Label OrdersInTotalLable;

    @FXML
    private Button backBtn;

    @FXML
    private ImageView canceledOrderImg;

    @FXML
    private ImageView ekrutLogoImage;

    @FXML
    private Button exitBtn;

    @FXML
    private ImageView machineNumberImg;

    @FXML
    private Label machineNumberLable;

    @FXML
    private ImageView managmentAreaImg;

    @FXML
    private Label managmentAreaLable;

    @FXML
    private ImageView mostPurchasedImg;

    @FXML
    private ImageView ordersInTotalImg;

    @FXML
    private PieChart pieChartOrder;

    @FXML
    private Label specificAreaLable;

    @FXML
    private Label specificCanceledOrdesLabel;

    @FXML
    private Label specificMachineNumberLable;

    @FXML
    private Label specificMostPurchasedLable;

    @FXML
    private Label specificOrdersInTotalLable;
    
	private double xoffset;
	private double yoffset;
	
	private static ArrayList<String> arrReportData;
	
	/*public OrderReportViewController(ArrayList<String> arrReportData2) {
		arrReportData = arrReportData2;
	}*/

	//start window to provide movement window
    public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/OrderReportView.fxml"));
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

	// press back button from area manager page
	@FXML
	void pressBackBtn(ActionEvent event) throws Exception {
		ArrayList<String> msg = new ArrayList<>();
		msg.add("getMachineNumber");
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		ClientUI.chat.accept(msg);
		Stage primaryStage = new Stage();
		primaryStage.initStyle(StageStyle.UNDECORATED);
		AreaManagerReportViewController amrvc = new AreaManagerReportViewController();
		amrvc.start(primaryStage);
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
	
	public static void getOrderReportDetails(ArrayList<String> massageFromServer) {
		arrReportData = massageFromServer;
		/*System.out.println("blablablba3 "  + arrReportData);
		int size = arrReportData.size();
		System.out.println(size);
		
		System.out.println(arrReportData.get(5).getClass());
		String str = arrReportData.get(5);
		System.out.println(str);
		specificAreaLable.setText(str); */
		/*;*/
		/*for(int i=size-1; i>=0; i--) {
			
		}*/

		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		specificAreaLable.setText(arrReportData.get(arrReportData.size()-1));
		specificMachineNumberLable.setText(arrReportData.get(arrReportData.size()-2));
		specificMostPurchasedLable.setText(arrReportData.get(arrReportData.size()-3));
		specificCanceledOrdesLabel.setText(arrReportData.get(arrReportData.size()-4));
		specificOrdersInTotalLable.setText(arrReportData.get(arrReportData.size()-5));
		
		
		
		
		
		
		
		
		
		
		
		
		/*machineNumberList = FXCollections.observableArrayList();

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
		specificAreaLable.setText(arrFromServerRet.get(arrFromServerRet.size() - 1)); // show the area label*/
	}

}
