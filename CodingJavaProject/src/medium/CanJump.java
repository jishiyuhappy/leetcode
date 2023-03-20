package medium;

import java.util.Arrays;

public class CanJump {

    /*
        试试贪心算法
        Pass 1: 1ms/99.99%, 43MB/51.51%
        只需要每个点计算一遍，
     */
    public boolean canJump2(int[] nums) {


        int targetIndex = nums.length-1;

        for (int i = nums.length-2; i >= 0; i--) {
            if (i+nums[i] >= targetIndex) targetIndex = i;
        }

        return targetIndex == 0;
    }

    /*
        Pass 1: 102ms/24.24%, 43.4MB/22.11%
        动态规划，似乎有点慢 -> 因为都算了？
     */
    public boolean canJump(int[] nums) {

        boolean[] numsCan = new boolean[nums.length];
        Arrays.fill(numsCan, false);
        numsCan[nums.length-1] = true;

        for (int i = nums.length-2; i >= 0; i--) {
            if (nums[i]+i >= nums.length-1) { // directly reach
                numsCan[i] = true;
                continue;
            }

            for (int j = nums[i]+i ; j > i; j--) { // reach to a point which can reach to the end
                if (numsCan[j]) {
                    numsCan[i] = true;
                    break;
                }
            }
        }

        return numsCan[0];
    }
}
