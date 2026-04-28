package priorityqueue;
import java.util.Scanner;
import application.Exercise;
import list.SimpleArrayList;

public class PriorityQueueExercise extends Exercise {
	private int currentPhase = 0;
	private boolean firstTime = true;
	private SimpleArrayPriorityQueue<CaseObject> caseQueue;
	private SimpleArrayList<CaseObject> closedCases = new SimpleArrayList<>();
	
	public PriorityQueueExercise(Scanner scanner) {
		super(scanner);
		this.caseQueue = new SimpleArrayPriorityQueue<>();
	}
	
	@Override
	protected void exerciseLogic() {
		
		if (firstTime) System.out.println("Bienvenido a Issue Tracker!\n");
		
		switch(currentPhase) {
		case 0:
			menuLogic();
			break;
		case 1:
			loadCase();
			break;
		case 2:
			closeCase();
			break;
		case 3:
			reviewClosedCases();
			break;
		case 4:
			showCases();
			break;
		}
		
	}
	
	private void menuLogic() {
		
		firstTime = false;
		
		if (!caseQueue.isEmpty()) {
			System.out.println("Próximo Caso prioritario a Resolver: Titulo: " + caseQueue.peek().caseTitle + " - Descripción: " + caseQueue.peek().caseDesc + " - Prioridad: " + casePriorityTranslator(caseQueue.peek().casePriority));
			System.out.println("Cantidad de casos abiertos: " + caseQueue.size());
		}
		else System.out.println("No hay casos abiertos.");

		System.out.println("\nIngrese la opción a ejecutar: ");
		System.out.println("1 - Nuevo Caso"); // Pide datos caso.
		System.out.println("2 - Atender Siguiente Caso"); // Peek() -> Opcion de Cerrar
		System.out.println("3 - Ver casos cerrados");
		System.out.println("4 - Generar Reporte");// Print # Casos Abiertos, Lista Casos Cerrados
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
	
	private void loadCase() {
		
		
		System.out.println("A continuación ingrese los datos del caso:\n");
		System.out.println("Título: ");
		String caseTitle = scanner.nextLine();
		System.out.println("Descripción: ");
		String caseDesc = scanner.nextLine();
		int casePriority = assignCasePriority();
		
		CaseObject newCase = new CaseObject(caseTitle,caseDesc, casePriority); 
		
		caseQueue.enqueue(newCase, casePriority);
		
		askInput();
		
	}
	
	private void closeCase() {
		
		if (caseQueue.isEmpty()) {
			System.out.println("La Cola Esta Vacia, Cargue casos e intente nuevamente.");
			currentPhase = 0;
		} else {
			System.out.println("Trabajando en: \n");
			System.out.println("Titulo: " + caseQueue.peek().caseTitle + "\n");
			System.out.println("Descripción: " + caseQueue.peek().caseDesc + "\n");
			System.out.println("Prioridad: " + casePriorityTranslator(caseQueue.peek().casePriority) + "\n");
			
			System.out.println("Desea Cerrar el Caso? (s/n)");
			
			String closeFlag = scanner.nextLine().toLowerCase();
			
			switch(closeFlag) {
			case "s":
				CaseObject dequeued = caseQueue.dequeue();
				closedCases.add(dequeued);
				currentPhase = 0;
				break;
			case "n":
				System.out.println("Volviendo al menu principal!\n");
				currentPhase = 0;
				break;
			default:
				System.out.println("Entrada Incorrecta, intentelo nuevamente\n");
				break;
			}
		}		
	}
	
	private void showCases() {
		if (caseQueue.isEmpty()) {
			System.out.println("La Cola Esta Vacia, Cargue casos e intente nuevamente.");
			currentPhase = 0;
		} else {
		Object[] result = caseQueue.toArray();
		for (int i = 0; i < result.length; i++) {
			CaseObject actualCase = (CaseObject) result[i];
			System.out.println("Titulo: " + actualCase.caseTitle + " - Descripción: " + actualCase.caseDesc + " - Prioridad: " + casePriorityTranslator(actualCase.casePriority) + "\n");
			
			System.out.println("Desea Cerrar el Caso? (s/n)");
			
			String closeFlag = scanner.nextLine().toLowerCase();
			
			switch(closeFlag) {
			case "s":
				CaseObject dequeued = caseQueue.dequeue();
				closedCases.add(dequeued);
				currentPhase = 0;
				break;
			case "n":
				continue;				
			default:
				System.out.println("Entrada Incorrecta, intentelo nuevamente\n");
				break;
			}
		}
		}
		currentPhase = 0;
	}
	
	private void reviewClosedCases() {
		System.out.println("---- Casos Cerrados -----");
		if (closedCases.isEmpty()) {
			System.out.println("No hay casos cerrados.\n");
			currentPhase = 0;
		}
		for (int i = 0; i < closedCases.size(); i++) {
			CaseObject caseClosed = closedCases.get(i);
			System.out.println("Titulo: " + caseClosed.caseTitle + " - Descripción: " + caseClosed.caseDesc + " - Prioridad: " + casePriorityTranslator(caseClosed.casePriority) + "\n");
		}
		currentPhase = 0;
	}
	
	private String casePriorityTranslator(int casePriority) {
		switch (casePriority) {
		case 1:	return "Baja";
		case 2: return "Media";
		case 3: return "Alta";
		case 4: return "Critica";
		default: break;}
		return null;
	}
	
	private int assignCasePriority() {
		System.out.println("Asignar Prioridad (C - Critica | A - Alta | M - Media | B - Baja): ");
		String priority = scanner.nextLine().toUpperCase();
		switch (priority) {
		case "C":	return 4;
		case "A": return 3;
		case "M": return 2;
		case "B": return 1;
		default:
			System.out.println("Entrada invalida. Intente nuevamente!");
			assignCasePriority();
			break;
		}
		return 0;
	}
	
	private void askInput() {
		System.out.println("Desea agregar otro caso? (s/n)\n");
		
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

}
