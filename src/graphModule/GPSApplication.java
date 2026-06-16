package graphModule;
import java.util.Scanner;

import application.Exercise;
import dictionary.SimpleArrayDictionary;
import dictionary.SimpleDictionary;
import list.SimpleArrayList;
import list.SimpleList;
import stack.SimpleArrayStack;
import stack.SimpleStack;

public class GPSApplication extends Exercise {
	private int currentPhase = 0;
	private boolean firstTime = true;
	private Graph<String> graph = null;
	private SimpleList<tripClass> savedRoutes = new SimpleArrayList<>();
	private String actualStation = null;
	
	public GPSApplication(Scanner scanner) {
		super(scanner);
	}
	
	@Override
	protected void exerciseLogic() {
		if (firstTime) {
			System.out.println("Bienvenido a UADEmaps!\n");
			loadMap();
		}
		
		switch(currentPhase) {
		case 0:
			menuLogic();
			break;
		case 1:
			getRoute();
			break;
		case 2:
			savedRoutes();
			break;
		case 3:
			getNearStations();
			break;
		case 4:
			getDistanceToAllNodes();
			break;
		case 5:
			changeSession();
			break;			
		}
	}

	private void loadMap() {
		System.out.println("Seleccione el mapa a cargar:");
		System.out.println("1 - Subte CABA");
		String userInput = scanner.nextLine();
		switch (userInput) {
		case "1":
			graph = loadSubte();
			break;
		default:
			System.out.println("Entrada Inválida, Intentá de nuevo!\n");
			loadMap();
			break;
		}
	}
	
	private void conectarLinea(Graph<String> g, String[] estaciones) {
        for (int i = 0; i < estaciones.length - 1; i++) {
            g.addEdge(estaciones[i], estaciones[i+1], 2);
            g.addEdge(estaciones[i+1], estaciones[i], 2);
        }
    }
	
	private void generarTrasbordo(Graph<String> graph, String edgeA, String edgeB, int weight) {
		graph.addEdge(edgeA, edgeB, weight);
		graph.addEdge(edgeB, edgeA, weight);
	}
	
