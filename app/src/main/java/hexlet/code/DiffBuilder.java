package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class DiffBuilder {
    private DiffBuilder() {
    }

    public static List<Map<String, Object>> build(Map<String, Object> firstData, Map<String, Object> secondData) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(firstData.keySet());
        keys.addAll(secondData.keySet());

        List<Map<String, Object>> diff = new ArrayList<>();

        for (String key : keys) {
            if (!firstData.containsKey(key)) {
                diff.add(buildNode("added", key, secondData.get(key)));
            } else if (!secondData.containsKey(key)) {
                diff.add(buildNode("removed", key, firstData.get(key)));
            } else if (Objects.equals(firstData.get(key), secondData.get(key))) {
                diff.add(buildNode("unchanged", key, firstData.get(key)));
            } else {
                diff.add(buildChangedNode(key, firstData.get(key), secondData.get(key)));
            }
        }

        return diff;
    }

    private static Map<String, Object> buildNode(String status, String key, Object value) {
        Map<String, Object> node = new LinkedHashMap<>();
        node.put("status", status);
        node.put("key", key);
        node.put("value", value);

        return node;
    }

    private static Map<String, Object> buildChangedNode(String key, Object oldValue, Object newValue) {
        Map<String, Object> node = new LinkedHashMap<>();
        node.put("status", "changed");
        node.put("key", key);
        node.put("oldValue", oldValue);
        node.put("newValue", newValue);

        return node;
    }
}
