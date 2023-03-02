package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import java.util.*;
import java.util.stream.Stream;

public class Json {

    private static Map<String, Object> map1 = new TreeMap<>();

    private static Map<String, Object> map2 = new TreeMap<>();

    public static Map<String, Object> getCorrectEntry(Map.Entry<String, Object> entry) {

        String keyOfEntry = entry.getKey();
        Object valueOfEntry = entry.getValue();
        Object valueOfMap1 =  map1.get(keyOfEntry);
        Object valueOfMap2 =  map2.get(keyOfEntry);

        Map<String, Object> map = new TreeMap<>();

        if (map1.containsKey(keyOfEntry) && map2.containsKey(keyOfEntry)) {
            if (valueOfMap1.equals(valueOfMap2)) {
                //map.put("key", entry.getKey());
                //map.put("type", "unchanged");
                //map.put("value", entry.getValue());
                map = Map.of ("value", valueOfEntry, "type", "unchanged", "key", keyOfEntry);
                return map;
            }
                //map.put("key", entryKey);
                //map.put("type", "changed");
                //map.put("value1", map1.get(entryKey));
                //map.put("value2", map2.get(entryKey));
                map = Map.of("key", keyOfEntry, "type", "changed","value1",
                        valueOfMap1, "value2", valueOfMap2);
                return map;
        }
        if (map1.containsKey(keyOfEntry)) {
            map.put("key", entry.getKey());
            map.put("type", "deleted");
            map.put("value", entry.getValue());
            return map;
        } else {
            map.put("key", entry.getKey());
            map.put("type", "added");
            map.put("value", entry.getValue());
            return map;
        }

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

        String json = b.writeValueAsString(list);
        return json;


    }
}
