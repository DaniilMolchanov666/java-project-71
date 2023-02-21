/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package hexlet.code;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable {

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format", defaultValue = "stylish")
    String format1;
    @CommandLine.Parameters(paramLabel = "filepath1",
            defaultValue = "./app/FirstYml.yml", description = "path to first file")
    Path path1;
    @CommandLine.Parameters(paramLabel = "filepath2",
            defaultValue = "./app/SecongYml.yml", description = "path to second file")
    Path path2;

    public static void main(String[] args) throws IOException {
        new CommandLine(new App()).execute(args);

    }
    @Override
    public Object call() throws IOException {

        File file1 = new File(path1.toAbsolutePath().normalize().toString());
        File file2 = new File(path2.toAbsolutePath().normalize().toString());
        String result = Differ.genDiff(file1, file2);
        System.out.println(result);
        return result;
    }
}
