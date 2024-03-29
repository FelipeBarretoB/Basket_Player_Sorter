package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import dataStructureTrees.BinaryTree;
import dataStructureTrees.Node;
import dataStructureTrees.RBNode;
import dataStructureTrees.RedBlackTree;
//import dataStructureTrees.RedBlackTree;
//import dataStructureTrees.RedBlackTree;
import threads.CreateBinarySearchTreeThread;
import threads.ImportDataBaseThread;
import threads.LinearSearchThread;
import threads.LinearSearchWithRange;


public class AppManager {
	
	public final static String PLAYERS_FILE = "./Data/players.apm";

	private List<Player> players;
	private ImportDataBaseThread importDataBaseThread;
	private LinearSearchThread linearSearchThread;
	private LinearSearchWithRange linearSearchWithRange;
	private List<BinaryTree<Integer, Player>> binarySearchTrees;
	private CreateBinarySearchTreeThread createBinarySearchTree;
	private RedBlackTree<Integer, Player> rbTree;
	private double time;

	public AppManager(){
		players=new ArrayList<>();
		importDataBaseThread= new ImportDataBaseThread(this);
		binarySearchTrees= new ArrayList<>();

	}
	
	public String getPlayersFile() {
		return PLAYERS_FILE;
	}

	public void callImportDataBase() {
		importDataBaseThread.start();
	}

