package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Formatter {
    private Formatter() {
    }

    public static String format(List<Map<String, Object>> diff, String format) throws Exception {
        if ("stylish".equals(format)) {
            return Stylish.format(diff);
        }

        if ("plain".equals(format)) {
            return Plain.format(diff);
        }

        if ("json".equals(format)) {
            return Json.format(diff);
        }

        throw new IllegalArgumentException("Unknown format: " + format);
    }
}
