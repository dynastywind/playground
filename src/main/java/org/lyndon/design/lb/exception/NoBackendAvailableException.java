package org.lyndon.design.lb.exception;

public class NoBackendAvailableException extends RuntimeException {

    public NoBackendAvailableException() {
        super("no backend available");
    }

}
