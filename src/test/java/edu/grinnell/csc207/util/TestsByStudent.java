package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertFigure;
import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TestsByStudent {
    @Test
    /** Testing insertRow, insertCol, deleteRow, and deleteCol methods. */
    void matrixExperiment() throws ArraySizeException {
        Matrix<String> fig1 = new MatrixV0<String>(3, 3, "O");

        assertFigure("""
            +---+---+---+
            | O | O | O |
            +---+---+---+
            | O | O | O |
            +---+---+---+
            | O | O | O |
            +---+---+---+
            """, fig1, "Matrix.default");

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                fig1.set(row, col, "@");
            } // for col
        } // for row

        fig1.insertRow(2, new String[] {"A", "A", "A"});
        fig1.insertCol(2, new String[] {"H", "I", "H", "I"});

        assertFigure("""
            +---+---+---+---+
            | O | O | H | O |
            +---+---+---+---+
            | O | O | I | O |
            +---+---+---+---+
            | A | A | H | O |
            +---+---+---+---+
            | O | O | I | O | 
            +---+---+---+---+
            """, fig1, "Matrix.insertRow and Matrix.insertCol");
        
        fig1.deleteCol(0);
        fig1.deleteRow(3);

        assertFigure("""
            +---+---+---+
            | O | H | O |
            +---+---+---+
            | O | I | O |
            +---+---+---+
            | A | H | O |
            +---+---+---+
            """, fig1, "Matrix.deleteRow and Matrix.deleteCol");
    } // matrixExperiment()

    @Test
    /** Test set, get, height, width, fillRegion, and fillLine methods. */
    void matrixExp2() {
        Matrix<String> fig2 = new MatrixV0<String>(2, 3, "g");

        fig2.set(1, 2, "T");
        
        assertFigure("""
            +---+---+---+
            | g | g | g |
            +---+---+---+
            | g | g | T |
            +---+---+---+
            | g | g | g |
            +---+---+---+
            """, fig2, "Matrix.set");

        assertEquals("T", fig2.get(1, 2));
        assertEquals(3, fig2.height());
        assertEquals(3, fig2.width());

        // fill in left side of the matrix
        fig2.fillRegion(0, 0, 3, 2, "'");

        assertFigure("""
            +---+---+---+
            | ' | ' | g |
            +---+---+---+
            | ' | ' | T |
            +---+---+---+
            | ' | ' | g |
            +---+---+---+
            """, fig2, "Matrix.fillRegion");

        // "empty out" middle row without deleting it
        fig2.fillLine(1, 0, 0, 1, 3, 3, " ");

        assertFigure("""
            +---+---+---+
            | ' | ' | g |
            +---+---+---+
            |   |   |   |
            +---+---+---+
            | ' | ' | g |
            +---+---+---+
            """, fig2, "Matrix.fillLine");
    } // matrixExp2()
} // TestsByStudent
