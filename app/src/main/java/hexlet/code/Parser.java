package hexlet.code;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Parser {

    private static StringBuilder generateString = new StringBuilder();

    public static String getStringFile(File file) {

        generateString.delete(0, generateString.length());

        try(FileReader a = new FileReader(file)) {
            Scanner sc = new Scanner(a);

            while (sc.hasNext()) {
                String string = sc.nextLine();
                generateString.append(string).append("\n");
            }
        } catch(IOException e) {
            System.out.println("File not found!");
        }
        return generateString.toString();
    }
}
