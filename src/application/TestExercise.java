package application;

import java.util.Scanner;

public class TestExercise extends Exercise{

	public TestExercise(Scanner scanner) {
		super(scanner);
	}
	
	@Override
	protected void exerciseLogic() {
		
		System.out.println("Bienvenido a Test Exercise\n");
		System.out.println("Ingrese 0 para volver el menú:\n");
		
		String input = scanner.nextLine();
		
		if (input.equals("0")) {
			running = false;
		}
	}
}
