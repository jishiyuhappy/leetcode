package hard;

public class LongestValidParentheses {
    // 动态规划？？ Dynamic Programming
    /*
    利用 计算结果

    dp[i] 表示以下标 i 字符结尾的最长有效括号的长度
     */

    public int longestValidParentheses3(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    // 双指针
    /*
        error - "()"
        第三次通过：Runtime 2ms/82.1%, Memory 42.6/27.79%
        为什么要从右边再来一次？？ "(()" -> 左边开始一遍，只满足 "左括号" 多的情况，且符合要求
     */

    public int longestValidParentheses2(String s) {

        if (s.length() <= 1) return 0;

        int result = 0;

        int balance = 0;
        int currentLength = 0;
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
            }

            currentLength++;

            if (balance == 0) {
                result = Math.max(result, currentLength);
            } else if (balance < 0) { // fail
                currentLength = 0;
                balance = 0;
            } // balance > 0 正常状态

        }


        balance = 0;
        currentLength = 0;
        for (int i = s.length()-1; i > 0 ; i--) {//不需要0，因为理论上上一个应该覆盖了

            if (s.charAt(i) == ')') {
                balance++;
            } else {
                balance--;
            }

            currentLength++;

            if (balance == 0) {
                result = Math.max(result, currentLength);
            } else if (balance < 0) { // fail
                currentLength = 0;
                balance = 0;
            } // balance > 0 正常状态

        }

        return result;

    }



    /*
        第一次通过：Runtime 1166ms/5%, Memory 42.5/38.15%
        基本上是暴力解法，每一个都查一下

        第二次通过：Runtime 1154/5%, Memory 42.3/56.98%
        跳过当前发现的最长长度，竟然没怎么提高？
     */
    public int longestValidParentheses(String s) {

        if (s.length() <= 1) return 0;

        int result = 0;
        for (int i = 0; i < s.length()-1;) {
            int curLong = longestValidParenthesesWithStart(s, i);

            if (curLong > 0) {
                i = i + curLong;
                if (curLong > result) result = curLong;
            } else {
                i++;
            }

        }
        return result;
    }

    private int longestValidParenthesesWithStart(String s, int start) {
        int balance = 0;
        int i = start;
        int latestBalanceZero = start - 1;// match 0 result

        for (; i < s.length() && balance >= 0; i++) {
            if (s.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
                if (balance == 0) latestBalanceZero=i;
            }
        }

        return latestBalanceZero - start + 1;
    }
}
