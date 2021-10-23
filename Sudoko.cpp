#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
  bool solve(vector<vector<int>> &board, int size) {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (board[i][j] == 0) {
          for (int c = 1; c <= size; c++) {
            if (isPossible(board, i, j, c, size)) {
              board[i][j] = c;
              if (solve(board, size))
                return true;
              else
                board[i][j] = 0;
            }
          }
          return false;
        }
      }
    }
    return true;
  }

  bool isPossible(vector<vector<int>> &board, int i, int j, int c, int size) {
    int fac = sqrt(size);
    for (int x = 0; x < size; x++) {
      if (board[i][x] == c)
        return false;

      if (board[x][j] == c)
        return false;

      if (board[fac * (i / fac) + x / fac][fac * (j / fac) + x % fac] == c)
        return false;
    }

    return true;
  }

  void solveSudoku(vector<vector<int>> &board) {
    int size = board.size();
    solve(board, size);
  }

  void print(vector<vector<int>> board) {
    for (auto i : board) {
      for (int j : i) {
        cout << j << " ";
      }
      cout << endl;
    }
  }
};

int main() {
  Solution obj;
  vector<vector<int>> board = {
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0},
      {0, 0, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0},
  };
  obj.solveSudoku(board);

  obj.print(board);
}
