package ui;

//import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AppManager;

public class Main extends Application{
	
	private double xOffset = 0;
    private double yOffset = 0;

	private AppManager app;
	private AppManagerGUI appManagerGUI;
	
	public Main() {
		app=new AppManager();
		appManagerGUI = new AppManagerGUI(app);
		
	}
	
	public static void main(String[] args) {
		launch(args);
		//Main ui= new Main();
		/*try {
			ui.app.importPlayerDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.out.println(ui.app.callLinearSearch("name", "Tori"));
		//System.out.println(ui.app.callLinearSearchWithRange(20, 22, "age"));
		//ui.app.callCreatBinarySearchTreeThread();
		//--Problem with thread and the time it takes to charge
		//System.out.println(ui.app.searchWithTree("age", 20));
		//Object pera; meme con un amigo mio 
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
		fxmlLoader.setController(appManagerGUI);
		Parent root = fxmlLoader.load();
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		
		// The next two events are used to move the complete pane (root)
		 // grab your root here
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        // move around here
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
