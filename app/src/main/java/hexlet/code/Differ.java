package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

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
                return "    " + entry;
            }
        }
        if (map1.entrySet().contains(entry)) {
            return "  - " + entry;
        } else {
            if (map2.entrySet().contains(entry)) {
                return "  + " + entry;
            }
        }

        return entry.toString();
    }

    public static String genDiff(File file1, File file2) throws IOException{

        generateString.delete(0, generateString.length());

        String fileLikeString1 = Parser.getStringFile(file1);
        String fileLikeString2 = Parser.getStringFile(file2);

        try {
            if (file1.getAbsolutePath().contains("json") && file2.getAbsolutePath().contains("json")) {

                map1.putAll(Formatter.geJsonMap(fileLikeString1));
                map2.putAll(Formatter.geJsonMap(fileLikeString2));
            } else if (file1.getAbsolutePath().contains("yml") && file2.getAbsolutePath().contains("yml")) {

                map1.putAll(Formatter.geYMLMap(fileLikeString1));
                map2.putAll(Formatter.geYMLMap(fileLikeString2));
            }
            else {
                return "Pls enter correct names of files and correct formats of files!";
            }

        } catch(UncheckedIOException o) {
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
