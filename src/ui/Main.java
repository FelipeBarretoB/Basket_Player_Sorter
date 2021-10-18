package ui;

import java.io.IOException;

import model.AppManager;

public class Main {

	private AppManager app;
	
	public Main() {
		app=new AppManager();
	}
	
	public static void main(String[] args) {
		Main ui= new Main();
		try {
			ui.app.importPlayerDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ui.app.callLinearSearch("name", "Tori");
		ui.app.callLinearSearchWithRange(20, 22, "age");
		//Object pera; meme con un amigo mio 

		
	}

}
