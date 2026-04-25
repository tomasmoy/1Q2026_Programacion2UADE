package set;

import java.util.Scanner;

import application.Exercise;

public class SetExercise extends Exercise {
	
	private int currentPhase = 0;
	private boolean firstTime = true;
	private SimpleSet<String> set_a;
	private SimpleSet<String> set_b;
	private SimpleSet<String> chosenSet;
	private String chosenSetText = "Set A";
	
	public SetExercise(Scanner scanner) {
		super(scanner);
		set_a = new SimpleLinkedSet<String>();
		set_b = new SimpleLinkedSet<String>();
		chosenSet = set_a;
	}

	@Override
	protected void exerciseLogic() {
		
		if (firstTime) System.out.println("Bienvenido a SetExercise!\n");
		
		switch(currentPhase) {
		case 0:
			menuLogic();
			break;
		case 1:
			addLogic(chosenSet);
			break;
		case 2:
			removeLogic(chosenSet);
			break;
		case 3:
			unionLogic();
			break;
		case 4:
			intersectLogic();
			break;
		case 5:
			differenceLogic();
			break;
		}
	}
	
	private void setSelector() {
		System.out.println("Ingrese el Set a modificar:");
		System.out.println("1 - Set A");
		System.out.println("2 - Set B");
		
		String userInput = scanner.nextLine().toLowerCase();
		
		switch(userInput) {
		case "1":
			chosenSet = set_a;
			chosenSetText = "Set A";
			break;
		case "2":
			chosenSet = set_b;
			chosenSetText = "Set B";
			break;
		default:
			System.out.println("Entrada Inválida, Intente de Nuevo!");
			setSelector();
			break;
		}
	}
	
	
	
	
	private void menuLogic() {
		firstTime = false;

		System.out.println("\nStatus Sets");
		System.out.println("Set A - Elementos: " + set_a.size());
		System.out.println("Set B - Elementos: " + set_b.size());
		
		System.out.println("Actualmente trabajando en: " + chosenSetText + "\n");
		
		System.out.println("Ingrese la opción a ejecutar:");
		System.out.println("0 - Selector de Set");
		System.out.println("1 - Agregar Elementos al Set");
		System.out.println("2 - Eliminar Elementos del Set");
		System.out.println("3 - Union de Sets");
		System.out.println("4 - Interseccion de Sets");
		System.out.println("5 - Diferencia entre Sets");
		System.out.println("6 - Volver al menú principal");
		
		String userInput = scanner.nextLine().toLowerCase();
		
		switch(userInput) {
		case "0":
			setSelector();
			break;
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
			currentPhase = 5;
			break;
		case "6":
			running = false;
			break;
		default:
			System.out.println("Entrada Inválida, Intentá de nuevo!\n");
			currentPhase = 0;
			break;
		}	
	}
	
	private void addLogic(SimpleSet<String> chosenSet) {
		System.out.println("Ingrese el dato a ingresar:\n");
		
		String input = scanner.nextLine();
		
		boolean successFlag = chosenSet.add(input);
		
		if (successFlag) {
			System.out.println("Elemento: " + input + " agregado exitosamente!");
		} else {
			System.out.println("Error. Elemento: " + input + " ya se encuentra en el set o es null!");
		}
		
		askInput("add");
		
	}
	
	private void removeLogic(SimpleSet<String> chosenSet) {
		if (chosenSet.isEmpty()) {
			System.out.println("El set está vacío! Volviendo al menú principal.");
			currentPhase = 0;
			return;
		}
		
		System.out.println("Ingrese el dato a eliminar:\n");
		
		String input = scanner.nextLine();
		
		boolean successFlag = chosenSet.remove(input);
		
		if (successFlag) {
			System.out.println("Elemento: " + input + " eliminado exitosamente!");
		} else {
			System.out.println("Error al eliminar! Intente nuevamente.");
		}
		
		askInput("delete");
	}
	
	private void unionLogic() {
		Object[] result = set_a.unionWith(set_b).toArray();
		System.out.println("Union entre Sets:");
		printList(result);
		currentPhase = 0;
	}
	
	
	private void printList(Object[] list) {
		String fullList = "";
		
		if (list.length != 0) {
			for (int i=0; i < list.length; i++) {
				fullList += list[i];
				if (i < list.length - 1) {
					fullList += ", ";							
				}
			}
			System.out.println(fullList);
		}
		else {
			System.out.println("No hay elementos en la lista para mostrar.\n");
		}
	}
	
	private void intersectLogic() {
		Object[] result = set_a.intersectWith(set_b).toArray();
		System.out.println("Intersección entre Sets:");
		printList(result);
		currentPhase = 0;
	}
	
	private void differenceLogic() {
		SimpleSet<String> chosenSetX = null;
		SimpleSet<String> chosenSetY = null;
		
		System.out.println("Ingrese el Set a comparar (A o B):\n");
		
		String setInput = scanner.nextLine().toLowerCase();
		
		switch(setInput) {
		case "a":
			chosenSetX = set_a;
			chosenSetY = set_b;
			break;
		case "b":
			chosenSetX = set_b;
			chosenSetY = set_a;
			break;
		default:
			System.out.println("Entrada Invalida. Elija una opcion correcta!");
			return;
		}
		
		Object[] result = chosenSetX.differenceWith(chosenSetY).toArray();
		System.out.println("Diferencia entre Sets:");
		printList(result);
		currentPhase = 0;
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
}
