
/*
 * @lc app=leetcode.cn id=560 lang=java
 *
 * [560] 和为K的子数组
 */

// @lc code=start
import java.util.Hashtable;

class Solution {
    public int subarraySum(int[] nums, int k) {
        Hashtable<Integer, Integer> table = new Hashtable<>();
        int count = 0;
        int sum = 0;
        table.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            count += table.getOrDefault(sum - k, 0);
            table.put(sum, table.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
// @lc code=end
