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

    private static final ObjectMapper YML_MAPPER = new YAMLMapper(new YAMLFactory());

    private static final ObjectMapper JSON_MAPPER = new JsonMapper(new JsonFactory());

    public static TreeMap<String, Object> getFileAsMap(String fileContent, String formatOfFile) throws IOException {
        return formatOfFile.contains("yml") ? YML_MAPPER.readValue(fileContent, new TypeReference<>() { })
                : JSON_MAPPER.readValue(fileContent, new TypeReference<>() { });
    }
}
