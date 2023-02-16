package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Formatter {

    public static Map<String, Object> getData(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());

            return mapper.readValue(content, Map.class);
    }
}
