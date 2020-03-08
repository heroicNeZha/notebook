/*
 * @lc app=leetcode.cn id=64 lang=java
 *
 * [64] 最小路径和
 */

// @lc code=start
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for(int j = 0;j<n;j++){
                if(j==0&&i==0){
                    dp[0]=grid[0][0];
                }else if(i==0){
                    dp[j] = dp[j-1]+grid[i][j];
                }else if(j==0){
                    dp[j] =dp[j]+ grid[i][j];
                }else{
                    dp[j] = Math.min(dp[j-1],dp[j])+grid[i][j];
                }
            }
        }
        return dp[n-1];
    }
}
// @lc code=end

