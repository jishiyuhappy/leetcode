package medium;/*
You are given an integer array height of length n.
There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Constraints:
n == height.length
2 <= n <= 10e5
0 <= height[i] <= 10e4

 */

public class SolutionContainerWithMostWater {
    /*
        第八次通过：Runtime 2ms/99.21%, Memory 52.7/51.71% (Refactor代码，cost变大了，O(n))
     */
    public int maxArea3(int[] height) {
        int n = height.length;
        int curMaxLowH = height[0] <= height[n-1] ? height[0] : height[n-1];
        int curMaxArea = curMaxLowH * (n-1); // initialize

        int i=0, j=n-1;
        while (i<j) {
            while ((i < j) && (height[i] <= curMaxLowH)) i++;
            while ((i < j) && (height[j] <= curMaxLowH)) j--;

            if (i<j) {
                curMaxLowH = height[i] <= height[j] ? height[i] : height[j];
                int tempArea = curMaxLowH * (j-i);
                if (curMaxArea < tempArea) {
                    curMaxArea = tempArea;
                }
            }
        }
        return curMaxArea;
    }

    /*
    第六次通过：Runtime 45ms/5.5%, Memory 52.5/62.75%
    高度可能为0
    第七次通过：Runtime 1ms/100%, Memory 52.7/41.69% (只找更大的height，从左右两边向内查找，O(n))
    */
    public int maxArea2(int[] height) {
        int n = height.length;
        int curMaxArea = height[0] > height[n-1] ? height[n-1] * (n-1) : height[0] * (n-1); // initialize
        int curLeftHighI = 0; //start from the left
        int curRightHighI = n-1; //start from the right

        int i=0, j=n-1;
        while (i<j) {
            if (height[curLeftHighI] <= height[curRightHighI]) { // move left index
                while ((i < j) && (height[i] <= height[curLeftHighI])) {
                    i++;
                }
                if (i < j) {
                    curLeftHighI = i;
                } else {
                    break;
                }

            } else { // move right index
                while (i < j && height[j] <= height[curRightHighI]) {
                    j--;
                }
                if (i < j) {
                    curRightHighI = j;
                } else {
                    break;
                }
            }

            int tempArea = height[i] <= height[j] ? height[i] * (j-i) : height[j] * (j-i);
            if (curMaxArea < tempArea) {
                curMaxArea = tempArea;
            }
        }
        return curMaxArea;
    }





    /*
        第三次通过：Runtime 45ms/5.5%, Memory 52.5/62.75% (继续控制内循环右侧开始的位置)
        ？要继续优化，看起来必须去掉内层循环？？
        第四次通过：Runtime 45ms/5.5%, Memory 52.2/94.91%
        第五次通过：Runtime 55ms/5.5%, Memory 52.5/73.94% (第一层循环，限制右边，竟然cost变大了？？？)
     */
    public int maxArea1(int[] height) {
        int n = height.length;
        int curMaxArea = height[0] > height[n-1] ? height[n-1] * (n-1) : height[0] * (n-1); // initialize
        int curMaxAreaL = 0; //start from the left
        int curMaxAreaR = n-1; //start from the right

        for (int i=0; i<curMaxAreaR; i++) {

            // Skip calculation
            if ((i != 0) && (height[i] <= height[curMaxAreaL])) {// won't find bigger case
                continue;
            }

            int tempArea = 0;
            for (int j=curMaxAreaR; j>i; j--) {
                tempArea = 0;
                if (height[j] >= height[i] ) { // all the left won't bigger than this case
                    tempArea = height[i] * (j-i);
                    if (curMaxArea < tempArea) {
                        curMaxArea = tempArea;
                        curMaxAreaL = i;
                        curMaxAreaR = j;
                    }
                    break;// Skip calculation
                } else if (height[j] >= height[curMaxAreaR]){
                    tempArea = height[j] * (j-i);
                    if (curMaxArea < tempArea) {
                        curMaxArea = tempArea;
                        curMaxAreaL = i;
                        curMaxAreaR = j;
                    }
                } else  {
                    // Skip calculation
                }
            }
        }
        return curMaxArea;
    }



/*
    Time Limit Exceeded：直接双循环,cost过高
    第一次通过：Runtime 188ms/5.5%, Memory 52.6/62.75% (单次判断优化，skip无效计算。固定左边的情况下，后面只选到比它大的点后，就不需要收缩)
    第二次通过：Runtime 133ms/5.5%, Memory 52.3/90.57 (第一层循环优化，skip无效计算。)
 */
    public int maxArea(int[] height) {
        int n = height.length;
        int curMaxArea = 0;
        int curMaxAreaL = 0;
//        int curMaxAreaR = 0;
//        int curMaxAreaHeight = 0;
//        int curRightHeight=0;

        for (int i=0; i<n; i++) {

            // Skip calculation
            if ((curMaxArea != 0) && (height[i] <= height[curMaxAreaL])) {// won't find bigger case
                continue;
            }

            int tempArea = 0;
            for (int j=n-1; j>i; j--) {
                tempArea = 0;
                if (height[i] <= height[j] ) { // all the left won't bigger than this case
                    tempArea = height[i] * (j-i);
                    if (curMaxArea < tempArea) {
                        curMaxArea = tempArea;
//                        curMaxAreaHeight = height[i];
                        curMaxAreaL = i;
//                        curMaxAreaR = j;
                    }
                    break;// Skip calculation
                } else {
                    tempArea = height[j] * (j-i);
                    if (curMaxArea < tempArea) {
                        curMaxArea = tempArea;
//                        curMaxAreaHeight = height[j];
                        curMaxAreaL = i;
//                        curMaxAreaR = j;
                    }
                }
            }
        }
        return curMaxArea;
    }
}
