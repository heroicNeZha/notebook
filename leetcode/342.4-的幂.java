/*
 * @lc app=leetcode.cn id=342 lang=java
 *
 * [342] 4的幂
 */

// @lc code=start
class Solution {
    public boolean isPowerOfFour(int num) {
        if (num == 1)
            return true;
        if (num < 4)
            return false;
        // 位中只有一个1,并且在奇数位;
        if ((num & (num - 1)) != 0)
            return false;
        return (num & 0x55555555) != num;
    }
}
// @lc code=end
