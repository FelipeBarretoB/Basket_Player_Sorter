package ui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import model.AppManager;

public class AppManagerGUI {
	private AppManager appManager;
	
	@FXML
	private BorderPane mainPane;
	
	public AppManagerGUI(AppManager app) {
		appManager = app;
		
	}
	
	//TODO There's a little problem, tho. Initialize is called everytime I change panes.
	// Therefore, we're reloading the controller
	@FXML
	public void initialize() {
		try {
			loadList(null);
		} catch (IOException e) {
			//TODO Don't mind this
			//System.out.println("Nah, its okay bro");
			//e.printStackTrace();
		}
	}
	//TODO we could end up needing it.
	@FXML
	public void exitApp(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML
	public void loadList(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("List.fxml"));
		fxmlLoader.setController(this);
		Parent pane = fxmlLoader.load();
		mainPane.setCenter(pane);
		
	}
	
	@FXML
	public void loadSearch(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Search.fxml"));
		fxmlLoader.setController(this);
		Parent pane = fxmlLoader.load();
		mainPane.setCenter(pane);
	}
	
	//TODO This method is so the appManager doesn't returns warnings as we're not using it. "Remove Later".
	public AppManager returnAppManager() {
		return appManager;
	}
}
