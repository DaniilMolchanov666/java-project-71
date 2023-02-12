package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {

    public static String genDiff(File file1, File file2) throws IOException{
        //mapper.writeValue(new File("hexlet.code.file.json"), car);
        //String newJSON = mapper.writeValueAsString(car);
        //System.out.println(mapper.writeValueAsString(car));
        String fileLikeString1 = getStringFile(file1);
        String fileLikeString2 = getStringFile(file2);

        Map<String, Object> map1 = getData(fileLikeString1);
        Map<String, Object> map2 = getData(fileLikeString2);

        List<Map.Entry<String, Object>> list = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .sorted(Map.Entry.comparingByKey())
                .toList();
        return list.toString();
    }

    public static Map getData(String content) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        TreeMap<String, Object> map= mapper.readValue(content, new TypeReference<TreeMap<String, Object>>() {});
        return map;
    }

    public static String getStringFile(File file) throws IOException{
        StringBuilder str = new StringBuilder();

        FileReader a = new FileReader(file);
        Scanner sc = new Scanner(a);

        while(sc.hasNext()) {
            String string = sc.nextLine();
            str.append(string);
        }
        return str.toString();
    }
}
