package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    private static final StringBuilder GENERATE_STRING = new StringBuilder();

    public static String genDiff(List<Map<String, Object>> mapDiff) {

        GENERATE_STRING.delete(0, GENERATE_STRING.length());

        GENERATE_STRING.append("{").append("\n");

        for (Map<String, Object> map: mapDiff) {
            switch (map.get("type").toString()) {
                case "unchanged" ->
                        GENERATE_STRING.append(String.format("    %s: %s\n", map.get("key"), map.get("value")));
                case "changed" -> {
                    GENERATE_STRING.append(String.format("  - %s: %s\n", map.get("key"), map.get("value1")));
                    GENERATE_STRING.append(String.format("  + %s: %s\n", map.get("key"), map.get("value2")));
                }
                case "deleted" ->
                        GENERATE_STRING.append(String.format("  - %s: %s\n", map.get("key"), map.get("value1")));
                case "added" ->
                        GENERATE_STRING.append(String.format("  + %s: %s\n", map.get("key"), map.get("value2")));
                default -> GENERATE_STRING.append("");
            }
        }
        return GENERATE_STRING.append("}\n").toString().trim();
    }
}
