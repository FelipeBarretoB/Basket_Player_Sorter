package Threads;

import java.io.IOException;

import model.AppManager;

public class ImportDataBaseThread extends Thread {
	
	private AppManager appManager;
	
	public ImportDataBaseThread(AppManager appManager){
		this.appManager=appManager;
	}
	
	@Override
	public void run() {	
		try {
			appManager.importPlayerDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
