import java.util.List;
import java.util.Stack;

/*
 * @lc app=leetcode.cn id=77 lang=java
 *
 * [77] 组合
 */

// @lc code=start
class Solution {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || n < k) {
            return result;
        }
        dfs(n, k, 1, new Stack<Integer>());
        return result;
    }

    public void dfs(int n, int k, int start, Stack<Integer> Item) {
        if (Item.size() == k) {
            result.add(new ArrayList<Integer>(Item));
            return;
        }

        for (int i = start; i <= n; i++) {
            Item.push(i);
            dfs(n, k, i + 1, Item);
            Item.pop();
        }
    }
}
// @lc code=end
