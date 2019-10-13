import java.util.Stack;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

/*
 * @lc app=leetcode.cn id=169 lang=java
 *
 * [169] 求众数
 */

// @lc code=start
class Solution {
    public int majorityElement(int[] nums) {
        //投票算法
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (stack.empty() || stack.peek() == nums[i]) {
                stack.push(nums[i]);
            } else {
                stack.pop();
            }
        }
        return stack.pop();
    }
}
// @lc code=end
