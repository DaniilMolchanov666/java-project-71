package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
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

        return "" + entry;
    }

    public static String genDiff(File file1, File file2) throws IOException{

        generateString.delete(0, generateString.length());

        String fileLikeString1 = getStringFile(file1);
        String fileLikeString2 = getStringFile(file2);

        map1.putAll(getData(fileLikeString1));
        map2.putAll(getData(fileLikeString2));

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

    public static Map<String, Object> getData(String content) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        TreeMap<String, Object> map= mapper.readValue(content, new TypeReference<TreeMap<String, Object>>() {});
        return map;
    }

    public static String getStringFile(File file) throws IOException{

        generateString.delete(0, generateString.length());

        FileReader a = new FileReader(file);
        Scanner sc = new Scanner(a);

        while(sc.hasNext()) {
            String string = sc.nextLine();
            generateString.append(string).append("\n");
        }
        return generateString.toString();
    }
}
