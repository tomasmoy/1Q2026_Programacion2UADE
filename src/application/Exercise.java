package application;

import java.util.Scanner;

public abstract class Exercise {
	protected boolean running = true;
	protected Scanner scanner;
	
	public Exercise(Scanner scanner) {
		this.scanner = scanner; 
	}
		
	public void run(){
		while(running) {
			exerciseLogic();
		}
		
	}

	protected abstract void exerciseLogic();
	
}