	private Graph<String> loadSubte() {
		Graph<String> subteMap = new ListGraph<String>();
// C->E(RetiroOK) E->B OK E->A OK C->A OK D->E E->H
	    String[] lineaA = {
	        "Linea A - San Pedrito", "Linea A - San Jose de Flores", "Linea A - Carabobo", "Linea A - Puan",
	        "Linea A - Primera Junta", "Linea A - Acoyte", "Linea A - Rio de Janeiro", "Linea A - Castro Barros",
	        "Linea A - Loria", "Linea A - Plaza Miserere", "Linea A - Alberti", "Linea A - Pasco",
	        "Linea A - Congreso", "Linea A - Saenz Peña", "Linea A - Lima", "Linea A - Piedras", "Linea A - Peru", "Linea A - Plaza de Mayo"
	    };

	    String[] lineaB = {
	        "Linea B - Juan Manuel de Rosas", "Linea B - Echeverria", "Linea B - Los Incas",
	        "Linea B - Tronador", "Linea B - Federico Lacroze", "Linea B - Dorrego", "Linea B - Malabia",
	        "Linea B - Angel Gallardo", "Linea B - Medrano", "Linea B - Carlos Gardel",
	        "Linea B - Pueyrredon", "Linea B - Pasteur", "Linea B - Callao",
	        "Linea B - Uruguay", "Linea B - Carlos Pellegrini", "Linea B - Florida", "Linea B - Leandro N. Alem"
	    };

	    String[] lineaC = {
	        "Linea C - Retiro", "Linea C - San Martin", "Linea C - Lavalle", "Linea C - Diagonal Norte",
	        "Linea C - Avenida de Mayo", "Linea C - Moreno", "Linea C - Independencia", "Linea C - Constitucion"
	    };

	    String[] lineaD = {
	        "Linea D - Congreso de Tucuman", "Linea D - Juramento", "Linea D - Jose Hernandez",
	        "Linea D - Olleros", "Linea D - Ministro Carranza", "Linea D - Palermo", "Linea D - Plaza Italia",
	        "Linea D - Scalabrini Ortiz", "Linea D - Bulnes", "Linea D - Aguero", "Linea D - Pueyrredon",
	        "Linea D - Facultad de Medicina", "Linea D - Callao", "Linea D - Tribunales",
	        "Linea D - 9 de Julio", "Linea D - Catedral"
	    };

	    String[] lineaE = {
	        "Linea E - Retiro", "Linea E - Catalinas", "Linea E - Correo Central",
	        "Linea E - Bolivar", "Linea E - Belgrano", "Linea E - Independencia", "Linea E - San Jose",
	        "Linea E - Entre Rios", "Linea E - Pichincha", "Linea E - Jujuy", "Linea E - General Urquiza",
	        "Linea E - Boedo", "Linea E - Avenida La Plata", "Linea E - Jose Maria Moreno",
	        "Linea E - Emilio Mitre", "Linea E - Medalla Milagrosa", "Linea E - Varela", "Linea E - Plaza de los Virreyes"
	    };

	    String[] lineaH = {
	        "Linea H - Facultad de Derecho", "Linea H - Las Heras", "Linea H - Santa Fe",
	        "Linea H - Cordoba", "Linea H - Corrientes", "Linea H - Once", "Linea H - Venezuela",
	        "Linea H - Humberto I", "Linea H - Inclan", "Linea H - Caseros", "Linea H - Parque Patricios",
	        "Linea H - Hospitales"
	    };
	    conectarLinea(subteMap, lineaA);
	    conectarLinea(subteMap, lineaB);
	    conectarLinea(subteMap, lineaC);
	    conectarLinea(subteMap, lineaD);
	    conectarLinea(subteMap, lineaE);
	    conectarLinea(subteMap, lineaH);

	    generarTrasbordo(subteMap,"Linea A - Lima","Linea C - Avenida de Mayo",1); 
	    generarTrasbordo(subteMap,"Linea A - Peru","Linea D - Catedral",1);     
	    generarTrasbordo(subteMap,"Linea B - Carlos Pellegrini","Linea D - 9 de Julio",1);
	    generarTrasbordo(subteMap,"Linea B - Carlos Pellegrini","Linea C - Diagonal Norte",1);
	    generarTrasbordo(subteMap,"Linea C - Independencia","Linea E - Independencia",1);
	    generarTrasbordo(subteMap,"Linea C - Retiro","Linea E - Retiro",1);
	    generarTrasbordo(subteMap,"Linea H - Once","Linea A - Plaza Miserere",1); 
	    generarTrasbordo(subteMap,"Linea H - Santa Fe","Linea D - Pueyrredon",1);
	    generarTrasbordo(subteMap,"Linea H - Corrientes","Linea B - Pueyrredon",1);
	    generarTrasbordo(subteMap,"Linea H - Corrientes","Linea B - Pueyrredon",1);
	    generarTrasbordo(subteMap,"Linea E - Correo Central","Linea B - Leandro N. Alem",1);
	    generarTrasbordo(subteMap,"Linea E - Bolivar","Linea A - Peru",1);
	    generarTrasbordo(subteMap,"Linea E - Bolivar","Linea D - Catedral",1);
	    
	    return subteMap;
	}

	private void changeSession() {
		if (actualStation != null) {
		System.out.println("Trabajando sobre: "+ actualStation + "\n");
		System.out.println("Seleccione la opción deseada: ");
		System.out.println("1- Seleccionar Estacion a Modificar");
		System.out.println("2- Agregar factor de trafico");
		System.out.println("3- Verificar estado estaciones");
		System.out.println("4- Volver al menú principal");
		
		String userInput = scanner.nextLine();
		switch (userInput) {
		case "1":
			selectStation();
			break;
		case "2":
			addTrafficFactor();
			break;
		case "3":
			checkStationStatuses();
			break;
		case "4":
			currentPhase = 0;
			break;
		default:
			System.out.println("Entrada Inválida, Intentá de nuevo!\n");
			break;
		}
		
		}
		else {
			System.out.println("\n --- No hay una estación seleccionada aún ---\n");
			System.out.println("Seleccione la opción deseada: ");
			System.out.println("0- Verificar estado estaciones");
			System.out.println("1- Seleccionar Estacion a Modificar");
			System.out.println("2- Volver al menú principal");
			String userInput = scanner.nextLine();
			switch (userInput) {
			case "0":
				checkStationStatuses();
				break;
			case "1":
				selectStation();
				break;
			case "2":
				currentPhase = 0;
				break;
			default:
				System.out.println("Entrada Inválida, Intentá de nuevo!\n");
				break;
			}
		}
	}
	
