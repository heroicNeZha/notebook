/*
 * @lc app=leetcode.cn id=33 lang=java
 *
 * [33] 搜索旋转排序数组
 */

// @lc code=start
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        return helper(nums, 0, nums.length - 1, target);
    }

    public int helper(int[] nums, int start, int end, int target) {
        int ans = -1;
        int mid = (start + end) / 2;
        if (start == end) {
            return nums[start] == target ? start : -1;
        }
        if (start > end)
            return -1;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[0] <= nums[mid]) {//左边升序
            if (target < nums[mid] && target >= nums[0]) {
                ans = helper(nums, start, mid - 1, target);
            } else {
                ans = helper(nums, mid + 1, end, target);
            }
        } else {//右边升序
            if (target > nums[mid] && target < nums[0]) {
                ans = helper(nums, mid + 1, end, target);
            } else {
                ans = helper(nums, start, mid - 1, target);
            }
        }
        return ans;
    }
}
// @lc code=end
