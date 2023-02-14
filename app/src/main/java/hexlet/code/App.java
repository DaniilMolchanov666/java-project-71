/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable {

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format", defaultValue = "stylish")
    String format1;
    @CommandLine.Parameters(paramLabel = "filepath1",
            defaultValue = "./app/File1.json", description = "path to first file")
    Path path1;
    @CommandLine.Parameters(paramLabel = "filepath2",
            defaultValue = "./app/File2.json", description = "path to second file")
    Path path2;

    public static void main(String[] args) throws IOException {
        new CommandLine(new App()).execute(args);

        Path path = Paths.get("./app/File1.json");
        ObjectMapper o = new ObjectMapper();
        String s = o.writeValueAsString(Files.readString(path));
        System.out.println(s);
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
