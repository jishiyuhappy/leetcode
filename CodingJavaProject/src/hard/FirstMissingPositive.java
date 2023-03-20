package hard;

import java.util.Arrays;

public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {

        int length = nums.length;
        boolean[] records = new boolean[length+2]; // possible from 1 to length, set extra 1 as false

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <= length) records[nums[i]] = true; // 不需要记录大于length的值，因为第一个肯定小于length+1
        }

        int k = 1;
        while (records[k]) k++;
        return k;

    }
}
