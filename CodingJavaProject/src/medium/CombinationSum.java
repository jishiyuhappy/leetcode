package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSum {

    // 动态规划？？？？？

    /*
        简化代码，其实就是 回溯法
        不用排序，基本都判断一遍
        Pass: 2ms/86.79%, 42.9B/40.26%
        ？ 为什么这个结果会更好点呢

        把之前方法的预先排序去掉后
        Pass: 2ms/86.79%, 43MB/25.20%
        我看test基本是升序的，那我排了降序是排了个寂寞 - 我的算法会skip大的值，但是与排序无关
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        backtracking(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtracking(List<List<Integer>> result, ArrayList<Integer> preNums, int[] candidates, int remainTarget, int start) {

        if (remainTarget < 0) {
            return;
        } else if (remainTarget == 0) {
            result.add(new ArrayList<>(preNums));
        } else {
            for (int i = start; i < candidates.length; i++) { // 逐一尝试
                preNums.add(candidates[i]); // 尝试当前选择
                backtracking(result, preNums, candidates, (remainTarget - candidates[i]), i); // 当前选择，i在这里会被继续调用
                preNums.remove(preNums.size()-1); // 去除当前选择
            }
        }
    }





    /*
        Pass: 6ms/13.96, 43.3MB/14.82%
        基本是 检查所有的case，判断，部分情况提前退出
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //Arrays.sort(candidates);
        Integer[] desendingCandidates = Arrays.stream(candidates).boxed().toArray(Integer[]::new);
        Arrays.sort(desendingCandidates, Collections.reverseOrder());

        List<List<Integer>> result = new ArrayList<>();
        findCombinationSum(candidates, 0, target, new ArrayList<>(), result);

        return result;
    }

    private void findCombinationSum(int[] candidates, int start, int target, ArrayList<Integer> preNums, List<List<Integer>> result) {

        if (target == 0) { // find one match
            //preNums.add(target);
            result.add(preNums);
            return;
        } else if (target < 0) {
            return;
        }

        if (start == candidates.length) return; // end

        while (start < candidates.length && candidates[start] > target) start++; // skip big num

        if (start == candidates.length) return; // end


        // not include current value
        findCombinationSum(candidates, start+1, target, new ArrayList<>(preNums), result);

        int leftTarget = target;
        while (target >= 0) {

            target -= candidates[start];
            preNums.add(candidates[start]);

            // add current value
            findCombinationSum(candidates, start+1, target, new ArrayList<>(preNums), result);

        }
    }
}
