package easy;/*

Constraints:
    1 <= nums.length <= 3 * 104
    -100 <= nums[i] <= 100
    nums is sorted in non-decreasing order.
第二次通过：Runtime 0ms/100%, Memory 42.2/44.95%
 */

public class RemoveDuplicates {


    public int removeDuplicates2(int[] nums) {
        if (nums.length <= 1) return 1; // won't be 0

        int curIndex = 0;
        for (int i=1; i<nums.length; i++) {
            if (nums[curIndex] < nums[i]) {
                curIndex++;
                nums[curIndex] = nums[i];
            }
        }
        return curIndex+1;
    }


    // 第一次通过：Runtime 1ms/99.99%, Memory 44.3/18.67%
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 1) return 1; // won't be 0

        int curIndex = 0;
        for (int i=1; i<length; i++) {
            if (nums[curIndex] < nums[i]) {
                curIndex++;
                nums[curIndex] = nums[i];
            }
        }
        return curIndex+1;
    }
}
