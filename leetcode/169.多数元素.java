/*
 * @lc app=leetcode.cn id=169 lang=java
 *
 * [169] 多数元素
 */

// @lc code=start
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int major = nums[0];
        for (int num : nums) {
            if (count == 0) {
                major = num;
            }
            if (num == major) {
                count++;
            } else {
                count--;
            }
        }
        return major;
    }
}
// @lc code=end
