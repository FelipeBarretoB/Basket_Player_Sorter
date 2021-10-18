package Threads;

import java.util.ArrayList;
import model.AppManager;
import model.Player;

public class LinearSearchThread extends Thread{
	private AppManager appManager;
	private ArrayList<Player> searchedPlayers;
	private String search;
	private String searchedFor;
	
	public LinearSearchThread(AppManager appManager,String search, String searchedFor){
		this.appManager=appManager;
		this.search=search;
		this.searchedFor=searchedFor;
		this.searchedPlayers= new ArrayList<>();
		
	}
	
	public ArrayList<Player> getPlayers(){
		return searchedPlayers;
	}
	
	@Override
	public void run() {	
		appManager.linearSearch(search, searchedFor);
	}
	
}
