package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permute {

    /*
        Pass 3: 1ms/392.27%, 43MB/22.41%
        回溯法 - 加入布尔数组判断是否已经使用，显著快于"contains"的比较
     */

    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] flags = new boolean[nums.length];
        backtracking3(result, new ArrayList<>(), nums, flags);
        return result;
    }

    private void backtracking3(List<List<Integer>> result, List<Integer> curList, int[] nums, boolean[] flags) {
        if (curList.size() == nums.length) {
            result.add(new ArrayList<>(curList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                //if (curList.contains(nums[i])) continue; // unique
                if (!flags[i]) {
                    flags[i] = true;
                    curList.add(nums[i]);

                    backtracking3(result, curList, nums, flags);

                    curList.remove(curList.size()-1);
                    flags[i] = false;
                }

            }
        }
    }


    /*
        Pass 2: 2ms/34.43%, 43.1MB/15.35%
        回溯法
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtracking(List<List<Integer>> result, List<Integer> curList, int[] nums) {
        if (curList.size() == nums.length) {
            result.add(new ArrayList<>(curList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (curList.contains(nums[i])) continue; // unique
                curList.add(nums[i]);
                backtracking(result, curList, nums);
                curList.remove(curList.size()-1);
            }
        }
    }



    /*
        Pass 1: 4ms/7.30%, 43.2MB/13.55%
        完全遍历
     */
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Integer[] numsInteger = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        List<Integer> numsList = new ArrayList<>(Arrays.asList(numsInteger));

        if (nums.length == 1) {
            result.add(numsList);
            return result;
        }

        appendList(result, numsList, new ArrayList<>());

        return result;
    }

    private void appendList(List<List<Integer>> result, List<Integer> remainingNums, List<Integer> curList) {

        if (remainingNums.size() == 0) result.add(curList);

        for (int i = 0; i < remainingNums.size(); i++) {
            List<Integer> newList = new ArrayList<>(curList);
            newList.add(remainingNums.get(i));

            List<Integer> newRemainingNums = new ArrayList<>(remainingNums);
            newRemainingNums.remove(i);

            appendList(result, newRemainingNums, newList);

        }
    }
}
