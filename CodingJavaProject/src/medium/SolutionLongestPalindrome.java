package medium;

/*
    Given a string s, return the longest palindromic substring in s.
 */
public class SolutionLongestPalindrome {

    /*
        Manacher - 马拉车算法
        第一次通过：Runtime %, Memory %
     */
    public String longestPalindrome6(String s) {

        return s;
    }

    /*
      中心开花 - Expand Around Center - 理论上是否优于DP？因为可以提前退出？
      第一个char也要测，因为可能是偶数回文
      第一次通过：Runtime 64.6%, Memory 89.86% (没有提前退出，大量减少了内存使用)
      第二次通过：Runtime 98.91%, Memory 97.62% (从中间开始，提前退出)
      第三次通过：Runtime 99.59%, Memory 97.62% (Refactor代码，去掉第一次的重复计算)
     */

    private int curLongest = 1, curLongestStartI = 0;

    public String longestPalindrome5(String s) {

        int length = s.length();

        if (length == 1) { //1 <= s.length <= 1000
            return s;
        }

        int medianIndex = (int) (Math.round(s.length() / 2.0) - 1); // left index for even 偶数

        checkPalindrome(s, length,medianIndex);// check the median once

        for (int extend = 1; extend <= medianIndex; extend++) {//axis

            //i -> axis index

            // go left
            int i = medianIndex - extend;
            if (curLongest >= i*2+3) {// (i+1)*2+1 possible biggest.with +1 for the even case go right
                break;
            }
            checkPalindrome(s, length,i);

            // go right
            i = medianIndex + extend;
            checkPalindrome(s, length,i);
        }

        // find the longest
        return s.substring(curLongestStartI, curLongestStartI + curLongest);
    }

    private void checkPalindrome(String s, int length, int i) {
        // single axis, odd palindrome
        int j=1;
        while ((i-j >= 0) && (i+j <= length-1) && (s.charAt(i-j) == s.charAt(i+j))) {
            j++;
        }

        if (curLongest < 2*j-1) {
            curLongest = 2*j-1;
            curLongestStartI = i-j+1;
        }

        // double axis, even palindrome
        j=0;
        while ((i-j >= 0) && (i+1+j <= length-1) && (s.charAt(i-j) == s.charAt(i+1+j))) {
            j++;
        }

        if (curLongest < 2*j) {
            curLongest = 2*j;
            curLongestStartI = i-j+1;
        }
    }


    /*public String longestPalindrome5(String s) {

        int length = s.length();

        if (length == 1) { //1 <= s.length <= 1000
            return s;
        }

        int curLongest = 1, curLongestStartI = 0;
        int medianIndex = (int) (Math.round(s.length() / 2.0) - 1); // left index for even 偶数
        for (int extend = 0; extend <= medianIndex; extend++) {//axis

            //i -> axis index

            // go left
            int i = medianIndex - extend;

            if (curLongest >= i*2+3) {// possible biggest.with +1 for the corner case
                break;
            }

            // single axis, odd palindrome
            int j=1;
            while ((i-j >= 0) && (i+j <= length-1) && (s.charAt(i-j) == s.charAt(i+j))) {
                j++;
            }

            if (curLongest < 2*j-1) {
                curLongest = 2*j-1;
                curLongestStartI = i-j+1;
            }

            // double axis, even palindrome
            j=0;
            while ((i-j >= 0) && (i+1+j <= length-1) && (s.charAt(i-j) == s.charAt(i+1+j))) {
                j++;
            }

            if (curLongest < 2*j) {
                curLongest = 2*j;
                curLongestStartI = i-j+1;
            }


            // go right
            i = medianIndex + extend;

            // single axis, odd palindrome
            j=1;
            while ((i-j >= 0) && (i+j <= length-1) && (s.charAt(i-j) == s.charAt(i+j))) {
                j++;
            }

            if (curLongest < 2*j-1) {
                curLongest = 2*j-1;
                curLongestStartI = i-j+1;
            }

            // double axis, even palindrome
            j=0;
            while ((i-j >= 0) && (i+1+j <= length-1) && (s.charAt(i-j) == s.charAt(i+1+j))) {
                j++;
            }

            if (curLongest < 2*j) {
                curLongest = 2*j;
                curLongestStartI = i-j+1;
            }

        }

        // find the longest
        return s.substring(curLongestStartI, curLongestStartI + curLongest);
    }*/

