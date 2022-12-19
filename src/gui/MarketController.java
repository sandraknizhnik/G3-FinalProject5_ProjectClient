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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {
    @FXML
    private VBox chosenFruitCard;
    
    @FXML
    private Button Exitbtn;
    
    @FXML
    private Label productNameLable;

    @FXML
    private Label productPriceLabel;

    @FXML
    private ImageView productImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

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
    	productNameLable.setText(product.getName());
    	productPriceLabel.setText("$" + product.getPrice());
        image = new Image(getClass().getResourceAsStream(product.getImgSrc()));
        productImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + product.getColor() + ";\n" +
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
    



    @FXML
    void pressExitbtn(ActionEvent event) throws IOException {
    	ArrayList<String> msg = new ArrayList<>();
		msg.add("quit");
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		ClientUI.chat.accept(msg);
		System.exit(1);

    }
    
    public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("/gui/market.fxml"));
		
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
