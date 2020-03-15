/*
 * @lc app=leetcode.cn id=33 lang=java
 *
 * [33] 搜索旋转排序数组
 */

// @lc code=start
class Solution {
    public int search(int[] nums, int target) {
        return helper(nums, 0, nums.length - 1, target);
    }

    public int helper(int[] nums, int start, int end, int target) {
        int ans = -1;
        int mid = start + end / 2;
        if (start == end) {
            return nums[start] == target ? start : -1;
        }

        if (nums[0] < nums[mid]) {
            if (target < nums[mid]) {
                ans = helper(nums, start, mid - 1, target);
            }
        }

        if (target < nums[mid] && target >= nums[start]) {
            ans = helper(nums, start, mid - 1, target);
        } else if (target > nums[mid] || target < nums[0]) {
            ans = helper(nums, mid + 1, end, target);
        }
        return ans;
    }
}
// @lc code=end
