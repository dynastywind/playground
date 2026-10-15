package org.lyndon.algo.array;

/**
 * LC3652
 * You are given two integer arrays prices and strategy, where:
 * <p>
 * prices[i] is the price of a given stock on the ith day.
 * strategy[i] represents a trading action on the ith day, where:
 * -1 indicates buying one unit of the stock.
 * 0 indicates holding the stock.
 * 1 indicates selling one unit of the stock.
 * You are also given an even integer k, and may perform at most one modification to strategy. A modification consists of:
 * <p>
 * Selecting exactly k consecutive elements in strategy.
 * Set the first k / 2 elements to 0 (hold).
 * Set the last k / 2 elements to 1 (sell).
 * The profit is defined as the sum of strategy[i] * prices[i] across all days.
 * <p>
 * Return the maximum possible profit you can achieve.
 * <p>
 * Note: There are no constraints on budget or stock ownership, so all buy and sell operations are feasible regardless of past actions.
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [4,2,8], strategy = [-1,0,1], k = 2
 * <p>
 * Output: 10
 * <p>
 * Example 2:
 * <p>
 * Input: prices = [5,4,3], strategy = [1,1,0], k = 2
 * <p>
 * Output: 9
 */
public class BestTimeBuyStockWithStrategy {

    /*
        delta = new_profit[i..i+k-1] = prices[i+k/2..i+k-1]
        - profit[i..i+k-1]
     */
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long[] prePrice = new long[n + 1];
        long[] preProfit = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prePrice[i] = prePrice[i - 1] + prices[i - 1];
            preProfit[i] = preProfit[i - 1] + (long) prices[i - 1] * strategy[i - 1];
        }
        long best = 0;
        for (int i = 0; i + k <= n; i++) {
            long profit = preProfit[i + k] - preProfit[i];
            long newProfit = prePrice[i + k] - prePrice[i + k / 2];
            best = Math.max(best, newProfit - profit);
        }
        return preProfit[n] + best;
    }

}
