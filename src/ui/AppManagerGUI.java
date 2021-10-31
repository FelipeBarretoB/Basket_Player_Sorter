package ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import model.AppManager;
import model.Filler;
import model.Loader;
import model.Player;


public class AppManagerGUI {
	private AppManager appManager;

	private boolean loading;

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

	@FXML
	private Label warningLabel;
	
	@FXML
	private Label numPlayers;

	// Primitives (Shapes)
	@FXML
	private Line line;

	private Loader l;

	@FXML
	private Rectangle rectangleContainer;

	@FXML
	private Rectangle rectangleFill;

	private Filler f;

	public AppManagerGUI() {
		appManager = new AppManager();
		try {
			if (new File(appManager.getPlayersFile()).exists()) {
				appManager.loadData();
			} else {				
				appManager.importPlayerDataBase();
			}
			appManager.callCreatBinarySearchTreeThread();
			load();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		

	}

	public void load() throws IOException{
		/*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
		fxmlLoader.setController(this);
		Parent pane = fxmlLoader.load();
		mainPane.setCenter(pane);
		loading = true;

        l = new Loader(line.getRotate());
        f = new Filler(rectangleFill.getWidth(), rectangleContainer.getWidth());

        new Thread() {
            public void run() {
                while (loading) {
                    l.load();
                    f.fill();

                    Platform.runLater(new Thread() {
                        public void run() {
                            // Update figures
                            updateLine(l.getDegrees());
                            updateRectangle(f.getWidth());
                        }
                    });

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();*/
	}

	//TODO There's a little problem, tho. Initialize is called everytime I change panes.
	// Therefore, we're reloading the controller
	@FXML
	public void initialize() {

	}

	public void updateLine(double x) {
		line.setRotate(x);
	}

	public void updateRectangle(double w) {
		rectangleFill.setWidth(w);
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
		tcPlayerBlocks.setCellValueFactory(new PropertyValueFactory<Player,String>("blocks"));
		tcPlayerTeam.setCellValueFactory(new PropertyValueFactory<Player,String>("team"));
	}

	@FXML
	public void test(ActionEvent event) throws IOException {
		//Test de busqueda de todos con el mismo valor en el arbol
		System.out.println(appManager.getBinarySearchTrees().get(0).getRoot());



		/*
		System.out.println("Fair enough!");
		BinaryTree<Integer, Player> bt = appManager.getBinarySearchTrees().get(0);
		System.out.println(bt.getRoot().getPlayer().toString());
		System.out.println(bt.delete(32, null).getPlayer().toString());
		 */
	}

	@FXML
	private ComboBox<String> cbSearchParameter;

	@FXML
	private TextField txtSearchValue;

	@FXML
	private ImageView imgPlayerCharacter;

	@FXML
	private TableView<Player> tvSimilarPlayers;

	@FXML
	private TableColumn<Player, String> tcPlayerData;

	@FXML
	public void loadSearch(ActionEvent event) throws IOException {
		loadPage("Search.fxml");
		loadComboBox();
	}

	public void loadComboBox() {
		ObservableList<String> observableList;
		ArrayList<String> values = new ArrayList<String>();
		values.add("Name");
		values.add("Age");
		values.add("Assists");
		values.add("Points");
		values.add("Rebounds");
		values.add("Steals");
		values.add("Team");
		observableList = FXCollections.observableArrayList(values);
		cbSearchParameter.setItems(observableList);
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
	private Label labPlayerSearchTime;

	@FXML
	private TextField txtBegRange;

	@FXML
	private TextField txtEndRange;

	@FXML
	private Label labWarning;


	public void activateModifyMode() {

	}

	@FXML
	public void searchPlayers(ActionEvent event) {
		ObservableList<Player> observableList;

		if(txtSearchValue.getText().equals("") && cbSearchParameter.getValue() == null && txtBegRange.getText().equals("") && txtEndRange.getText().equals("")) {
			labWarning.setText("Por favor ingrese los datos a buscar");
		}else if(txtSearchValue.getText().equals("/all")) {
			activateModifyMode();
			observableList = FXCollections.observableArrayList(appManager.getPlayers());
			tvSimilarPlayers.setItems(observableList);
			tcPlayerData.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
			labWarning.setText("");


			showPlayer(0, observableList);

			changeImage();
			numPlayers.setText(observableList.size() + "");

		}else if(!txtSearchValue.getText().equals("") && cbSearchParameter.getValue() != null && !txtBegRange.getText().equals("") && !txtEndRange.getText().equals("")) {
			labWarning.setText("Por favor busque solo en rango o individualmente|");
		}else if(!txtBegRange.getText().equals("") && !txtEndRange.getText().equals("") && cbSearchParameter.getValue() != null) {
			try {
				String parameter = cbSearchParameter.getValue().toLowerCase();
				if (parameter.equals("rebounds")) {
					parameter = "reBounds";
				}

				observableList = FXCollections.observableArrayList(appManager.callLinearSearchWithRange(Integer.parseInt(txtBegRange.getText()),Integer.parseInt(txtEndRange.getText()),parameter));
				tvSimilarPlayers.setItems(observableList);
				tcPlayerData.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));

				if (observableList.size() > 0) {
					labWarning.setText("");

					
					showPlayer(0,observableList);

					if(cbSearchParameter.getValue().equals("Age")||cbSearchParameter.getValue().equals("Points")||cbSearchParameter.getValue().equals("reBounds")||cbSearchParameter.getValue().equals("blocks")) {
						labPlayerSearchTime.setText("ABB time "+ appManager.getTime()+"s");
					}else {
						labPlayerSearchTime.setText("linear search "+ appManager.getTime()+"s");
					}
					changeImage();
				} else {
					labWarning.setText("No se ha encontrado ningún jugador con ese parametro!");
				}
				numPlayers.setText(observableList.size() + "");

			}catch(NumberFormatException nfe) {
				labWarning.setText("Por favor ingrese valores numéricos para buscar en rango");
			}catch(Exception e) {
				System.out.println("b");
			}
		}
		//linearSearch
		else if(!txtSearchValue.getText().equals("") && cbSearchParameter.getValue() != null) {
			String parameter = cbSearchParameter.getValue().toLowerCase();
			if (parameter.equals("rebounds")) {
				parameter = "reBounds";
			}
			observableList = FXCollections.observableArrayList(appManager.searchWithTree(parameter,txtSearchValue.getText()));
			tvSimilarPlayers.setItems(observableList);
			tcPlayerData.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));

