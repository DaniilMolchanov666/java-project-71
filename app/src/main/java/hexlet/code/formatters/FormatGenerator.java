package hexlet.code.formatters;

import java.util.Map;
import java.util.TreeMap;

public class FormatGenerator {

    private static Map<String, Object> map1 = new TreeMap<>();

    private static Map<String, Object> map2 = new TreeMap<>();

    public static boolean isContainsInMaps(String key) {
        return map1.containsKey(key) && map2.containsKey(key);
    }
    public static boolean isEqualInValues(String key) {
        return map1.get(key).equals(map2.get(key));
    }

    public static Map<String, Object> getMap1() {
        return map1;
    }

    public static Map<String, Object> getMap2() {
        return map2;
    }

    public static void setMap1(Map<String, Object> map1) {
        FormatGenerator.map1 = map1;
    }

    public static void setMap2(Map<String, Object> map2) {
        FormatGenerator.map2 = map2;
    }
}
