package controllers.interfaces;
import java.util.List;
import models.Cell;
import models.Maze;

/**
 * Interfaz para los algoritmos de solución de laberintos.
 * Define el método general para obtener el camino óptimo.
 */
public interface MazeSolver {
    /**
     * Obtiene el camino desde el punto de inicio (start) hasta el punto final (end) en un laberinto.
     * @param maze Objeto que representa el laberinto.
     * @param grid Matriz booleana que representa celdas transitables y no transitables.
     * @param start Punto de inicio.
     * @param end Punto de destino.
     * @return Lista de celdas que representan el camino óptimo.
     */
    List<Cell> getPath(Maze maze, boolean[][] grid, Cell start, Cell end);
}
