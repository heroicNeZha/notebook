/*
 * @lc app=leetcode.cn id=349 lang=java
 *
 * [349] 两个数组的交集
 */

// @lc code=start
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        int[] result = new int[Math.max(nums1.length, nums2.length)];
        for (int i : nums1) {
            set.add(i);
        }
        int index = 0;
        for (int i : nums2) {
            if (set.contains(i)) {
                set.remove(i);
                result[index++] = i;
            }
        }
        return Arrays.copyOf(result, index);
    }
}
// @lc code=end
