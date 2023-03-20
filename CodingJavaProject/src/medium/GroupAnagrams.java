package medium;

import java.util.*;

public class GroupAnagrams {

    /*
        这个别人的代码快在了哪里？？？
        "getOrDefault"
     */
    public List<List<String>> groupAnagrams5(String[] strs) {
        // 纯粹测试速度，直接copy排名高的代码
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            List<String> anagrams = map.getOrDefault(sortedStr, new ArrayList<>());
            anagrams.add(str);
            map.put(sortedStr, anagrams);
        }
        return new ArrayList<>(map.values());
    }

    /*
        这个速度反而变慢了
        主要是key的选择：这里是用一个26位的数组，对于重复数，计入次数
     */
    public List<List<String>> groupAnagrams4(String[] strs) {

        Map<String, List<String>> resultMap = new HashMap<>();

        int[] count = new int[26];

        for (int i = 0; i < strs.length; i++) {

            Arrays.fill(count, 0);

            for (char c: strs[i].toCharArray()) {
                count[c-'a']++;
            }

            String newKey = Arrays.toString(count);


            if (resultMap.containsKey(newKey)) {
                resultMap.get(newKey).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                resultMap.put(newKey, temp);
            }
        }

        List<List<String>> result = new ArrayList<>(resultMap.values());
        return result;
    }

    /*
        用sorted string做key
        Pass 1: 10ms/42.56%, 45.9MB/48.27%
     */
    public List<List<String>> groupAnagrams3(String[] strs) {

        Map<String, List<String>> resultMap = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {

            char[] charArray = strs[i].toCharArray();
            Arrays.sort(charArray);
            //String newKey = charArray.toString();
            String newKey = Arrays.toString(charArray);

            if (resultMap.containsKey(newKey)) {
                resultMap.get(newKey).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                resultMap.put(newKey, temp);
            }
        }

        List<List<String>> result = new ArrayList<>(resultMap.values());
        return result;
    }


    /*
        Wrong 3: 加24次推移 依然有错，["ddddddddddg","dgggggggggg"]
    */
    public List<List<String>> groupAnagrams2(String[] strs) {

        Map<TempKey, List<String>> resultMap = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {

            Integer binarySum = 0;
            for (char c: strs[i].toCharArray()) {
                Integer curValue = 1 << (c - 'a');
                if ((binarySum & curValue) == 0) {
                    binarySum += curValue;
                } else { // duplicated value
                    curValue = curValue << ('z' - 'a');
                }
            }

            TempKey tempKey = new TempKey();
            tempKey.hashValue = binarySum;
            tempKey.stringLength = strs[i].length();

            if (resultMap.containsKey(tempKey)) {
                resultMap.get(tempKey).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                resultMap.put(tempKey, temp);
            }
        }

        List<List<String>> result = new ArrayList<>(resultMap.values());
        return result;
    }


    /*
        Wrong: 重复字母没有处理
        Wrong: 加上string的长度后，还是有问题 - huh, tit -> 算出来的还是一样
        ？改成乘法
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<TempKey, List<String>> resultMap = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {

            Integer binarySum = 0;
            for (char c: strs[i].toCharArray()) {
                binarySum += (1 << (c - 'a'));
            }

            TempKey tempKey = new TempKey();
            tempKey.hashValue = binarySum;
            tempKey.stringLength = strs[i].length();

            if (resultMap.containsKey(tempKey)) {
                resultMap.get(tempKey).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                resultMap.put(tempKey, temp);
            }
        }

        List<List<String>> result = new ArrayList<>(resultMap.values());
        return result;
    }

    private class TempKey {
        Integer hashValue;
        Integer stringLength;

        @Override
        public int hashCode() {
            //return super.hashCode();
            return hashValue * stringLength;
        }

        @Override
        public boolean equals(Object obj) {
            //return super.equals(obj);
            return this.hashCode() == obj.hashCode();
        }
    }

}
