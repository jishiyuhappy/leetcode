package easy;

/*
 Write a function to find the longest common prefix string amongst an array of strings.
 If there is no common prefix, return an empty string "".
 20230213: 未考虑到只有一个输入string，以及多个string全部一致的case
 */
public class SolutionLongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {

        if (strs == null) {
            return  "";
        } else if (strs.length == 1) {
            return strs[0];
        }

        StringBuffer comPrefix = new StringBuffer();
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 0; j < strs.length; j++) {
                if ((i >= strs[j].length()) || (strs[0].charAt(i) != strs[j].charAt(i))) {
                    return  comPrefix.toString();
                }
            }
            comPrefix.append(strs[0].charAt(i));
        }
        return  comPrefix.toString();
    }
}
