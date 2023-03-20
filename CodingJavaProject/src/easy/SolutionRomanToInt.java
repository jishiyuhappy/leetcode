package easy;

/*
Constraints:

1 <= s.length <= 15
s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
public class SolutionRomanToInt {

    /*
    第二次通过：Runtime 4ms/83.1%, Memory 42.6mb/55.57%
     */
    public int romanToInt2(String s) {
        int length = s.length();

        int result = 0;
        int num = 0;
        int pre = 0;
        for (int i=length-1; i>=0; i--) {

            switch (s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }

            if (num >= pre) {
                result += num;
            } else {
                result -= num;
            }

            pre = num;
        }

        return result;
    }

    /*
    第一次通过：Runtime 4ms/83.1%, Memory 42.8mb/47.1%
     */
    public int romanToInt(String s) {
        //char[] chars = s.toCharArray();

        int result = 0;
        char preChar = 'M';
        for (char c: s.toCharArray()) {
            switch (c) {
                case 'I':
                    result += 1;
                    break;
                case 'V':
                    result += 5;
                    if (preChar == 'I') result -= 2;
                    break;
                case 'X':
                    result += 10;
                    if (preChar == 'I') result -= 2;
                    break;
                case 'L':
                    result += 50;
                    if (preChar == 'X') result -= 20;
                    break;
                case 'C':
                    result += 100;
                    if (preChar == 'X') result -= 20;
                    break;
                case 'D':
                    result += 500;
                    if (preChar == 'C') result -= 200;
                    break;
                case 'M':
                    result += 1000;
                    if (preChar == 'C') result -= 200;
                    break;
                default:
            }
            preChar = c;
        }
        return result;
    }

}
