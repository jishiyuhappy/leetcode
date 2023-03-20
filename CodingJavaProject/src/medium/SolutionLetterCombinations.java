package medium;/*
Constraints:
0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].

 */

import java.util.*;

public class SolutionLetterCombinations {

    /*
        尝试缩减代码

        第二次通过：Runtime 0ms/100%, Memory 41.9MB/35.98%
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;

        Map<Integer, String> matchMap = new HashMap<>();
        matchMap.put(2, "abc");
        matchMap.put(3, "def");
        matchMap.put(4, "ghi");
        matchMap.put(5, "jkl");
        matchMap.put(6, "mno");
        matchMap.put(7, "pqrs");
        matchMap.put(8, "tuv");
        matchMap.put(9, "wxyz");


        List<String> resultList = new ArrayList<>();
        StringBuilder preList = new StringBuilder();
        paddingChars(resultList, matchMap, preList, digits, 0);

        return resultList;
    }

    private void paddingChars(List<String> resultList, Map<Integer, String> matchMap, StringBuilder preList, String digits, int currentI) {
        if (currentI >= digits.length()) {
            resultList.add(preList.toString());
            return;
        }
        String matchString = matchMap.get(digits.charAt(currentI)-'0');
        for (char c: matchString.toCharArray()) {
            preList.append(c);
            paddingChars(resultList, matchMap, preList, digits, currentI+1);
            preList.delete(preList.length()-1, preList.length());
        }
    }



    /*
    第一次通过：Runtime 1ms/83.69%, Memory 41.1MB/56%
     */
    /*
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;

        List<String> resultList = new ArrayList<>();
        StringBuilder preList = new StringBuilder();
        pendingArrarys(resultList, preList, digits, 0);

        return resultList;
    }

    private void pendingArrarys(List<String> resultList, StringBuilder preList, String digits, int currentI) {
        if (currentI >= digits.length()) {
            resultList.add(preList.toString());
            return;
        }

        List<Character> currentMatchChars = getMatchChars(digits.charAt(currentI));
        for (char c: currentMatchChars) {
            StringBuilder newList = new StringBuilder(preList);
            newList.append(c);
            pendingArrarys(resultList, newList, digits, currentI+1);
        }
    }

    private List<Character> getMatchChars(char c) {
        switch (c) {
            case '2':
                return new ArrayList<Character>(Arrays.asList('a', 'b', 'c'));
            case '3':
                return new ArrayList<Character>(Arrays.asList('d', 'e', 'f'));
            case '4':
                return new ArrayList<Character>(Arrays.asList('g', 'h', 'i'));
            case '5':
                return new ArrayList<Character>(Arrays.asList('j', 'k', 'l'));
            case '6':
                return new ArrayList<Character>(Arrays.asList('m', 'n', 'o'));
            case '7':
                return new ArrayList<Character>(Arrays.asList('p', 'q', 'r', 's'));
            case '8':
                return new ArrayList<Character>(Arrays.asList('t', 'u', 'v'));
            case '9':
                return new ArrayList<Character>(Arrays.asList('w', 'x', 'y', 'z'));
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
    }

    */
}
