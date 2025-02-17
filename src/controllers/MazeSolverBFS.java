package controllers;
import java.util.*;
import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

/**
 * Implementación de BFS (Breadth-First Search) para encontrar la ruta óptima en un laberinto.
 */
public class MazeSolverBFS implements MazeSolver {
    @Override
    public List<Cell> getPath(Maze maze, boolean[][] grid, Cell start, Cell end) {
        Queue<Cell> queue = new LinkedList<>();
        Map<Cell, Cell> predecessors = new HashMap<>();
        List<Cell> path = new ArrayList<>();

        // Añadimos el punto de inicio a la cola y marcamos como visitado
        queue.add(start);
        predecessors.put(start, null);

        // Exploración BFS
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            // Si llegamos al destino, reconstruimos el camino
            if (current.equals(end)) {
                while (current != null) {
                    path.add(0, current); // Insertar al inicio para mantener el orden
                    current = predecessors.get(current);
                }
                return path;
            }

            // Explorar direcciones: abajo, derecha, arriba, izquierda
            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];
                Cell next = new Cell(newRow, newCol);

                // Validar si la celda es transitable y no visitada
                if (isValid(grid, newRow, newCol) && !predecessors.containsKey(next)) {
                    queue.add(next);
                    predecessors.put(next, current);
                }
            }
        }
        return new ArrayList<>(); // No se encontró camino
    }

    /**
     * Valida si la celda está dentro de los límites del laberinto y es transitable.
     * @param grid Matriz booleana del laberinto.
     * @param row Fila de la celda.
     * @param col Columna de la celda.
     * @return true si es válida y transitable, false en caso contrario.
     */
    private boolean isValid(boolean[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col];
    }
}
