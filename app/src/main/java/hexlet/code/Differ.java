package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.util.List;
import java.util.Map;

public final class Differ {
    private Differ() {
    }

    public static String generate(String firstFilePath, String secondFilePath) throws Exception {
        return generate(firstFilePath, secondFilePath, "stylish");
    }

    public static String generate(String firstFilePath, String secondFilePath, String format) throws Exception {
        Map<String, Object> firstData = Parser.parse(firstFilePath);
        Map<String, Object> secondData = Parser.parse(secondFilePath);
        List<Map<String, Object>> diff = DiffBuilder.build(firstData, secondData);

        return Formatter.format(diff, format);
    }
}
