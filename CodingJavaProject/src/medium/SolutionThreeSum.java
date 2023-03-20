package medium;/*
    Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
    such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

    Notice that the solution set must not contain duplicate triplets.

    3 <= nums.length <= 3000
    -10e5 <= nums[i] <= 10e5
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionThreeSum {


    /*
        第二次通过：Runtime 278ms/33.78%, Memory 50mb/33.22% (与之前是同一级别的O(nn), 但是代码量精简)
        第三次通过：Runtime 63ms/40.86%, Memory 50.2mb/27.89% (skip 相等的值的重复计算，就不需要set了)
        第四次通过：Runtime 38ms/68.4%, Memory 50.2mb/30.71% (对i和rightMax也判断skip条件，过大和过小值)
        第五次通过：Runtime 42ms/57.13%, Memory 49.3mb/59.18% (加了>0退出反而变差了, 数组优化)
        第五次通过：Runtime 37ms/71.59%, Memory 49.7mb/45.48% (测试注释掉 >0 判断, 可能是后继while循环已经cover了这种case，再加上就等于每次for都加了一次计算)
        第六次通过：Runtime 38ms/68.4%, Memory 49.5mb/54.86%
     */
    public List<List<Integer>> threeSum2(int[] nums) {

        // Sort at first
        Arrays.sort(nums); // Dual-Pivot Quicksort, O(n log(n))

        // Result
        List<List<Integer>> listResult = new ArrayList<>();

        int rightMax = nums.length - 1;

        for (int i = 0; i < rightMax - 1; ) { // fix left -> i


            //if (nums[i] > 0) break;

            int right = rightMax;

            while ((i < right - 1) && (nums[i] + nums[right-1] + nums[right] < 0)) i++;
            int left = i + 1;

            while ((left < right) && (nums[i] + nums[left] + nums[right] > 0)) right--;//skip right point
            rightMax = right;

            while (left < right) {
                int total = nums[i] + nums[left] + nums[right];
                if (total == 0) {

                    listResult.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (left < right && nums[left] == nums[left+1]) left++;
                    left++;
                    while (left < right && nums[right] == nums[right-1]) right--;
                    right--;


                } else if (total < 0) {
                    while (left < right && nums[left] == nums[left+1]) left++;
                    left++;
                } else {
                    while (left < right && nums[right] == nums[right-1]) right--;
                    right--;
                }
            }

            while (i < rightMax - 1 && nums[i] == nums[i+1]) i++;
            i++;
        }

        return listResult;
    }


///////////////////////////////////////////////////////////////////////////////////////////////////

    /*
        第一次通过：Runtime 473ms/27.12%, Memory 49.9mb/38.62% ()
     */
    private List<List<Integer>> result;
    
    public List<List<Integer>> threeSum(int[] nums) {
        // result
        result = new ArrayList<>();

        // Sort at first
        Arrays.sort(nums); // Dual-Pivot Quicksort, O(n log(n))

        // reduce 3/3+ duplicated numbers

        // Check
        checkThreeSum(nums, 0, nums.length-1);
        
        return result;
    }
    
    private void checkThreeSum(int[] nums, int start, int end) {

        if (end - start <= 1) return;

        int total = nums[start] + nums[end];// fix 2 points

        if (total >= 0) { // positive gap, check from left negative value

            // can fix the end, find all cases with end at first
            checkThreeSumFixEnd(nums, start, end);

            // now can skip end point

            // skip same value
            int newEnd = end-1;
            while (newEnd > start && nums[newEnd] == nums[end]) newEnd--;
            if (newEnd > start) {
                checkThreeSum(nums, start, newEnd);
            }

        } else { // negative gap, check from right positive value

            // can fix the end, find all cases with end at first
            checkThreeSumFixLef(nums, start, end);

            // now can skip start point

            // skip same value
            int newStart = start+1;
            while (newStart < end && nums[newStart] == nums[start]) newStart++;
            if (newStart < end) {
                checkThreeSum(nums, newStart, end);
            }
        }
    }

    private void checkThreeSumFixEnd(int[] nums, int start, int end) {

        if (end - start <= 1) return;

        int total = nums[start] + nums[end];

        int left = start+1;
        while ((left < end) && (nums[left] + total < 0)) { // negative -> continue
            left++;
        }

        if (left < end && nums[left] + total == 0) { // one case find
            attachList(nums, start, left, end);
        }

        if (nums[start] + nums[start+1] + nums[end] > 0) {
            // no need check the end -> too big
        } else {
            // skip same value
            int newStart = start+1;
            while (newStart < end && nums[newStart] == nums[start]) newStart++;
            if (newStart < end) {
                checkThreeSumFixEnd(nums, newStart, end);
            }
        }
    }

    private void checkThreeSumFixLef(int[] nums, int start, int end) {

        if (end - start <= 1) return;

        if (nums[start] + nums[end-1] + nums[end] < 0) return; // no need check the left -> too small

        int total = nums[start] + nums[end];

        int right = end-1;
        while ((right > start) && (nums[right] + total > 0)) { // negative -> continue
            right--;
        }

        if (right > start && nums[right] + total == 0) { // one case find
            attachList(nums, start, right, end);
        }

        // skip same value
        int newEnd = end-1;
        while (newEnd > start && nums[newEnd] == nums[end]) newEnd--;
        if (newEnd > start) {
            checkThreeSumFixLef(nums, start, newEnd);
        }
    }

    private void attachList(int[] nums, int start, int match, int end) {
        ArrayList<Integer> tempArray = new ArrayList<>();
        tempArray.add(nums[start]);
        tempArray.add(nums[match]); // this is the found poin
        tempArray.add(nums[end]);
        result.add(tempArray);
        //while (nums[left+1] == nums[left]) // same value, no need to include
    }

}
