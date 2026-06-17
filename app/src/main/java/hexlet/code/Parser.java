package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public final class Parser {
    private Parser() {
    }

    public static Map<String, Object> parse(String filePath) throws Exception {
        String content = Files.readString(Path.of(filePath));
        ObjectMapper mapper = getMapper(filePath);

        return mapper.readValue(content, new TypeReference<>() {
        });
    }

    private static ObjectMapper getMapper(String filePath) {
        if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return new ObjectMapper(new YAMLFactory());
        }

        return new ObjectMapper();
    }
}
