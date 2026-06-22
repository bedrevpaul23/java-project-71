package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public final class Parser {
    private Parser() {
    }

    public static Map<String, Object> parse(String data, String dataFormat) throws Exception {
        ObjectMapper mapper = getMapper(dataFormat);

        return mapper.readValue(data, new TypeReference<>() {
        });
    }

    private static ObjectMapper getMapper(String dataFormat) {
        return switch (dataFormat) {
            case "json" -> new ObjectMapper();
            case "yml", "yaml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new IllegalArgumentException("Unsupported data format: " + dataFormat);
        };
    }
}
