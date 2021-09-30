import java.io.*;

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

  public void solve() {
    solver(0, 0);
  }

  private boolean solver(int row, int col) {
    // If the col is 9, that means you've filled out a whole row. Start the search
    // on the next row by resetting column and incrementing the row by 1
    if (col == boardSize) {
      col = 0;
      row += 1;
    }
    // If we've reached 9, that means we didn't run into any errors with our
    // blocks in the previous rows, so we have a valid solution
    if (row == boardSize) {
      return true;
    }
    // If this piece already has a value, call for next
    if (board[row][col] != '.')
      return solver(row, col + 1);

    // We want to try every number for this block
    for (char num = '1'; num <= '9'; num++) {
      // check for constraints, go ahead only if constraints are valid
      if (checkConstraints(row, col, num)) {
        // Set the value of the current block to the valid num
        board[row][col] = num;

        // call for next
        boolean solved = solver(row, col + 1);
        // The only way we can trigger a true is if we got to the end, so if it's true
        // that means we have a solved board so you just keep returning
        if (solved)
          return true;
        // If our board isn't solved, backtrack and try the next number
        else
          board[row][col] = '.';
      }
    }
    // we get this when every value of the board is filled, because we don't run
    // anything on it
    // If we get to this step, that means that no values fit, which means the
    // current iteration of the board is wrong so return false and try the previous
    // step again with a different value
    return false;
  }

  private boolean checkConstraints(int row, int col, char c) {
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