    /*public String longestPalindrome5(String s) {

        int length = s.length();

        if (length == 1) { //1 <= s.length <= 1000
            return s;
        }

        int curLongest = 1, curLongestStartI = 0;
        //int medianIndex = (int) (Math.round(s.length() / 2.0) - 1); // left index for even 偶数
        for (int i = 0; i < length-1; i++) {//axis, no need check the last char
            int j=1;
            // single axis, odd palindrome
            while ((i-j >= 0) && (i+j <= length-1) && (s.charAt(i-j) == s.charAt(i+j))) {
                j++;
            }

            if (curLongest < 2*j-1) {
                curLongest = 2*j-1;
                curLongestStartI = i-j+1;
            }

            // double axis, even palindrome
            j=0;
            while ((i-j >= 0) && (i+1+j <= length-1) && (s.charAt(i-j) == s.charAt(i+1+j))) {
                j++;
            }

            if (curLongest < 2*j) {
                curLongest = 2*j;
                curLongestStartI = i-j+1;
            }
        }

        // find the longest
        return s.substring(curLongestStartI, curLongestStartI + curLongest);
    }*/


    /*
        动态规划 - 迭代
        第一次通过：Runtime 40.7%, Memory 27.47% （比递归效果好。为什么没有提前退出也会更快？）
     */

    public String longestPalindrome4(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }

        int length = s.length();
        boolean[][] results = new boolean[length][length];

        // Default as false

        // Single value is true
        for (int i = 0; i < length; i++) {
            results[i][i] = true;
        }

        char[] chars = s.toCharArray();
        int curLongest = 1, curLongestI =0 , curLongestJ = 0;
        for (int i = length - 1 - 1; i >= 0 ; i--) {
            for (int j = i + 1; j <= length-1; j++) {
                if (i+1 == j) {
                    if (chars[i] == chars[j]) {
                        results[i][j] = true;
                        if (curLongest < 2) {
                            curLongest = 2;
                            curLongestI = i;
                            curLongestJ = j;
                        }
                    }
                } else if (results[i + 1][j - 1] && (chars[i] == chars[j])) {
                    results[i][j] = true;
                    if (curLongest < (j - i + 1)) {
                        curLongest = j - i + 1;
                        curLongestI = i;
                        curLongestJ = j;
                    }
                }
            }
        }

        // find the longest
        return s.substring(curLongestI, curLongestJ+1);
    }



    /*
        动态规划 - 递归
        leetcode 里用static的时候结果不对？
        第一次通过：Runtime 27.76%, Memory 14.24% （比中心开花更慢）
        第二次通过：Runtime 28.38%, Memory 14.4%
    */

    //private int curLongest, curLongestI, curLongestJ = 0;
    private int curLongestI, curLongestJ = 0;

    public String longestPalindrome3(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }

        int length = s.length();
        boolean[][] results = new boolean[length][length];

        // mark flag
        // the first & longest
        checkPlaindrome(s.toCharArray(), results, 0, length-1);
        if (results[0][length-1]) {
            return s;
        }

        int currentLongest = 0;
        for (int i = 1; i < length; i++) {

            if (curLongest >= (length-1-i + 1)) {
                break;
            }

            // left
            checkPlaindrome(s.toCharArray(), results, 0, length-1-i);

            // may skip because the curLongest will be changed

            // right
            checkPlaindrome(s.toCharArray(), results, i, length-1);

        }

        // find the longest
        return s.substring(curLongestI, curLongestJ+1);
    }

    private boolean checkPlaindrome(char[] chars, boolean[][] results, int i, int j) {

        if (i == j) { // single char
            results[i][j] = true;
        } else if (i + 1 == j) { // pair of chars
            if (chars[i] == chars[j]) {
                results[i][j] = true;
                if (curLongest < 2) {
                    curLongest = 2;
                    curLongestI = i;
                    curLongestJ = j;
                }
            } else {
                results[i][j] = false;
            }
            results[i][i] = true;
        } else if (checkPlaindrome(chars, results, i+1, j-1) && (chars[i] == chars[j])) {
            results[i][j] = true;
            if (curLongest < (j-i+1)) {
                curLongest = j-i+1;
                curLongestI = i;
                curLongestJ = j;
            }
        } else {
            results[i][j] = false;
        }

        return results[i][j];
    }

