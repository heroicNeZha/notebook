/*
 * @lc app=leetcode.cn id=6 lang=java
 *
 * [6] Z 字形变换
 */

// @lc code=start
import java.util.*;

class Solution {
    public String convert(String s, int numRows) {
        List<StringBuilder> sbs = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            sbs.add(new StringBuilder());
        }

        int i = 0;
        while (i < s.length()) {
            for (int j = 0; j < numRows; j++) {
                if (i < s.length()) {
                    sbs.get(j).append(s.charAt(i));
                    i++;
                } else {
                    break;
                }
            }
            if (i >= s.length()) {
                break;
            }
            for (int j = numRows - 2; j > 0; j--) {
                if (i < s.length()) {
                    sbs.get(j).append(s.charAt(i));
                    i++;
                } else {
                    break;
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for (StringBuilder stringBuilder : sbs) {
            ans.append(stringBuilder.toString());
        }
        return ans.toString();
    }
}
// @lc code=end
