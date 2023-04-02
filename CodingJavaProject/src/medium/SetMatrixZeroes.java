package medium;

public class SetMatrixZeroes {

    // set in the matrix?
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        if (m == 1) {
            for (int i = 0; i < n; i++) {
                if (matrix[0][i] == 0) {
                    for (int j = 0; j < n; j++) {
                        matrix[0][j] = 0;
                    }
                    return;
                }
            }
        }

        if (n == 1) {
            for (int i = 0; i < m; i++) {
                if (matrix[i][0] == 0) {
                    for (int j = 0; j < m; j++) {
                        matrix[j][0] = 0;
                    }
                    return;
                }
            }
        }

        //isRowZero -> matrix[0][0]
        boolean isColZeoro = false;

        // check zeros
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                isColZeoro = true;
            }

            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // set zeros
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        if (matrix[0][0] == 0) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }

        if (isColZeoro) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }


    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];

        // check zeros
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        // set zeros
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
