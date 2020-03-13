/*
 * @lc app=leetcode.cn id=29 lang=java
 *
 * [29] 两数相除
 */

// @lc code=start
class Solution {
    public int divide(int dividend, int divisor) {
        boolean pos = true;
        int ans = 0;
        // if(dividend == Integer.MIN_VALUE && divisor == -1){
        // return Integer.MAX_VALUE;
        // }
        if (dividend > 0) {
            dividend = -dividend;
            pos = !pos;
        }
        if (divisor > 0) {
            divisor = -divisor;
            pos = !pos;
        }
        while (dividend <= divisor) {
            int b = divisor;
            int div = -1;
            while (dividend <= (b << 1)) {
                if (b <= (Integer.MIN_VALUE >> 1))
                    break;
                b = b << 1;
                div = div << 1;
            }
            dividend -= b;
            ans += div;
        }
        if (pos) {
            if (ans <= Integer.MIN_VALUE)
                return Integer.MAX_VALUE;
            ans = -ans;
        }
        return ans;
    }
}
// @lc code=end
