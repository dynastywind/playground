package org.lyndon.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LC3481
 */
public class ApplySubs {

    private static final Pattern varPattern = Pattern.compile("%([^%]+)%");

    public String applySubstitutions(List<List<String>> replacements, String text) {
        Map<String, String> dict = new HashMap<>();
        for (List<String> replacement : replacements) {
            dict.put(replacement.get(0), replacement.get(1));
        }
        for (String key : dict.keySet()) {
            dict.put(key, parse(dict.get(key), dict));
        }
        Matcher matcher = varPattern.matcher(text);
        while (matcher.find()) {
            String var = matcher.group(1);
            text = text.replaceAll("%" + var + "%", dict.get(var));
        }
        return text;
    }

    private String parse(String value, Map<String, String> dict) {
        Matcher matcher = varPattern.matcher(value);
        while (matcher.find()) {
            String var = matcher.group(1);
            value = value.replaceAll("%" + var + "%", parse(dict.get(var), dict));
        }
        return value;
    }

    public static void main(String[] args) {
        var solution = new ApplySubs();
        List<List<String>> replacements = new ArrayList<>();
        replacements.add(List.of("A", "abc"));
        replacements.add(List.of("B", "def"));
        System.out.println(solution.applySubstitutions(replacements, "%A%_%B%"));
    }

}
