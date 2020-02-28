/*
 * @lc app=leetcode.cn id=70 lang=java
 *
 * [70] 爬楼梯
 */

// @lc code=start
class Solution {
    public int climbStairs(int n) {
        int x = 1, y = 1;
        while (--n > 0) {
            int temp = y;
            y = x + y;
            x = temp;
        }
        return y;
    }
}
// @lc code=end
