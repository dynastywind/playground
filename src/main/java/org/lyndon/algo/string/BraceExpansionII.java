package org.lyndon.algo.string;

import java.util.*;

/**
 * LC1096
 * expr   := term (',' term)*
 * term   := factor factor*
 * factor := letter | '{' expr '}'
 */
public class BraceExpansionII {

    private String s;
    private int i;

    public List<String> braceExpansionII(String expression) {
        this.s = expression;
        this.i = 0;
        return new ArrayList<>(new TreeSet<>(parseExpr()));
    }

    private Set<String> parseExpr() {
        Set<String> res = new HashSet<>(parseTerm());
        while (i < s.length() && s.charAt(i) == ',') {
            i++;
            res.addAll(parseTerm());
        }
        return res;
    }

    private Set<String> parseTerm() {
        Set<String> res = new HashSet<>();
        res.add("");
        while (i < s.length() && (s.charAt(i) == '{' || Character.isLetter(s.charAt(i)))) {
            Set<String> f = parseFactor();
            Set<String> next = new HashSet<>();
            for (String a : res) {
                for (String b : f) {
                    next.add(a + b);
                }
            }
            res = next;
        }
        return res;
    }

    private Set<String> parseFactor() {
        if (s.charAt(i) == '{') {
            // skip '{'
            i++;
            Set<String> r = parseExpr();
            // skip '}'
            i++;
            return r;
        }
        StringBuilder sb = new StringBuilder();
        while (i < s.length() && Character.isLetter(s.charAt(i))) {
            sb.append(s.charAt(i++));
        }
        Set<String> r = new HashSet<>();
        r.add(sb.toString());
        return r;
    }

}
