package hexlet.code.formatters;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Plain {

    private static final String EMPTY_STRING = "";

    private static final String COMPLEX_VALUE= "[complex value]";

    private static Map<String, Object> map1 = new TreeMap<>();

    private static Map<String, Object> map2 = new TreeMap<>();

    public static Object complexValuesCheck(Object value) {
        if(value.getClass() == ArrayList.class || value.getClass() == LinkedHashMap.class) {
            return COMPLEX_VALUE;
        }
        if (value.getClass() == String.class) {
            return "'" + value + "'";
        }
        return value;
    }

    public static String genDiff(TreeMap<String, Object> mapFirst, TreeMap<String, Object> mapSecond) {

    map1 = mapFirst;
    map2 = mapSecond;

   return Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
            .sorted(Map.Entry.comparingByKey())
            .map(Plain::getCorrectEntry)
            .distinct()
            .collect(Collectors.joining(""));

    }
    public static String getCorrectEntry(Map.Entry<String, Object> entry) {
        String keyOfEntry = entry.getKey();
        Object valueOfMap1 = map1.get(keyOfEntry);
        Object valueOfMap2 = map2.get(keyOfEntry);

        if (isContainsInBothMaps(keyOfEntry)) {
            if (!isEqualInValues(keyOfEntry)) {
                return String.format("Property '%s' was updated. From %s to %s\n", keyOfEntry,
                        complexValuesCheck(valueOfMap1), complexValuesCheck(valueOfMap2));
            }
            return EMPTY_STRING;
        }
        if (map2.containsKey(keyOfEntry)) {
            return String.format("Property '%s' was added with value: %s\n", keyOfEntry,
                    complexValuesCheck(valueOfMap2));
        }
        return String.format("Property '%s' was removed\n", keyOfEntry);
    }

    public static boolean isContainsInBothMaps(String key) {
        return map1.containsKey(key) && map2.containsKey(key);
    }

    public static boolean isEqualInValues(String key) {
        return isContainsInBothMaps(key) && map1.get(key).equals(map2.get(key));
    }

}
