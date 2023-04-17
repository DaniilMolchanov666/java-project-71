package hexlet; /*
 * This Java source file was generated by the Gradle 'init' task.
 */
import hexlet.code.Differ;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    private static final String FILE_JSON_FIRST = "./src/test/resources/FileForTesting1.json";

    private static final String FILE_JSON_SECOND = "./src/test/resources/FileForTesting2.json";

    private static final String FILE_YML_FIRST = "./src/test/resources/FileForTesting3.yml";

    private static final String FILE_YML_SECOND = "./src/test/resources/FileForTesting4.yml";

    @Test
    @DisplayName("'stylish' test for 'json' format files:")
    public void stylishJsonTest() throws IOException {
        String actual = Differ.generate(FILE_JSON_FIRST, FILE_JSON_SECOND, "stylish");
        String expected = Files.readString(Paths.get("./src/test/resources/ExpectedStylishJson"));
        assertEquals(expected, actual, "for 'json' files");
    }
    @Test
    @DisplayName("'stylish' test for 'yml' format files:")
    public void stylishYmlTest() throws IOException {
        String actual = Differ.generate(FILE_YML_FIRST, FILE_YML_SECOND, "stylish");
        String expected = Files.readString(Paths.get("./src/test/resources/ExpectedStylishYml"));
        assertEquals(actual, expected, "for 'yml' files");
    }

    @Test
    @DisplayName("'plain' format for 'json' files test:")
    public void plainJsonTest() throws IOException {
        String actual = Differ.generate(FILE_JSON_FIRST, FILE_JSON_SECOND, "plain");
        String expected = Files.readString(Paths.get("./src/test/resources/ExpectedPlainJson")).trim();
        assertEquals(actual, expected, "for 'json' files");
    }

    @Test
    @DisplayName("'plain' format for 'yml' files test:")
    public void plainYmlTest() throws IOException {
        String actual = Differ.generate(FILE_YML_FIRST, FILE_YML_SECOND, "plain");
        String expected = Files.readString(Paths.get("./src/test/resources/ExpectedPlainYml")).trim();
        assertEquals(actual, expected, "for 'yml' files");
    }

    @Test
    @DisplayName("'json' format for 'json' files test:")
    public void jsonFormatForJsonFilesTest() throws IOException {
        String actual = Differ.generate(FILE_JSON_FIRST, FILE_JSON_SECOND, "json");
        String expected = Files.readString(Paths.get("./src/test/resources/ExpectedJsonJson"));
        assertEquals(actual, expected, "for 'json' files");
    }

    @Test
    @DisplayName("'json' format for 'yml' files test:")
    public void jsonFormatForYmlFilesTest() throws IOException {
        String actual = Differ.generate(FILE_YML_FIRST, FILE_YML_SECOND, "json");
        String expected = Files.readString(Paths.get("./src/test/resources/ExpectedJsonYml"));
        assertEquals(actual, expected, "for 'yml' files");
    }
}
