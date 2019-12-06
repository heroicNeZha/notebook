/*
 * @lc app=leetcode.cn id=14 lang=java
 *
 * [14] 最长公共前缀
 */

// @lc code=start
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1)
            return strs[0];
        int length = strs[0].length();
        for (String string : strs) {
            if (length > string.length())
                length = string.length();
        }
        int i ;
        for ( i = 0; i < length; i++) {
            for (int j = 0; j < strs.length - 1; j++) {
                if (strs[j].charAt(i) != strs[j + 1].charAt(i)) {
                    return strs[0].subSequence(0, i).toString();
                }
            }
        }
        return strs[0].subSequence(0, i).toString();
    }
}
// @lc code=end
