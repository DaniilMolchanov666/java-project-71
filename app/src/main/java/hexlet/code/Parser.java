package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;
import java.util.TreeMap;

public class Parser {

    private static StringBuilder generateString = new StringBuilder();

    private static FileReader newFile;

    private static Scanner scanner;

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper(new JsonFactory());

    private static final ObjectMapper YML_MAPPER = new YAMLMapper(new YAMLFactory());

    public static TreeMap geJsonMap(File file) {

        try {
            String fileData  = getStringFile(file);

            if (file.getAbsolutePath().contains("json")) {
                return JSON_MAPPER.readValue(fileData, TreeMap.class);
            } else if(file.getAbsolutePath().contains("yml")) {
                return YML_MAPPER.readValue(fileData, TreeMap.class);
            }
        } catch (IOException e) {
            System.out.println("Your file has not the wrong format");
        }
        return new TreeMap<>();
    }

    public static String getStringFile(File file) throws IOException {

        generateString.delete(0, generateString.length());

            try {
                newFile = new FileReader(file);
                scanner = new Scanner(file);

                    while(scanner.hasNext()) {
                        String string = scanner.nextLine();
                        generateString.append(string).append("\n");
                    }

            } catch (InvalidPathException e) {
                System.out.println("Not correct path of file");
            } catch (IOException e) {
                System.out.println("File not found!");
            } finally {
                newFile.close();
                scanner.close();
            }
            return generateString.toString();
    }
}
