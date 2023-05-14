package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

public class Differ {

    private static final String FILE_NOT_FOUND_MESSAGE = "File not found!";

    public static String generate(String filepath1, String filepath2, String format) throws IOException {

        File file1 = new File(filepath1);
        File file2 = new File(filepath2);

        TreeMap<String, Object> map1 = Parser.getFileAsMap(getFileContent(file1), getDataFormat(filepath1));

        TreeMap<String, Object> map2 = Parser.getFileAsMap(getFileContent(file2), getDataFormat(filepath2));

        List<Map<String, Object>> listDiff = GenerateDifference.diff(map1, map2);

        return Formatter.chooseFormat(format, listDiff);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String getFileContent(File file) throws IOException {

        StringBuilder generateString = new StringBuilder();

        generateString.delete(0, generateString.length());

        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                String string = scanner.nextLine();
                generateString.append(string).append("\n");
            }
        } catch (IOException e) {
            throw new IOException(FILE_NOT_FOUND_MESSAGE);
        }
        return generateString.toString();
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}


