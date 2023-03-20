package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    /*
        merge: 一开始指向记录flag，然后循环判断。但是这种处理 [x,x] 这种比较麻烦，特别是这种与前后对接的情况
        merge2: 改成全部 "染色" 这种 "[[1,4],[5,6]]" 又处理不好了


        merge3: 走传统，先排序的路子
        Pass: 11ms/23.97%, 47.2MB/62.38%
     */

    public int[][] merge3(int[][] intervals) {

        //Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        //System.out.println(Arrays.deepToString(intervals));

//        List<int[]> resultList = new ArrayList<>();
        int [][] result = new int[intervals.length][2];
        int row = -1;
        for (int[] interval: intervals) {

            if ( row == -1 || interval[0] > result[row][1]) { // new set
                row++;
                result[row] = interval; // set 2 values directly?
            } else {
                result[row][1] = Math.max(result[row][1], interval[1]); // if need to extend the end index
            }
        }

        return Arrays.copyOf(result, row+1);
    }




    public int[][] merge2(int[][] intervals) {

        boolean[] flags = new boolean[10001];

        for (int i = 0; i < intervals.length; i++) {
            Arrays.fill(flags, intervals[i][0], intervals[i][1]+1, true);
        }

        List<int[]> resultList = new ArrayList<>();
        int start = -1;
        for (int i = 0; i < flags.length; i++) {
            if (flags[i]) {
                if (start == -1) start = i;
            } else {
                if (start != -1) {
                    int[] temp = new int[2];
                    temp[0] = start;
                    temp[1] = i-1;
                    start = -1; // reset
                    resultList.add(temp);
                }
            }
        }

        int[][] result = resultList.toArray(new int[0][]);

        return result;
    }


    public int[][] merge(int[][] intervals) {

        int[] flags = new int[10001];

        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] == intervals[i][1]) {
                if (intervals[i][0] == 0) {
                    flags[intervals[i][0]] = Integer.MAX_VALUE;
                }
            } else {
                if (flags[intervals[i][0]] == Integer.MAX_VALUE) flags[intervals[i][0]] = 0;
                flags[intervals[i][0]]++;

                if (flags[intervals[i][1]] == Integer.MAX_VALUE) flags[intervals[i][1]] = 0;
                flags[intervals[i][1]]--;
            }
        }

        List<int[]> resultList = new ArrayList<>();

        int balance = 0;
        int start = -1;

        for (int i = 0; i < flags.length; i++) {
            if (flags[i] == 0) continue;

            if (flags[i] == Integer.MAX_VALUE) {
                if (start == -1) {
                    int[] temp = new int[2];
                    temp[0] = i;
                    temp[1] = i;
                    //start = -1; // reset
                    resultList.add(temp);
                }
                continue;
            }

            if (start == -1 && flags[i] > 0) start = i;

            balance += flags[i];

            if (balance == 0) { // find one
                int[] temp = new int[2];
                temp[0] = start;
                temp[1] = i;
                start = -1; // reset
                resultList.add(temp);
            }
        }

        int[][] result = resultList.toArray(new int[0][]);

        return result;
    }
}
