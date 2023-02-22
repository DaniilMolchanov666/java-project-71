package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Formatter {

    public static Map<String, Object> geJsonMap(String content) throws IOException {

        try {
                ObjectMapper mapper = new ObjectMapper(new JsonFactory());
                return mapper.readValue(content, Map.class);
        } catch (IOException e) {
            System.out.println("Your file has not the wrong format");
        }
        return new TreeMap<>();
    }

    public static Map<String, Object> geYMLMap(String content) throws IOException {

        try {
            ObjectMapper mapper = new YAMLMapper(new YAMLFactory());
            return mapper.readValue(content, Map.class);
        } catch (IOException e) {
            System.out.println("Your file has not the wrong format");
        }
        return new TreeMap<>();
    }
}
