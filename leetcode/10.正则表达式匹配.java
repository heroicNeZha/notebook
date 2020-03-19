/*
 * @lc app=leetcode.cn id=10 lang=java
 *
 * [10] 正则表达式匹配
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < p.length() + 1; i++) {
            if (p.charAt(i-1) == '.') {
                for (int j = 1; j < s.length() + 1; j++) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            } else if (p.charAt(i-1) == '*') {
                dp[i][0] = dp[i - 2][0];
                for (int j = 1; j < s.length() + 1; j++) {
                    dp[i][j] = dp[i - 2][j] || (dp[i][j - 1] && (p.charAt(i - 2) == s.charAt(j-1)||p.charAt(i-2)=='.'));
                }
            } else {
                for (int j = 1; j < s.length() + 1; j++) {
                    dp[i][j] = dp[i - 1][j - 1] && p.charAt(i-1) == s.charAt(j-1);
                }
            }
        }
        return dp[p.length()][s.length()];
    }
}
// @lc code=end
