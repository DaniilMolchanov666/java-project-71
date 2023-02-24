package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.Stylish;

import javax.swing.text.Style;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Differ {

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

    public static String genDiff(File file1, File file2, String format) {

        generateString.delete(0, generateString.length());

        try {
                map1 = Parser.geJsonMap(file1);
                map2 = Parser.geJsonMap(file2);

        } catch (UncheckedIOException o) {
            return "Incorrect format of files!";
        }

        generateString.append("{" + "\n");

        List<String> list = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .distinct()
                .sorted(Map.Entry.comparingByKey())
                .map(Differ::getCorrectEntry)
                .toList();

        for (String a:list) {
            generateString.append(a).append("\n");
        }

        generateString.append("}");
        return generateString.toString();
    }
}
