/*
 * @lc app=leetcode.cn id=437 lang=java
 *
 * [437] 路径总和 III
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;
        int pathsum = helpFunc(root, sum);
        pathsum += pathSum(root.left, sum);
        pathsum += pathSum(root.right, sum);
        return pathsum;
    }

    public int helpFunc(TreeNode root, int sum) {
        if (root == null)
            return 0;
        int left = helpFunc(root.left, sum - root.val);
        int right = helpFunc(root.right, sum - root.val);
        return left + right + (root.val == sum ? 1 : 0);
    }
}
// @lc code=end
