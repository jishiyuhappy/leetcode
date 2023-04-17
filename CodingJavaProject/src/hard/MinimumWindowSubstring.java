package hard;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    // 优化的话尝试用数组

    public String minWindow(String s, String t) {

        int m = s.length();
        int n = t.length();

        if (m < n) return "";

        Map<Character, Integer> tMap = new HashMap<>();
        for (Character character:t.toCharArray()) {
            tMap.put(character, tMap.getOrDefault(character, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int min_left = 0;
        int min_right = 0;
        int min_length = Integer.MAX_VALUE;

        while (right < s.length()) {

            Character character = s.charAt(right);

            if (tMap.containsKey(character)) {
                tMap.put(character, tMap.get(character) - 1);

                while ((right+1 >= n) && isMatch(tMap)) { // 是可能有冗余的，所以每次检查
                    int curLength = right - left + 1;
                    if (curLength < min_length) {
                        min_length = curLength;
                        min_left = left;
                        min_right = right;
                    }

                    Character leftChar = s.charAt(left);

                    if (tMap.containsKey(leftChar)) {
                        tMap.put(leftChar, tMap.get(leftChar) + 1);
                    }
                    left++;
                }
            }

            right++;
        }

        return min_length == Integer.MAX_VALUE ? "" : s.substring(min_left, min_right+1);
    }

    private boolean isMatch(Map<Character, Integer> map) {
        for (Integer value: map.values()) {
            if (value > 0) return false;
        }
        return true;
    }
}
