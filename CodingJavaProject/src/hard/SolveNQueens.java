package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolveNQueens {

    public List<List<String>> solveNQueens(int n) {

        List<List<String>> result = new ArrayList<>();

        char[][] charsResult = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(charsResult[i], '.');
        }


        // Calling
        backtracking(result, charsResult, 0);

        return result;
    }

    private void backtracking(List<List<String>> result, char[][] charsResult, int row) {
        if (row == charsResult.length) {
            List<String> tempList = new ArrayList<String>();
            for (int i = 0; i < charsResult.length; i++) {
                StringBuilder temp = new StringBuilder();
                for (int j = 0; j < charsResult.length; j++) {
                    temp.append(charsResult[i][j]);
                }
                tempList.add(temp.toString());
            }
            result.add(tempList);
            return;
        }

        for (int i = 0; i < charsResult.length; i++) {

            if (!isValid(charsResult, row, i)) continue;

            // put Queue
            charsResult[row][i] = 'Q';

            // check next
            backtracking(result, charsResult, row+1);

            // remove Queue
            charsResult[row][i] = '.';
        }
    }

    private boolean isValid(char[][] charsResult, int row, int col) {
        // check up column
        for (int i = 0; i < row; i++) {
            if (charsResult[i][col] == 'Q') return false;
        }

        // check up left
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if (charsResult[i][j] == 'Q') return false;
        }

        // check up right
        for (int i = row-1, j = col+1; i >= 0 && j <= charsResult.length-1; i--, j++) {
            if (charsResult[i][j] == 'Q') return false;
        }

        return true;
    }
}
