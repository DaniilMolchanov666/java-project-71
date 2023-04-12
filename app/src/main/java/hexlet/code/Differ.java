package hexlet.code;

import java.io.IOException;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        return Formatter.chooseFormat(format, Parser.getFileAsMap(filepath1), Parser.getFileAsMap(filepath2));
    }
}
