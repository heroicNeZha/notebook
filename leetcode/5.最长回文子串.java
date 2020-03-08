import sun.security.util.Length;

/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 */

// @lc code=start
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int maxlen = 0;
        String ans = "";
        boolean[][] dp = new boolean[n][n];
        for (int len = 1; len <= n; len++) {// 长度
            for (int start = 0; start < n - len + 1; start++) {
                int end = start + len - 1;
                dp[start][end] = (len <= 2 || dp[start + 1][end - 1]) && s.charAt(start) == s.charAt(end);
                if (dp[start][end] && len > maxlen) {
                    maxlen = len;
                    ans = s.substring(start, end + 1);
                }
            }
        }
        return ans;
    }
}
// @lc code=end
