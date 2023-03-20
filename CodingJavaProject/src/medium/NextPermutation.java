package medium;

/*
Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100
 */

public class NextPermutation {

    /*
    数据相等的情况
     */
    public void nextPermutation(int[] nums) {

        if (nums.length == 1) return;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) { // find the first degrade number -> i

                int j = i+1;
                for (; j < nums.length; j++) { // find the position
                    if (nums[i] >= nums[j]) {
                        break;
                    }
                }

                // swap
                int temp = nums[i];
                nums[i] = nums[j-1];
                nums[j-1] = temp;

                // reverse from i+1 all left nodes
                reserveList(nums, i+1);

                return;
            }
        }

        // the biggest, should find the
        reserveList(nums, 0);
    }

    private void reserveList(int[] nums, int left) {
        for (int right = nums.length-1; left < right ; left++, right--) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
    }
}
