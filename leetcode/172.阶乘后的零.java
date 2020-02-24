/*
 * @lc app=leetcode.cn id=172 lang=java
 *
 * [172] 阶乘后的零
 */

// @lc code=start
class Solution {
    public int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5) {
            n = Math.floorDiv(n, 5);
            count += n;
        }
        return count;
    }
}
// @lc code=end
