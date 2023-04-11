package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Parser {

    private static final StringBuilder generateString = new StringBuilder();

    private static final ObjectMapper YML_MAPPER = new YAMLMapper(new YAMLFactory());

    private static final ObjectMapper JSON_MAPPER = new JsonMapper(new JsonFactory());

    private static final String FILE_NOT_FOUND_MESSAGE = "File not found!";

    private static final String FORMAT_WARNING = "Your file has the wrong format! " +
            "Pls use files which have 'yml' or 'json' formats!";

    public static TreeMap<String, Object> getFileAsMap(File file) throws IOException {

        String fileData  = getFileContent(file);

        if (fileData.equals(FILE_NOT_FOUND_MESSAGE)) {
            throw new IOException(FILE_NOT_FOUND_MESSAGE);
        }
        if (!isSuitableFileExtension(file)) {
            throw new IOException(FORMAT_WARNING);
        }
        return file.getAbsolutePath().contains("yml") ? YML_MAPPER.readValue(fileData, new TypeReference<>() {}) :
                JSON_MAPPER.readValue(fileData, new TypeReference<>() {});
    }

    public static boolean isSuitableFileExtension(File file) {
        String pathOfFile = file.getAbsolutePath();
        return pathOfFile.contains("yml") || pathOfFile.contains("json");
    }

    public static String getFileContent(File file) {
        generateString.delete(0, generateString.length());

            try (Scanner scanner = new Scanner(file)) {
                    while(scanner.hasNext()) {
                        String string = scanner.nextLine();
                        generateString.append(string).append("\n");
                    }
            } catch (IOException e) {
                return FILE_NOT_FOUND_MESSAGE;
            }
            return generateString.toString();
    }
}
