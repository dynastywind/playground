package org.lyndon.algo.tree;

import java.util.*;

/**
 * LC635
 * You are given several logs, where each log contains a unique ID and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
 * <p>
 * Implement the LogSystem class:
 * <p>
 * LogSystem() Initializes the LogSystem object.
 * void put(int id, string timestamp) Stores the given log (id, timestamp) in your storage system.
 * int[] retrieve(string start, string end, string granularity) Returns the IDs of the logs whose timestamps are within the range from start to end inclusive. start and end all have the same format as timestamp, and granularity means how precise the range should be (i.e. to the exact Day, Minute, etc.). For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", and granularity = "Day" means that we need to find the logs within the inclusive range from Jan. 1st 2017 to Jan. 2nd 2017, and the Hour, Minute, and Second for each log entry can be ignored.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["LogSystem", "put", "put", "put", "retrieve", "retrieve"]
 * [[], [1, "2017:01:01:23:59:59"], [2, "2017:01:01:22:59:59"], [3, "2016:01:01:00:00:00"], ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"], ["2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"]]
 * Output
 * [null, null, null, null, [3, 2, 1], [2, 1]]
 * <p>
 * Explanation
 * LogSystem logSystem = new LogSystem();
 * logSystem.put(1, "2017:01:01:23:59:59");
 * logSystem.put(2, "2017:01:01:22:59:59");
 * logSystem.put(3, "2016:01:01:00:00:00");
 * <p>
 * // return [3,2,1], because you need to return all logs between 2016 and 2017.
 * logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year");
 * <p>
 * // return [2,1], because you need to return all logs between Jan. 1, 2016 01:XX:XX and Jan. 1, 2017 23:XX:XX.
 * // Log 3 is not returned because Jan. 1, 2016 00:00:00 comes before the start of the range.
 * logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour");
 */
public class LogSystem {

    private static final String MIN = "0000:00:00:00:00:00";
    private static final String MAX = "9999:99:99:99:99:99";

    private final TreeMap<String, List<Integer>> map;
    private final Map<String, Integer> granularityMap;

    public LogSystem() {
        this.map = new TreeMap<>();
        this.granularityMap = new HashMap<>();
        granularityMap.put("Year", 4);
        granularityMap.put("Month", 7);
        granularityMap.put("Day", 10);
        granularityMap.put("Hour", 13);
        granularityMap.put("Minute", 10);
        granularityMap.put("Second", 19);
    }

    public void put(int id, String timestamp) {
        map.computeIfAbsent(timestamp, k -> new ArrayList<>()).add(id);
    }

    public List<Integer> retrieve(String start, String end, String granularity) {
        int len = granularityMap.get(granularity);
        String lo = start.substring(0, len) + MIN.substring(len);
        String hi = end.substring(0, len) + MAX.substring(len);

        List<Integer> result = new ArrayList<>();
        for (List<Integer> l : map.subMap(lo, true, hi, true).values()) {
            result.addAll(l);
        }
        return result;
    }

}
