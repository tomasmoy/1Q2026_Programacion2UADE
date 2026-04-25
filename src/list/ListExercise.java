package list;
import java.util.Scanner;
import application.Exercise;

public class ListExercise extends Exercise {
	private int currentPhase = 0;
	private boolean firstTime = true;
	private SimpleArrayList<String> list;
	
	public ListExercise(Scanner scanner) {
		super(scanner);
		this.list = new SimpleArrayList<>();
	}
	
	@Override
	protected void exerciseLogic() {
		
		if (firstTime) System.out.println("Bienvenido a ListExercise!\n");
		
		switch(currentPhase) {
		case 0:
			menuLogic();
			break;
		case 1:
			addElement();
			break;
		case 2:
			removeElementByIndex();
			break;
		case 3:
			removeElementByRef();
			break;
		case 4:
			clearList();
			break;
		}
		
	}
	
	private void menuLogic() {
		
		firstTime = false;
		
		String fullList = "";
		int cantElementos = list.size();
		
		
		System.out.println("Cantidad de elementos de la lista: " + cantElementos);
		
		if (cantElementos != 0) {
			for (int i=0; i < list.size(); i++) {
				fullList += list.get(i);
				if (i < list.size() - 1) {
					fullList += ", ";							
				}
			}
			System.out.println(fullList);
		}
		else {
			System.out.println("No hay elementos en la lista para mostrar.\n");
		}
		
		System.out.println("Ingrese la opción a ejecutar:");
		System.out.println("1 - Agregar Elementos a la lista");
		System.out.println("2 - Eliminar elemento por Indice");
		System.out.println("3 - Eliminar elemento por referencia");
		System.out.println("4 - Eliminar todos elementos de la lista");
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
	
	private void addElement() {
		
		System.out.println("Ingrese el dato a ingresar:\n");
		
		String input = scanner.nextLine();
		
		list.add(input);
		
		askInput();
		
	}
	
	private void askInput() {
		System.out.println("Desea agregar otro elemento? (s/n)\n");
		
		String input = scanner.nextLine();
		
		switch(input) {
		case "s":
			currentPhase = 1;
			break;
		case "n":
			currentPhase = 0;
			break;
		default:
			System.out.println("Entrada Inválida, Intente Nuevamente");
			askInput();
			break;
		}
		
	}
	
	private void removeElementByIndex() {
		
		int cantElementos = list.size();
		
		if (cantElementos == 0) {
			System.out.println("La lista está vacía, cargue datos e intente nuevamente.");
			currentPhase = 0;
			return;
		}
		
		System.out.println("Ingrese el indice a eliminar:\n");
		
		String input = scanner.nextLine();
		
		try {
			int index = Integer.parseInt(input);
			if (index >= list.size() || index < 0) {
				System.out.println("Elemento en Posición: " + input + " no encontrado.");
			}
			else {
				list.remove(index);
				System.out.println("Elemento en Posición: " + input + " Eliminado con exito.");
			}
		} catch (Exception e) {
			System.out.println("Entrada Inválida, intente nuevamente.");
			
		}
		
			
		currentPhase = 0;
		
	}
	
	private void removeElementByRef() {
		
		int cantElementos = list.size();
		
		if (cantElementos == 0) {
			System.out.println("La lista está vacía, cargue datos e intente nuevamente.");
			currentPhase = 0;
			return;
		}
		
		System.out.println("Ingrese el elemento a eliminar:\n");
		
		String input = scanner.nextLine();
		
		if (list.contains(input)) {
			list.remove(input);
			System.out.println("Elemento con referencia: " + input + " Eliminado con exito.");
		} else {
			System.out.println("Elemento con referencia: " + input + " no encontrado.");
		}
		
		currentPhase = 0;		
	}
	
	private void clearList() {
		int cantElementos = list.size();
		
		if (cantElementos == 0) {
			System.out.println("La lista está vacía, cargue datos e intente nuevamente.");
			currentPhase = 0;
		} else {
			list.clear();
			currentPhase = 0;
			System.out.println("Lista vaciada correctamente!");
		}	
	}	
}
