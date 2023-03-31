package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import java.util.*;
import java.util.stream.Stream;

public class Json extends FormatGenerator{

    public static TreeMap<String, Object> map1 = new TreeMap<>();

    private static TreeMap<String, Object> map2 = new TreeMap<>();

    public static Map<String, Object> getCorrectEntry(Map.Entry<String, Object> entry) {

        String key = entry.getKey();
        Object value = entry.getValue();
        Object valueOfMap1 = map1.get(key);
        Object valueOfMap2 = map2.get(key);

        Map<String, Object> map = new TreeMap<>();

        if (isContainsInMaps(key)) {
            if (valueOfMap1.equals(value)) {
                map = Map.of("value", value, "type", "unchanged", "key", key);
            }
            map = Map.of("key", key, "type", "changed", "value1",
                    valueOfMap1, "value2", valueOfMap2);
        }
        if (map1.containsKey(key)) {
            map = Map.of("key", key, "type", "deleted", "value", value);
        }
        map = Map.of("key", key, "type", "added", "value", value);

        return map;
    }

    public static String genDiff(TreeMap<String, Object> mapFirst, TreeMap<String, Object> mapSecond) throws JsonProcessingException {

        map1 = mapFirst;
        map2 = mapSecond;

        ObjectMapper a = new ObjectMapper();
        ObjectWriter b = a.writerWithDefaultPrettyPrinter();

        List<Map<String, Object>> list = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .distinct()
                .sorted(Map.Entry.comparingByKey())
                .map(Json::getCorrectEntry)
                .toList().stream()
                .distinct()
                .toList();

        return b.writeValueAsString(list);
    }
}
