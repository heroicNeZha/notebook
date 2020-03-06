import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=501 lang=java
 *
 * [501] 二叉搜索树中的众数
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    int max = 0;
    int count = 0;
    List<Integer> result = new ArrayList<>();
    int pre = 0;

    public int[] findMode(TreeNode root) {
        helper(root);
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    public void helper(TreeNode node) {
        if (node == null)
            return;
        helper(node.left);

        if (max == 0) {
            max++;
            count++;
        } else {
            if (node.val == pre) {
                count++;
            } else {
                count = 1;
            }
        }
        if (count > max) {
            result.clear();
            max = count;
            result.add(node.val);
        } else if (count == max) {
            result.add(node.val);
        }
        pre = node.val;
        helper(node.right);
    }
}
// @lc code=end
