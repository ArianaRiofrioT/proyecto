package models;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Maze {
    private boolean[][] grid;
    private int size;
    private String[][] displayGrid;

    public Maze(int size) {
        this.size = size;
        grid = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(grid[i], true);
        }
    }

    // Constructor con laberinto predefinido
    public Maze(boolean[][] predefinedGrid) {
        this.size = predefinedGrid.length;
        this.grid = predefinedGrid;
        displayGrid = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                displayGrid[i][j] = grid[i][j] ? " - " : " * "; // 'X' para obstáculos
            }
        }
    }

    // Método para actualizar la visualización del laberinto mientras se explora
    public void updateMaze(Cell current, Cell start, Cell end) {
        if (current.equals(start)) {
            displayGrid[current.row][current.col] = " S "; // Punto de inicio
        } else if (current.equals(end)) {
            displayGrid[current.row][current.col] = " E "; // Punto de destino
        } else {
            displayGrid[current.row][current.col] = " > "; // Celda visitada
        }

        printMaze(); // Imprimir el estado actual del laberinto
        try {
            Thread.sleep(200); // Pequeño retraso para ver la animación en consola
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public void printMaze() {
        System.out.println("\nEstado actual del laberinto:");
        for (String[] row : displayGrid) {
            for (String cell : row) {
                System.out.print(" " + cell + " ");
            }
            System.out.println();
        }
    }

    public void configureMaze(Scanner scanner) {
        System.out.println(
                "Configure el laberinto. Ingrese las coordenadas de las celdas no transitables (fila, columna). Escriba -1 para terminar:");
        while (true) {
            System.out.print("Ingrese fila: ");
            int row = scanner.nextInt();
            if (row == -1)
                break;
            System.out.print("Ingrese columna: ");
            int col = scanner.nextInt();
            grid[row][col] = false;
        }
    }

    // Método para dibujar el laberinto con el camino encontrado
    public void printMazeWithPath(List<Cell> path) {
        String[][] displayGrid = new String[size][size];

        // Inicializar la matriz visual con los valores del laberinto
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                displayGrid[i][j] = grid[i][j] ? " - " : " * "; // 'T' para transitable, 'X' para obstáculo
            }
        }

        // Marcar el camino encontrado con '*'
        for (Cell cell : path) {
            displayGrid[cell.row][cell.col] = " = ";
        }

        // Imprimir el laberinto con el camino resaltado
        for (String[] row : displayGrid) {
            for (String cell : row) {
                System.out.print(" " + cell + " ");
            }
            System.out.println();
        }
    }

}