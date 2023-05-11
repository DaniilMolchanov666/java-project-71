package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.List;
import java.util.Map;

public class Formatter {

    private static final String PARSING_PROCESSING_WARNING = "Incorrect format!";

    private static final String PLAIN = "plain";

    private static final String STYLISH = "stylish";

    private static final String JSON = "json";

    public static String chooseFormat(String format, List<Map<String, Object>> listOfComparedValues)
            throws JsonProcessingException {

        return switch (format) {
            case PLAIN -> Plain.genDiff(listOfComparedValues);
            case STYLISH -> Stylish.genDiff(listOfComparedValues);
            case JSON -> Json.genDiff(listOfComparedValues);
            default -> throw new RuntimeException(PARSING_PROCESSING_WARNING);
        };
    }
}