	private void checkStationStatuses() {

		System.out.println("\n--- Estado de las estaciones ---");
		
	    SimpleList<String> vertices = graph.vertices();

	    int normal = 0;
	    int highDelay = 0;
	    int criticalDelay = 0;


	    for (int i = 0; i < vertices.size(); i++) {

	        String station = vertices.get(i);

	        SimpleList<Edge<String>> neighbors =
	                graph.getNeighbors(station);

	        for (int j = 0; j < neighbors.size(); j++) {

	            Edge<String> edge = neighbors.get(j);

	            if (edge.weight > 10) {
	                criticalDelay++;

	                System.out.println("DEMORA: [CRITICA] " + station + " -> " + edge.destination + " (Peso: " + edge.weight + ")");

	            } else if (edge.weight > 3) {
	                highDelay++;

	                System.out.println("DEMORA: [ALTA] " + station + " -> " + edge.destination + " (Peso: " + edge.weight + ")");

	            } else {
	                normal++;
	            }
	        }
	    }

	    System.out.println("\n--- Resumen ---");
	    System.out.println("Conexiones normales: " + normal);
	    System.out.println("Demora alta: " + highDelay);
	    System.out.println("Demora crítica: " + criticalDelay);

	    return;
	}
	
	private void selectStation() {
		System.out.println("--- Ingrese la estacion a modificar ---");
		String station = searchStationsResult();
		
		if (station == null) {
	    	System.out.println("No se estableció una estacion.\n");
	    	currentPhase = 5;
	    	return;
	    }
		actualStation = station;
		
	}
	
	
	private void addTrafficFactor() {
		System.out.println("--- Modificando factor de tránsito ---");
		
		Edge<String> selectedEdge = selectConnection(actualStation);
		
		if (selectedEdge == null)
		    return;

		System.out.println("Seleccionado: " + actualStation + " -> " + selectedEdge.destination);
		System.out.println("Ingrese el factor de tránsito a aplicar");
		double trafficFactor = readDouble();
		
		double newWeight = selectedEdge.weight * trafficFactor;
		
		graph.addEdge(actualStation, selectedEdge.destination, (int) newWeight);
		
		System.out.println("Factor tránsito aplicado");
		
		currentPhase = 5;
		return;
	}
	
	private Edge<String> selectConnection(String station) {

	    SimpleList<Edge<String>> neighbors =
	            graph.getNeighbors(station);

	    if (neighbors == null || neighbors.isEmpty()) {
	        System.out.println("La estación no tiene conexiones.");
	        return null;
	    }

	    System.out.println("\nConexiones de " + station + ":");

	    for (int i = 0; i < neighbors.size(); i++) {
	        Edge<String> edge = neighbors.get(i);

	        System.out.println(
	            i + " - " +
	            edge.destination +
	            " (Peso: " + edge.weight + ")"
	        );
	    }

	    int choice = readInt();

	    if (choice < 0 || choice >= neighbors.size()) {
	        System.out.println("Selección inválida.");
	        return null;
	    }

	    return neighbors.get(choice);
	}
	
	private void getNearStations() {

	    System.out.println("Ingrese el radio de búsqueda:");
	    int userRadius = readInt();

	    System.out.println("Ingrese la estación origen:");
	    String station = searchStationsResult();

	    if (station == null)
	        return;

	    SimpleDictionary<String, Edge<String>> distances =
	            DijkstraSolver.dijkstraAllNodes(graph, station);

	    SimpleList<String> keys = distances.keys();

	    System.out.println("\nEstaciones dentro del radio:");

	    for (int i = 0; i < keys.size(); i++) {

	        String destination = keys.get(i);

	        Edge<String> data = distances.get(destination);

	        if (data.weight <= userRadius &&
	            !destination.equals(station)) {

	            System.out.println(
	                destination +
	                " (" + data.weight + " km)"
	            );
	        }
	    }
	    currentPhase = 0;
	    return;
	}
	
	private void savedRoutes() {
		System.out.println("--- Navegación desde Rutas Guardadas --- \n");
		if (savedRoutes.isEmpty()) {
			System.out.println("No existen rutas guardadas.");
			currentPhase = 0;
			return;
		}
		int savedQty = savedRoutes.size();
		for (int i = 0; i < savedQty; i++) {
			System.out.println(i + "-" + savedRoutes.get(i).origin + " -> " + savedRoutes.get(i).destination);
		}
		System.out.println("Indique la estacion deseada por numero: ");
    	int userChoice = readInt();
    	if (userChoice >= 0 && userChoice < savedQty){
    		tripClass selectedTrip = savedRoutes.get(userChoice);
        	navigate(selectedTrip.origin,selectedTrip.destination);
        	currentPhase = 0;
    	} else {
    		System.out.println("Entrada no valida.");
    		currentPhase = 0;
    	}	
	}
	
