package org.lyndon.design;

public enum IpUtils {
    ;

    public static long ipToLong(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid IP address: " + ip);
        }

        long res = 0;
        for (String part : parts) {
            int b = Integer.parseInt(part);
            if (b < 0 || b > 255) {
                throw new IllegalArgumentException("Invalid IP address: " + ip);
            }
            res = (res << 8) | b;
        }
        return res;
    }

    public static long mask(int prefix) {
        if (prefix < 0 || prefix > 32) {
            throw new IllegalArgumentException("Invalid prefix: " + prefix);
        }
        return prefix == 0 ? 0L : 0xFFFFFFFL << (32 - prefix) & 0xFFFFFFFL;
    }

    public static boolean contains(long base, int prefix, long ip) {
        long mask = mask(prefix);
        return (base & mask) == (ip & mask);
    }
}
