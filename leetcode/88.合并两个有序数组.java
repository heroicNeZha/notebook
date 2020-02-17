/*
 * @lc app=leetcode.cn id=88 lang=java
 *
 * [88] 合并两个有序数组
 */

// @lc code=start
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m + n - 1; i >= 0; i--) {
            if (m > 0 && n > 0) {
                if (nums1[m - 1] >= nums2[n - 1]) {
                    nums1[i] = nums1[--m];
                    continue;
                } else {
                    nums1[i] = nums2[--n];
                    continue;
                }
            }
            if (m > 0) {
                nums1[i] = nums1[--m];
            }
            if (n > 0) {
                nums1[i] = nums2[--n];
            }
        }
    }
}
// @lc code=end
