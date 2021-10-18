package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Threads.ImportDataBaseThread;
import Threads.LinearSearchThread;
import Threads.LinearSearchWithRange;

public class AppManager {
	
	private List<Player> players;
	private ImportDataBaseThread importDataBaseThread;
	private LinearSearchThread linearSearchThread;
	private LinearSearchWithRange linearSearchWithRange;

	public AppManager(){
		players=new ArrayList<>();
		importDataBaseThread= new ImportDataBaseThread(this);
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
		System.out.println(players.size());
		br.close();
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
	
		
	
	public ArrayList<Player> linearSearch(String search, String searchedFor){
		ArrayList<Player> searchedPlayers= new ArrayList<>();
		for(int c=0;c< players.size();c++) {
			if(players.get(c).get(search).equalsIgnoreCase(searchedFor)) {
				System.out.println(players.get(c).get(search));
				searchedPlayers.add(players.get(c));
			}
		}
		return searchedPlayers;
	}
	
	
	public ArrayList<Player> linearSearchWithRange(int min, int max,String search) {
		ArrayList<Player> searchedPlayers= new ArrayList<>();
		for(int c=0;c< players.size();c++) {
			if(Integer.parseInt(players.get(c).get(search))>=min && Integer.parseInt(players.get(c).get(search))<=max) {
				System.out.println(players.get(c).get(search));
				searchedPlayers.add(players.get(c));
			}
		}
		return searchedPlayers;
	}
}

