package easy;

/*
0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int curIndex = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != val) {
                nums[curIndex] = nums[i];
                curIndex++;
            } // skip
        }
        return curIndex;
    }
}
