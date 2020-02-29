import java.util.Hashtable;

/*
 * @lc app=leetcode.cn id=219 lang=java
 *
 * [219] 存在重复元素 II
 */

// @lc code=start
import java.util.Hashtable;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (table.get(nums[i]) != null && i - table.get(nums[i]) <= k) {
                return true;
            }
            table.put(nums[i], i);
        }
        return false;
    }
}
// @lc code=end
