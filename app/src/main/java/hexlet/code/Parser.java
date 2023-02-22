package hexlet.code;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;

public class Parser {

    private static StringBuilder generateString = new StringBuilder();

    private static Scanner scanner;

    public static String getStringFile(File file) {

        generateString.delete(0, generateString.length());

            try (FileReader a = new FileReader(file)) {
                scanner = new Scanner(file);

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
