package org.lyndon.algo.dp;

/**
 * LC714
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * <p>
 * Note:
 * <p>
 * You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * The transaction fee is only charged once for each stock purchase and sale.
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Example 2:
 * <p>
 * Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 */
public class TradeStockWithFee {

    /*
        hold[i] = max(sell[i - 1] - price[i], hold[i - 1])
        sell[i] = max(hold[i - 1] + price[i] - fee, sell[i - 1])
     */
    public int maxProfit(int[] prices, int fee) {
        int hold = -prices[0];
        int sell = 0;
        for (int i = 1; i < prices.length; i++) {
            int oHold = hold;
            int oSell = sell;
            hold = Math.max(oSell - prices[i], oHold);
            sell = Math.max(oHold + prices[i] - fee, oSell);
        }
        return sell;
    }

}
