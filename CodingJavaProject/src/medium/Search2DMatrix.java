package medium;

public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;

        // one row
        if (row == 1) {
            for (int i = 0; i < col; i++) {
                if (matrix[0][i] == target) return true;
            }
            return false;
        }

        int i = 0;
        for (; i < row; i++) {
            if (matrix[i][0] > target) {
                if (i == 0) return false;
                for (int j = 0; j < col; j++) {
                    if (matrix[i-1][j] == target) return true;
                }
                return false;
            }
        }

        // the last row
        if (i == row) {
            for (int j = 0; j < col; j++) {
                if (matrix[row-1][j] == target) return true;
            }
        }

        return false;
    }
}
