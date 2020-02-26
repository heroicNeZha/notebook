/*
 * @lc app=leetcode.cn id=191 lang=java
 *
 * [191] 位1的个数
 */

// @lc code=start
class Solution {
    // you need to treat n as an unsigned value
    // 右移计数
    // n&(n-1)消除1
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (n >>> i & 1);

        }
        return count;
    }
}
// @lc code=end
