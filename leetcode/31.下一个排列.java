import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=31 lang=java
 *
 * [31] 下一个排列
 */

// @lc code=start
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length - 1;
        for (int i = n; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                int j = nums.length - 1;
                while (j > 0 && nums[j] <= nums[i]) {
                    j--;
                }
                int temp = nums[j];
                nums[j] = nums[i - 1];
                nums[i - 1] = temp;
                Arrays.sort(nums, i, nums.length);
                return;
            }
        }
        for (int i = 0; i < n; i++, n--) {
            int temp = nums[i];
            nums[i] = nums[n];
            nums[n] = temp;
        }
    }
}
// @lc code=end
