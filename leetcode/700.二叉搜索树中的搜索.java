/*
 * @lc app=leetcode.cn id=700 lang=java
 *
 * [700] 二叉搜索树中的搜索
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (null == root)
            return null;
        if (root.val == val)
            return root;
        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
    }
}
// @lc code=end