	public void importPlayerDataBase() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Data/DataBase.csv"));
		String line= br.readLine();
		while(line!=null) {
			String[] parts= line.split(";");
			Player currentPlayer= new Player(parts[0], parts[2], Integer.parseInt(parts[1]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), Integer.parseInt(parts[7]));
			players.add(currentPlayer);
			line=br.readLine();
		}
		br.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(PLAYERS_FILE);
		if (f.exists()) {			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			players = (List<Player>) ois.readObject();
			ois.close();
		}
	}
	
	public void savePlayers() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PLAYERS_FILE));
		oos.writeObject(players);
		oos.close();
	}



	public ArrayList<Player> callLinearSearch(String search, String searchedFor){
		linearSearchThread= new LinearSearchThread(this, search,  searchedFor);
		linearSearchThread.start();
		//TODO toca que ver que podemos hacer para sacar este while
		while(linearSearchThread.isAlive()) {}
		return linearSearchThread.getPlayers();
	}

	public ArrayList<Player> callLinearSearchWithRange(int min, int max,String search){
		linearSearchWithRange = new LinearSearchWithRange(this, min, max, search);
		linearSearchWithRange.start();
		//TODO toca que ver que podemos hacer para sacar este while
		while(linearSearchWithRange.isAlive()) {}
		return linearSearchWithRange.getPlayers();
	}


	//Todos los jugadores con el mismo dato (Aka, devuelve todos los jugadores con el mismo nombre)
	public ArrayList<Player> linearSearch(String search, String searchedFor){
		ArrayList<Player> searchedPlayers= new ArrayList<>();
		for(int c=0;c< players.size();c++) {
			if(players.get(c).get(search).equalsIgnoreCase(searchedFor)) {
				searchedPlayers.add(players.get(c));
			}
		}
		return searchedPlayers;
	}

	//Todos los jugadores que se encuentren en el rango (Aka, devuelve todos los jugadores de la edad desde 18 a 20 [teniendo en cuenta 20])
	public ArrayList<Player> linearSearchWithRange(int min, int max,String search) {
		ArrayList<Player> searchedPlayers= new ArrayList<>();
		for(int c=0;c< players.size();c++) {
			if(Integer.parseInt(players.get(c).get(search))>=min && Integer.parseInt(players.get(c).get(search))<=max) {
				searchedPlayers.add(players.get(c));
			}
		}
		return searchedPlayers;
	}

	//Retorna el primer jugador que encuentre con un dato especifico 
	public Player linearSearchForFirstPlayerWithValue(String search,String searchedFor) {
		boolean found=false;
		Player playerFound=null;
		for(int c=0;c< players.size()&& !found;c++) {
			if(players.get(c).get(search).equalsIgnoreCase(searchedFor)) {
				found=true;
				playerFound=players.get(c);
			}
		}
		return playerFound;
	}

	public ArrayList<Player> searchWithTree(String search, String searchedFor) {
		int index = 0;
		double first=0;
		double second=0;
		switch (search) {
		case "age":
			index = 0;
			break;
		case "points":
			index = 1;
			break;
		case "reBounds":
			index = 2;
			break;
		case "blocks":
			index = 3;
			break;
		case "assists":
			ArrayList<Player> pl= new ArrayList<Player>();
			ArrayList<RBNode<Integer, Player>> nodes= new ArrayList<>();
			first = System.nanoTime();
			nodes = rbTree.getRBSameValueNodes(Integer.parseInt(searchedFor), null);
			second = System.nanoTime();
			time=(second - first)/1000000000;
			for (int c = 0; c < nodes.size(); c++) {
				pl.add(nodes.get(c).getPlayer());
			}
			return pl;
		default:
			index = -1;
			break;
		}
		ArrayList<Player> pl = new ArrayList<Player>();
		if (index != -1) {
			first = System.nanoTime();
			ArrayList<Node<Integer,Player>> temp=binarySearchTrees.get(index).getSameValueNodes(Integer.parseInt(searchedFor), null);
			second = System.nanoTime();
			time=(second - first)/1000000000;
			for(int c=0;c<temp.size();c++) {
				pl.add(temp.get(c).getPlayer());
			}
		}else {
			first = System.nanoTime();
			pl = linearSearch(search, searchedFor);
			second = System.nanoTime();
			time=(second - first)/1000000000;
		}
		return pl;
	}
	//indice de el player que se va a cambiar
	//los nuevos datos del player (observe que son los mismos datos del constructor) 
	public void modify(int index,String name,String team, int age, int points,int reBounds, int blocks, int assists, int steals ) {

		Player modifiedPlayer=players.get(index);
		Player newPlayer= new Player(name, team, age, points, reBounds, blocks, assists, steals);
		//TODO sPodemos hacer players.get(index) = newPlayer; O algo parecido con un set
		players.remove(index);
		players.add(index, newPlayer);
		System.out.println(modifiedPlayer.getName());
		System.out.println(players.get(index).getName());
		int i=0;
		String[] values= {"age","points","reBounds","blocks"};
		// TODO Verificar que valores vamos a editar, pues si editamos el nombre, tambi�n estamos eliminando y volviendo a insertar.
		while(i<4) {
			// TODO Est� eliminando mal, elimina el primero que encuentre, no al que se busca
			System.out.println(binarySearchTrees.get(i).deleteSpecificPlayer(Integer.parseInt(modifiedPlayer.get(values[i])), modifiedPlayer).getPlayer());
			binarySearchTrees.get(i).insert(Integer.parseInt(newPlayer.get(values[i])), newPlayer);
			i++;
		}
		RBNode<Integer, Player> node = rbTree.searchRB(modifiedPlayer.getAssists(), modifiedPlayer);
		rbTree.rbDelete(node);
		rbTree.insert(newPlayer.getAssists(), newPlayer);
	}
	
	public void deletePlayer(int index) {
		Player deletedPlayer=players.get(index);
		players.remove(index);
		int i=0;
		String[] values= {"age","points","reBounds","blocks"};
		while(i<4) {
			binarySearchTrees.get(i).deleteSpecificPlayer(Integer.parseInt(deletedPlayer.get(values[i])), deletedPlayer);
			i++;
		}
		RBNode<Integer, Player> node = rbTree.searchRB(deletedPlayer.getAssists(), deletedPlayer);
		rbTree.rbDelete(node);
	}


	public void creatBinarySearchTree() {
		int i=0;
		String[] values= {"age","points","reBounds","blocks"};
		double first = System.nanoTime();
		
		while(i<4) {
			binarySearchTrees.add(new BinaryTree<Integer, Player>());
			for(int c=0;c<players.size();c++) {
				binarySearchTrees.get(i).insert(Integer.parseInt(players.get(c).get(values[i])), players.get(c));
			}

			i++;
		
		}
		
		rbTree = new RedBlackTree<Integer, Player>();
		for (int c = 0; c < players.size(); c++ ) {
			rbTree.insert(players.get(c).getAssists(), players.get(c));
		}

		/*RedBlackTree<Integer, Player> rb = new RedBlackTree<Integer, Player>();
		for (int c = 0; c < players.size(); c++ ) {
			rb.insert(players.get(c).getAge(), players.get(c));
		}*/
		double second = System.nanoTime();
		System.out.println((second - first)/1000000000 + "-- Creation time for the 5 trees (Seconds)");
		double searchOne = System.nanoTime();
		System.out.println(binarySearchTrees.get(1).search(98, null).getPlayer().getName());
		double searchTwo = System.nanoTime();
		System.out.println((searchTwo - searchOne)/1000000000 + " -- Search Time (Seconds)");
		//System.out.println(searchWithTree("age", 20));
		/*System.out.println(rb.getBlackHeight());*/
	}

	public void callCreatBinarySearchTreeThread() {
		createBinarySearchTree= new CreateBinarySearchTreeThread(this);
		createBinarySearchTree.start();
	}

	public List<BinaryTree<Integer, Player>> getBinarySearchTrees() {
		return binarySearchTrees;
	}

	public List<Player> getPlayers(){
		return players;
	}

	public void testCase() {
		int i=0;
		String[] values= {"age","points","reBounds","blocks"};
		binarySearchTrees.add(new BinaryTree<Integer, Player>());
		for(int c=0;c<5;c++) {
			System.out.println(players.get(c).getName());
			binarySearchTrees.get(i).insert(Integer.parseInt(players.get(c).get(values[i])), players.get(c));
		}
		System.out.println(binarySearchTrees.get(i).searchSpecificPlayer(players.get(2).getAge(), players.get(2)).getPlayer().getName());
		System.out.println("ANTES");
		binarySearchTrees.get(i).printTree(binarySearchTrees.get(i).getRoot());
		//binarySearchTrees.get(i).deleteSpecificPlayer(players.get(2).getAge(), players.get(2));
		modify(2, "SAPO DE MIERDA", "wewewewewewewe", 69, 69, 69, 69, 69, 69);
		System.out.println("DESPUES \n \n");
		binarySearchTrees.get(i).printTree(binarySearchTrees.get(i).getRoot());
	}
	
	public double getTime() {
		return time;
	}

}

