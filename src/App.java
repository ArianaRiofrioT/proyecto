import java.util.Arrays;
import java.util.List;

import controllers.MazeSolverRecursivo;
import controllers.MazeSolverDP;
import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

public class App {
    public static void main(String[] args) throws Exception {
        boolean[][] laberinto = {
            { true, true, true, true },
            { false, true, true, true },
            { true, true, false, false },
            { true, true, true, true },
        };

        Maze maze = new Maze(laberinto);
        System.out.println("Laberinto:");
        maze.printMaze();

        Cell start = new Cell(0, 3);
        Cell end = new Cell(3, 3);

        List<MazeSolver> soluciones = Arrays.asList(new MazeSolverRecursivo(), new MazeSolverDP());

        int opcion = 1;

        MazeSolver solver = soluciones.get(opcion - 1);  
        List<Cell> path = solver.getPath(maze, laberinto, start, end);  // Pasa el objeto maze aquí
        System.out.println("Camino encontrado:");
        System.out.println(path); 
        System.out.println("\nLaberinto con el camino recorrido:");
        maze.printMazeSolved(path);  // Verifica que este método esté correctamente implementado
    }

    // Método para actualizar la visualización del laberinto mientras se explora
    public void updateMaze(Cell current, Cell start, Cell end, Maze maze) {
        String[][] displayGrid = maze.getDisplayGrid(); // Obtén la representación del laberinto desde la clase Maze

        if (current.equals(start)) {
            displayGrid[current.row][current.col] = " S "; // Punto de inicio
        } else if (current.equals(end)) {
            displayGrid[current.row][current.col] = " E "; // Punto de destino
        } else {
            displayGrid[current.row][current.col] = " > "; // Celda visitada
        }

        maze.printMaze(); // Imprimir el estado actual del laberinto
        try {
            Thread.sleep(200); // Pequeño retraso para ver la animación en consola
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
