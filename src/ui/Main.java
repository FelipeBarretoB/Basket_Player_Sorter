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
		//System.out.println(ui.app.callLinearSearch("name", "Tori"));
		//System.out.println(ui.app.callLinearSearchWithRange(20, 22, "age"));
		ui.app.callCreatBinarySearchTreeThread();
		//--Problem with thread and the time it takes to charge
		//System.out.println(ui.app.searchWithTree("age", 20));
		//Object pera; meme con un amigo mio 

		
	}

}
