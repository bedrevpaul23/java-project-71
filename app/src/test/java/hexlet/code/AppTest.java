package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {
    private static final String RESOURCES_PATH = "src/test/resources/";

    @Test
    void testGenerateDefaultJson() throws Exception {
        String expected = readExpected("expected_nested_stylish.txt");
        String result = Differ.generate(
                getResourcePath("nested_file1.json"),
                getResourcePath("nested_file2.json")
        );

        assertEquals(expected, result);
    }

    @Test
    void testGenerateDefaultYaml() throws Exception {
        String expected = readExpected("expected_nested_stylish.txt");
        String result = Differ.generate(
                getResourcePath("nested_file1.yml"),
                getResourcePath("nested_file2.yml")
        );

        assertEquals(expected, result);
    }

    @Test
    void testGenerateStylishJson() throws Exception {
        String expected = readExpected("expected_nested_stylish.txt");
        String result = Differ.generate(
                getResourcePath("nested_file1.json"),
                getResourcePath("nested_file2.json"),
                "stylish"
        );

        assertEquals(expected, result);
    }

    @Test
    void testGenerateStylishYaml() throws Exception {
        String expected = readExpected("expected_nested_stylish.txt");
        String result = Differ.generate(
                getResourcePath("nested_file1.yml"),
                getResourcePath("nested_file2.yml"),
                "stylish"
        );

        assertEquals(expected, result);
    }

    @Test
    void testGeneratePlainJson() throws Exception {
        String expected = readExpected("expected_plain.txt");
        String result = Differ.generate(
                getResourcePath("nested_file1.json"),
                getResourcePath("nested_file2.json"),
                "plain"
        );

        assertEquals(expected, result);
    }

    @Test
    void testGeneratePlainYaml() throws Exception {
        String expected = readExpected("expected_plain.txt");
        String result = Differ.generate(
                getResourcePath("nested_file1.yml"),
                getResourcePath("nested_file2.yml"),
                "plain"
        );

        assertEquals(expected, result);
    }

    @Test
    void testGenerateJsonFormatJson() throws Exception {
        String expected = readExpected("expected_json.txt");
        String result = Differ.generate(
                getResourcePath("nested_file1.json"),
                getResourcePath("nested_file2.json"),
                "json"
        );

        assertEquals(parseJson(expected), parseJson(result));
    }

    @Test
    void testGenerateJsonFormatYaml() throws Exception {
        String expected = readExpected("expected_json.txt");
        String result = Differ.generate(
                getResourcePath("nested_file1.yml"),
                getResourcePath("nested_file2.yml"),
                "json"
        );

        assertEquals(parseJson(expected), parseJson(result));
    }


    @Test
    void testGenerateDefaultYamlLongExtension() throws Exception {
        String expected = readExpected("expected_nested_stylish.txt");
        String result = Differ.generate(
                getResourcePath("nested_file1.yaml"),
                getResourcePath("nested_file2.yaml")
        );

        assertEquals(expected, result);
    }

    @Test
    void testGenerateUnsupportedInputFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Differ.generate(
                getResourcePath("expected_plain.txt"),
                getResourcePath("nested_file2.json")
        ));

        assertEquals("Unsupported data format", exception.getMessage());
    }

    @Test
    void testParserUnsupportedDataFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Parser.parse("{}", "xml"));

        assertEquals("Unsupported data format: xml", exception.getMessage());
    }

    private static String getResourcePath(String fileName) {
        return RESOURCES_PATH + fileName;
    }

    private static String readExpected(String fileName) throws Exception {
        return Files.readString(Path.of(getResourcePath(fileName))).trim();
    }

    private static List<Map<String, Object>> parseJson(String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(data, new TypeReference<>() {
        });
    }
}
