package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Stream;

public class GenerateDifference {

    private static final String UNCHANGED = "unchanged";

    private static final String ADDED = "added";

    private static final String DELETED = "deleted";

    private static final String CHANGED = "changed";

    public static TreeMap<String, Object> map1 = new TreeMap<>();

    private static TreeMap<String, Object> map2 = new TreeMap<>();

    public static List<Map<String, Object>> diff(TreeMap<String, Object> mapFirst, TreeMap<String, Object> mapSecond) {
        map1 = mapFirst;
        map2 = mapSecond;

        return Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .sorted(Map.Entry.comparingByKey())
                .map(GenerateDifference::getCorrectEntry)
                .distinct()
                .toList();
    }

    public static Map<String, Object> getCorrectEntry(Map.Entry<String, Object> entry) {

        String key = entry.getKey();
        Object value = entry.getValue();

        TreeMap<String, Object> map = new TreeMap<>();

        if (checkIsContainsInBothMaps(key)) {
            if (checkForEqualityOfValues(key)) {
                map.put("key", key);
                map.put("value", value);
                map.put("type", UNCHANGED);
                return map;
            }
            map.put("key", key);
            map.put("value1", map1.get(key));
            map.put("value2", map2.get(key));
            map.put("type", CHANGED);
            return map;
        }
        if (map1.containsKey(key)) {
            map.put("key", key);
            map.put("value1", map1.get(key));
            map.put("type", DELETED);
            return map;
        }
        map.put("key", key);
        map.put("value2", map2.get(key));
        map.put("type", ADDED);
        return map;
    }

//    public static Object checkNullValue(Object value) {
//        return value == null ? "null" : value;
//    }

    public static boolean checkIsContainsInBothMaps(String key) {
        return map1.containsKey(key) && map2.containsKey(key);
    }

    public static boolean checkForEqualityOfValues(String key) {
        return Objects.equals(map1.get(key), map2.get(key));
    }
}
