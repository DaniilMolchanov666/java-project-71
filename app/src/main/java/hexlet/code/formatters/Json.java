package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import java.util.*;
import java.util.stream.Stream;

public class Json {

    public static TreeMap<String, Object> map1 = new TreeMap<>();

    private static TreeMap<String, Object> map2 = new TreeMap<>();

    private static ObjectMapper mapper = new ObjectMapper();

    public static ObjectWriter writer = mapper.writer();

    public static Map<String, Object> getCorrectEntry(Map.Entry<String, Object> entry) {

        String key = entry.getKey();
        Object value = entry.getValue();
        Object valueOfMap1 = map1.get(key);
        Object valueOfMap2 = map2.get(key);

        Map<String, Object> map = new TreeMap<>();

        if (isContainsInBothMaps(key)) {
            if (isEqualInValues(key)) {
                return Map.of("value", value, "type", "unchanged", "key", key);
            }
            return Map.of("key", key, "type", "changed", "value1",
                    valueOfMap1, "value2", valueOfMap2);
        }
        if (map1.containsKey(key)) {
            return Map.of("key", key, "type", "deleted", "value", value);
        }
        return Map.of("key", key, "type", "added", "value", value);
    }

    public static String genDiff(TreeMap<String, Object> mapFirst, TreeMap<String, Object> mapSecond) throws JsonProcessingException {

        map1 = mapFirst;
        map2 = mapSecond;

        writer = mapper.writerWithDefaultPrettyPrinter();

        List<Map<String, Object>> list = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .sorted(Map.Entry.comparingByKey())
                .map(Json::getCorrectEntry)
                .distinct()
                .toList();

        return writer.writeValueAsString(list);
    }

    public static boolean isContainsInBothMaps(String key) {
        return map1.containsKey(key) && map2.containsKey(key);
    }

    public static boolean isEqualInValues(String key) {
        return isContainsInBothMaps(key) && map1.get(key).equals(map2.get(key));
    }
}
