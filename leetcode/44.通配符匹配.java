/*
 * @lc app=leetcode.cn id=44 lang=java
 *
 * [44] 通配符匹配
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] book = new boolean[s.length() + 1][p.length() + 1];
        book[0][0] = false;
        for (int i = 1; i <= p.length(); i++)
            book[i][0] = p.charAt(i - 1) == '*' && book[i - 1][0];
        for (int i = 1; i <= p.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (p.charAt(i-1) == '*') {
                    book[i][j] = book[i - 1][j] || book[i][j - 1];
                }
                if (s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1) == '?') {
                    book[i][j] = book[i - 1][j - 1];
                }
            }
        }
        return book[s.length()][p.length()];
    }
}
// @lc code=end
