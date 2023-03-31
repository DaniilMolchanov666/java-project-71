package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;
import java.util.TreeMap;

public class Parser {

    private static StringBuilder generateString = new StringBuilder();

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper(new JsonFactory());

    private static final ObjectMapper YML_MAPPER = new YAMLMapper(new YAMLFactory());

    private static final String FORMAT_WARNING = "Your file has the wrong format! " +
            "Pls use files which have 'yml' or 'json' formats!";

    public static TreeMap geJsonMap(File file) {

        String fileExtension = Files.getFileExtension(file.getName());

        try {
            String fileData  = getStringFile(file);

            if (fileExtension.equals("json")) {
                return JSON_MAPPER.readValue(fileData, TreeMap.class);
            } else if(fileExtension.equals("yml")) {
                return YML_MAPPER.readValue(fileData, TreeMap.class);
            }
        } catch (IOException e) {
            System.out.println(FORMAT_WARNING);
        }
        return new TreeMap<>();
    }

    public static String getStringFile(File file) {
        generateString.delete(0, generateString.length());

            try (Scanner scanner = new Scanner(file)) {
                    while(scanner.hasNext()) {
                        String string = scanner.nextLine();
                        generateString.append(string).append("\n");
                    }
            } catch (InvalidPathException e) {
                System.out.println("Not correct path of file");
            } catch (IOException e) {
                System.out.println("File not found!");
            }
            return generateString.toString();
    }
}
