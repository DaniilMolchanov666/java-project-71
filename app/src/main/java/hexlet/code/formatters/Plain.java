package hexlet.code.formatters;

import java.util.*;
import java.util.stream.Stream;

public class Plain {

    private static StringBuilder generateString = new StringBuilder();

    public static Object valueChanger(Object value) { // проверка является ли значение пары массивом или мапой
        //if (value.toString().startsWith("[") || value.toString().startsWith("{")) {
        if(value.getClass() == ArrayList.class || value.getClass() == LinkedHashMap.class) {
            return "[complex value]";
        } else if(value.getClass() == String.class) {
            return "'" + value + "'";
        }
        return value;
    }

    public static String genDiff(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {

        generateString.delete(0, generateString.length());

        List<Map.Entry<String, Object>> list = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .distinct()
                .sorted(Map.Entry.comparingByKey())
                .toList();

        for (Map.Entry<String, Object> entry: list) {
            if (map1.containsKey(entry.getKey()) && map2.containsKey(entry.getKey())) { // если пары равны по ключу
                if (!map1.get(entry.getKey()).toString().equals(map2.get(entry.getKey()).toString())) { // если пары не равны по значению
                    generateString.append("Property" + " '")
                            .append(entry.getKey())
                            .append("' ").append("was updated. From ")
                            .append(valueChanger(map1.get(entry.getKey())))
                            .append(" to ")
                            .append((valueChanger(map2.get(entry.getKey()))))
                            .append("\n");
                }
            } else if (map2.containsKey(entry.getKey())) {
                generateString.append("Property" + " '")
                        .append(entry.getKey())
                        .append("' ").append("was added with value: ")
                        .append(valueChanger(map2.get(entry.getKey())))
                        .append("\n");
            } else if (map1.containsKey(entry.getKey())) {
                generateString.append("Property" + " '")
                        .append(entry.getKey())
                        .append("' ").append("was removed")
                        .append("\n");
            }
        }
            return generateString.toString();
    }

}
