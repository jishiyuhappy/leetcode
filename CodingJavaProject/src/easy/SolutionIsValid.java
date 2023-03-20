package easy;/*
    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Every close bracket has a corresponding open bracket of the same type.
 */

import java.util.ArrayList;

public class SolutionIsValid {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        ArrayList<Character> stackCache = new ArrayList<>();
        int lastIndex = -1;
        for (char c:s.toCharArray()) {
            switch (c) {
                case '(' :
                case '{' :
                case '[' :
                    if (lastIndex == -1) {
                        stackCache.add(c);
                        lastIndex++;
                    } else if (stackCache.get(lastIndex) == '('
                            || stackCache.get(lastIndex) == '{'
                            || stackCache.get(lastIndex) == '[') {
                        stackCache.add(c);
                        lastIndex++;
                    } else { // ')', '}', ']' shouldn't be these
                        return false;
                    }
                    break;
                case ')' :
                    if (lastIndex == -1) {
                        return false;
                    } else if (stackCache.get(lastIndex) == '(') {
                        stackCache.remove(lastIndex);
                        lastIndex--;
                    } else {
                        return false;
                    }
                    break;
//                case '{' :
//                    break;
                case '}' :
                    if (lastIndex == -1) {
                        return false;
                    } else if (stackCache.get(lastIndex) == '{') {
                        stackCache.remove(lastIndex);
                        lastIndex--;
                    } else {
                        return false;
                    }
                    break;
//                case '[' :
//                    break;
                case ']' :
                    if (lastIndex == -1) {
                        return false;
                    } else if (stackCache.get(lastIndex) == '[') {
                        stackCache.remove(lastIndex);
                        lastIndex--;
                    } else {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }

        return stackCache.isEmpty();
    }
}
