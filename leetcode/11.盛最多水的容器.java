/*
 * @lc app=leetcode.cn id=11 lang=java
 *
 * [11] 盛最多水的容器
 */

// @lc code=start
class Solution {
    public int maxArea(int[] height) {
        int ans = 0;
        int start = 0;
        int end = height.length - 1;
        while (start < end) {
            if (height[start] <= height[end]) {
                ans = Math.max(ans, (end - start) * height[start++]);
            } else {
                ans = Math.max(ans, (end - start) * height[end--]);
            }
        }
        return ans;
    }
}
// @lc code=end