//    private String findLongestPlaindrome(String s, boolean[][] results, int i, int j) {
//        if (results[i][j]) {
//            return s.substring(i,j+1);
//        } else if (results[i][j-1]) {
//            return s.substring(i,j);
//        } else if (results[i+1][j]) {
//            return s.substring(i+1,j+1);
//        }
//
//        return findLongestPlaindrome(s,results,i+1,j-1);
//    }


    /*
    中心开花
    错误："dd" 算出的中间index为1，算不出dd，改成round
    第一次通过：Runtime 28.35%, Memory 43.12%
    第二次通过：Runtime 38.40%, Memory 55.51%
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }

        String currentLongestPalindrome = "";
        char[] charsArray = s.toCharArray();
        //int startIndex = s.length() / 2; // start from middle
        int startIndex = (int) (Math.round(s.length() / 2.0) - 1);
        for (int i=0; (startIndex - i >= 0) && (startIndex + i < s.length()); i++) {

            // skip if the biggest possible count is less than the current found value
            int possibleLength = (startIndex - i + 1) * 2;
            int possibleLength2 = (s.length() - (startIndex + i)) * 2 - 1;
            if (currentLongestPalindrome.length() >= possibleLength && currentLongestPalindrome.length() >= possibleLength2) {
                break;
            }

            // Go left
            String tempString = singleAxis(s, startIndex - i);
            if (tempString.length() > currentLongestPalindrome.length()) {
                currentLongestPalindrome = tempString;
            }

            tempString = doubleAxis(s, startIndex - i);
            if (tempString.length() > currentLongestPalindrome.length()) {
                currentLongestPalindrome = tempString;
            }

            // Go right
            tempString = singleAxis(s, startIndex + i);
            if (tempString.length() > currentLongestPalindrome.length()) {
                currentLongestPalindrome = tempString;
            }

            tempString = doubleAxis(s, startIndex + i);
            if (tempString.length() > currentLongestPalindrome.length()) {
                currentLongestPalindrome = tempString;
            }
        }
        return currentLongestPalindrome;
    }

    private String singleAxis(String s, int medianIndex) {
        char[] chars = s.toCharArray();
        int i = 1;
        for (; (medianIndex - i >= 0) && (medianIndex + i < chars.length); i++) {
            if (chars[medianIndex-i] != chars[medianIndex+i]) {
                return s.substring(medianIndex-i+1, medianIndex + i);
            }
            //continue
        }
        return s.substring(medianIndex-i+1, medianIndex + i);
    }

    private String doubleAxis(String s, int medianIndex) {
        char[] chars = s.toCharArray();
        int i = 1;
        for (; (medianIndex - i + 1 >= 0) && (medianIndex + i < chars.length); i++) {
            if (chars[medianIndex-i+1] != chars[medianIndex+i]) {
                return s.substring(medianIndex-i+1+1, medianIndex + i);
            }
            //continue
        }
        return s.substring(medianIndex-i+1+1, medianIndex + i);
    }



    /////////////////////////////////////////////////////////////////////////////////
    /*
    单独字符也算是一个最小回文
    String里subString的 endindex是不包含的
    Time Limit Exceeded
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }

        for (int count=s.length(); count > 1; count--) {// from longest
            for (int i=0; i + count <= s.length(); i++) {
                String subString = s.substring(i, i+count);//endindex not include, with -1
                if (isPlaindrome(subString)) {
                    return subString;
                }
            }
        }

        return s.substring(0,1);//didn't find, return the first char
    }

    private boolean isPlaindrome(String s) {
        char[] charArray = s.toCharArray();
        for (int i=0, j=charArray.length - 1; i < j; i++, j--) {
            if (charArray[i] == charArray[j]) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
