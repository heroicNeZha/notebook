/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] 移动零
 */

// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
        int count = 0;
        for (int i : nums) {
            if (i == 0)
                count++;
        }
        int zp = 0, nzp = 0;
        while (nzp < count) {
            while (nums[nzp] == 0) {
                nzp++;
            }
            while (nums[zp] != 0) {
                zp++;
            }
        }
    }
}
// @lc code=end
