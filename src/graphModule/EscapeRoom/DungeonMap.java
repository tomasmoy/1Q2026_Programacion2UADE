package graphModule.EscapeRoom;

import graphModule.Graph;
import graphModule.ListGraph;
import list.SimpleList;

public class DungeonMap {
    private final String[] PLANTILLA = {
            "PPPPPPPPPPPPPPP",
            "PNNTNNNNNLLLNNE",
            "PNNLLLLLLLLNNNP",
            "PSLLLLNNNLLLLNP",
            "PLLNNNNNNNNNNNP",
            "PLLLLNNNNNNLNNP",
            "PLLLLLLNNNLLLNP",
            "PNLLLLLLLLLLNNP",
            "PPPPPPPPPPPPPPP"
    };

    private final int filas;
    private final int columnas;
    private final Celda[][] matriz;
    private Celda celdaStart;
    private Celda celdaExit;

    public DungeonMap() {
        this.filas = PLANTILLA.length;
        this.columnas = PLANTILLA[0].length();
        this.matriz = new Celda[filas][columnas];

        inicializarDesdePlantilla();
    }

    public Celda getCeldaStart(){
        return celdaStart;
    }

    public Celda getCeldaExit(){
        return celdaExit;
    }

    // Creo las celdas de la dungeon desde la plantilla
    private void inicializarDesdePlantilla() {
        for (int x = 0; x < filas; x++) {
            String linea = PLANTILLA[x];
            for (int y = 0; y < columnas; y++) {
                char caracter = linea.charAt(y);
                TipoTerreno tipo = TipoTerreno.desdeSimbolo(caracter);

                matriz[x][y] = new Celda(x, y, tipo);

                if (tipo == TipoTerreno.START) celdaStart = matriz[x][y];
                if (tipo == TipoTerreno.EXIT) celdaExit = matriz[x][y];
            }
        }

        aplicarAlertasDeTorres();
    }

    // Actualizo los costos de las celdas por torres cercanas
    private void aplicarAlertasDeTorres() {
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas; y++) {
                if (matriz[x][y].getTipo() == TipoTerreno.TORRE) {

                    // Reccorremos las celdas al rededor de la torre
                    for (int dx = -2; dx <= 2; dx++) {
                        for (int dy = -2; dy <= 2; dy++) {
                            int nx = x + dx;
                            int ny = y + dy;

                            // Validamos límites de la matriz
                            if (nx >= 0 && nx < filas && ny >= 0 && ny < columnas) {
                                int distancia = Math.abs(dx) + Math.abs(dy);

                                // Esta celda es la torre, no nos interesa
                                if (distancia == 0) continue;

                                // Si está a 1 casillero de distancia, suma 3
                                if (distancia == 1) {
                                    matriz[nx][ny].setCostoAgregado(Math.max(matriz[nx][ny].getCostoTotal() - matriz[nx][ny].getTipo().getCostoBase(), 3));
                                }
                                // Si está a 2 casilleros de distancia, suma 2
                                else if (distancia == 2) {
                                    matriz[nx][ny].setCostoAgregado(Math.max(matriz[nx][ny].getCostoTotal() - matriz[nx][ny].getTipo().getCostoBase(), 2));
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    // Convierto la matriz a grafo
    public Graph<Celda> graphifyMatrix() {
        Graph<Celda> grafo = new ListGraph<>();

        // En este for ignoro las paredes, para que no se tengan en cuenta
        // Con esto logramos que hayan dungeons que no se puedan resolver
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas; y++) {
                if (matriz[x][y].getTipo() != TipoTerreno.PARED) {
                    grafo.addVertex(matriz[x][y]);
                }
            }
        }

        // Vectores de dirección para moverse en cruz: Arriba, Abajo, Izquierda, Derecha
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Armado del grafo, conecto las celdas con las vecinas
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas; y++) {
                Celda origen = matriz[x][y];

                if (origen.getTipo() == TipoTerreno.PARED) continue;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && nx < filas && ny >= 0 && ny < columnas) {
                        Celda destino = matriz[nx][ny];

                        if (destino.getTipo() != TipoTerreno.PARED) {
                            int peso = destino.getCostoTotal();
                            grafo.addEdge(origen, destino, peso);
                        }
                    }
                }
            }
        }

        return grafo;
    }

    // Imprime la matriz original
    public void imprimirMatrizVisual() {
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas; y++) {
                Celda c = matriz[x][y];
                imprimirCelda(c);
            }
            System.out.println();
        }
    }

    // Imprime la celda con su estilo correspondiente
    private void imprimirCelda(Celda c) {
        System.out.print(c.getAnsiColor() + " " + c.getTipo().getSimbolo() + " \u001B[0m");
    }

    public void imprimirMatrizConCamino(SimpleList<Celda> camino) {
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas; y++) {
                Celda c = matriz[x][y];

                // Verifico si la celda forma parte del camino mas corto
                boolean esParteDelCamino = false;
                for (int i = 0; i < camino.size(); i++) {
                    if (camino.get(i).equals(c)) {
                        esParteDelCamino = true;
                        break;
                    }
                }

                // Las celdas que no son inicio y fin, se pintan "iluminadas"
                if (esParteDelCamino && c.getTipo() != TipoTerreno.START && c.getTipo() != TipoTerreno.EXIT) {
                    System.out.print("\u001B[33m · \u001B[0m");
                } else {
                    System.out.print(c.getAnsiColor() + " " + c.getTipo().getSimbolo() + " \u001B[0m");
                }
            }
            System.out.println();
        }
    }
}