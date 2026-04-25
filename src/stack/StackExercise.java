package stack;

import java.util.Scanner;

import application.Exercise;

public class StackExercise extends Exercise {
	
	private int currentPhase = 0;
	private boolean firstTime = true;
	private SimpleStack<String> stack;

	public StackExercise(Scanner scanner) {
		super(scanner);
		stack = new SimpleArrayStack<String>();
	}

	@Override
	protected void exerciseLogic() {
		
		if (firstTime) System.out.println("Bienvenido a StackExercise!\n");
		
		switch(currentPhase) {
		case 0:
			menuLogic();
			break;
		case 1:
			pushLogic();
			break;
		case 2:
			popLogic();
			break;
		case 3:
			peekLogic();
			break;
		case 4:
			clearStack();
			break;
		}
	}
		
	private void menuLogic() {
		firstTime = false;

		if (!stack.isEmpty()) {
			System.out.println("Pila No Vacía");
			System.out.println("Cantidad de elementos de la Pila: " + stack.size());
		}
		else {
			System.out.println("Pila Vacía");
			System.out.println("No hay elementos en la Pila para mostrar.\n");
		}
		
		System.out.println("Ingrese la opción a ejecutar:");
		System.out.println("1 - Agregar Elementos a la Pila");
		System.out.println("2 - Ver Tope y eliminar primer elemento de la Pila");
		System.out.println("3 - Ver Tope");
		System.out.println("4 - Eliminar todos elementos de la Pila");
		System.out.println("5 - Volver al menú principal");
		
		String userInput = scanner.nextLine().toLowerCase();
		
		switch(userInput) {
		case "1":
			currentPhase = 1;
			break;
		case "2":
			currentPhase = 2;
			break;
		case "3":
			currentPhase = 3;
			break;
		case "4":
			currentPhase = 4;
			break;
		case "5":
			running = false;
			break;
		default:
			System.out.println("Entrada Inválida, Intentá de nuevo!\n");
			currentPhase = 0;
			break;
		}	
	}
	
	private void pushLogic() {
		
		System.out.println("Ingrese el dato a ingresar:\n");
		
		String input = scanner.nextLine();
		
		stack.push(input);
		
		System.out.println("Elemento: " + input + " agregado exitosamente!");
		
		askInput("add");
		
	}

	
	private void askInput(String method) {
		
		if (method == "delete") System.out.println("Desea remover otro elemento? (s/n)\n");
		else if (method == "add") System.out.println("Desea agregar otro elemento? (s/n)\n");
		
		String input = scanner.nextLine();
		
		switch(input) {
		case "s":
			if (method == "delete") {
				currentPhase = 2;
				break;
			}
			else currentPhase = 1;
			break;
		case "n":
			currentPhase = 0;
			break;
		default:
			System.out.println("Entrada Inválida, Intente Nuevamente");
			askInput(method);
			break;
		}
	}
	
	private void popLogic() {
		
		if (stack.isEmpty()) {
			System.out.println("La Pila está vacía, cargue datos e intente nuevamente.");
			currentPhase = 0;
			return;
		}
		
		String element = stack.pop();
		System.out.println(element);
		
		askInput("delete");
		
		
	}
	
	private void peekLogic() {
		if (stack.isEmpty()) {
			System.out.println("La Pila está vacía, cargue datos e intente nuevamente.");	
		} else {
			System.out.println("Primer elemento de la Pila: " + stack.peek());
		}	
		currentPhase = 0;
	}
	
	private void clearStack() {		
		if (stack.isEmpty()) {
			System.out.println("La Pila está vacía, cargue datos e intente nuevamente.");

		} else {
			stack.clear();
			System.out.println("Pila vaciada correctamente!");
		}	
		currentPhase = 0;
	}
}
