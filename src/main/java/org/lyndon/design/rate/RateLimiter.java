package org.lyndon.design.rate;

public interface RateLimiter {

    boolean tryAcquire();

}
