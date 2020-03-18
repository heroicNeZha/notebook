
/*
 * @lc app=leetcode.cn id=39 lang=java
 *
 * [39] 组合总和
 */

// @lc code=start
import java.util.*;

class Solution {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null)
            return result;
        Stack<Integer> stack = new Stack<>();
        dfs(candidates, stack, 0, target);
        return result;
    }

    public void dfs(int[] candidates, Stack<Integer> pre, int begin, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(pre));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            if (target >= candidates[i]) {
                pre.push(candidates[i]);
                dfs(candidates, pre,i, target - candidates[i]);
                pre.pop();
            }
        }
    }
}
// @lc code=end
