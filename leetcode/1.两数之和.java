
/*
 * @lc app=leetcode.cn id=1 lang=java
 *
 * [1] 两数之和
 */

// @lc code=start
import java.util.Hashtable;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (table.get(nums[i]) == null) {
                table.put(target - nums[i], i);
            } else {
                int[] result = new int[2];
                result[0] = table.get(nums[i]);
                result[1] = i;
                return result;
            }
        }
        return null;
    }
}
// @lc code=end
