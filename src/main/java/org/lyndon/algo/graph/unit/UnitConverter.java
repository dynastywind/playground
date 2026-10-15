package org.lyndon.algo.graph.unit;

import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class UnitConverter {

    /*
    m <-> dm <-> cm
     */
    private static class Node {
        int index;
        String unit;
        Edge prev, next;

        Node(String unit) {
            this.unit = unit;
        }
    }

    @AllArgsConstructor
    private static class Edge {
        Node target;
        double weight;
    }

    private static Map<String, Map<String, Double>> parseFacts(String facts) {
        var lines = facts.split("\\n");
        Map<String, Map<String, Double>> result = new HashMap<>();
        Arrays.stream(lines).forEach(line -> {
            var parts = line.split("=");
            String sourceUnit = parts[0].trim();
            var pair = parts[1].trim().split(" ");
            double value = Double.parseDouble(pair[0].trim());
            String targetUnit = pair[1].trim();
            result.computeIfAbsent(sourceUnit, k -> new HashMap<>());
            result.get(sourceUnit).put(targetUnit, value);
            result.computeIfAbsent(targetUnit, k -> new HashMap<>());
            result.get(targetUnit).put(sourceUnit, 1 / value);
        });
        return result;
    }

    private static Map<String, Node> parseFactsToNodes(String facts) {
        Map<String, Node> result = new HashMap<>();
        Arrays.stream(facts.split("\\n")).forEach(line -> {
            var parts = line.split("=");
            String sourceUnit = parts[0].trim();
            var pair = parts[1].trim().split(" ");
            double value = Double.parseDouble(pair[0].trim());
            String targetUnit = pair[1].trim();
            result.computeIfAbsent(sourceUnit, k -> new Node(sourceUnit));
            result.computeIfAbsent(targetUnit, k -> new Node(targetUnit));
            if (value > 1) {
                result.get(sourceUnit).next = new Edge(result.get(targetUnit), value);
                result.get(targetUnit).prev = new Edge(result.get(sourceUnit), 1 / value);
            } else {
                result.get(sourceUnit).prev = new Edge(result.get(targetUnit), value);
                result.get(targetUnit).next = new Edge(result.get(sourceUnit), 1 / value);
            }
        });
        for (String key : result.keySet()) {
            var cur = result.get(key);
            if (cur.prev != null) {
                continue;
            }
            int index = 0;
            while (cur.next != null) {
                cur.index = index++;
                cur = cur.next.target;
            }
            cur.index = index;
        }
        return result;
    }

    /*
    m -> dm
    dm -> m

    dm -> m/cm
    cm -> dm

    cm -> dm/mm
    mm -> cm
     */

    private static List<String> answer(String queries, Map<String, Map<String, Double>> facts) {
        var lines = queries.split("\\n");
        return Arrays.stream(lines).map(line -> {
            var pair = line.split("=");
            var sourceParts = pair[0].trim().split(" ");
            var targetParts = pair[1].trim().split(" ");
            Double answer = answer(Double.parseDouble(sourceParts[0]), sourceParts[1].trim(), targetParts[1].trim(), facts, new HashSet<>());
            if (answer != null) {
                return String.format("%s = %s %s", pair[0].trim(), answer, targetParts[1].trim());
            }
            return "not convertible";
        }).collect(Collectors.toList());
    }

    private static Double answer(double quantity, String sourceUnit, String targetUnit, Map<String, Map<String, Double>> facts, Set<String> visited) {
        Double result = null;
        if (facts.containsKey(sourceUnit)) {
            var node = facts.get(sourceUnit);
            for (String neighbor : node.keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    if (neighbor.equals(targetUnit)) {
                        return quantity * node.get(neighbor);
                    }
                    result = answer(quantity * node.get(neighbor), neighbor, targetUnit, facts, visited);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return result;
    }

    private static List<String> answerBFS(String queries, Map<String, Map<String, Double>> facts) {
        var lines = queries.split("\\n");
        return Arrays.stream(lines).map(line -> {
            var pair = line.split("=");
            var sourceParts = pair[0].trim().split(" ");
            var targetParts = pair[1].trim().split(" ");
            Double answer = answerBFS(Double.parseDouble(sourceParts[0]), sourceParts[1].trim(), targetParts[1].trim(), facts);
            if (answer != null) {
                return String.format("%s = %s %s", pair[0].trim(), answer, targetParts[1].trim());
            }
            return "not convertible";
        }).collect(Collectors.toList());
    }

    private static Double answerBFS(double quantity, String sourceUnit, String targetUnit, Map<String, Map<String, Double>> facts) {
        Map<String, Double> visited = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(sourceUnit);
        visited.put(sourceUnit, 1.0);
        while (!queue.isEmpty()) {
            String key = queue.poll();
            if (!facts.containsKey(key)) {
                return null;
            }
            double factor = visited.get(key);
            if (key.equals(targetUnit)) {
                return quantity * factor;
            }
            for (String neighbor : facts.get(key).keySet()) {
                if (visited.containsKey(neighbor)) {
                    continue;
                }
                double newFactor = factor * facts.get(key).get(neighbor);
                visited.put(neighbor, newFactor);
                queue.add(neighbor);
            }
        }
        return null;
    }

    private static List<String> answerFromNodes(String queries, Map<String, Node> facts) {
        var lines = queries.split("\\n");
        return Arrays.stream(lines).map(line -> {
            var pair = line.split("=");
            var sourceParts = pair[0].trim().split(" ");
            var targetParts = pair[1].trim().split(" ");
            Double answer = answer(Double.parseDouble(sourceParts[0]), sourceParts[1].trim(), targetParts[1].trim(), facts);
            if (answer != null) {
                return String.format("%s = %s %s", pair[0].trim(), answer, targetParts[1].trim());
            }
            return "not convertible";
        }).collect(Collectors.toList());
    }

    private static Double answer(double quantity, String sourceUnit, String targetUnit, Map<String, Node> facts) {
        if (!facts.containsKey(sourceUnit) || !facts.containsKey(targetUnit)) {
            return null;
        }
        var source = facts.get(sourceUnit);
        var target = facts.get(targetUnit);
        if (sourceUnit.equals(targetUnit)) {
            return quantity;
        }

        String key = sourceUnit;
        double result = quantity;
        int direction = source.index < target.index ? 1 : -1;
        while (facts.containsKey(key)) {
            source = facts.get(key);
            if (direction > 0) {
                if (source.next == null) {
                    return null;
                }
                String nextUnit = source.next.target.unit;
                result *= source.next.weight;
                if (nextUnit.equals(targetUnit)) {
                    return result;
                }
                key = source.next.target.unit;
            } else {
                if (source.prev == null) {
                    return null;
                }
                String prevUnit = source.prev.target.unit;
                result *= source.prev.weight;
                if (prevUnit.equals(targetUnit)) {
                    return result;
                }
                key = source.prev.target.unit;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String facts = IOUtils.toString(Objects.requireNonNull(UnitConverter.class.getResourceAsStream("/algo/graph/unit/facts.txt")), Charset.defaultCharset());
        var map = parseFacts(facts);
        String queries = IOUtils.toString(Objects.requireNonNull(UnitConverter.class.getResourceAsStream("/algo/graph/unit/queries.txt")), Charset.defaultCharset());
        var answers = answer(queries, map);
        answers.forEach(System.out::println);
        System.out.println();
        answers = answerBFS(queries, map);
        answers.forEach(System.out::println);
        System.out.println();
        var a = parseFactsToNodes(facts);
        answers = answerFromNodes(queries, a);
        answers.forEach(System.out::println);
    }

}
