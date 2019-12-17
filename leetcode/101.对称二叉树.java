import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/*
 * @lc app=leetcode.cn id=101 lang=java
 *
 * [101] 对称二叉树
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return false;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Stack<Integer> stack = new Stack<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null)
                if (stack.peek() == node.val)
                    stack.pop();
                else
                    stack.push(node.val);
            else if (stack.peek() == 0)
                stack.pop();
            else
                stack.push(0);
        }
        return stack.isEmpty();
    }
}
// @lc code=end
