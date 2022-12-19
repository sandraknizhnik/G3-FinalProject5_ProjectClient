package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EKrutFinalMarketController implements Initializable {

    @FXML
    private Label EkrutBasketLabel;

    @FXML
    private Button backbt;

    @FXML
    private Button cancelOrder;

    @FXML
    private Button exitbtn;

    @FXML
    private GridPane grid;

    @FXML
    private Button minosBtnLeft;

    @FXML
    private Button plusBtnLeft;
    @FXML
    private AnchorPane chosenProductCard;
    
    @FXML
    private TextField productAmountValueLefft;

    @FXML
    private TextField productCodeValueLeft;

    @FXML
    private TextField productCostValueLeft;

    @FXML
    private ImageView productLeftPicture;

    @FXML
    private Label productNameLableLeft;

    @FXML
    private Button reviewOrder;

    @FXML
    private ScrollPane scroll;
    
    private List<Product> products = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    private double xoffset;
	private double yoffset;
	
	 private List<Product> getData() {
	        List<Product> products = new ArrayList<>();
	        Product product;
	        product = new Product("Pringels","..\\images\\Pringles .png",12,"6A7324");
	        products.add(product);
	        product = new Product("TimTam","..\\images\\TimTam.png",10,"A7745B");
	        products.add(product);
	        product = new Product("Toffife","..\\images\\Toffife.png",15,"6A7324");
	        products.add(product);
	        product = new Product("Doritos","..\\images\\Doritos.png",20,"6A7324");
	        products.add(product);
	        product = new Product("KinderBueno","..\\images\\KinderBueno.png",8,"6A7324");
	        products.add(product);
	        

	        /*fruit = new Fruit();
	        fruit.setName("Coconut");
	        fruit.setPrice(3.99);
	        fruit.setImgSrc("/img/coconut.png");
	        fruit.setColor("A7745B");
	        fruits.add(fruit);

	        */

	        return products;
	   }
	 private void setChosenFruit(Product product) {
		    productNameLableLeft.setText(product.getName());
		    productCostValueLeft.setText("$" + product.getPrice());
	        image = new Image(getClass().getResourceAsStream(product.getImgSrc()));
	        productLeftPicture.setImage(image);
	        chosenProductCard.setStyle("-fx-background-color: #" + product.getColor() + ";\n" +
	                "    -fx-background-radius: 30;");
	    }
	 @Override
	    public void initialize(URL location, ResourceBundle resources) {
	    	products.addAll(getData());
	        if (products.size() > 0) {
	            setChosenFruit(products.get(0));
	            myListener = new MyListener() {
	                @Override
	                public void onClickListener(Product product) {
	                    setChosenFruit(product);
	                }
	            };
	        }
	        int column = 0;
	        int row = 1;
	        try {
	            for (int i = 0; i < products.size(); i++) {
	                FXMLLoader fxmlLoader = new FXMLLoader();
	                fxmlLoader.setLocation(getClass().getResource("/gui/item.fxml"));
	                AnchorPane anchorPane = fxmlLoader.load();

	                ItemController itemController = fxmlLoader.getController();
	                itemController.setData(products.get(i),myListener);

	                if (column == 3) {
	                    column = 0;
	                    row++;
	                }

	                grid.add(anchorPane, column++, row); //(child,column,row)
	                //set grid width
	                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
	                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
	                grid.setMaxWidth(Region.USE_PREF_SIZE);

	                //set grid height
	                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
	                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
	                grid.setMaxHeight(Region.USE_PREF_SIZE);

	                GridPane.setMargin(anchorPane, new Insets(10));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 public void start(Stage primaryStage) throws Exception {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/EKrutFinalMarket.fxml"));
			
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
    
    

    @FXML
    void pressBack(ActionEvent event) throws Exception {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		primaryStage.initStyle(StageStyle.UNDECORATED);
		CustomerMainScreenController cmsc = new CustomerMainScreenController();
		cmsc.start(primaryStage);
    }

    @FXML
    void presscancelOrder(ActionEvent event) {

    }

    @FXML
    void pressedExit(ActionEvent event) throws IOException {
    	ArrayList<String> msg = new ArrayList<>();
		msg.add("quit");
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ClientUI.chat.accept(msg);
		System.exit(1);
    }

    @FXML
    void pressminosBtnLeft(ActionEvent event) {

    }

    @FXML
    void pressplusBtnLeft(ActionEvent event) {

    }

    @FXML
    void reviewOrderpressed(ActionEvent event) {

    }

}
