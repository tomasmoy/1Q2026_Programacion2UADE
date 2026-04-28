package application;

import java.util.Scanner;

import dictionary.DictionaryExercise;
import list.ListExercise;
import priorityqueue.PriorityQueueExercise;
import queue.QueueExercise;
import set.SetExercise;
import stack.StackExercise;

public class MainProgram {

	private boolean running = true;
	private Exercise exercise = null;
	
	public static void main(String[] args) {
		new MainProgram().run();
	}
	
	private void run() {
		Scanner scanner = new Scanner(System.in);
		while (running) {
			selectExercise(scanner);	
			if (exercise != null) exercise.run();
		}
		System.out.println("Programa finalizado!");
		
	}
	
	private void selectExercise(Scanner scanner){
			System.out.println("Elija una opcion para continuar:");
			System.out.println("0 - Salir");
			System.out.println("1 - Test Exercise");
			System.out.println("2 - List Exercise");
			System.out.println("3 - Queue Exercise");
			System.out.println("4 - Stack Exercise");
			System.out.println("5 - Set Exercise");
			System.out.println("6 - Issue Tracker");
			System.out.println("7 - Inventory Management");
			
			String userInput = scanner.nextLine();
			
			switch (userInput) {
			case "0":
				running = false;
				break;
			case "1":
				exercise = new TestExercise(scanner);
				break;
			case "2":
				exercise = new ListExercise(scanner);
				break;
			case "3":
				exercise = new QueueExercise(scanner);
				break;
			case "4":
				exercise = new StackExercise(scanner);
				break;
			case "5":
				exercise = new SetExercise(scanner);
				break;
			case "6":
				exercise = new PriorityQueueExercise(scanner);
				break;
			case "7":
				exercise = new DictionaryExercise(scanner);
				break;
			default:
				System.out.println("Entrada inválida, intentá de nuevo.");			
				break;
			}
			
	}
 
}
