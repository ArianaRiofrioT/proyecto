import java.util.*;
import controllers.MazeSolverBFS;
import controllers.MazeSolverDFS;
import controllers.MazeSolverRecursivo;
import controllers.MazeSolverDP;
import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

/**
 * Clase principal para ejecutar la aplicación de búsqueda de rutas en laberinto.
 * Permite seleccionar el algoritmo a utilizar e imprime el camino óptimo.
 */
public class App {
    public static void main(String[] args) {
        // Definición del laberinto (true = transitable, false = obstáculo)
        boolean[][] laberinto = {
            { true, true, true, false, true },
            { false, true, true, true, true },
            { true, false, false, true, false },
            { true, true, true, true, true },
            { false, false, true, false, true }
        };

        // Crear el objeto Maze y definir puntos de inicio y fin
        Maze maze = new Maze(laberinto);
        Cell start = new Cell(0, 0); // Punto de inicio (fila, columna)
        Cell end = new Cell(4, 4);   // Punto de destino (fila, columna)

        // Imprimir el laberinto inicial
        System.out.println("Laberinto Inicial:");
        maze.printMaze();

        // Mostrar opciones de algoritmos
        System.out.println("\nSeleccione el algoritmo de búsqueda:");
        System.out.println("1. BFS (Breadth-First Search)");
        System.out.println("2. DFS (Depth-First Search)");
        System.out.println("3. Recursivo Simple");
        System.out.println("4. Programación Dinámica (Memoización)");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        scanner.close();

        // Crear instancia del algoritmo seleccionado
        MazeSolver solver;
        switch (opcion) {
            case 1:
                solver = new MazeSolverBFS();
                System.out.println("\nUsando BFS (Breadth-First Search)...");
                break;
            case 2:
                solver = new MazeSolverDFS();
                System.out.println("\nUsando DFS (Depth-First Search)...");
                break;
            case 3:
                solver = new MazeSolverRecursivo();
                System.out.println("\nUsando Método Recursivo Simple...");
                break;
            case 4:
                solver = new MazeSolverDP();
                System.out.println("\nUsando Programación Dinámica (Memoización)...");
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        // Obtener el camino óptimo usando el algoritmo seleccionado
        List<Cell> path = solver.getPath(maze, laberinto, start, end);

        // Imprimir el camino encontrado
        System.out.println("\nCamino encontrado:");
        if (path.isEmpty()) {
            System.out.println("No se encontró un camino del inicio al destino.");
        } else {
            System.out.println(path);
            System.out.println("\nLaberinto con el camino recorrido:");
            maze.printMazeWithPath(path);
        }
    }
}
