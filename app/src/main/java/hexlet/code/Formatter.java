package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.TreeMap;

public class Formatter {
   public static String chooseFormat(String format, TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
       if (format.length() == 0) {
           return "Incorrect format!";
       }
       if (format.equals("stylish")) {
           return Stylish.genDiff(map1, map2);
       } else if (format.equals("plain")) {
           return null;
       }
       return null;
   }
}
