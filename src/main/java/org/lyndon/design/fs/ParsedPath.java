package org.lyndon.design.fs;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.lyndon.design.fs.Constants.SEP;

public record ParsedPath(boolean absolute, List<String> parts) {

    private static final int NAME_MAX = 255, PATH_MAX = 4096;

    static ParsedPath split(String path) {
        if (path == null || path.isEmpty()) {
            throw new FsException(FsErrorCode.ENOENT, "empty path");
        }
        if (path.getBytes(StandardCharsets.UTF_8).length > PATH_MAX) {
            throw new FsException(FsErrorCode.ENAMETOOLONG, "path too long");
        }

        boolean absolute = path.startsWith(SEP);
        List<String> parts = new ArrayList<>();
        for (String part : path.split(SEP)) {
            if (part.isEmpty() || ".".equals(part)) {
                continue;
            }
            if (!"..".equals(part)) {
                if (part.getBytes(StandardCharsets.UTF_8).length > NAME_MAX) {
                    throw new FsException(FsErrorCode.ENAMETOOLONG, "name too long");
                }
                if (part.indexOf('\0') >= 0) {
                    throw new FsException(FsErrorCode.EINVAL, "NUL in name");
                }
            }
            parts.add(part);
        }
        return new ParsedPath(absolute, parts);
    }

}