			if (observableList.size() > 0) {
				labWarning.setText("");


				showPlayer(0, observableList);
				System.out.println(appManager.getPlayers().get(0));

				if(cbSearchParameter.getValue().equals("Age")||cbSearchParameter.getValue().equals("Points")||cbSearchParameter.getValue().equals("Rebounds")||cbSearchParameter.getValue().equals("Blocks")) {
					labPlayerSearchTime.setText("ABB time "+ appManager.getTime()+"s");
				}else {
					labPlayerSearchTime.setText("linear search "+ appManager.getTime()+"s");
				}
				changeImage();
			} else {
				labWarning.setText("No se ha encontrado ningún jugador con ese parametro!");
			}

			numPlayers.setText(observableList.size() + "");
		}else {
			System.out.println("a");
		}
	}

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtAge;

	@FXML
	private TextField txtTeam;

	@FXML
	private TextField txtPoints;

	@FXML
	private TextField txtRebounds;

	@FXML
	private TextField txtBlocks;

	@FXML
	private TextField txtAssists;

	@FXML
	private TextField txtSteals;

	@FXML
	public void modifyPlayer(ActionEvent event) {
		if(tvSimilarPlayers.getSelectionModel().getSelectedItem() != null) {
			for(int i = 0; i < appManager.getPlayers().size(); i++) {
				if(tvSimilarPlayers.getSelectionModel().getSelectedItem() == appManager.getPlayers().get(i)) {
					appManager.modify(i,txtName.getText(),txtTeam.getText(),Integer.parseInt(txtAge.getText()),Integer.parseInt(txtPoints.getText()),
							Integer.parseInt(txtRebounds.getText()),Integer.parseInt(txtBlocks.getText()),Integer.parseInt(txtAssists.getText()),
							Integer.parseInt(txtSteals.getText()));
				}
			}
			try {
				appManager.savePlayers();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else {
			//TODO ALGUIEN META ESTO A LA GUI
			System.out.println("Seleccione la tabla de nuevo");
		}

	}


	@FXML
	public void deletePlayer(ActionEvent event) {
		if(tvSimilarPlayers.getSelectionModel().getSelectedItem() != null) {
			for(int i = 0; i < appManager.getPlayers().size(); i++) {
				if(tvSimilarPlayers.getSelectionModel().getSelectedItem() == appManager.getPlayers().get(i)) {
					appManager.deletePlayer(i);
				}
			}

		}

		txtName.setText("");
		txtAge.setText("");
		txtTeam.setText("");
		txtPoints.setText("");
		txtRebounds.setText("");
		txtBlocks.setText("");
		txtAssists.setText("");
		txtSteals.setText("");
		
		try {
			appManager.savePlayers();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void showPlayerInfo(MouseEvent event) {
		if(tvSimilarPlayers.getSelectionModel().getSelectedItem() != null) {
			for(int i = 0; i < appManager.getPlayers().size(); i++) {
				if(tvSimilarPlayers.getSelectionModel().getSelectedItem() == appManager.getPlayers().get(i)) {
					showPlayer(i, appManager.getPlayers());
					changeImage();
				}
			}
		}

	}

	public void showPlayer(int index, List<Player> ob) {
		txtName.setText(ob.get(index).getName());
		txtAge.setText(String.valueOf(ob.get(index).getAge()));
		txtTeam.setText(ob.get(index).getTeam());
		txtPoints.setText(String.valueOf(ob.get(index).getPoints()));
		txtRebounds.setText(String.valueOf(ob.get(index).getReBounds()));
		txtBlocks.setText(String.valueOf(ob.get(index).getBlocks()));
		txtAssists.setText(String.valueOf(ob.get(index).getAssists()));
		txtSteals.setText(String.valueOf(ob.get(index).getSteals()));
	}

	public void changeImage(){
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
	/*public ArrayList<String> playersNamesFilter(ArrayList<Player> players){
    	ArrayList<String> names = new ArrayList<String>();
    	for(int i = 0; i < players.size(); i++) {
    		names.add(players.get(i).getName());
    	}
    	return names;
    }*/

	public int randNum(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}


}
