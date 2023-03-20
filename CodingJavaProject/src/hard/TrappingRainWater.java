package hard;

public class TrappingRainWater {

    /*
        Pass 2: 1ms/98.39%, 43.4MB/21.53%
        动态规划 ->  问题拆解后就比较简单
        "先算出每个元素的left_max和right_max，再遍历数组，相当于把嵌套遍历拆分成并行遍历，还有就是采用了memoization优化技术，避免重复计算"

     */
    public int trap2(int[] height) {

        if (height.length <= 2) return 0;

        // from left to right -> the biggest Left
        int[] left_max = new int[height.length];
        left_max[0] = 0;
        for (int i = 1; i < height.length; i++) {
            left_max[i] = Math.max(left_max[i-1], height[i-1]);
        }

        // from right to left -> the biggest Right
        int[] right_max = new int[height.length];
        right_max[height.length-1] = 0;
        for (int i = height.length-2; i > 0; i--) {
            right_max[i] = Math.max(right_max[i+1], height[i+1]);
        }

        // calculate each value
        int result = 0;
        for (int i = 1; i < height.length-1; i++) {
            int temp = Math.min(left_max[i], right_max[i]) - height[i];
            if (temp > 0)  result += temp;
        }

        return result;
    }




    /*
     Pass 1: 158ms/5.2%, 43.4MB/21.43%
     ?? 过多的重复计算
     */
    public int trap(int[] height) {

        if (height.length <= 2) return 0;

        int result = 0;

        for (int i = 2; i < height.length; i++) { // start from the 3th value

            //int currentBottom = height[i-1];
            if (height[i-1] >= height[i]) continue;

            int preHighI = i-1; // preMax
            int j = i-2;
            // check previous
            for (; j >= 0 && height[j] < height[i] ; j--) {
                if (height[j] > height[preHighI]) {
                    result += (height[j] - height[preHighI]) * (i - j-1); // calculate previous
                    preHighI = j;
                }
            }

            // last one, no need check back. still calculate
            if (j>=0 && height[j] >= height[i]) {
                result += (height[i] - height[preHighI]) * (i - j-1);
            }
        }

        return result;
    }
    
}
