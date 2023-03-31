package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Stylish extends FormatGenerator{

    private static StringBuilder generateString = new StringBuilder();

    private static TreeMap<String, Object> map1 = new TreeMap<>();

    private static TreeMap<String, Object> map2 = new TreeMap<>();


    public static String getCorrectEntry(Map.Entry<String, Object> entry) {
        String key = entry.getKey();

        if (isContainsInMaps(key)) {
            if (isEqualInValues(key)) {
                return String.format("\t%s: %s", entry.getKey(), entry.getValue());
            }
        }
        if (map1.entrySet().contains(entry)) {
            return String.format("  - %s: %s", entry.getKey(), entry.getValue());
//                    "   - " + entry.getKey() + ": " + entry.getValue();
        }
        return String.format("  + %s: %s", entry.getKey(), entry.getValue());
                        //"   + " + entry.getKey() + ": " + entry.getValue();
    }

    public static String genDiff(TreeMap<String, Object> mapFirst, TreeMap<String, Object> mapSecond) throws JsonProcessingException {

        map1 = mapFirst;
        map2 = mapSecond;

        generateString.delete(0, generateString.length());
        generateString.append("{" + "\n");

        Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .distinct()
                .sorted(Map.Entry.comparingByKey())
                .map(Stylish::getCorrectEntry)
                .peek(u-> generateString.append(u).append("\n"))
                .toList();

        return generateString.append("}").toString();
    }
}
