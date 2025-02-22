package controllers;
import java.util.*;
import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

/**
 * Implementación de método recursivo simple para encontrar la ruta en un laberinto.
 * Utiliza backtracking para explorar todos los caminos posibles.
 */
public class MazeSolverRecursivo implements MazeSolver {

    @Override
    public List<Cell> getPath(Maze maze, boolean[][] grid, Cell start, Cell end) {
        List<Cell> path = new ArrayList<>();
        Set<Cell> visited = new HashSet<>();

        // Inicia la búsqueda recursiva desde el punto de inicio
        findPath(grid, start, end, path, visited);
        return path;
    }

    /**
     * Método recursivo que explora todos los caminos posibles.
     * @param grid Matriz booleana del laberinto.
     * @param current Celda actual en la exploración.
     * @param end Celda de destino.
     * @param path Lista de celdas que forman el camino actual.
     * @param visited Conjunto de celdas visitadas para evitar ciclos.
     * @return true si encuentra el camino al destino, false si no.
     */
    private boolean findPath(boolean[][] grid, Cell current, Cell end, List<Cell> path, Set<Cell> visited) {
        // Validar límites y si la celda es transitable
        if (!isValid(grid, current) || visited.contains(current)) {
            return false;
        }

        // Añadir la celda actual al camino y marcar como visitada
        path.add(current);
        visited.add(current);

        // Si llegamos al destino, terminamos
        if (current.equals(end)) {
            return true;
        }

        // Explorar en las cuatro direcciones: derecha, abajo, izquierda, arriba
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dir : directions) {
            Cell next = new Cell(current.row + dir[0], current.col + dir[1]);
            if (findPath(grid, next, end, path, visited)) {
                return true;
            }
        }

        // Si no encuentra camino, retrocede (backtracking)
        path.remove(path.size() - 1);
        visited.remove(current);
        return false;
    }

    /**
     * Valida si la celda está dentro de los límites del laberinto y es transitable.
     * @param grid Matriz booleana del laberinto.
     * @param cell Celda a validar.
     * @return true si es válida y transitable, false en caso contrario.
     */
    private boolean isValid(boolean[][] grid, Cell cell) {
        return cell.row >= 0 && cell.row < grid.length && 
               cell.col >= 0 && cell.col < grid[0].length && 
               grid[cell.row][cell.col];
    }
}
