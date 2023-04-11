package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
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
        Object valueOfMap1 = map1.get(key);
        Object valueOfMap2 = map2.get(key);

        Map<String, Object> map = new TreeMap<>();

        if (checkIsContainsInBothMaps(key)) {
            if (checkForEqualityOfValues(key)) {
                map.putAll(Map.of("key", key, "value", value, "type", UNCHANGED));
                return map;
            }
            map.putAll(Map.of("key", key, "value1",  valueOfMap1, "value2", valueOfMap2,
                    "type", CHANGED));
            return map;
        }
        if (map1.containsKey(key)) {
            map.putAll(Map.of("key", key, "value1", valueOfMap1, "type", DELETED));
            return map;
        }
        map.putAll(Map.of("key", key, "value2", valueOfMap2, "type", ADDED));
        return map;
    }

    public static boolean checkIsContainsInBothMaps(String key) {
        return map1.containsKey(key) && map2.containsKey(key);
    }

    public static boolean checkForEqualityOfValues(String key) {
        return checkIsContainsInBothMaps(key) && map1.get(key).equals(map2.get(key));
    }
}
