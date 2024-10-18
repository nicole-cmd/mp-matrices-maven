package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Nicole Gorrell
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  int w;
  int h;
  T defVal;
  T[][] matrix;


  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    if(width < 0 || height < 0) {
      new NegativeArraySizeException("The provided width and height values cannot be negative.");
    } // if

    this.w = width;
    this.h = height;
    this.defVal = def;

    this.matrix = (T[][]) new Object[height][width];
    
    for(int r = 0; r < this.w; r++) {
      for(int c = 0; c < this.h; c++) {
        this.matrix[r][c] = this.defVal;
      } // for
    } // for
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0


  // +----------------+------------------------------------------------
  // | Helper methods |
  // +----------------+

  // /**
  //  * Traverse through rows and columns of a matrix (implemented as a 2D array).
  //  */
  // void search() {
  //   for(int r = 0; r < this.h; r++) { // searching through rows
  //     for(int c = 0; c < this.w; c++) { // searching through columns

  //     } // for
  //   } // for
  // } // search()


  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) throws IndexOutOfBoundsException {
    if(row < 0 || col < 0 || row > this.h || col > this.w) {
      new IndexOutOfBoundsException("The row and column values cannot be negative nor greater than the matrix's width or height.");
    } // if

    T val = (T) null; // set to some form of null to avoid an error

    for(int r = 0; r < this.w; r++) {
      for(int c = 0; c < this.h; c++) {
        if(r == row && c == col) {
          val = this.matrix[r][c];
        } // if
      } // for
    } // for 

    return val;
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) throws IndexOutOfBoundsException {
    if(row < 0 || col < 0 || row > this.h || col > this.w) {
      new IndexOutOfBoundsException("The row and column values cannot be negative nor greater than the matrix's width or height.");
    } // if

    for(int r = 0; r < this.h; r++) {
      for(int c = 0; c < this.w; c++) {
        if(r == row && c == col) {
          this.matrix[r][c] = val;
        } // if
      } // for
    } // for
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.h;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.w;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if(row < 0 || row > this.h) {
      new IndexOutOfBoundsException("The row value cannot be negative or greater than the height.");
    } // if

    // expand array height to make room for new row
    this.h = this.height() + 1;

    for(int r = 0; r < this.h; r++) {
      if(r == row) {
        for(int c = 0; c < this.w; c++) {
          this.set(r, c, this.defVal);
        } // for
      } // if

      for(int c = 0; c < this.w; c++) {
        this.set(r, c, this.defVal);
      } // for
    } // for
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if(row < 0 || row > this.h) {
      new IndexOutOfBoundsException("The row value cannot be negative or greater than the height.");
    } // if

    if(vals.length > this.w) {
      new ArraySizeException();
    } // if

    // expand array height to make room for new row
    this.h = this.height() + 1;

    T[] current = null;

    for(int r = 0; r < this.h; r++) {
      if(r == row) {
        for(int c = 0; c < this.w; c++) {
          current[c] = this.matrix[r][c];
          this.matrix[r][c] = vals[c]; // assign each value to new row
        } // for
      } // if

      for(int c = 0; c < this.w; c++) {
        this.set(r, c, this.defVal);
      } // for
    } // for

    // STUB
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    // STUB
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    // STUB
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    // STUB
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    // STUB
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    // STUB
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    // STUB
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    return this;        // STUB
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    return this == other;       // STUB
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
