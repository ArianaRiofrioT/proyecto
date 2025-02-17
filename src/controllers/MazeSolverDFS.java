package controllers;
import java.util.*;
import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

/**
 * Implementación de DFS (Depth-First Search) para encontrar la ruta en un laberinto.
 * Utiliza recursividad para explorar cada camino hasta el destino.
 */
public class MazeSolverDFS implements MazeSolver {

    @Override
    public List<Cell> getPath(Maze maze, boolean[][] grid, Cell start, Cell end) {
        List<Cell> path = new ArrayList<>();
        Set<Cell> visited = new HashSet<>(); // Para rastrear las celdas visitadas

        // Inicia la búsqueda desde el punto de inicio
        dfs(grid, start, end, path, visited);
        return path;
    }

    /**
     * Método recursivo que explora cada camino en profundidad.
     * @param grid Matriz booleana del laberinto.
     * @param current Celda actual en la exploración.
     * @param end Celda de destino.
     * @param path Lista de celdas que forman el camino actual.
     * @param visited Conjunto de celdas visitadas para evitar ciclos.
     * @return true si encuentra el camino al destino, false si no.
     */
    private boolean dfs(boolean[][] grid, Cell current, Cell end, List<Cell> path, Set<Cell> visited) {
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
            if (dfs(grid, next, end, path, visited)) {
                return true;
            }
        }

        // Si no encuentra camino, retrocede (backtracking)
        path.remove(path.size() - 1);
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

