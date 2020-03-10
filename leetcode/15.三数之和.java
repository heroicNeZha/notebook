
/*
 * @lc app=leetcode.cn id=15 lang=java
 *
 * [15] 三数之和
 */

// @lc code=start
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        if(n==0) return ans;
        Arrays.sort(nums);
        if(nums[0]>0||nums[n-1]<0) return ans;
        for(int i =0;i<n-2;i++){
            if (nums[i] > 0) break;
            if(i!=0&&nums[i] ==nums[i-1]){
               continue; 
            }
            int start = i+1;
            int end = n -1;
            while(start<end){
                if(start!=i+1&&nums[start] ==nums[start-1]){
                    start++;
                    continue; 
                 }
                if(nums[start]+nums[end] == -nums[i]){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[start]);
                    list.add(nums[end]);
                    ans.add(list);
                    start ++;
                }
                else if(nums[start]+nums[end]<-nums[i]){
                    start++;
                }else{
                    end --;
                }
            }
        }
        return ans;
    }
}
// @lc code=end

