package org.lyndon.design.fs;

import lombok.Getter;

public class FsException extends RuntimeException {

    @Getter
    private final FsErrorCode code;

    public FsException(FsErrorCode code, String message) {
        super(message);
        this.code = code;
    }

}
