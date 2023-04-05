package hexlet.code;

import java.io.*;
import java.util.*;

public class Differ {

    public static String generate(File file1, File file2, String format) {
        TreeMap<String, Object> map1;
        TreeMap<String, Object> map2;

        try {
            map1 = Parser.getFileAsMap(file1);
            map2 = Parser.getFileAsMap(file2);
        } catch (IOException e) {
            return e.getMessage();
        }
        return Formatter.chooseFormat(format, map1, map2);
    }
}
