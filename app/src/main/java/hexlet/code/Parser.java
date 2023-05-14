package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.util.TreeMap;

public class Parser {

    private static final String FORMAT_WARNING = "Your file has the wrong format! "
            + "Pls use files which have 'yml' or 'json' formats!";
    public static TreeMap<String, Object> getFileAsMap(String contentData, String formatOfData) throws IOException {

        ObjectMapper yamlMapper = new YAMLMapper(new YAMLFactory());
        ObjectMapper jsonMapper = new JsonMapper(new JsonFactory());

        return switch (formatOfData) {
            case "yaml", "yml" -> yamlMapper.readValue(contentData, new TypeReference<>() { });
            case "json" -> jsonMapper.readValue(contentData, new TypeReference<>() { });

            default -> throw new RuntimeException(FORMAT_WARNING);
        };
    }
}
