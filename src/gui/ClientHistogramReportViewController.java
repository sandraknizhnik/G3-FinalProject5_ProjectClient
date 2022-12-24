package gui;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.scene.chart.StackedBarChart;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClientHistogramReportViewController implements Initializable{

    @FXML
    private Label ClientHistogramReportLabel;

    @FXML
    private NumberAxis amountOfCostumers;
    
    @FXML
    private StackedBarChart stackedBarChart; //!!!!
    
    @FXML
    private Button backBtn;

    @FXML
    //private BarChart<Number, String> clientBarChart;
    private BarChart clientBarChart;

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
    private CategoryAxis rangeOfOrders;

    @FXML
    private Label specificAreaLable;

    @FXML
    private Label specificMachineNumberLable;
    
	private double xoffset;
	private double yoffset;
	
	private static ArrayList<String> arrReportData;
	//private static ArrayList<Integer> arrReportData;
	
	// start window to provide movement window
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/ClientHistogramReportView.fxml"));

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
	
	public static void getCustomersReportDetails(ArrayList<String> massageFromServer) {
		arrReportData = massageFromServer;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		specificMachineNumberLable.setText(arrReportData.get(arrReportData.size()-1));
		specificAreaLable.setText(arrReportData.get(arrReportData.size()-2));
		//clientBarChart = new BarChart<>(rangeOfOrders, amountOfCostumers, null);
		amountOfCostumers.setLabel("Clients");
		rangeOfOrders.setLabel("Orders");
		String str = arrReportData.get(0);
		System.out.println("blabla1" + str);
		String[] arrOfItems = str.split(",");
		System.out.println("blabla2" + arrOfItems);
		System.out.println("blablalba3 " + arrOfItems[0].getClass());
		System.out.println("blablalba4 " + arrOfItems[1].getClass());
		//XYChart.Series series = new XYChart.Series();
		
		for(int i=0; i<arrOfItems.length-1; i+=2) { 
			//clientBarChart.add(new PieChart.Data(arrOfItems[i], Double.parseDouble(arrOfItems[i+1])));
			
			
				//series.getData().add(new XYChart.Data<Number, String>(NumberFormat.getInstance().parse(arrOfItems[i+1]), (arrOfItems[i])));
			XYChart.Series series = new XYChart.Series();
			XYChart.Data dataCustomers = new XYChart.Data((arrOfItems[i+1]),Integer.parseInt(arrOfItems[i]));
			series.getData().add(dataCustomers);
			
			//clientBarChart.getData().add(new Series(arrOfItems[i], Integer.parseInt(arrOfItems[i+1])));
			stackedBarChart.getData().addAll(series);
		}
		stackedBarChart.setLegendVisible(false);
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

}
