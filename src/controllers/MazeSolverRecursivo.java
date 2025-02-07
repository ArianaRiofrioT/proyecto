package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

public class MazeSolverRecursivo implements MazeSolver {

    @Override
    public List<Cell> getPath(Maze maze, boolean[][] grid, Cell start, Cell end) {
        List<Cell> path = new ArrayList<>();
        Set<Cell> visitadas = new HashSet<>();
        if (grid == null || grid.length == 0) {
            return path;
        }
        if (findPath(grid, start.row, start.col, end, path, visitadas)) {
            return path;
        }
        return new ArrayList<>();
    }

    private boolean findPath(boolean[][] grid, int row, int col, Cell end, List<Cell> path, Set<Cell> visitadas) {
        Cell cell = new Cell(row, col);
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || !grid[row][col]) {
            return false;
        }

        if (visitadas.contains(cell)) {
            return false;
        }

        visitadas.add(cell);

        if (row == end.row && col == end.col) {
            path.add(cell);  // Se agrega al final solo cuando se llega al destino
            return true;
        }

        // Recorre en las cuatro direcciones
        if (findPath(grid, row, col + 1, end, path, visitadas) || // derecha
            findPath(grid, row + 1, col, end, path, visitadas) || // abajo
            findPath(grid, row, col - 1, end, path, visitadas) || // izquierda
            findPath(grid, row - 1, col, end, path, visitadas)) { // arriba
            path.add(cell);  // Se agrega al camino si se encuentra un camino válido
            return true;
        }

        // Si no encontró camino, retrocede
        visitadas.remove(cell);
        return false;
    }
}
