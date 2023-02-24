package hexlet.code.formatters;

import java.util.Map;
import java.util.TreeMap;

public class Stylish {

    private Map<String, Object> map1;

    private Map<String, Object> map2;
    private static StringBuilder generateString = new StringBuilder();


    public static String getCorrectEntry(Map.Entry<String, Object> entry) {

        Map<String, Object> map1 = new TreeMap<>();
        Map<String, Object> map2 = new TreeMap<>();

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

    public static String genDiff(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        generateString.delete(0, generateString.length());
        generateString.append("{" + "\n");
        return "";

    }

}
