/*
 * @lc app=leetcode.cn id=371 lang=java
 *
 * [371] 两整数之和
 */

// @lc code=start
class Solution {
    public int getSum(int a, int b) {
        for (int i = 0; i < 32; i++) {
            int x = a ^ b;
            int y = a & b;
            y = y << 1;
            a = x;
            b = y;
        }

        return a;
    }
}
// @lc code=end
