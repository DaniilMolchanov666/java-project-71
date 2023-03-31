package hexlet.code.formatters;

import java.util.Map;
import java.util.TreeMap;

public interface Formatter {

    public static Map<String, Object> map1 = new TreeMap<>();

    public static Map<String, Object> map2 = new TreeMap<>();

    public String updated();

    public String unchanged();

    public String deleted();

    public String added();

}
