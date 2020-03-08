/*
 * @lc app=leetcode.cn id=63 lang=java
 *
 * [63] 不同路径 II
 */

// @lc code=start
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid.length<1) return 0;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < obstacleGrid.length; i++) {
            for(int j = 0;j<n;j++){
                if(obstacleGrid[i][j]==1){
                    dp[j] = 0;
                }else if(i==0&&j==0){
                    dp[i] = 1;
                }else if(i==0){
                    dp[j] = dp[j-1];
                }else if(j!=0){
                    dp[j] = dp[j-1]+dp[j];
                }
               
            }
        }
        return dp[n-1];
    }
}
// @lc code=end
