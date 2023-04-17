/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package hexlet.code;

import picocli.CommandLine;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable {

    @CommandLine.Option(names = {"-f", "--format"},  required = true, description = "output format [default: stylish]",
            defaultValue = "stylish")
    String format1;
    @CommandLine.Parameters(paramLabel = "filepath1",
            defaultValue = "./src/test/resources/FileForTesting3.yml", description = "path to first file")
    Path path1;
    @CommandLine.Parameters(paramLabel = "filepath2",
            defaultValue = "./src/test/resources/FileForTesting4.yml", description = "path to second file")
    Path path2;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public Object call() {

        String pathOfFile1 = path1.toAbsolutePath().normalize().toString();
        String pathOfFile2 = path2.toAbsolutePath().normalize().toString();

        String result;

        try {
            if (format1.equals("stylish")) {
                result = Differ.generate(pathOfFile1, pathOfFile2);
            } else {
                result = Differ.generate(pathOfFile1, pathOfFile2, format1);
            }
            System.out.println(result);
            return result;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
