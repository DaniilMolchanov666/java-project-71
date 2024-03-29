package hexlet; /*
 * This Java source file was generated by the Gradle 'init' task.
 */
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import hexlet.code.Formatter;
import hexlet.code.Parser;
import hexlet.code.GenerateDifference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
class AppTest {

    private static final String FILE_JSON_FIRST = "./src/test/resources/FIleForTesting1.json";

    private static final String FILE_JSON_SECOND = "./src/test/resources/FileForTesting2.json";

    private static final String FILE_YML_FIRST = "./src/test/resources/FileForTesting3.yml";

    private static final String FILE_YML_SECOND = "./src/test/resources/FileForTesting4.yml";

    private static final String EXPECTED_STYLISH_JSON = "./src/test/resources/Fixtures/expected_stylish_json";

    private static final String EXPECTED_STYLISH_YML = "./src/test/resources/Fixtures/expected_stylish_yml";

    private static final String EXPECTED_PLAIN_JSON = "./src/test/resources/Fixtures/expected_plain_json";

    private static final String EXPECTED_PLAIN_YML = "./src/test/resources/Fixtures/expected_plain_yml";

    private static final String EXPECTED_JSON_JSON = "./src/test/resources/Fixtures/expected_json_json";

    private static final String EXPECTED_JSON_YML = "./src/test/resources/Fixtures/expected_json_yml";

    @Test
    @DisplayName("'default' test for 'json' format files:")
    public void defaultJsonTest() throws IOException {
        String actual = Differ.generate(FILE_JSON_FIRST, FILE_JSON_SECOND);
        String expected = Files.readString(Paths.get(EXPECTED_STYLISH_JSON));
        assertEquals(expected, actual, "for 'json' files");
    }

    @Test
    @DisplayName("'default' test for 'yml' format files:")
    public void defaultYmlTest() throws IOException {
        String actual = Differ.generate(FILE_YML_FIRST, FILE_YML_SECOND);
        String expected = Files.readString(Paths.get(EXPECTED_STYLISH_YML));
        assertEquals(expected, actual, "for 'yml' files");
    }
    @Test
    @DisplayName("'stylish' test for 'json' format files:")
    public void stylishJsonTest() throws IOException {
        String actual = Differ.generate(FILE_JSON_FIRST, FILE_JSON_SECOND, "stylish");
        String expected = Files.readString(Paths.get(EXPECTED_STYLISH_JSON));
        assertEquals(expected, actual, "for 'json' files");
    }

    @Test
    @DisplayName("'stylish' test for 'yml' format files:")
    public void stylishYmlTest() throws IOException {
        String actual = Differ.generate(FILE_YML_FIRST, FILE_YML_SECOND, "stylish");
        String expected = Files.readString(Paths.get(EXPECTED_STYLISH_YML));
        assertEquals(actual, expected, "for 'yml' files");
    }

    @Test
    @DisplayName("'plain' format for 'json' files test:")
    public void plainJsonTest() throws IOException {
        String actual = Differ.generate(FILE_JSON_FIRST, FILE_JSON_SECOND, "plain");
        String expected = Files.readString(Paths.get(EXPECTED_PLAIN_JSON));
        assertEquals(actual, expected, "for 'json' files");
    }

    @Test
    @DisplayName("'plain' format for 'yml' files test:")
    public void plainYmlTest() throws IOException {
        String actual = Differ.generate(FILE_YML_FIRST, FILE_YML_SECOND, "plain");
        String expected = Files.readString(Paths.get(EXPECTED_PLAIN_YML));
        assertEquals(actual, expected, "for 'yml' files");
    }

    @Test
    @DisplayName("'json' format for 'json' files test:")
    public void jsonFormatForJsonFilesTest() throws IOException {
        String actual = Differ.generate(FILE_JSON_FIRST, FILE_JSON_SECOND, "json");
        String expected = Files.readString(Paths.get(EXPECTED_JSON_JSON));
        assertEquals(actual, expected, "for 'json' files");
    }

    @Test
    @DisplayName("'json' format for 'yml' files test:")
    public void jsonFormatForYmlFilesTest() throws IOException {
        String actual = Differ.generate(FILE_YML_FIRST, FILE_YML_SECOND, "json");
        String expected = Files.readString(Paths.get(EXPECTED_JSON_YML));
        assertEquals(actual, expected, "for 'yml' files");
    }

    @Test
    @DisplayName("'Formatter' test")
    public void formatterTest() throws IOException {
        String fileData1 = Files.readString(Paths.get(FILE_JSON_FIRST));
        String fileData2 = Files.readString(Paths.get(FILE_JSON_SECOND));
        TreeMap<String, Object> map1 = new ObjectMapper().readValue(fileData1, new TypeReference<>() { });
        TreeMap<String, Object> map2 = new ObjectMapper().readValue(fileData2, new TypeReference<>() { });

        List<Map<String, Object>> listDIff = GenerateDifference.diff(map1, map2);

        String incorrectFormat = "Incorrect format!";
        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            Differ.generate(FILE_JSON_FIRST, FILE_JSON_SECOND, "txt");
        }, incorrectFormat);

        assertAll("Formatter test for difference formats of files",
                () -> assertEquals(Formatter.chooseFormat("stylish", listDIff),
                        Files.readString(Paths.get(EXPECTED_STYLISH_JSON))),
                () -> assertEquals(Formatter.chooseFormat("plain", listDIff),
                        Files.readString(Paths.get(EXPECTED_PLAIN_JSON))),
                () -> assertEquals(Formatter.chooseFormat("json", listDIff),
                        Files.readString(Paths.get(EXPECTED_JSON_JSON))),
                () -> assertTrue(e.getMessage().contentEquals(incorrectFormat))
        );
    }

    @Test
    @DisplayName("'Parser' test")
    public void parserTest() throws IOException, RuntimeException {
        String fileData = Files.readString(Paths.get(FILE_JSON_FIRST));
        String actual = Parser.getFileAsMap(fileData, "json").toString();
        String expected = "{follow=false, host=hexlet.io, proxy=123.234.53.22, timeout=50}";

        String fileData2 = Files.readString(Paths.get(FILE_YML_FIRST));
        String actual2 = Parser.getFileAsMap(fileData2, "yml").toString();
        String expected2 = "{age=33, animal=-dog -cat, country=[1, 2, 3], name=John Smith, sex=man}";

        String incorrectFormatOfFIleMessage = "Your file has the wrong format! "
                + "Pls use files which have 'yml' or 'json' formats!";
        String fileTXT = Files.readString(Paths.get("./src/test/resources/FIle.txt"));

        assertAll("Parser tests for difference formats",
                () -> assertEquals(actual, expected),
                () -> assertEquals(actual2, expected2),
                () -> assertThrows(IOException.class, () -> {
                    Differ.generate(FILE_JSON_FIRST, fileTXT, "json");
                }, incorrectFormatOfFIleMessage)
        );
    }
}
