package org.lyndon.design;

import java.util.Arrays;

/**
 * LC933
 * You have a RecentCounter class which counts the number of recent requests within a certain time frame.
 * <p>
 * Implement the RecentCounter class:
 * <p>
 * RecentCounter() Initializes the counter with zero recent requests.
 * int ping(int t) Adds a new request at time t, where t represents some time in milliseconds, and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
 * It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * Output
 * [null, 1, 2, 3, 3]
 * <p>
 * Explanation
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
 * recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
 */
public class RecentCounter {

    private static final int SIZE = 3001;

    private final int[] times;

    public RecentCounter() {
        this.times = new int[SIZE];
        Arrays.fill(times, -1);
    }

    public int ping(int t) {
        int i = t % SIZE;
        times[i] = t;
        int cnt = 0;
        for (int time : times) {
            if (time > 0 && time >= t - SIZE) {
                cnt++;
            }
        }
        return cnt;
    }

}
