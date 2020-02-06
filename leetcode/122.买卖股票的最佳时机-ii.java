/*
 * @lc app=leetcode.cn id=122 lang=java
 *
 * [122] 买卖股票的最佳时机 II
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int inPrice = -1;
        for (int i = 0; i < prices.length - 1; i++) {
            if (inPrice>=0) {
                if (i + 1 < prices.length && prices[i + 1] < prices[i]) {
                    profit = profit + prices[i] - inPrice;
                    inPrice = -1;
                } else {
                    continue;
                }
            } else {
                if (i + 1 < prices.length && prices[i + 1] > prices[i]) {
                    inPrice = prices[i];
                } else {
                    continue;
                }
            }
        }
        if(inPrice>=0){
            profit = profit + prices[prices.length-1] - inPrice;
        }
        return profit;
    }
}
// @lc code=end
