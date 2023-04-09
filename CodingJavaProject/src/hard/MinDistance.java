package hard;

public class MinDistance {

    /*
        没啥思路
        ？？ 如何去理解动态规划

        对于m，n的string，与m-1，n-1的string的关系
        如果两个char相同 -> 则 dp[m][n] == dp[m-1][n-1]
        如果两个char不同 -> 3中可能 -> 选最小的
            替换这个char -> dp[m][n] == dp[m-1][n-1] + 1
            删掉第一个char -> dp[m][n] == dp[m-1][n] + 1
            添加需要的char -> dp[m][n] == dp[m][n-1] + 1

     */

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int i = 0; i <= n; i++) dp[0][i] = i;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }

        return dp[m][n];
    }
}
