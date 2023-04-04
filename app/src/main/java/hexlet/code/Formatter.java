package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.TreeMap;

public class Formatter {

    private static final String PARSING_PROCESSING_WARNING = "Something went wrong with parsing!";

    private static final String PLAIN = "plain";

    private static final String STYLISH = "stylish";

    private static final String JSON = "json";

    public static String chooseFormat(String format, TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        try {
            switch (format) {
                case PLAIN:
                    return Plain.genDiff(map1, map2);
                case STYLISH:
                    return Stylish.genDiff(map1, map2);
                case JSON:
                    return Json.genDiff(map1, map2);
            }
        } catch(JsonProcessingException e) {
            e.getMessage();
        }
        return PARSING_PROCESSING_WARNING;
    }
}
