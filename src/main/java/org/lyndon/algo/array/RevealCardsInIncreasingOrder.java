package org.lyndon.algo.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LC950
 */
public class RevealCardsInIncreasingOrder {

    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            deque.addLast(i);
        }

        Arrays.sort(deck);
        int[] ans = new int[n];
        for (int card : deck) {
            ans[deque.pollFirst()] = card;
            if (!deque.isEmpty()) {
                deque.addLast(deque.pollFirst());
            }
        }
        return ans;
    }

}
