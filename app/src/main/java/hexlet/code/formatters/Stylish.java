package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public final class Stylish {
    private Stylish() {
    }

    public static String format(List<Map<String, Object>> diff) {
        StringJoiner result = new StringJoiner("\n", "{\n", "\n}");

        for (Map<String, Object> node : diff) {
            String status = (String) node.get("status");
            String key = (String) node.get("key");

            if ("added".equals(status)) {
                result.add("  + " + key + ": " + stringify(node.get("value")));
            } else if ("removed".equals(status)) {
                result.add("  - " + key + ": " + stringify(node.get("value")));
            } else if ("unchanged".equals(status)) {
                result.add("    " + key + ": " + stringify(node.get("value")));
            } else if ("changed".equals(status)) {
                result.add("  - " + key + ": " + stringify(node.get("oldValue")));
                result.add("  + " + key + ": " + stringify(node.get("newValue")));
            }
        }

        return result.toString();
    }

    private static String stringify(Object value) {
        return String.valueOf(value);
    }
}
