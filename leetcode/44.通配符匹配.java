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
        if (book[i + 1][j + 1] != null) {
            return book[i + 1][j + 1];
        }
        // 判断最后一个
        if (p.length() == i + 1) {
            book[i + 1][j + 1] = s.length() == j + 1;
            return book[i][j];
        }
        if (p.charAt(i) == '*') {
            if (p.charAt(i + 1) == '*') {
                book[p.length()][s.length()] = isMatch(s, p.substring(1));
                return book[p.length()][s.length()];
            }
            for (int x = s.length() - 1; x >= 0; x--) {
                if (p.charAt(1) != s.charAt(x) && p.charAt(1) != '?')
                    continue;
                if (isMatch(s.substring(x), p.substring(1))) {
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
        if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(0) == '?') {
            book[p.length()][s.length()] = isMatch(s.substring(1), p.substring(1));
            return book[p.length()][s.length()];
        }
        return false;
    }
}
// @lc code=end
