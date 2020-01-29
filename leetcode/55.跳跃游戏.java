/*
 * @lc app=leetcode.cn id=55 lang=java
 *
 * [55] 跳跃游戏
 */

// @lc code=start
class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length < 2)
            return true;
        int[] toggets = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            toggets[i] = nums[i] + i;
        }
        quicksort(toggets, 0, toggets.length - 1);
        int max = toggets[0],i=0;
        while(i<=max){
            if(max<toggets[i]){
                max=toggets[i];
            }
        }
        if(i>nums.length-1)
            return true;
        return false;
    }

    public int division(int[] nums, int left, int right) {
        int flag = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= flag) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[right] > flag) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = flag;
        return left;
    }

    public void quicksort(int[] nums, int start, int end) {
        if (start < end) {
            int base = division(nums, start, end);
            quicksort(nums, start, base - 1);
            quicksort(nums, base + 1, end);
        }
    }
}
// @lc code=end
