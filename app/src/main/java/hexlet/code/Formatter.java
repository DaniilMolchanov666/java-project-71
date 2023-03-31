package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.TreeMap;

public class Formatter {

    private static final String INCORRECT_FORMAT = "Incorrect format!";

    private static final String PARSING_PROCESSING_WARNING = "Something went wrong with parsing!";

    public static String chooseFormat(String format, TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        try {
            switch (format) {
                case "plain":
                    return Plain.genDiff(map1, map2);
                case "stylish":
                    return Stylish.genDiff(map1, map2);
                case "json":
                    return Json.genDiff(map1, map2);
            }
        } catch(JsonProcessingException e) {
            System.out.println(PARSING_PROCESSING_WARNING);
        }
        return INCORRECT_FORMAT;
    }
}
