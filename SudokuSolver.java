import java.io.*;
import java.util.*;

public class SudokuSolver {
  private char[][] board; // sudoku board
  private int boardSize;

  public SudokuSolver(String sudoku) {
    // get length for board
    boardSize = (int) Math.sqrt(sudoku.length());
    board = new char[boardSize][boardSize];

    // update board
    parseString(sudoku);
  }

  private void parseString(String sudoku) {
    int temp = 0;
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        board[i][j] = sudoku.charAt(temp + j);
      }
      temp += boardSize;
    }
  }

  public boolean solve() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == '.') {
          for (char c = '1'; c <= '9'; c++) {// trial. Try 1 through 9
            if (isValid(i, j, c)) {
              board[i][j] = c; // Put c for this cell

              if (solve())
                return true; // If it's the solution return true
              else
                board[i][j] = '.'; // Otherwise go back
            }
          }

          return false;
        }
      }
    }

    return true;
  }

  private boolean isValid(int row, int col, char c) {
    for (int i = 0; i < boardSize; i++) {
      // check row
      if (board[i][col] != '.' && board[i][col] == c)
        return false;

      // check column
      if (board[row][i] != '.' && board[row][i] == c)
        return false;

      // check sqrt(boardSize) * sqrt(boardSize) block
      int block = (int) Math.sqrt(boardSize);
      if (board[block * (row / block) + i / block][block * (col / block) + i % block] != '.'
          && board[block * (row / block) + i / block][block * (col / block) + i % block] == c)
        return false;
    }

    return true;
  }

  public void printBoard() {
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        System.out.print(board[i][j] + "\t");
      }
      System.out.println();
    }
  }

  public static void main(String args[]) {
    try (BufferedReader br = new BufferedReader(new FileReader("sudoku.txt"))) {
      String line = br.readLine();
      int count = 1;
      while (line != null) {
        SudokuSolver sol = new SudokuSolver(line);
        sol.solve();
        System.out.println("Sudoku " + count);
        sol.printBoard();
        count++;
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}