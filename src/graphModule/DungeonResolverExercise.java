package graphModule;

import application.Exercise;
import java.util.Scanner;
import dictionary.SimpleDictionary;
import graphModule.EscapeRoom.Celda;
import graphModule.EscapeRoom.DungeonMap;
import list.SimpleList;

public class DungeonResolverExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private final Graph<Celda> graph;
    private final DungeonMap dungeonMap;
    private SimpleDictionary<Celda, Edge<Celda>> lastDijkstraResult;
    private boolean isDijkstraCalculated;

    public DungeonResolverExercise(Scanner scanner) {
        super(scanner);
        this.dungeonMap = new DungeonMap();
        this.graph = dungeonMap.graphifyMatrix();
        this.isDijkstraCalculated = false;
        this.lastDijkstraResult = null;
    }

    @Override
    protected void exerciseLogic() {
        if (this.firstTime) {
            System.out.println("=========================================");
            System.out.println("   BIENVENIDO AL MÓDULO DE DIJKSTRA  ");
            System.out.println("=========================================");
            this.firstTime = false;
        }

        switch (currentPhase) {
            case 1:
                mostrarDungeon();
                break;
            case 2:
                mostrarGrafo();
                break;
            case 3:
                resolverDungeon();
                mostrarCaminoMasCorto();
                break;
            case 4:
                running = false;
                break;
            default:
                menuLogic();
                boolean isActionSelected = false;
                while (!isActionSelected) {
                    try {
                        int action = Integer.parseInt(scanner.nextLine());
                        if (action < 1 || action > 5) {
                            System.out.println("No existe una acción correspondiente con ese valor");
                        } else {
                            this.currentPhase = action;
                            isActionSelected = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Ingrese un valor numérico válido");
                    }
                }
                break;
        }
    }

    private void menuLogic() {
        System.out.println("\n============ MENÚ ESCAPE THE DUNGEON ============");
        System.out.println("1: Imprimir Dungeon");
        System.out.println("2: Imprimir Aristas del Grafo");
        System.out.println("3: Resolver Dungeon");
        System.out.println("4: Volver al menú principal");
        System.out.print("\nSeleccione la operación a realizar: ");
    }

    private void mostrarDungeon() {
        System.out.println("\nMAPA VISUAL DE LA DUNGEON");
        dungeonMap.imprimirMatrizVisual();

        backToMenu();
    }

    private void mostrarGrafo(){
        System.out.println("\nLISTA DE ARISTAS DEL GRAFO");
        SimpleList<Celda> vertices = graph.vertices();

        for (int i = 0; i < vertices.size(); i++) {
            Celda origen = vertices.get(i);
            SimpleList<Edge<Celda>> edges = graph.getNeighbors(origen);

            if (edges != null) {
                for (int j = 0; j < edges.size(); j++) {
                    Edge<Celda> arista = edges.get(j);
                    System.out.printf("%s -> %s : %d%n",
                            origen.coordenadasToString(),
                            arista.destination.coordenadasToString(),
                            arista.weight);
                }
            }
        }
        backToMenu();
    }

    private void resolverDungeon() {
        Celda start = dungeonMap.getCeldaStart();
        if (start == null) {
            System.out.println("Error: No se encontro la celda de inicio (S) en el mapa.");
            backToMenu();
            return;
        }

        System.out.printf("\nBuscando el camino mas corto desde el inicio %s", start.coordenadasToString());

        this.lastDijkstraResult = DijkstraSolver.dijkstraAllNodes(this.graph, start);
        this.isDijkstraCalculated = true;

        System.out.println("\nDungeon resuelta.");
        backToMenu();
    }

    private void mostrarCaminoMasCorto() {
        if (!isDijkstraCalculated || lastDijkstraResult == null) {
            System.out.println("No se ha resuelto aun la dungeon");
            backToMenu();
            return;
        }

        Celda start = dungeonMap.getCeldaStart();
        Celda exit = dungeonMap.getCeldaExit();

        if (exit == null) {
            System.out.println("Error: No se encontro la celda de salida (E) en el mapa.");
            backToMenu();
            return;
        }

        SimpleList<Celda> camino = DijkstraSolver.findShortestPath(lastDijkstraResult, start, exit);

        if (camino.isEmpty()) {
            System.out.println("No existe un camino posible, la salida es inalcanzable desde el inicio.");
        } else {
            int costoTotal = lastDijkstraResult.get(exit).weight;
            System.out.printf("\nCamino optimo encontrado, costo total de escape: %d%n", costoTotal);
            for (int i = 0; i < camino.size(); i++) {
                Celda c = camino.get(i);
                System.out.print(c.coordenadasToString());
                if (i < camino.size() - 1) {
                    System.out.print(" -> ");
                }
            }

            System.out.println("\n\nDungeon con el camino mas corto iluminado");
            dungeonMap.imprimirMatrizConCamino(camino);
        }
        backToMenu();
    }

    protected void backToMenu() {
        this.currentPhase = 0;
    }
}
