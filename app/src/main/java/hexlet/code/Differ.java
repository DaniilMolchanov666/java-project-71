package hexlet.code;

import java.io.*;
import java.util.*;

public class Differ {

    public static TreeMap<String, Object> map1 = new TreeMap<>();

    private static TreeMap<String, Object> map2 = new TreeMap<>();

    public static String generate(File file1, File file2, String format) {

            map1 = Parser.geJsonMap(file1);
            map2 = Parser.geJsonMap(file2);

        return Formatter.chooseFormat(format, map1, map2);
    }
}
