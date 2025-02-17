package models;
import java.util.Objects;

/**
 * Representa una celda en el laberinto con coordenadas fila y columna.
 */
public class Cell {
    public int row, col;

    /**
     * Constructor de la celda.
     * @param row Fila de la celda.
     * @param col Columna de la celda.
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Método para comparar si dos celdas son iguales.
     * Se comparan las coordenadas (fila y columna).
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cell cell = (Cell) obj;
        return row == cell.row && col == cell.col;
    }

    /**
     * Método para generar un hash único de la celda.
     * Es necesario para usar la celda en estructuras como HashMap.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    /**
     * Representación en texto de la celda (fila, columna).
     */
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
