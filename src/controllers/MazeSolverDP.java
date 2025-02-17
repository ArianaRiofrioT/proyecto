package controllers;
import java.util.*;
import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

/**
 * Implementación de Programación Dinámica (DP) para encontrar la ruta óptima en un laberinto.
 * Utiliza memoización para almacenar resultados parciales y optimizar la búsqueda.
 */
public class MazeSolverDP implements MazeSolver {

    // Mapa para memorizar resultados parciales y evitar cálculos redundantes
    private Map<String, Boolean> memoria = new HashMap<>();

    @Override
    public List<Cell> getPath(Maze maze, boolean[][] grid, Cell start, Cell end) {
        List<Cell> path = new ArrayList<>();
        if (grid == null || grid.length == 0) return path;

        // Inicia la búsqueda con memoización
        if (findPath(grid, start.row, start.col, end, path)) {
            Collections.reverse(path); // Invertimos para mostrar desde inicio a fin
            return path;
        }

        return new ArrayList<>(); // No se encontró camino
    }

    /**
     * Método recursivo con memoización para encontrar el camino.
     * @param grid Matriz booleana del laberinto.
     * @param row Fila de la celda actual.
     * @param col Columna de la celda actual.
     * @param end Celda de destino.
     * @param path Lista de celdas que forman el camino actual.
     * @return true si encuentra el camino al destino, false si no.
     */
    private boolean findPath(boolean[][] grid, int row, int col, Cell end, List<Cell> path) {
        String key = row + "," + col; // Clave para la memoización

        // Validaciones de límites, obstáculos y si ya se visitó
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || 
            !grid[row][col] || memoria.containsKey(key)) {
            return false;
        }

        // Añadir la celda actual al camino
        path.add(new Cell(row, col));
        memoria.put(key, true); // Marcar como visitado

        // Si llegamos al destino, terminamos
        if (row == end.row && col == end.col) {
            return true;
        }

        // Exploramos en las 4 direcciones: derecha, abajo, izquierda, arriba
        if (findPath(grid, row, col + 1, end, path) || 
            findPath(grid, row + 1, col, end, path) || 
            findPath(grid, row, col - 1, end, path) || 
            findPath(grid, row - 1, col, end, path)) {
            return true;
        }

        // Si no encuentra camino, retrocede (backtracking)
        path.remove(path.size() - 1);
        memoria.put(key, false); // Marcar como sin solución

        return false;
    }
}
