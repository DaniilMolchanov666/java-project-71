package hexlet.code.formatters;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Stylish {

    private static final StringBuilder generateString = new StringBuilder();

    private static TreeMap<String, Object> map1 = new TreeMap<>();

    private static TreeMap<String, Object> map2 = new TreeMap<>();


    public static String getCorrectEntry(Map.Entry<String, Object> entry) {
        String key = entry.getKey();

        if (isContainsInBothMaps(key)) {
            if (isEqualInValues(key)) {
                return String.format("\t%s: %s", entry.getKey(), entry.getValue());
            }
        }
        if (map1.entrySet().contains(entry)) {
            return String.format("  - %s: %s", entry.getKey(), entry.getValue());
        }
        return String.format("  + %s: %s", entry.getKey(), entry.getValue());
    }

    public static String genDiff(TreeMap<String, Object> mapFirst, TreeMap<String, Object> mapSecond) {

        map1 = mapFirst;
        map2 = mapSecond;

        generateString.append("{" + "\n");;

        Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .distinct()
                .sorted(Map.Entry.comparingByKey())
                .map(Stylish::getCorrectEntry)
                .forEach(a -> generateString.append(a).append("\n"));

        return generateString.append("}").toString();
    }

    public static boolean isContainsInBothMaps(String key) {
        return map1.containsKey(key) && map2.containsKey(key);
    }

    public static boolean isEqualInValues(String key) {
        return isContainsInBothMaps(key) && map1.get(key).equals(map2.get(key));
    }
}
