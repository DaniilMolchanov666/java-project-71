package hexlet.code.formatters;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Plain {

    private static final String COMPLEX_VALUE= "[complex value]";

    private static final List<String> valueDifferenceList = new ArrayList<>();

    public static String genDiff(List<Map<String, Object>> mapDiff) {

        for(Map<String, Object> map: mapDiff) {

            switch (map.get("type").toString()) {
                case "unchanged" ->
                        valueDifferenceList.add("\n");
                case "changed" -> {
                    valueDifferenceList.add(String.format("Property %s was updated. From %s to %s\n",
                            complexValuesCheck(map.get("key")),
                            complexValuesCheck(map.get("value1")), complexValuesCheck(map.get("value2"))));
                }
                case "deleted" ->
                    valueDifferenceList.add(String.format("Property '%s' was removed\n", map.get("key")));
                case "added" ->
                    valueDifferenceList.add(String.format("Property '%s' was added with value: %s\n",
                            map.get("key"), complexValuesCheck(map.get("value2"))));
            }
        }
        return valueDifferenceList.stream()
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
