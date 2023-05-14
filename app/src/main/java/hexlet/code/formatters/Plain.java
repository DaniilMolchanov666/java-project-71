package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Plain {

    private static final String COMPLEX_VALUE = "[complex value]";

    private static final List<String> LIST_OF_VALUE_DIFFERENCE = new ArrayList<>();

    public static String genDiff(List<Map<String, Object>> mapDiff) {

        LIST_OF_VALUE_DIFFERENCE.clear();

        for (Map<String, Object> map: mapDiff) {

            switch (map.get("type").toString()) {
                case "unchanged" ->
                        LIST_OF_VALUE_DIFFERENCE.add("\n");
                case "changed" -> {
                    LIST_OF_VALUE_DIFFERENCE.add(String.format("Property '%s' was updated. From %s to %s\n",
                            map.get("key"), typeOfValueCheck(map.get("value1")), typeOfValueCheck(map.get("value2"))));
                }
                case "deleted" ->
                    LIST_OF_VALUE_DIFFERENCE.add(String.format("Property '%s' was removed\n", map.get("key")));
                case "added" ->
                    LIST_OF_VALUE_DIFFERENCE.add(String.format("Property '%s' was added with value: %s\n",
                            map.get("key"), typeOfValueCheck(map.get("value2"))));
                default -> throw new RuntimeException("Unknown type!" + map.get("type"));
            }
        }
        return LIST_OF_VALUE_DIFFERENCE.stream()
                .sorted()
                .collect(Collectors.joining(""))
                .trim();
    }

    public static String typeOfValueCheck(Object value) {
        if (value == null) {
            return null;
        }
        if (value.getClass() == ArrayList.class || value.getClass() == LinkedHashMap.class) {
            return COMPLEX_VALUE;
        }
        if (value.getClass() == String.class) {
            return "'" + value + "'";
        }
        return value.toString();
    }
}
