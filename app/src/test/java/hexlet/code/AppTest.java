package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private static final String EXPECTED = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

    @Test
    void testGenerateJson() throws Exception {
        String result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json"
        );

        assertEquals(EXPECTED, result);
    }

    @Test
    void testGenerateYaml() throws Exception {
        String result = Differ.generate(
                "src/test/resources/file1.yml",
                "src/test/resources/file2.yml"
        );

        assertEquals(EXPECTED, result);
    }

    @Test
    void testGenerateNestedJson() throws Exception {
        String expected = Files.readString(Path.of("src/test/resources/expected_nested_stylish.txt")).trim();
        String result = Differ.generate(
                "src/test/resources/nested_file1.json",
                "src/test/resources/nested_file2.json"
        );

        assertEquals(expected, result);
    }

    @Test
    void testGenerateNestedYaml() throws Exception {
        String expected = Files.readString(Path.of("src/test/resources/expected_nested_stylish.txt")).trim();
        String result = Differ.generate(
                "src/test/resources/nested_file1.yml",
                "src/test/resources/nested_file2.yml"
        );

        assertEquals(expected, result);
    }

    @Test
    void testGeneratePlainJson() throws Exception {
        String expected = Files.readString(Path.of("src/test/resources/expected_plain.txt")).trim();
        String result = Differ.generate(
                "src/test/resources/nested_file1.json",
                "src/test/resources/nested_file2.json",
                "plain"
        );

        assertEquals(expected, result);
    }

    @Test
    void testGeneratePlainYaml() throws Exception {
        String expected = Files.readString(Path.of("src/test/resources/expected_plain.txt")).trim();
        String result = Differ.generate(
                "src/test/resources/nested_file1.yml",
                "src/test/resources/nested_file2.yml",
                "plain"
        );

        assertEquals(expected, result);
    }


    @Test
    void testGenerateJsonFormatJson() throws Exception {
        String result = Differ.generate(
                "src/test/resources/nested_file1.json",
                "src/test/resources/nested_file2.json",
                "json"
        );

        List<Map<String, Object>> diff = parseJson(result);

        assertEquals("changed", findNode(diff, "chars2").get("status"));
        assertEquals("removed", findNode(diff, "key1").get("status"));
        assertEquals("added", findNode(diff, "key2").get("status"));
        assertEquals("added", findNode(diff, "obj1").get("status"));
    }

    @Test
    void testGenerateJsonFormatYaml() throws Exception {
        String result = Differ.generate(
                "src/test/resources/nested_file1.yml",
                "src/test/resources/nested_file2.yml",
                "json"
        );

        List<Map<String, Object>> diff = parseJson(result);

        assertEquals("changed", findNode(diff, "chars2").get("status"));
        assertEquals("removed", findNode(diff, "key1").get("status"));
        assertEquals("added", findNode(diff, "key2").get("status"));
        assertEquals("added", findNode(diff, "obj1").get("status"));
    }


    private static Map<String, Object> findNode(List<Map<String, Object>> diff, String key) {
        for (Map<String, Object> node : diff) {
            if (key.equals(node.get("key"))) {
                return node;
            }
        }

        throw new IllegalArgumentException("Node not found: " + key);
    }

    private static List<Map<String, Object>> parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(content, new TypeReference<>() {
        });
    }

}
