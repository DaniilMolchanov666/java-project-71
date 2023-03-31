package hexlet.code.formatters;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StrBuilder;
import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.translate.*;


public class Plain extends FormatGenerator{

    public static Object complexValuesCheck(Object value) { // проверка является ли значение пары массивом или мапой
        if(value.getClass() == ArrayList.class || value.getClass() == LinkedHashMap.class) {
            return "[complex value]";
        }
        if (value.getClass() == String.class) {
            return "'" + value + "'";
        }
        return value;
    }

    public static String genDiff(TreeMap<String, Object> mapFirst, TreeMap<String, Object> mapSecond) {

        setMap1(mapFirst);
        setMap2(mapSecond);

       return Stream.concat(getMap1().keySet().stream(), getMap2().keySet().stream())
                .distinct()
                .sorted()
                .map(key -> getCorrectEntry(Map.entry(key, getMap1().containsKey(key) ? getMap1().get(key): getMap2().get(key))))
                .collect(Collectors.joining(""));

    }
        public static String getCorrectEntry(Map.Entry<String, Object> entry) {
        String key = entry.getKey();
            if (isContainsInMaps(key)) {
                if (!isEqualInValues(key)) {
                    return String.format("Property '%s' was updated. From %s to %s\n", entry.getKey(),
                            complexValuesCheck(getMap1().get(key)), complexValuesCheck(getMap2().get(key)));
                }
            } if (getMap2().containsKey(key)) {
                return String.format("Property '%s' was added with value: %s\n", key,
                        complexValuesCheck(getMap2().get(key)));
            } if (getMap1().containsKey(key)) {
                return String.format("Property '%s' was removed\n", key);
            }
            return null;
    }
}
