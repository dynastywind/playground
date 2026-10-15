package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC881
 */
public class BoatsToSavePeople {

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int ans = 0;
        int i = 0;
        int j = people.length - 1;
        while (i <= j) {
            if (i < j && people[j] + people[i] <= limit) {
                i++;
            }
            j--;
            ans++;
        }
        return ans;
    }

}
