package medium;

import java.util.ArrayList;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */
public class GenerateParenthesis {

    // 怎么理解？？？？
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis2(c))
                    for (String right: generateParenthesis2(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }

    /*
        "Backtracking" 这种会提前结束不符合要求的组合
        第二次通过：Runtime 0ms/100%, Memory 42.9MB/23.43%
         */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        //StringBuilder curString = new StringBuilder();
        StringBuilder curString = new StringBuilder();
        backtrack(result, curString, 0, 0, n);
        return result;
    }

    private void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max*2) {
            ans.add(cur.toString());
            return;
        }

        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open+1, close, max);
            cur.deleteCharAt(cur.length()-1);// 另一种选择，不加 '('
        }
        if (close < open) { // 闭的不能超过左括号
            cur.append(')');
            backtrack(ans, cur, open, close+1, max);
            cur.deleteCharAt(cur.length()-1);
        }
    }


    /*
    想不出了 - 递归的思维乱了
    递归，树，深度搜索
    换种思维 -> 构建所有可能性，树的遍历，valid判断
    第一次通过：Runtime 2ms/31.83%, Memory 42.4MB/51.7%
     */
    /*
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        //StringBuilder curString = new StringBuilder();
        char[] curString = new char[n*2];
        composeString(result, curString, 0);
        return result;
    }

    private void composeString(List<String> result, char[] curString, int pos) {
        if (pos == curString.length) {
            if (isValid(curString)) {
                result.add(new String(curString));
            }
        } else {
            // add left parenthesis
            curString[pos] = '(';
            // go deeper
            composeString(result, curString, pos+1);

            // add right parenthesis
            curString[pos] = ')';
            // go deeper
            composeString(result, curString, pos+1);
        }
    }

    private boolean isValid(char[] curString) {
        int balance = 0;
        for (char c : curString) {
            if (c == '(') {
                balance ++;
            } else {
                balance --;
            }
            if (balance < 0) return false;
        }
        return balance == 0;
    }
    */
}
