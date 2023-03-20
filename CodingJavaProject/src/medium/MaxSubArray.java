package medium;

public class MaxSubArray {

    /*
        Pass： 1ms/100%, 51.5MB/79.3%
        动态规划: 利用之前的值，数组会记录当前-~i的最大值
     */
    public int maxSubArray3(int[] nums) {
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] > 0) {
                nums[i] += nums[i-1];

            }
            result = Math.max(result, nums[i]);
        }

        return result;
    }


    /*
        Pass 1： 1ms/100%, 52.1MB/18.7%
        为啥这种方法 代码这么简单？效率这么高

        curSum -> 某一阶段的值，需要重置 -> 用当前的值重置，就没有了0的问题
        resut -> 主要是记录上一个最大值
    */
    public int maxSubArray2(int[] nums) {
        int result = -100000; // the minimal value
        int curSum = 0;

        for (int value: nums) {
            curSum = Math.max(curSum + value, value); // 没有0的问题了？？？？
            result = Math.max(result, curSum);
        }

        return result;
    }

    /*
        Fail多次
        Pass 1： 3ms/7.18%, 51.9MB/29.43%
     */
    public int maxSubArray(int[] nums) {

        if (nums.length == 1) return nums[0];

        int result = -100000; // the minimal value
        int curMax = 0;

        boolean newValue = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {// check point
                if (newValue) {
                    result = Math.max(result, curMax);
                } else {
                    result = Math.max(result, nums[i]);
                }
            }

            curMax += nums[i];
            newValue = true;

            if (curMax < 0) { // drop -> reset
                curMax = 0;
                newValue = false;
            }
        }

        return curMax > 0 ? Math.max(result, curMax) : result;
    }
}
