package medium;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        listSubsets(nums, result, new ArrayList<>(), 0);
        return result;
    }

    private void listSubsets(int[] nums, List<List<Integer>> result, List<Integer> subset, int cur) {
        if (cur >= nums.length) {
            result.add(new ArrayList<>(subset));
            return;
        }

        // add
        subset.add(nums[cur]);
        listSubsets(nums, result, subset, cur+1);
        // remove
        subset.remove(subset.size()-1);
        listSubsets(nums, result, subset, cur+1);
    }

    private void listSubsets2(int[] nums, List<List<Integer>> result, List<Integer> subset, int cur) {
        if (cur >= nums.length) {
            result.add(subset);
            return;
        }

        listSubsets(nums, result, new ArrayList<>(subset), cur+1);
        // add
        //subset.add(nums[cur]);
        List<Integer> newArray = new ArrayList<>(subset);
        newArray.add(nums[cur]);
        listSubsets(nums, result, newArray, cur+1);
        // remove
        //subset.remove(subset.size()-1);
    }
}
