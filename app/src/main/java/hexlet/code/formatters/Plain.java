package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public final class Plain {
    private Plain() {
    }

    public static String format(List<Map<String, Object>> diff) {
        StringJoiner result = new StringJoiner("\n");

        for (Map<String, Object> node : diff) {
            String status = (String) node.get("status");
            String key = (String) node.get("key");

            if ("added".equals(status)) {
                result.add("Property '" + key + "' was added with value: " + stringify(node.get("value")));
            } else if ("removed".equals(status)) {
                result.add("Property '" + key + "' was removed");
            } else if ("changed".equals(status)) {
                result.add("Property '" + key + "' was updated. From "
                        + stringify(node.get("oldValue")) + " to " + stringify(node.get("newValue")));
            }
        }

        return result.toString();
    }

    private static String stringify(Object value) {
        if (value instanceof Map<?, ?> || value instanceof List<?>) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return String.valueOf(value);
    }
}
