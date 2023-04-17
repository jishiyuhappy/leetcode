package hard;

public class LargestRectangleInHistogram {

    // 还是DP，找出可以重复利用的数值
    public int largestRectangleArea(int[] heights) {

        if (heights.length == 1) return heights[0];

        int[] leftHeights = new int[heights.length];
        leftHeights[0] = -1;//第一个不是
        int[] rightHeights = new int[heights.length];
        rightHeights[heights.length-1] = heights.length;


        for (int i = 1; i < heights.length; i++) {
            int j = i-1;
            while ((j >= 0) && (heights[i] <= heights[j])) {
                j = leftHeights[j];
            }
            leftHeights[i] = j;
        }

        for (int i = heights.length-2; i >= 0; i--) {
            int j = i+1;
            while ((j < heights.length) && (heights[i] <= heights[j])) {
                j = rightHeights[j];
            }
            rightHeights[i] = j;
        }

        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            result = Math.max(result, (rightHeights[i]-leftHeights[i]-1) * heights[i]);
        }
        return result;
    }
}
