package org.lyndon.design.ip;

import org.lyndon.design.IpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LinearMatcher {
    record Rule(long base, int prefix, String action) {
    }

    private final List<Rule> rules = new ArrayList<>();

    public void add(String cidr, String action) {
        String[] parts = cidr.split("/");
        int prefix = Integer.parseInt(parts[1]);
        long base = IpUtils.ipToLong(parts[0]) & IpUtils.mask(prefix);
        rules.add(new Rule(base, prefix, action));
    }

    public Optional<String> match(String ip) {
        long v = IpUtils.ipToLong(ip);
        Rule ans = null;
        for (Rule rule : rules) {
            if (IpUtils.contains(rule.base, rule.prefix, v)) {
                if (ans == null) {
                    ans = rule;
                } else if (rule.prefix > ans.prefix) {
                    ans = rule;
                }
            }
        }
        return Optional.ofNullable(ans).map(r -> r.action);
    }
}
