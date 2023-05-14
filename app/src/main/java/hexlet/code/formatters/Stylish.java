package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String genDiff(List<Map<String, Object>> mapDiff) {

        StringBuilder generateString = new StringBuilder();

        generateString.delete(0, generateString.length());

        generateString.append("{").append("\n");

        for (Map<String, Object> map: mapDiff) {
            switch (map.get("type").toString()) {
                case "unchanged" ->
                        generateString.append(String.format("    %s: %s\n", map.get("key"), map.get("value")));
                case "changed" -> {
                    generateString.append(String.format("  - %s: %s\n", map.get("key"), map.get("value1")));
                    generateString.append(String.format("  + %s: %s\n", map.get("key"), map.get("value2")));
                }
                case "deleted" ->
                        generateString.append(String.format("  - %s: %s\n", map.get("key"), map.get("value1")));
                case "added" ->
                        generateString.append(String.format("  + %s: %s\n", map.get("key"), map.get("value2")));
                default -> generateString.append("");
            }
        }
        return generateString.append("}\n").toString().trim();
    }
}
