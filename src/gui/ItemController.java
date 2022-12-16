//package gui;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import main.Main;
//import main.MyListener;
//import model.Fruit;
//
//public class ItemController {
//    @FXML
//    private Label nameLabel;
//
//    @FXML
//    private Label priceLable;
//
//    @FXML
//    private ImageView img;
//
//    @FXML
//    private void click(MouseEvent mouseEvent) {
//        myListener.onClickListener(fruit);
//    }
//
//    private Fruit fruit;
//    private MyListener myListener;
//
//    public void setData(Fruit fruit, MyListener myListener) {
//        this.fruit = fruit;
//        this.myListener = myListener;
//        nameLabel.setText(fruit.getName());
//        priceLable.setText(Main.CURRENCY + fruit.getPrice());
//        Image image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
//        img.setImage(image);
//    }
//}

package gui;

import com.gluonhq.charm.glisten.control.BottomNavigationButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemController {

    @FXML
    private BottomNavigationButton additem;

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private Label nameLabel1;

    @FXML
    private Label nameLabel11;

    @FXML
    private Label priceLable;

    @FXML
    private TextField productAmount;

    @FXML
    private BottomNavigationButton substract;

    @FXML
    void additembtn(ActionEvent event) {

    }

    @FXML
    void click(MouseEvent event) {

    }

    @FXML
    void susbtractbtn(ActionEvent event) {

    }

}

