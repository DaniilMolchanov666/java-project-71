package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Plain {

    private static final String COMPLEX_VALUE = "[complex value]";

    private static final List<String> VALUE_DIFFERENCE_LIST = new ArrayList<>();

    public static String genDiff(List<Map<String, Object>> mapDiff) {

        VALUE_DIFFERENCE_LIST.clear();

        for (Map<String, Object> map: mapDiff) {

            switch (map.get("type").toString()) {
                case "unchanged" ->
                        VALUE_DIFFERENCE_LIST.add("\n");
                case "changed" -> {
                    VALUE_DIFFERENCE_LIST.add(String.format("Property %s was updated. From %s to %s\n",
                            complexValuesCheck(map.get("key")),
                            complexValuesCheck(map.get("value1")), complexValuesCheck(map.get("value2"))));
                }
                case "deleted" ->
                    VALUE_DIFFERENCE_LIST.add(String.format("Property '%s' was removed\n", map.get("key")));
                case "added" ->
                    VALUE_DIFFERENCE_LIST.add(String.format("Property '%s' was added with value: %s\n",
                            map.get("key"), complexValuesCheck(map.get("value2"))));
                default -> VALUE_DIFFERENCE_LIST.add("");
            }
        }
        return VALUE_DIFFERENCE_LIST.stream()
                .sorted()
                .collect(Collectors.joining(""))
                .trim();
    }
    public static Object complexValuesCheck(Object value) {
        if (value.getClass() == ArrayList.class || value.getClass() == LinkedHashMap.class) {
            return COMPLEX_VALUE;
        }
        if (value.getClass() == String.class) {
            return "'" + value + "'";
        }
        return value;
    }
}
