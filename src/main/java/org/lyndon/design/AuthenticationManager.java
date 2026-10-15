package org.lyndon.design;

import java.util.*;

/**
 * LC1797
 */
public class AuthenticationManager {

    private final LinkedHashMap<String, Integer> tokens;
    private final int ttl;

    public AuthenticationManager(int timeToLive) {
        this.tokens = new LinkedHashMap<>();
        this.ttl = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        expire(currentTime);
        tokens.put(tokenId, currentTime + ttl);
    }

    public void renew(String tokenId, int currentTime) {
        expire(currentTime);
        if (!tokens.containsKey(tokenId)) {
            return;
        }
        tokens.remove(tokenId);
        tokens.put(tokenId, currentTime + ttl);
    }

    public int countUnexpiredTokens(int currentTime) {
        expire(currentTime);
        return tokens.size();
    }

    private void expire(int time) {
        Iterator<Map.Entry<String, Integer>> iterator = tokens.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue() <= time) {
                iterator.remove();
            } else {
                break;
            }
        }
    }

}
