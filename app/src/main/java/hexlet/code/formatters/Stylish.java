package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stylish {

    private static final StringBuilder generateString = new StringBuilder();

    public static String genDiff(List<Map<String, Object>> mapDiff) {

        generateString.append("{").append("\n");

        for(Map<String, Object> map: mapDiff) {
            switch (map.get("type").toString()) {
                case "unchanged" ->
                        generateString.append(String.format("    %s: %s\n", map.get("key"), map.get("value")));
                case "changed" -> {
                    generateString.append(String.format("  - %s: %s\n", map.get("key"), map.get("value")));
                    generateString.append(String.format("  + %s: %s\n", map.get("key"), map.get("value2")));
                }
                case "deleted" ->
                        generateString.append(String.format("  - %s: %s\n", map.get("key"), map.get("value1")));
                case "added" ->
                        generateString.append(String.format("  + %s: %s\n", map.get("key"), map.get("value2")));
            }
        }
        return generateString.append("}\n").toString().trim();
    }
}