	private <T> void getDistanceToAllNodes() {
	    System.out.println("--- Establecer punto de origen ---\n");
	    String org = searchStationsResult();
	    if (org == null) {
	    	System.out.println("No se estableció un punto de origen.\n");
	    	currentPhase = 0;
	    	return;
	    }
	    SimpleDictionary<String, Edge<String>> result = DijkstraSolver.dijkstraAllNodes(graph, org);

	    SimpleList<String> resultK = result.keys();

	    for (int i = 0; i < resultK.size(); i++) {
	    	String current = resultK.get(i);
	        Edge<String> data = result.get(current);

	        if (data.weight == Integer.MAX_VALUE) {
	            System.out.println(current.toString() + " -> No es posible llegar.");
	        } else {
	            System.out.println(current.toString() + " -> " + data.weight + "km");
	        }
	    }
	    currentPhase = 0;
	}
	
	private void navigate(String org, String dest) {
		SimpleDictionary<String, Edge<String>> result = DijkstraSolver.dijkstraAllNodes(graph, org);
		SimpleStack<String> pathInverted = new SimpleArrayStack<String>();
		
		String act = dest;
		while (act != null && act != org) {
			pathInverted.push(act);
			act = result.get(act).destination;
		}
		while (!pathInverted.isEmpty()) {
			System.out.println(pathInverted.pop());
		}
	}
	
	private void getRoute() {
		System.out.println("--- Establecer punto de origen ---\n");
		String org = searchStationsResult();
		System.out.println("--- Establecer punto final ---\n");
		String dest = searchStationsResult();
	    
		if (org == null || dest == null) {
	    	System.out.println("No se estableció un punto de origen/llegada.\n");
	    	currentPhase = 0;
	    	return;
	    }
		
		System.out.println("--- Comenzando Recorrido ---");
		System.out.println("Origen Seleccionado: " + org);
		System.out.println("Destino Seleccionado: " + dest);
		System.out.println("--- Paso a Paso ---");
		
		navigate(org,dest);		
		
		System.out.println("--- Desea guardar esta ruta para sus proximos viajes? (y/n) ---\n");
		String userChoice = scanner.nextLine().toLowerCase();
		switch (userChoice) {
		case "y":
			saveRoute(org,dest);
			break;
		case "n":
			break;
		default:
			System.out.println("Entrada Inválida. Intente nuevamente.");
			
		}
		
		currentPhase = 0;
	}
	
	private void saveRoute(String org, String dest) {
		tripClass trip = new tripClass(org,dest);
		savedRoutes.add(trip);
	}
	
	private void menuLogic() {
		firstTime = false;
		
		System.out.println("\nIngrese la opción a ejecutar:");
		System.out.println("1 - Iniciar Navegación");
		System.out.println("2 - Navegar desde rutas guardadas");  
		System.out.println("3 - Obtener estaciones cercanas");
		System.out.println("4 - Obtener Distancias Totales desde Origen");
		System.out.println("5 - Cambiar Sesion a Administrador");
		System.out.println("6 - Volver al menú principal\n");
		
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
			currentPhase = 5;
			break;
		case "6":
			System.out.println("--- Volviendo al menú principal ---\n");
			running = false;
			break;
		default:
			System.out.println("Entrada Inválida, Intentá de nuevo!\n");
			currentPhase = 0;
			break;
		}	
	}

	private String searchStationsResult() {
		System.out.println("Ingrese el texto a buscar: ");
		String userInput = scanner.nextLine();
		SimpleList<String> vertices = graph.vertices();
		SimpleList<String> result = new SimpleArrayList<String>();
		int vertexSize = vertices.size();
    	for (int i = 0; i < vertexSize; i++) {
			if (vertices.get(i).toString().toLowerCase().contains(userInput.toLowerCase())){
				System.out.println(i + " - " + vertices.get(i));
				result.add(vertices.get(i));
			}
    	}
    	if (result == null || result.isEmpty()) { System.out.println("La búsqueda no arrojó resultados."); return null;}
    	
    	System.out.println("Indique la estacion deseada por numero: ");
    	int userChoice = readInt();
    	if (userChoice >= 0 && userChoice < vertexSize){
        	return vertices.get(userChoice);
    	} else {
    		System.out.println("Entrada no valida.");
    		return null;
    	}	
	}
	
	private int readInt() {
	    while (true) {
	        String input = scanner.nextLine();
	        try {
	            return Integer.parseInt(input);
	        } catch (NumberFormatException e) {
	            System.out.println("Valor inválido. Intentá nuevamente.");
	        }
	    }
	}
	
	private double readDouble() {
	    while (true) {
	        String input = scanner.nextLine();

	        try {
	            return Double.parseDouble(input);
	        } catch (NumberFormatException e) {
	            System.out.println("Valor inválido. Intentá nuevamente.");
	        }
	    }
	}
	
	
}
