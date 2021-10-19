package Threads;

import model.AppManager;

public class CreateBinarySearchTreeThread extends Thread {
	private AppManager appManager;
	
	public CreateBinarySearchTreeThread(AppManager appManager) {
		this.appManager=appManager;
	}
	
	@Override
	public void run() {	
		appManager.creatBinarySearchTree();
	}
}
