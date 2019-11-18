/*
 * @lc app=leetcode.cn id=654 lang=java
 *
 * [654] 最大二叉树
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums,0,nums.length);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start == end)
            return null;

        int mark = start;
        for (int i = start; i < end; i++) {
            if (nums[i] > nums[mark])
                mark = i;
        }
        TreeNode max = new TreeNode(nums[mark]);

        max.left = constructMaximumBinaryTree(nums, start, mark);
        max.right = constructMaximumBinaryTree(nums, mark + 1, end);
        return max;
    }
}
// @lc code=end
