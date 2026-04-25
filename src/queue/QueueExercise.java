package queue;

import java.util.Scanner;

import application.Exercise;

public class QueueExercise extends Exercise {
	
	private int currentPhase = 0;
	private boolean firstTime = true;
	private SimpleQueue<String> queue;

	public QueueExercise(Scanner scanner) {
		super(scanner);
		queue = new SimpleLinkedQueue<String>();
	}

	@Override
	protected void exerciseLogic() {
		
		if (firstTime) System.out.println("Bienvenido a QueueExercise!\n");
		
		switch(currentPhase) {
		case 0:
			menuLogic();
			break;
		case 1:
			enqueueLogic();
			break;
		case 2:
			dequeueLogic();
			break;
		case 3:
			peekLogic();
			break;
		case 4:
			clearQueue();
			break;
		}
	}
		
	private void menuLogic() {
		firstTime = false;

		if (!queue.isEmpty()) {
			System.out.println("Cola No Vacía");
			System.out.println("Cantidad de elementos de la Cola: " + queue.size());
		}
		else {
			System.out.println("Cola Vacía");
			System.out.println("No hay elementos en la Cola para mostrar.\n");
		}
		
		System.out.println("Ingrese la opción a ejecutar:");
		System.out.println("1 - Agregar Elementos a la Cola");
		System.out.println("2 - Eliminar primer elemento de la Cola y Mostrarlo");
		System.out.println("3 - Observar primer elemento de la Cola");
		System.out.println("4 - Eliminar todos elementos de la Cola");
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
	
	private void enqueueLogic() {
		
		
		System.out.println("Ingrese el dato a ingresar:\n");
		
		String input = scanner.nextLine();
		
		queue.enqueue(input);
		
		System.out.println("Elemento: " + input + " agregado exitosamente!");
		
		askInput("add");
		
	}

	
	private void askInput(String method) {
		
		if (method == "remove") System.out.println("Desea remover otro elemento? (s/n)\n");
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
	
	private void dequeueLogic() {
		
		if (queue.isEmpty()) {
			System.out.println("La Cola está vacía, cargue datos e intente nuevamente.");
			currentPhase = 0;
			return;
		}
		
		String element = queue.dequeue();
		System.out.println("Elemento: " + element + " eliminado con éxito!");
		
		askInput("delete");
		
		
	}
	
	private void peekLogic() {
		if (queue.isEmpty()) {
			System.out.println("La Cola está vacía, cargue datos e intente nuevamente.");
			currentPhase = 0;
		} else {
			currentPhase = 0;
			System.out.println("Primer elemento de la Cola: " + queue.peek());
		}	
	}
	
	private void clearQueue() {		
		if (queue.isEmpty()) {
			System.out.println("La Cola está vacía, cargue datos e intente nuevamente.");
			currentPhase = 0;
		} else {
			queue.clear();
			currentPhase = 0;
			System.out.println("Cola vaciada correctamente!");
		}	
	}
}
