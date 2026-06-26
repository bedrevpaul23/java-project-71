package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public final class Differ {
    private Differ() {
    }

    public static String generate(String firstFilePath, String secondFilePath) throws Exception {
        return generate(firstFilePath, secondFilePath, "stylish");
    }

    public static String generate(String firstFilePath, String secondFilePath, String format) throws Exception {
        String firstData = Files.readString(Path.of(firstFilePath));
        String secondData = Files.readString(Path.of(secondFilePath));

        Map<String, Object> firstParsedData = Parser.parse(firstData, getFileExtension(firstFilePath));
        Map<String, Object> secondParsedData = Parser.parse(secondData, getFileExtension(secondFilePath));

        List<Map<String, Object>> diff = DiffBuilder.build(firstParsedData, secondParsedData);

        return Formatter.format(diff, format);
    }

    private static String getFileExtension(String filePath) {
        return filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
    }
}
