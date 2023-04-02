package medium;

public class SortColors {

    public void sortColors2(int[] nums) {

        int k = -1;
        int l = -1;
        int m = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) { // 0
                k++;
                l++;
                m++;
                nums[k] = 0;
                if (l > k) nums[l] = 1;
                if (m > l) nums[m] = 2;
            } else if (nums[i] == 1) { // 1
                l++;
                m++;
                nums[l] = 1;
                if (m > l) nums[m] = 2;
            } else { // 2
                m++;
            }
        }
    }

    public void sortColors(int[] nums) {

        int k = 0;
        int l = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                k++;
                l++;
            } else if (nums[i] == 1) {
                l++;
            }
        }

        int i = 0;
        for (; i < k; i++) {
            nums[i] = 0;
        }
        for (; i < l; i++) {
            nums[i] = 1;
        }
        for (; i < nums.length; i++) {
            nums[i] = 2;
        }
    }
}
