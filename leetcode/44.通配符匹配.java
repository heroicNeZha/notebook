/*
 * @lc app=leetcode.cn id=44 lang=java
 *
 * [44] 通配符匹配
 */

// @lc code=start
class Solution {
    Boolean[][] book;

    public boolean isMatch(String s, String p) {
        book = new Boolean[p.length() + 1][s.length() + 1];
        book[0][0] = true;
        return isMatch(s, p, 0, 0);
    }

    public boolean isMatch(String s, String p, int i, int j) {
        if (book[i][j] != null) {
            return book[i][j];
        }
        if (p.length() == i) {
            book[i][j] = s.length() == j;
            return book[i][j];
        }
        if (p.charAt(0) == '*') {
            if (p.length() == 1) {
                book[p.length()][s.length()] = true;
                return book[p.length()][s.length()];
            }
            if (p.length() > 1 && p.charAt(1) == '*') {
                book[p.length()][s.length()] = isMatch(s, p.substring(1));
                return book[p.length()][s.length()];
            }
            for (int i = s.length() - 1; i >= 0; i--) {
                if (p.charAt(1) != s.charAt(i) && p.charAt(1) != '?')
                    continue;
                if (isMatch(s.substring(i), p.substring(1))) {
                    book[p.length()][s.length()] = true;
                    return book[p.length()][s.length()];
                }
            }
            return false;
        }
        if (s.length() == 0) {
            book[p.length()][s.length()] = false;
            return book[p.length()][s.length()];
        }
        if (p.charAt(0) == s.charAt(0) || p.charAt(0) == '?') {
            book[p.length()][s.length()] = isMatch(s.substring(1), p.substring(1));
            return book[p.length()][s.length()];
        }
        return false;
    }
}
// @lc code=end
