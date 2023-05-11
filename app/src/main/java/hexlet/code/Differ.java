package hexlet.code;

import hexlet.code.formatters.GenerateDifference;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Differ {

    private static final StringBuilder GENERATE_STRING = new StringBuilder();

    private static final String FILE_NOT_FOUND_MESSAGE = "File not found!";

    private static final String FORMAT_WARNING = "Your file has the wrong format! "
            + "Pls use files which have 'yml' or 'json' formats!";

    public static String generate(String filepath1, String filepath2, String format) throws IOException {

        File file1 = new File(filepath1);
        File file2 = new File(filepath2);

        if (isSuitableFileExtension(file1) && isSuitableFileExtension(file2)) {

            TreeMap<String, Object> map1 = Parser.getFileAsMap(getFileContent(file1),
                    filepath1.substring(filepath1.indexOf(".")));

            TreeMap<String, Object> map2 = Parser.getFileAsMap(getFileContent(file2),
                    filepath2.substring(filepath1.indexOf(".")));

            List<Map<String, Object>> listDiff = GenerateDifference.diff(map1, map2);
            return Formatter.chooseFormat(format, listDiff);
        }
        throw new IOException(FORMAT_WARNING);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String getFileContent(File file) {
        GENERATE_STRING.delete(0, GENERATE_STRING.length());

        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                String string = scanner.nextLine();
                GENERATE_STRING.append(string).append("\n");
            }
        } catch (IOException e) {
            return FILE_NOT_FOUND_MESSAGE;
        }
        return GENERATE_STRING.toString();
    }

    public static boolean isSuitableFileExtension(File file) {
        String pathOfFile = file.getAbsolutePath();
        return pathOfFile.contains("yml") || pathOfFile.contains("json");
    }
}


