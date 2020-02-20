/*
 * @lc app=leetcode.cn id=136 lang=java
 *
 * [136] 只出现一次的数字
 */

// @lc code=start
class Solution {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int i : nums) {
            single = single ^ i;
        }
        return single;
    }
}
// @lc code=end
