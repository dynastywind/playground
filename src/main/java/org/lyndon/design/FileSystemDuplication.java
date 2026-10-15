package org.lyndon.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * LC609
 */
public class FileSystemDuplication {

    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            process(path, map);
        }
        return map.values().stream().filter(l -> l.size() > 1).collect(Collectors.toList());
    }

    private void process(String path, Map<String, List<String>> map) {
        String[] parts = path.split(" ");
        String dir = parts[0];
        for (int i = 1; i < parts.length; i++) {
            String desc = parts[i];
            int index = desc.indexOf("(");
            String file = desc.substring(0, index);
            String content = desc.substring(index, desc.length() - 1);
            map.computeIfAbsent(content, k -> new ArrayList<>()).add(dir + "/" + file);
        }
    }

}
