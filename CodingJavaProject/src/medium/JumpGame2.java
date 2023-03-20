package medium;

public class JumpGame2 {

    /*
        贪心算法 
        Pass 2: 2ms/38.35%, 42.9MB/43.92%
     */
    public int jump2(int[] nums) {
        if (nums.length <= 1) return 0;

        int step = 0;
        int lastMax = 0;
        int curMax = 0;


        for (int i = 0; i < nums.length; i++) {

            if (i > lastMax) { // 需要一次跳跃
                lastMax = curMax;
                step++;
            }

            curMax = Math.max(curMax, i+nums[i]); // 可能得最远距离
        }

        return step;
    }


    /*
        动态规划，从后往前，每一个点的最小值
        Pass 1: 57ms/23.34%, 43.4MB/10.50%
     */

    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;

        int[] steps = new int[nums.length];

        for (int i = nums.length-2; i >= 0; i--) {

            if (i+nums[i] >= nums.length - 1) {
                steps[i] = 1;
                continue;
            }

            steps[i] = nums.length; // won't be this value, because most is nums.length-1
            for (int j = i+nums[i]; j > i; j--) {
                steps[i] = Math.min(1+steps[j], steps[i]);
            }

        }

        return steps[0];
    }
}
