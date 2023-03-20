package medium;

public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};

        int find = searchValue(nums, 0, nums.length-1, target);
        if (find == -1) return new int[]{-1, -1}; // not found

        int left = find;
        while (left > 0 && nums[left-1] == target) left--;

        int right = find;
        while (right < nums.length-1 && nums[right+1] == target) right++;

        return new int[]{left, right};
    }

    private int searchValue(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        } else if (left == right) {
            return nums[left] == target ? left : -1;
        }

        int mid = left + (right - left)/2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return searchValue(nums, left, mid-1, target);
        } else {
            return searchValue(nums, mid+1, right, target);
        }
    }
}
