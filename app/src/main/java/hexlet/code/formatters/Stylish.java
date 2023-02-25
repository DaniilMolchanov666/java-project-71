package hexlet.code.formatters;

import hexlet.code.Formatter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Stylish {

    private static StringBuilder generateString = new StringBuilder();
    private static TreeMap<String, Object> map1 = new TreeMap<>();

    private static TreeMap<String, Object> map2 = new TreeMap<>();


    public static String getCorrectEntry(Map.Entry<String, Object> entry) {

        if (map1.containsKey(entry.getKey()) && map2.containsKey(entry.getKey())) {
            if (map1.get(entry.getKey()).equals(map2.get(entry.getKey()))) {
                return "     " + entry.getKey() + ": " + entry.getValue();
            }
        }
        if (map1.entrySet().contains(entry)) {
            return "   - " + entry.getKey() + ": " + entry.getValue();
        } else {
            if (map2.entrySet().contains(entry)) {
                return "   + " + entry.getKey() + ": " + entry.getValue();
            }
        }

        return entry.toString();
    }

    public static String genDiff(TreeMap<String, Object> mapFirst, TreeMap<String, Object> mapSecond) {

        map1.putAll(mapFirst);
        map2.putAll(mapSecond);

        generateString.delete(0, generateString.length());

        generateString.append("{" + "\n");

        List<String> list = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .distinct()
                .sorted(Map.Entry.comparingByKey())
                .map(Stylish::getCorrectEntry)
                .toList();

        for (String a:list) {
               generateString.append(a).append("\n");
        }

        generateString.append("}");
        return generateString.toString();
    }
}
