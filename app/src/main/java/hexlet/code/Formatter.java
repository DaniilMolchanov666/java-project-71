package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.GenerateDifference;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Formatter {

    private static final String PARSING_PROCESSING_WARNING = "Something went wrong with parsing!";

    private static final String PLAIN = "plain";

    private static final String STYLISH = "stylish";

    private static final String JSON = "json";

    public static String chooseFormat(String format, TreeMap<String, Object> map1, TreeMap<String, Object> map2) throws JsonProcessingException {

        List<Map<String, Object>> listOfComparedValues = GenerateDifference.diff(map1, map2);

        return switch (format) {
            case PLAIN -> Plain.genDiff(listOfComparedValues);
            case STYLISH -> Stylish.genDiff(listOfComparedValues);
            case JSON -> Json.genDiff(listOfComparedValues);
            default -> PARSING_PROCESSING_WARNING;
        };
    }
}
