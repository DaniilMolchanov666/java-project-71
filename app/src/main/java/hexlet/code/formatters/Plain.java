package hexlet.code.formatters;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StrBuilder;
import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.translate.*;


public class Plain {

    private static final StringBuilder generateString = new StringBuilder();

    private static Map<String, Object> map1 = new TreeMap<>();

    private static Map<String, Object> map2 = new TreeMap<>();

    private static String update  = "Property 'key' was updated. From value 1 to value2";


    public static Object valueChanger(Object value) { // проверка является ли значение пары массивом или мапой
        //if (value.toString().startsWith("[") || value.toString().startsWith("{")) {
        if(value.getClass() == ArrayList.class || value.getClass() == LinkedHashMap.class) {
            return "[complex value]";
        } else if(value.getClass() == String.class) {
            return "'" + value + "'";
        }
        return value;
    }

    public static String genDiff(TreeMap<String, Object> mapFirst, TreeMap<String, Object> mapSecond) {

        map1 = mapFirst;
        map2 = mapSecond;

       return Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .sorted()
                .map(key -> getCorrectEntry(Map.entry(key, map1.containsKey(key) ? map1.get(key): map2.get(key))))
                .collect(Collectors.joining(""));

    }
        public static String getCorrectEntry(Map.Entry<String, Object> entry) {
            generateString.delete(0, generateString.length());

            if (map1.containsKey(entry.getKey()) && map2.containsKey(entry.getKey())) { // если пары равны по ключу
                if (!map1.get(entry.getKey()).equals(map2.get(entry.getKey()))) { // если пары не равны по значению
                    return "Property" + " '" + entry.getKey() + "' " + "was updated. From "
                    + valueChanger(map1.get(entry.getKey()))  + " to " + valueChanger(map2.get(entry.getKey()))
                    + "\n";
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

            return generateString.toString();
    }

}
