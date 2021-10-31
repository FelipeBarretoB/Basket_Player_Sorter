package threads;

import java.util.ArrayList;

import model.AppManager;
import model.Player;

public class LinearSearchWithRange extends Thread{
	private AppManager appManager;
	private ArrayList<Player> searchedPlayers;
	private int min;
	private int max;
	private String search;
	
	public LinearSearchWithRange(AppManager appManager,int min, int max, String search){
		this.appManager=appManager;
		this.min=min;
		this.max=max;
		this.searchedPlayers= new ArrayList<>();
		this.search=search;
	}
	
	public ArrayList<Player> getPlayers(){
		return searchedPlayers;
	}
	
	@Override
	public void run() {	
		searchedPlayers=appManager.linearSearchWithRange(min, max, search);
	}
	
}