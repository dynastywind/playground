package org.lyndon.design.lb;

import java.util.Objects;

public record Backend(String id, int weight) {

    public Backend {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Backend id cannot be null or empty");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Backend weight cannot be negative");
        }
    }

    public Backend(String id) {
        this(id, 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Backend other)) {
            return false;
        }
        return other.id.equals(id);
    }

}
