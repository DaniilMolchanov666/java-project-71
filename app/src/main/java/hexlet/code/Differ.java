package hexlet.code;

import java.io.File;
import java.io.IOException;

public class Differ {

    public static String generate(File file1, File file2, String format) throws IOException {
        return Formatter.chooseFormat(format, Parser.getFileAsMap(file1), Parser.getFileAsMap(file2));
    }
}
