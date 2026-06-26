package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Formatter {
    private Formatter() {
    }

    public static String format(List<Map<String, Object>> diff, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.format(diff);
            case "plain" -> Plain.format(diff);
            case "json" -> Json.format(diff);
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }
}
