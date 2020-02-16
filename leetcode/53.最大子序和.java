/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子序和
 */

// @lc code=start
class Solution {
    // 前缀和解法优化暴力解
    public int maxSubArray(final int[] nums) {
        if (nums.length == 0)
            return 0;
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum = sum + nums[j];
                max = Math.max(sum, max);
            }
        }
        return max;
    }
}
// @lc code=end
