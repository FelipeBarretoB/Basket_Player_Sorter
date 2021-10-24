package ui;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.AppManager;
import model.Player;


public class AppManagerGUI {
	private AppManager appManager;
	
	@FXML
	private BorderPane mainPane;
	
	//La tabla principal de los jugadores
    @FXML
    private TableView<Player> tvPlayers;
    
    @FXML
    private TableColumn<Player, String> tcPlayerName;

    @FXML
    private TableColumn<Player, String> tcPlayerTeam;

    @FXML
    private TableColumn<Player, String> tcPlayerAge;

    @FXML
    private TableColumn<Player, String> tcPlayerPoints;

    @FXML
    private TableColumn<Player, String> tcPlayerRebounds;

    @FXML
    private TableColumn<Player, String> tcPlayerBlocks;

    @FXML
    private TableColumn<Player, String> tcPlayerAssists;

    @FXML
    private TableColumn<Player, String> tcPlayerSteals;
	
	public AppManagerGUI(AppManager app) {
		appManager = app;
		try {
			appManager.importPlayerDataBase();
			appManager.callCreatBinarySearchTreeThread();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//TODO There's a little problem, tho. Initialize is called everytime I change panes.
	// Therefore, we're reloading the controller
	@FXML
	public void initialize() {
		/*try {
			//loadList(null);
		} catch (IOException e) {
			//TODO Don't mind this
			//System.out.println("Nah, its okay bro");
			//e.printStackTrace();
		}*/
	}
	//TODO we could end up needing it.
	@FXML
	public void exitApp(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML
	public void loadList(ActionEvent event) throws IOException {
		loadPage("List.fxml");
		loadPlayerListValues();
	}
	
	public void loadPlayerListValues() {
		ObservableList<Player> observableList;
		observableList = FXCollections.observableArrayList(appManager.getPlayers());
		tvPlayers.setItems(observableList);
		tcPlayerName.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
		tcPlayerAge.setCellValueFactory(new PropertyValueFactory<Player,String>("age"));
		tcPlayerAssists.setCellValueFactory(new PropertyValueFactory<Player,String>("assists"));
		tcPlayerPoints.setCellValueFactory(new PropertyValueFactory<Player,String>("points"));
		tcPlayerRebounds.setCellValueFactory(new PropertyValueFactory<Player,String>("reBounds"));
		tcPlayerSteals.setCellValueFactory(new PropertyValueFactory<Player,String>("steals"));
		tcPlayerTeam.setCellValueFactory(new PropertyValueFactory<Player,String>("team"));
	}
	
	@FXML
	public void test(ActionEvent event) throws IOException {
		//Test de busqueda de todos con el mismo valor en el arbol
		//loadPlayerListValues();
		//appManager.testCase();
		
		
		/*
		System.out.println("Fair enough!");
		BinaryTree<Integer, Player> bt = appManager.getBinarySearchTrees().get(0);
		System.out.println(bt.getRoot().getPlayer().toString());
		System.out.println(bt.delete(32, null).getPlayer().toString());
		*/
	}
	
	@FXML
	public void loadSearch(ActionEvent event) throws IOException {
		loadPage("Search.fxml");
	}
	
	//TODO This method is so the appManager doesn't returns warnings as we're not using it. "Remove Later".
	public AppManager returnAppManager() {
		return appManager;
	}
    
    public void loadPage(String page) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page));
		fxmlLoader.setController(this);
		Parent pane = fxmlLoader.load();
		mainPane.setCenter(pane);
    }
    
    @FXML
    private ComboBox<?> cbSearchParameter;

    @FXML
    private TextField txtSearchValue;

    @FXML
    private ImageView imgPlayerCharacter;

    @FXML
    private ListView<?> lvSimilarPlayers;

    @FXML
    void searchPlayers(ActionEvent event) {
    	
    	int x = randNum(1, 100);
    	if(x > 0 && x <= 33) {
    		imgPlayerCharacter.setImage(new Image("file:Data\\images\\basketball_player_1.png"));
    	}else if(x > 33 && x <= 66) {
    		imgPlayerCharacter.setImage(new Image("file:Data\\images\\basketball_player_2.png"));
    	}else if(x > 66 && x <= 99) {
    		imgPlayerCharacter.setImage(new Image("file:Data\\images\\basketball_player_3.png"));
    	}else {
    		imgPlayerCharacter.setImage(new Image("file:Data\\images\\sape.png"));
    	}
    	
    }
    
    public static int randNum(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}
