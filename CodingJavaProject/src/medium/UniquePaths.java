package medium;

public class UniquePaths {

    /*
        uniquePaths: Time Limit Exceeded

        uniquePaths2: 0ms/100%, 39.1MB/68.68%
        动态规划，只需要遍历一遍

        uniquePaths2: 0ms/100%, 39.2MB/68.68%
        反过来的动态规划：从终点开始，没一点到达终点的paths

     */

    public int uniquePaths3(int m, int n) {

        if (m==1 || n==1) return 1;

        // how many paths to the current postion
        int[][] dp = new int[m][n]; // default should be 0

        // right or down, only 1
        for (int i = m-1; i >= 0; i--) dp[i][n-1] = 1;
        for (int i = n-1; i >= 0; i--) dp[m-1][i] = 1;


        for (int i = m-2; i >= 0; i--) {
            for (int j = n-2; j >= 0; j--) {
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }

        return dp[0][0];
    }




    public int uniquePaths2(int m, int n) {

        // how many paths to the current postion
        int[][] dp = new int[m][n]; // default should be 0

        // right or down, only 1
        for (int i=0; i<m; i++) dp[i][0] = 1;
        for (int i = 0; i < n; i++) dp[0][i] = 1;


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }




    private int output = 0;

    public int uniquePaths(int m, int n) {
        continueMove(1, 1, m, n);
        return output;
    }

    private void continueMove(int curM, int curN, int m, int n) {
        if (curM == m && curN == n) {
            output++;
            return;
        }


        // move down
        if (curM < m) {
            continueMove(curM+1, curN, m, n);
        }

        // move right
        if (curN < n) {
            continueMove(curM, curN+1, m, n);
        }

    }
}
