package medium;

import java.util.Arrays;

public class WordSearch {

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] flags = new boolean[row][col]; // default should be false

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                flags[i][j] = true;
                if (forward(board, word, i, j, 0, flags)) return true;
                flags[i][j] = false;
            }
        }
        return false;
    }

    private boolean forward(char[][] board, String word, int row, int col, int index, boolean[][] flags) {

        if (word.charAt(index) != board[row][col]) return false;
        // match, continue

        if (index == word.length() - 1) return true; // find one

        // continue find the next one

        // up
        if ((row - 1 >= 0) && (flags[row-1][col] == false)) {
            flags[row-1][col] = true;
            if (forward(board, word, row-1, col, index+1, flags)) return true;
            flags[row-1][col] = false;
        }

        // down
        if ((row + 1 < board.length) && (flags[row+1][col] == false)) {
            flags[row+1][col] = true;
            if (forward(board, word, row+1, col, index+1, flags)) return true;
            flags[row+1][col] = false;
        }

        // left
        if ((col - 1 >= 0) && (flags[row][col-1] == false)) {
            flags[row][col-1] = true;
            if (forward(board, word, row, col-1, index+1, flags)) return true;
            flags[row][col-1] = false;
        }

        // right
        if ((col + 1 < board[0].length) && (flags[row][col+1] == false)) {
            flags[row][col+1] = true;
            if (forward(board, word, row, col+1, index+1, flags)) return true;
            flags[row][col+1] = false;
        }

        return false;
    }
}
