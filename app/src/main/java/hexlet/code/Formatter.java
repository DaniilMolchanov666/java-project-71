package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Formatter {

     private Formatter format;

    private static TreeMap<String, Object> map1;

    private static TreeMap<String, Object> map2 = new TreeMap<>();

    public String generate(File file1, File file2, String format) {

        List<Map.Entry<String, Object>> list = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .distinct()
                .sorted(Map.Entry.comparingByKey())
                .toList();
        return list.toString();
    }
}
