/*
 * @lc app=leetcode.cn id=110 lang=java
 *
 * [110] 平衡二叉树
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        return helper(root) != -1;
    }

    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = helper(root.left);
        int rightHeight = helper(root.right);
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }
        int minus = leftHeight - rightHeight;
        if (minus <= 1 && minus >= -1) {
            return Math.max(leftHeight, rightHeight) + 1;
        } else {
            return -1;
        }
    }
}
// @lc code=end
