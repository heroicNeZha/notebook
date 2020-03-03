/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] 移动零
 */

// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                nums[p++] = nums[i];
        }
        while (p < nums.length) {
            nums[p++] = 0;
        }
    }
}
// @lc code=end
