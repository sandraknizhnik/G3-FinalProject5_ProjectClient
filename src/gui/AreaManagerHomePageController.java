package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AreaManagerHomePageController {

    @FXML
    private Button ExitBtn;

    @FXML
    private Button SetMinimummachinelevel;

    @FXML
    private Button SignOutbtn;

    @FXML
    private Button WatchReports;
    private double xoffset;
	private double yoffset;

    @FXML
    void pressExitBtn(ActionEvent event) {

    }

    @FXML
    void pressSetMinimummachinelevel(ActionEvent event) {

    }

    @FXML
    void pressSignOut(ActionEvent event) {

    }

    @FXML
    void pressWatchReports(ActionEvent event) {

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

}
