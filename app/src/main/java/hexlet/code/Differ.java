package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import javax.swing.text.Style;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Differ {
    private static StringBuilder generateString = new StringBuilder();

    public static TreeMap<String, Object> map1 = new TreeMap<>();

    private static TreeMap<String, Object> map2 = new TreeMap<>();


    public static String generate(File file1, File file2, String format) throws JsonProcessingException {

        generateString.delete(0, generateString.length());

        try {
            map1 = Parser.geJsonMap(file1);
            map2 = Parser.geJsonMap(file2);

        } catch (UncheckedIOException o) {
            return "Incorrect format of files!";
        }

        if (format.equals("plain")) {
            return Plain.genDiff(map1, map2);
        } else if (format.equals("stylish")) {
            return Stylish.genDiff(map1, map2);
        } else if(format.equals("json")) {
            return Json.genDiff(map1, map2);
        }

        return "Incorrect format!";

    }

}
