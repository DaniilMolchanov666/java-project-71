package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.Map;
import java.util.List;

public class Json {

    private static ObjectMapper mapper = new ObjectMapper();

    public static ObjectWriter writer = mapper.writer();

    public static String genDiff(List<Map<String, Object>> mapDiff) throws JsonProcessingException {

        StringBuilder s = new StringBuilder();

        writer = mapper.writerWithDefaultPrettyPrinter();

        return s.append(writer.writeValueAsString(mapDiff)).append("\n").toString().trim();

    }
}
