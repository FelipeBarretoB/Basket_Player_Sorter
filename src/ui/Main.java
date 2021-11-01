package ui;

import dataStructureTrees.RedBlackTree;

//import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Player;


public class Main extends Application{
	
	private double xOffset = 0;
    private double yOffset = 0;

	
	private AppManagerGUI appManagerGUI;
	
	public Main() {
		appManagerGUI = new AppManagerGUI();
	}
	
	public static void main(String[] args) {
		/*RedBlackTree<Integer, Player> rb = new RedBlackTree<>();
		Player pl = new Player("Gabriel","Sapetas",12,32,34,54,65,12);
		rb.insert(Integer.parseInt(pl.get("age")), pl);
		//System.out.println(rb.getRoot().getPlayer());
		pl = new Player("Alejandro","Sapetas",13,32,34,54,65,12);
		rb.insert(Integer.parseInt(pl.get("age")), pl);
		pl = new Player("Miguel","Tumama",11,32,34,54,65,12);
		rb.insert(Integer.parseInt(pl.get("age")), pl);
		pl = new Player("Felipe","Tugfa",5,32,34,54,65,12);
		rb.insert(Integer.parseInt(pl.get("age")), pl);
		System.out.println(rb.getRoot().getPlayer());
		System.out.println(rb.getRoot().getColor());
		System.out.println(rb.getRoot().getLeft().getPlayer());
		System.out.println(rb.getRoot().getLeft().getColor());
		System.out.println(rb.getRoot().getRight().getPlayer());
		System.out.println(rb.getRoot().getRight().getColor());
		System.out.println(rb.getRoot().getLeft().getLeft().getPlayer());
		System.out.println(rb.getRoot().getLeft().getLeft().getColor());
		pl = new Player("Martin","Xd",21,32,34,54,65,12);
		rb.insert(Integer.parseInt(pl.get("age")), pl);
		pl = new Player("Sapetin","Sapetim",14,32,34,54,65,12);
		rb.insert(Integer.parseInt(pl.get("age")), pl);
		//System.out.println(rb.getBlackHeight());
		System.out.println(rb.getRoot().getPlayer());
		System.out.println(rb.getRoot().getColor());
		System.out.println(rb.getRoot().getLeft().getPlayer());
		System.out.println(rb.getRoot().getLeft().getColor());
		System.out.println(rb.getRoot().getRight().getPlayer());
		System.out.println(rb.getRoot().getRight().getColor());
		System.out.println(rb.getRoot().getRight().getRight().getPlayer());
		System.out.println(rb.getRoot().getRight().getRight().getColor());*/
		//rb.printTree(rb.getRoot());
		
		launch(args);
		//Main ui= new Main();
		/*try {
			ui.app.importPlayerDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//ui.app.test();
		//System.out.println(ui.app.callLinearSearch("name", "Tori"));
		//System.out.println(ui.app.callLinearSearchWithRange(20, 22, "age"));
		//ui.app.callCreatBinarySearchTreeThread();
		//--Problem with thread and the time it takes to charge
		//System.out.println(ui.app.searchWithTree("age", 20));
		
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
